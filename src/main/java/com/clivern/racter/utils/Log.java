/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
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

	private static final Logger log = Logger.getLogger(Log.class.getName());
	private static Log instance;
	private static Handler consoleHandler;
	private static Handler fileHandler;

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
	 * @param  console_handler_status
	 * @param  console_handler_level
	 * @param  file_handler_status
	 * @param  file_handler_level
	 * @param  file_path
	 * @return Log
	 * @throws IOException
	 */
	public static Log config(Boolean console_handler_status, Level console_handler_level, Boolean file_handler_status, Level file_handler_level, String file_path) throws IOException
	{
		if( console_handler_status ){
			consoleHandler = new ConsoleHandler();
			log.addHandler(consoleHandler);
			consoleHandler.setLevel(console_handler_level);
		}

		if( file_handler_status ){
			fileHandler  = new FileHandler(file_path, 50000000, 1, true);
			log.addHandler(fileHandler);
			fileHandler.setLevel(file_handler_level);
		}

		return instance;
	}




	/**
	 * Get Logger
	 *
	 * @return Logger
	 */
	public static Logger getLogger()
	{
		return log;
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