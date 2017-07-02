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
 *
 * <code>
 * import com.clivern.racter.utils.Log;
 * import java.util.logging.Level;
 *
 * Log log_handle = Log.getInstance();
 * log_handle.config(true, Level.ALL, true, Level.ALL, "src/main/java/resources/app.log");
 * log_handle.getLogger().warning("warning MSG");
 * log_handle.getLogger().severe("severe MSG");
 * log_handle.close();
 * </code>
 * @since 1.0.0
 */
public class Log {

	private static final Logger LOGGER = Logger.getLogger("com.clivern.racter");
	private static Log instance = null;
	private static Handler consoleHandler = null;
	private static Handler fileHandler = null;

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
			LOGGER.addHandler(consoleHandler);
			consoleHandler.setLevel(console_handler_level);
		}

		if( file_handler_status ){
			fileHandler  = new FileHandler(file_path, 50000000, 1, true);
			LOGGER.addHandler(fileHandler);
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
		return LOGGER;
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