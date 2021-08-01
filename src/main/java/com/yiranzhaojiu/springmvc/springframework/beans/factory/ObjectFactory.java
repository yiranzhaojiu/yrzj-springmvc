package com.yiranzhaojiu.springmvc.springframework.beans.factory;

/**
 * 声明式函数编程
 * */
@FunctionalInterface
public interface ObjectFactory<T> {
    T getObject();
}
