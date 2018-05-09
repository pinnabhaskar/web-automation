package com.org.web.automation.configuration;

public abstract class Configuration {

    private String env;

    protected Configuration(String env) {
        setEnv(env);
    }

    protected String getEnv() {
        return env;
    }

    protected void setEnv(String env) {
        this.env = env;
    }

}
