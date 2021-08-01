package com.yiranzhaojiu.springmvc.springframework.beans.factory.support;

public class GenericBeanDefinition extends AbstractBeanDefinition {

    private  String beanName;

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    private volatile String beanClass;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


}
