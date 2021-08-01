package com.yiranzhaojiu.springmvc.springframework.web.context.support;


import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlWebApplicationContext extends AbstractRefreshableWebApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        String[] configLocations = getConfigLocations();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        for (String location:configLocations){
            beanDefinitionReader.loadBeanDefinitions(location);
        }
    }

}
