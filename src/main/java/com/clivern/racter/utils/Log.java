/*
 * Copyright (C) 2017 A.F <http://clivern.com>
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

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger Utils Class
 */
public class Log {

    protected Logger log = Logger.getLogger(Log.class.getName());

    protected Handler consoleHandler;

    protected Handler fileHandler;

    protected Config config;

    /**
     * Constructor
     */
    public Log(Config config) throws IOException
    {
        this.config = config;
        this.config();
    }

    /**
     * Log Fine Messages
     *
     * @param message
     */
    public void fine(String message)
    {
        log.fine(message);
    }

    /**
     * Log Fine Messages
     *
     * @param message
     */
    public void finer(String message)
    {
        log.finer(message);
    }

    /**
     * Log Finest Messages
     *
     * @param message
     */
    public void finest(String message)
    {
        log.finest(message);
    }

    /**
     * Log Info Messages
     *
     * @param message
     */
    public void info(String message)
    {
        log.info(message);
    }

    /**
     * Log Severe Messages
     *
     * @param message
     */
    public void severe(String message)
    {
        log.severe(message);
    }

    /**
     * Log Warning Messages
     *
     * @param message
     */
    public void warning(String message)
    {
        log.warning(message);
    }

    /**
     * Close File Handler
     */
    public void close()
    {
        if( fileHandler != null ){
            fileHandler.close();
        }
    }

    /**
     * Configure Logger
     *
     * @throws IOException
     */
    protected void config() throws IOException
    {
        if( Boolean.parseBoolean(this.config.get("log_console_status", "false")) ){
            consoleHandler = new ConsoleHandler();
            log.addHandler(consoleHandler);
            if( this.config.get("log_console_level", "SEVERE").equals("ALL") ){
                consoleHandler.setLevel(Level.ALL);
            }else if( this.config.get("log_console_level", "SEVERE").equals("CONFIG") ){
                consoleHandler.setLevel(Level.CONFIG);
            }else if( this.config.get("log_console_level", "SEVERE").equals("FINE") ){
                consoleHandler.setLevel(Level.FINE);
            }else if( this.config.get("log_console_level", "SEVERE").equals("FINER") ){
                consoleHandler.setLevel(Level.FINER);
            }else if( this.config.get("log_console_level", "SEVERE").equals("FINEST") ){
                consoleHandler.setLevel(Level.FINEST);
            }else if( this.config.get("log_console_level", "SEVERE").equals("INFO") ){
                consoleHandler.setLevel(Level.INFO);
            }else if( this.config.get("log_console_level", "SEVERE").equals("OFF") ){
                consoleHandler.setLevel(Level.OFF);
            }else if( this.config.get("log_console_level", "SEVERE").equals("SEVERE") ){
                consoleHandler.setLevel(Level.SEVERE);
            }else if( this.config.get("log_console_level", "SEVERE").equals("WARNING") ){
                consoleHandler.setLevel(Level.WARNING);
            }
        }

        if( Boolean.parseBoolean(this.config.get("log_file_status", "false")) ){
            fileHandler  = new FileHandler(this.config.get("log_file_path", "src/main/java/resources/app.log"),
                Integer.parseInt(this.config.get("log_file_limit", "1")),
                Integer.parseInt(this.config.get("log_file_count", "200000")),
                Boolean.parseBoolean(this.config.get("log_file_append", "true"))
            );
            log.addHandler(fileHandler);
            if( this.config.get("log_file_level", "SEVERE").equals("ALL") ){
                fileHandler.setLevel(Level.ALL);
            }else if( this.config.get("log_file_level", "SEVERE").equals("CONFIG") ){
                fileHandler.setLevel(Level.CONFIG);
            }else if( this.config.get("log_file_level", "SEVERE").equals("FINE") ){
                fileHandler.setLevel(Level.FINE);
            }else if( this.config.get("log_file_level", "SEVERE").equals("FINER") ){
                fileHandler.setLevel(Level.FINER);
            }else if( this.config.get("log_file_level", "SEVERE").equals("FINEST") ){
                fileHandler.setLevel(Level.FINEST);
            }else if( this.config.get("log_file_level", "SEVERE").equals("INFO") ){
                fileHandler.setLevel(Level.INFO);
            }else if( this.config.get("log_file_level", "SEVERE").equals("OFF") ){
                fileHandler.setLevel(Level.OFF);
            }else if( this.config.get("log_file_level", "SEVERE").equals("SEVERE") ){
                fileHandler.setLevel(Level.SEVERE);
            }else if( this.config.get("log_file_level", "SEVERE").equals("WARNING") ){
                fileHandler.setLevel(Level.WARNING);
            }
        }
    }
}