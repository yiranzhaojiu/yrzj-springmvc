package com.yiranzhaojiu.springmvc.springframework.context.support;


import com.yiranzhaojiu.springmvc.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    /** Synchronization monitor for the internal BeanFactory */
    private final Object beanFactoryMonitor = new Object();

    private Lock lock=new ReentrantLock();

    @Override
    protected void refreshBeanFactory() {
        //创建Bean工厂对象
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        loadBeanDefinitions(beanFactory);
        try{
            lock.lock();
            this.beanFactory=beanFactory;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return  this.beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
