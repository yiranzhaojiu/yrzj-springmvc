package com.yiranzhaojiu.springmvc.springframework.beans.factory.support;

public interface BeanDefinitionReader {
    int loadBeanDefinitions(String location);

    BeanDefinitionRegistry getRegistry();
}
