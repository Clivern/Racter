/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.utils;

import java.util.HashMap;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Config Class
 */
public class Config {

    /**
     * @var Map<String, String>
     */
    protected Map<String, String> configs = new HashMap<String, String>();

    /**
     * @var Config
     */
    private static Config instance;

    /**
     * Constructor
     */
    protected Config() { }

    /**
     * Get Instance
     *
     * @return Config
     */
    public static Config instance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Load Properties File
     *
     * @param path
     * @return Boolean
     * @throws IOException
     */
    public Boolean loadPropertiesFile(String path) throws IOException
    {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(path);
            prop.load(input);

            for(String key : prop.stringPropertyNames()) {
                String value = prop.getProperty(key);
                configs.put(key, value);
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Store Configs in Properties File
     *
     * @param path
     * @return Boolean
     * @throws IOException
     */
    public Boolean storePropertiesFile(String path) throws IOException
    {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(path);
            for (String key : configs.keySet()){
                prop.setProperty(key, configs.get(key));
            }
            prop.store(output, null);

            return true;

        } catch (IOException io) {
            return false;
        }
    }

    /**
     * Set Config
     *
     * @param key
     * @param value
     * @retun void
     */
    public void set(String key, String value)
    {
        configs.put(key, value);
    }

    /**
     * Get Config
     *
     * @param key
     * @param defaultValue
     * @return String
     */
    public String get(String key, String defaultValue)
    {
        if( configs.containsKey(key) ){
            return configs.get(key);
        }

        return defaultValue;
    }

    /**
     * Get All Configs
     *
     * @return Map<String, String>
     */
    public Map<String, String> getAll()
    {
        return configs;
    }
}