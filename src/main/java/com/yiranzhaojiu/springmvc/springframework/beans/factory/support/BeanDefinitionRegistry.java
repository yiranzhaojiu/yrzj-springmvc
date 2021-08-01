package com.yiranzhaojiu.springmvc.springframework.beans.factory.support;

import com.yiranzhaojiu.springmvc.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName,BeanDefinition beanDefinition);
}
