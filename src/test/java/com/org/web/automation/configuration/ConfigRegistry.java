package com.org.web.automation.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
@Component
public class ConfigRegistry {

    private static final Logger LOGGER = Logger.getLogger(ConfigRegistry.class.getName());
    private static final String RESOURCE_PATH = "src/main/resources";

    private static String env = null;
    private static ProjectConfig config = null;

    public synchronized static ProjectConfig getConfig(String env) {
        if (env == null) {
            LOGGER.log(Level.CONFIG, "Argument env is null, loading default environment configuration");
        }

        ConfigRegistry.env = env;

        try {
            Properties props = new Properties();
            env = env == null || env.isEmpty() ? "" : env;
            String hyphen = env.isEmpty() ? "" : "-";
            FileInputStream fis = new FileInputStream(RESOURCE_PATH + "/application" + hyphen + env + ".properties");
            props.load(fis);

            config = new ProjectConfig(env);
            PropertiesResolver.mapPropertiesToObject(props, config);
            ConfigRegistry.setEnv(config.getEnv());
        } catch (IOException e) {
        	LOGGER.log(Level.SEVERE, "Invalid environment specified: " + env);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return config;
    }

    private static void setEnv(String env) {
        ConfigRegistry.env = env;
    }

    public synchronized static ProjectConfig getConfig() {
        // if an environment is not yet set, check if the System knows about it..
        // or else use default
        if (env == null) {
            String env = System.getProperty("env");
            return getConfig(env);
        }
        return config;
    }

}
