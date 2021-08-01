package com.yiranzhaojiu.springmvc.springframework.beans.factory.support;


import com.yiranzhaojiu.springmvc.springframework.beans.BeanWrapper;
import com.yiranzhaojiu.springmvc.springframework.beans.BeanWrapperImpl;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.annotation.Autowired;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.config.BeanDefinition;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    /**
     * Cache of singleton objects: bean name --> bean instance
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    /**
     * Cache of unfinished FactoryBean instances: FactoryBean name --> BeanWrapper
     */
    private final Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>(16);

    /**
     * List of bean definition names, in registration order
     */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (this.beanDefinitionMap.containsKey(beanName)) return;
        this.beanDefinitionMap.put(beanName, beanDefinition);
        beanDefinitionNames.add(beanName);
    }

    @Override
    public void preInstantiateSingletons() {
        if (beanDefinitionNames.isEmpty()) return;
        try {
            beanDefinitionNames.forEach(beanName -> {
                if (!beanDefinitionMap.containsKey(beanName) || singletonObjects.containsKey(beanName)) return;
                GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionMap.get(beanName);
                if (Objects.nonNull(beanDefinition.getBeanClass())) {
                    //IOC，先不往下封装,后期向后扩展
                    Object instance = null;
                    try {
                        instance = Class.forName(beanDefinition.getBeanClass()).newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (Objects.nonNull(instance)) {
                        BeanWrapper beanWrapper = new BeanWrapperImpl(instance);
                        factoryBeanInstanceCache.put(beanName, beanWrapper);
                        singletonObjects.put(beanName, instance);
                    }
                }
            });
            //DI，先不往下封装,后期向后扩展
            populateBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populateBean() throws Exception {
        for (Map.Entry<String, Object> entry : singletonObjects.entrySet()) {
            Object instance = entry.getValue();
            populateBean(instance);
        }
    }
    /**
     * 依赖注入
     **/
    private void populateBean(Object instance) throws Exception {
        Class<?> instanceClass = instance.getClass();
        Field[] declaredFields = instanceClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                String beanName = field.getName();
                if (singletonObjects.containsKey(beanName)) {
                    //设置属性暴力访问
                    field.setAccessible(true);
                    field.set(instance, singletonObjects.get(beanName));
                }
            }
        }
    }

    public Map<String, Object> getSingletonObjects(){
        return this.singletonObjects;
    }
}
