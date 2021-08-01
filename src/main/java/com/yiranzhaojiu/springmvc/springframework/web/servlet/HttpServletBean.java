package com.yiranzhaojiu.springmvc.springframework.web.servlet;

import com.yiranzhaojiu.springmvc.springframework.beans.MutablePropertyValues;
import com.yiranzhaojiu.springmvc.springframework.beans.PropertyValue;
import com.yiranzhaojiu.springmvc.springframework.beans.PropertyValues;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Enumeration;
import java.util.Objects;

@Slf4j
public class HttpServletBean extends HttpServlet {

    @Override
    public final void init() throws ServletException {
        //保存web.xml的配置属性信息
        PropertyValues pvs = new ServletConfigPropertyValues(getServletConfig());
        if (!pvs.isEmpty()) {
            //简化源码实现
            MapProperty(pvs);
        }
        initServletBean();
    }

    protected void initServletBean() throws ServletException {
    }

    protected void MapProperty(PropertyValues pvs){
    }


    private class ServletConfigPropertyValues extends MutablePropertyValues {
        public ServletConfigPropertyValues(ServletConfig config) throws ServletException {
            if (Objects.isNull(config)) {
                throw new ServletException("ServletConfig is null");
            }
            Enumeration<String> parameterNames = config.getInitParameterNames();
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = config.getInitParameter(name);
                addPropertyValue(new PropertyValue(name, value));
            }
        }
    }
}
