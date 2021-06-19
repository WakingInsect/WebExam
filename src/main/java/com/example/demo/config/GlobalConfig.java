package com.example.demo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GlobalConfig {

    private static GlobalConfig instance = null;

    private String host;

    private int port;

    private String resource_path;


    public static GlobalConfig getInstance() {
        if (instance == null)
            instance = new GlobalConfig();
        return instance;
    }
    
    private GlobalConfig() {
        File config = new File("./config", "config.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(config));
            host = prop.getProperty("host", "127.0.0.1");
            port = Integer.valueOf(prop.getProperty("port", "8080"));
            resource_path = prop.getProperty("resource", "./resource");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getResource() {
        return resource_path;
    }
}
