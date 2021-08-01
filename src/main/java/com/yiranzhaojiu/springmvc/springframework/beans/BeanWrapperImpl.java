package com.yiranzhaojiu.springmvc.springframework.beans;

public class BeanWrapperImpl implements BeanWrapper {
    String beanName;
    Object wrappedObject;

    public BeanWrapperImpl(Object wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    public BeanWrapperImpl(String beanName, Object wrappedObject) {
        this.beanName = beanName;
        this.wrappedObject = wrappedObject;
    }
}
