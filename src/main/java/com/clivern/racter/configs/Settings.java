/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.configs;

import java.util.HashMap;
import java.util.Map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Chat Bot Settings
 */
public class Settings {

	private Map<String, String> settings = new HashMap<String, String>();

	private static Settings instance;

	/**
	 * Constructor
	 */
	protected Settings() { }

	/**
	 * Get Instance
	 *
	 * @return Settings
	 */
	public static Settings getInstance() {
	    if(instance == null) {
	        instance = new Settings();
	    }
	    return instance;
	}

	/**
	 * Load Properties File
	 *
	 * @param path
	 * @throws IOException
	 */
	public Boolean loadPropertiesFile(String path) throws IOException
	{
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(path);
			prop.load(input);

			settings.put("app_id", prop.getProperty("app_id"));
			settings.put("verify_token", prop.getProperty("verify_token"));
			settings.put("page_access_token", prop.getProperty("page_access_token"));
			settings.put("log_console_status", prop.getProperty("log_console_status"));
			settings.put("log_console_level", prop.getProperty("log_console_level"));
			settings.put("log_file_status", prop.getProperty("log_file_status"));
			settings.put("log_file_level", prop.getProperty("log_file_level"));
			settings.put("log_file_path", prop.getProperty("log_file_path"));
			settings.put("log_file_limit", prop.getProperty("log_file_limit"));
			settings.put("log_file_count", prop.getProperty("log_file_count"));
			settings.put("log_file_append", prop.getProperty("log_file_append"));

			return true;
		} catch (IOException ex) {
			// Log Error

			return false;
		}
	}

	/**
	 * Set an Option
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value)
	{
		settings.put(key, value);
	}

	/**
	 * Get an Option
	 *
	 * @param key
	 * @param def_value
	 * @return String
	 */
	public String get(String key, String def_value)
	{
		if( settings.containsKey(key) ){
			return settings.get(key);
		}

		return def_value;
	}

	/**
	 * Store Settings in Properties File
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
			prop.setProperty("app_id", settings.get("app_id"));
			prop.setProperty("verify_token", settings.get("verify_token"));
			prop.setProperty("page_access_token", settings.get("page_access_token"));
			prop.setProperty("log_console_status", settings.get("log_console_status"));
			prop.setProperty("log_console_level", settings.get("log_console_level"));
			prop.setProperty("log_file_status", settings.get("log_file_status"));
			prop.setProperty("log_file_level", settings.get("log_file_level"));
			prop.setProperty("log_file_path", settings.get("log_file_path"));
			prop.setProperty("log_file_limit", settings.get("log_file_limit"));
			prop.setProperty("log_file_count", settings.get("log_file_count"));
			prop.setProperty("log_file_append", settings.get("log_file_append"));
            prop.store(output, null);

            return true;

        } catch (IOException io) {
        	// Log Error

            return false;
        }
	}

	public Map<String, String> getAllSettings()
	{
		return settings;
	}
}