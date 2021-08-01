package com.yiranzhaojiu.springmvc.springframework.web.handler;

import com.yiranzhaojiu.springmvc.springframework.web.servlet.HandlerMapping;

import java.lang.reflect.Method;

public class AbstractHandlerMapping implements HandlerMapping {

    private String url;
    private Object instance;
    private Method method;
    private String requestMethod;

    public AbstractHandlerMapping(String url, Object instance, Method method) {
        this.url = url;
        this.instance = instance;
        this.method = method;
    }

    public AbstractHandlerMapping(String url, Object instance, Method method, String requestMethod) {
        this.url = url;
        this.instance = instance;
        this.method = method;
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
