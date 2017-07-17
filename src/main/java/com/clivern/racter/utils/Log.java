/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.utils;

import com.clivern.racter.configs.Settings;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.lang.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger Utils Class
 */
public class Log {

    private static final Logger log = Logger.getLogger(Log.class.getName());
    private static Log instance;
    private static Handler consoleHandler;
    private static Handler fileHandler;
    private Settings settings;

    /**
     * Constructor
     */
    protected Log() { }

    /**
     * Get Instance
     *
     * @return Log
     */
    public static Log getInstance() {
        if(instance == null) {
            instance = new Log();
        }
        return instance;
    }

    /**
     * Config Logger
     *
     * @param  settings
     * @return Log
     */
    public Log config(Settings settings) throws IOException
    {
        this.settings = settings;

        Map<String, String> options = this.settings.getAllSettings();

        if( Boolean.parseBoolean(options.get("log_console_status")) ){
            consoleHandler = new ConsoleHandler();
            log.addHandler(consoleHandler);
            if( options.get("log_console_level").equals("ALL") ){
                consoleHandler.setLevel(Level.ALL);
            }else if( options.get("log_console_level").equals("CONFIG") ){
                consoleHandler.setLevel(Level.CONFIG);
            }else if( options.get("log_console_level").equals("FINE") ){
                consoleHandler.setLevel(Level.FINE);
            }else if( options.get("log_console_level").equals("FINER") ){
                consoleHandler.setLevel(Level.FINER);
            }else if( options.get("log_console_level").equals("FINEST") ){
                consoleHandler.setLevel(Level.FINEST);
            }else if( options.get("log_console_level").equals("INFO") ){
                consoleHandler.setLevel(Level.INFO);
            }else if( options.get("log_console_level").equals("OFF") ){
                consoleHandler.setLevel(Level.OFF);
            }else if( options.get("log_console_level").equals("SEVERE") ){
                consoleHandler.setLevel(Level.SEVERE);
            }else if( options.get("log_console_level").equals("WARNING") ){
                consoleHandler.setLevel(Level.WARNING);
            }
        }

        if( Boolean.parseBoolean(options.get("log_file_status")) ){
            fileHandler  = new FileHandler(options.get("log_file_path"), Integer.parseInt(options.get("log_file_limit")), Integer.parseInt(options.get("log_file_count")), Boolean.parseBoolean(options.get("log_file_append")));
            log.addHandler(fileHandler);
            if( options.get("log_file_level").equals("ALL") ){
                fileHandler.setLevel(Level.ALL);
            }else if( options.get("log_file_level").equals("CONFIG") ){
                fileHandler.setLevel(Level.CONFIG);
            }else if( options.get("log_file_level").equals("FINE") ){
                fileHandler.setLevel(Level.FINE);
            }else if( options.get("log_file_level").equals("FINER") ){
                fileHandler.setLevel(Level.FINER);
            }else if( options.get("log_file_level").equals("FINEST") ){
                fileHandler.setLevel(Level.FINEST);
            }else if( options.get("log_file_level").equals("INFO") ){
                fileHandler.setLevel(Level.INFO);
            }else if( options.get("log_file_level").equals("OFF") ){
                fileHandler.setLevel(Level.OFF);
            }else if( options.get("log_file_level").equals("SEVERE") ){
                fileHandler.setLevel(Level.SEVERE);
            }else if( options.get("log_file_level").equals("WARNING") ){
                fileHandler.setLevel(Level.WARNING);
            }
        }

        return instance;
    }

    /**
     * Log Fine Messages
     *
     * @param msg
     */
    public static void fine(String msg)
    {
        log.fine(msg);
    }

    /**
     * Log Fine Messages
     *
     * @param msg
     */
    public static void finer(String msg)
    {
        log.finer(msg);
    }

    /**
     * Log Finest Messages
     *
     * @param msg
     */
    public static void finest(String msg)
    {
        log.finest(msg);
    }

    /**
     * Log Info Messages
     *
     * @param msg
     */
    public static void info(String msg)
    {
        log.info(msg);
    }

    /**
     * Log Severe Messages
     *
     * @param msg
     */
    public static void severe(String msg)
    {
        log.severe(msg);
    }

    /**
     * Log Warning Messages
     *
     * @param msg
     */
    public static void warning(String msg)
    {
        log.warning(msg);
    }

    /**
     * Close File Handler
     */
    public static void close()
    {
        if( fileHandler != null ){
            fileHandler.close();
        }
    }

}