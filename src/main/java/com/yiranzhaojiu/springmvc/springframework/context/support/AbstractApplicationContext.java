package com.yiranzhaojiu.springmvc.springframework.context.support;

import com.yiranzhaojiu.springmvc.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.yiranzhaojiu.springmvc.springframework.context.ConfigurableApplicationContext;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        //解析Bean标签
        ConfigurableListableBeanFactory beanFactory =  obtainFreshBeanFactory();

        // Instantiate all remaining (non-lazy-init) singletons.
        finishBeanFactoryInitialization(beanFactory);
    }

    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory){
        beanFactory.preInstantiateSingletons();
    }

    protected ConfigurableListableBeanFactory obtainFreshBeanFactory(){
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory=getBeanFactory();
        return beanFactory;
    }

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 父类提供标准-子类实现
     * */
    protected abstract void refreshBeanFactory();

}
