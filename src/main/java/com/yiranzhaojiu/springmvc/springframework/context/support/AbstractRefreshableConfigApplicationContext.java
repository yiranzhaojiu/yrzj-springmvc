package com.yiranzhaojiu.springmvc.springframework.context.support;


import com.yiranzhaojiu.springmvc.springframework.web.context.ConfigurableWebApplicationContext;

public abstract class AbstractRefreshableConfigApplicationContext extends AbstractRefreshableApplicationContext
        implements ConfigurableWebApplicationContext {

    private String[] configLocations;

    @Override
    public void setConfigLocations(String... locations) {
        this.configLocations = new String[locations.length];
        for (int i = 0; i < locations.length; i++) {
            this.configLocations[i] = locations[i].trim();
        }
    }

    protected String[] getConfigLocations() {
        return (this.configLocations != null ? this.configLocations : null);
    }

}
