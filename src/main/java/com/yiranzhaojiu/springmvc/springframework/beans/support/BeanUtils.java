package com.yiranzhaojiu.springmvc.springframework.beans.support;

public class BeanUtils {

    public static <T> T instantiateClass(Class<T> clazz) throws Exception {
        if (clazz.isInterface()) {
            throw new Exception(clazz.getName() + " Specified class is an interface");
        }
        return clazz.newInstance();
    }
}
