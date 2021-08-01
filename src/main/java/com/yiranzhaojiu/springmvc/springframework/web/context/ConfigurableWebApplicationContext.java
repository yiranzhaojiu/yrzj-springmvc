package com.yiranzhaojiu.springmvc.springframework.web.context;

import com.yiranzhaojiu.springmvc.springframework.context.ConfigurableApplicationContext;

public interface ConfigurableWebApplicationContext extends WebApplicationContext,ConfigurableApplicationContext {

    void setConfigLocations(String... locations);

}
