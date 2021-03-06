package com.yiranzhaojiu.springmvc.springframework.web.servlet;

import com.yiranzhaojiu.springmvc.springframework.beans.PropertyValue;
import com.yiranzhaojiu.springmvc.springframework.beans.PropertyValues;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yiranzhaojiu.springmvc.springframework.beans.support.BeanUtils;
import com.yiranzhaojiu.springmvc.springframework.build.annotation.RequestMapping;
import com.yiranzhaojiu.springmvc.springframework.build.annotation.RestController;
import com.yiranzhaojiu.springmvc.springframework.context.ApplicationContext;
import com.yiranzhaojiu.springmvc.springframework.web.context.ConfigurableWebApplicationContext;
import com.yiranzhaojiu.springmvc.springframework.web.context.WebApplicationContext;
import com.yiranzhaojiu.springmvc.springframework.web.context.support.XmlWebApplicationContext;
import com.yiranzhaojiu.springmvc.springframework.web.handler.AbstractHandlerMapping;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class FrameworkServlet extends HttpServletBean {

    private WebApplicationContext webApplicationContext;

    private String contextConfigLocation;

    public static final Class<?> DEFAULT_CONTEXT_CLASS = XmlWebApplicationContext.class;

    public void setContextConfigLocation(String contextConfigLocation) {
        this.contextConfigLocation = contextConfigLocation;
    }

    public String getContextConfigLocation() {
        return this.contextConfigLocation;
    }

    @Override
    protected void initServletBean() throws ServletException {
        this.webApplicationContext = initWebApplicationContext();
    }

    @Override
    protected void MapProperty(PropertyValues pvs) {
        if (!pvs.isEmpty()) {
            for (PropertyValue pv : pvs.getPropertyValues()) {
                //????????????????????????????????????????????????????????????contextConfigLocation????????????
                setContextConfigLocation(pv.getValue());
            }
        }
    }

    /**
     * ?????????ApplicationContext ???????????????
     */
    private WebApplicationContext initWebApplicationContext() {
        WebApplicationContext wac = null;
        if (!Objects.isNull(webApplicationContext)) {
            wac = this.webApplicationContext;
        }
        if (Objects.isNull(wac)) {
            wac = createWebApplicationContext();
        }
        return wac;
    }

    /**
     * ??????ApplicationContext ???????????????
     */
    private WebApplicationContext createWebApplicationContext() {
        try {
            ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(DEFAULT_CONTEXT_CLASS);

            String contextConfigLocation = getContextConfigLocation();
            if (StringUtils.isNotBlank(contextConfigLocation)) {
                wac.setConfigLocations(contextConfigLocation);
            }

            configureAndRefreshWebApplicationContext(wac);
            //???????????????????????????HandlerMapping --spring ??????????????????????????????,????????????
            onRefresh(wac);
            return wac;
        } catch (Exception ex) {
            return null;
        }
    }

    protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac) {
        wac.refresh();
    }

    protected void onRefresh(ApplicationContext context) {
        // For subclasses: do nothing by default.
    }

}
