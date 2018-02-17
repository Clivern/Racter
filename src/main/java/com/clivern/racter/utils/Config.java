/*
 * Copyright (C) 2017 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.racter.utils;

import java.util.HashMap;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.pmw.tinylog.*;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.*;

/**
 * Config Utils Class
 *
 * @since 1.0.0
 */
public class Config {

    protected Map<String, String> configs = new HashMap<String, String>();

    /**
     * Load Properties File
     *
     * @param path relative path to the config file
     * @return Boolean whether config file loaded or not
     * @throws IOException May throw IOException if it cannot open configs file
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
     * @param path relative path to the config file
     * @return Boolean whether config file stored or not
     * @throws IOException May throw IOException if it cannot open configs file
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
     * @param key the config item key
     * @param value the config item value
     */
    public void set(String key, String value)
    {
        configs.put(key, value);
    }

    /**
     * Get Config
     *
     * @param key the config item key
     * @param defaultValue the config item default value
     * @return String the config item value
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
     * @return Map list of all configs
     */
    public Map<String, String> getAll()
    {
        return configs;
    }

    /**
     * Configure Logger
     */
    public void configLogger()
    {
        Map<String, Level> logLevels = new HashMap<String, Level>();
        logLevels.put("trace", Level.TRACE);
        logLevels.put("debug", Level.DEBUG);
        logLevels.put("info", Level.INFO);
        logLevels.put("warning", Level.WARNING);
        logLevels.put("error", Level.ERROR);

        if( this.get("logging_log_type", "file").equals("file") ){

            DateFormat dateFormat = new SimpleDateFormat(this.get("logging_current_date_format","yyyy-MM-dd"));
            Date date = new Date();
            String logFileName = (this.get("logging_file_format", "current_date").equals("current_date")) ? dateFormat.format(date) + ".log" : this.get("logging_file_format", "current_date") + ".log";
            Configurator.defaultConfig()
                .writer(new FileWriter(this.get("logging_file_path", "src/main/java/resources/") + logFileName, (this.get("logging_buffered", "false").equals("true")) ? true : false, (this.get("logging_append", "true").equals("true")) ? true : false))
                .level((logLevels.containsKey(this.get("logging_level","debug"))) ? logLevels.get(this.get("logging_level","debug")) : Level.INFO)
                .activate();

        }else if( this.get("logging_log_type", "file").equals("both") ){

            DateFormat dateFormat = new SimpleDateFormat(this.get("logging_current_date_format","yyyy-MM-dd"));
            Date date = new Date();
            String logFileName = (this.get("logging_file_format", "current_date").equals("current_date")) ? dateFormat.format(date) + ".log" : this.get("logging_file_format", "current_date") + ".log";
            Configurator.defaultConfig()
                .writer(new ConsoleWriter())
                .addWriter(new FileWriter(this.get("logging_file_path", "src/main/java/resources/") + logFileName))
                .level((logLevels.containsKey(this.get("logging_level","debug"))) ? logLevels.get(this.get("logging_level","debug")) : Level.INFO)
                .activate();

        }else{

            Configurator.defaultConfig()
                .writer(new ConsoleWriter())
                .level((logLevels.containsKey(this.get("logging_level","debug"))) ? logLevels.get(this.get("logging_level","debug")) : Level.INFO)
                .activate();

        }
    }
}