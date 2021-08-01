package com.yiranzhaojiu.springmvc.springframework.web.servlet;


import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yiranzhaojiu.springmvc.springframework.build.annotation.RequestMapping;
import com.yiranzhaojiu.springmvc.springframework.build.annotation.RestController;
import com.yiranzhaojiu.springmvc.springframework.context.ApplicationContext;
import com.yiranzhaojiu.springmvc.springframework.web.context.support.XmlWebApplicationContext;
import com.yiranzhaojiu.springmvc.springframework.web.handler.AbstractHandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends FrameworkServlet {

    //先假简单粗暴处理一下~以后优化处理
    private Map<String, HandlerMapping> handlerMappings = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req, resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse res) {
        try {
            String url = req.getRequestURI();
            if (!handlerMappings.containsKey(url)) {
                res.getWriter().write("404 Not Found!!!");
                return;
            }
            AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping) handlerMappings.get(url);
            if (!handlerMapping.getRequestMethod().equalsIgnoreCase(req.getMethod())) {
                res.getWriter().write("Request Not Allowed!!!");
                return;
            }
            String invoke = handlerMapping.getMethod().invoke(handlerMapping.getInstance(), null).toString();
            res.getWriter().write(invoke);
        } catch (Exception ex) {
            try {
                res.getWriter().write("500 error~");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    @Override
    protected void onRefresh(ApplicationContext context) {

        //处理HandlerMapping
        initHandlerMappings(context);
    }

    private void initHandlerMappings(ApplicationContext context) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ((XmlWebApplicationContext) context).getBeanFactory();
        Map<String, Object> singletonObjects = beanFactory.getSingletonObjects();
        if (singletonObjects.isEmpty()) return;
        for (Map.Entry<String, Object> entry : singletonObjects.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(RestController.class)) continue;
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(RequestMapping.class)) continue;
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                String url = annotation.value();
                if (handlerMappings.containsKey(url)) continue;
                String requestMethod = annotation.method();
                HandlerMapping handlerMapping = new AbstractHandlerMapping(url, entry.getValue(), method, requestMethod);
                handlerMappings.put(url, handlerMapping);
            }
        }
    }
}
