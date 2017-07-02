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
 *
 * <code>
 * 	import com.clivern.racter.configs.Settings;
 *
 *  Settings settingsObj = new Settings();
 *  settingsObj.loadPropertiesFile("src/main/java/resources/config.properties");
 *  settingsObj.setPageAccessToken("256633ffgs");
 *  settingsObj.storePropertiesFile("src/main/java/resources/config.properties");
 *  settingsObj.getAppId("app_id");
 * </code>
 *
 * @since 1.0.0
 */
public class Settings {

	private Map<String, String> settings = new HashMap<String, String>();

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
			settings.put("log_level", prop.getProperty("log_level"));

			return true;
		} catch (IOException ex) {
			// Log Error

			return false;
		}
	}

	/**
	 * Set App Id
	 *
	 * @param app_id
	 */
	public void setAppId(String app_id)
	{
		settings.put("app_id", app_id);
	}

	/**
	 * Set Verify Token
	 *
	 * @param verify_token
	 */
	public void setVerifyToken(String verify_token)
	{
		settings.put("verify_token", verify_token);
	}

	/**
	 * Set Page Access Token
	 *
	 * @param page_access_token
	 */
	public void setPageAccessToken(String page_access_token)
	{
		settings.put("page_access_token", page_access_token);
	}

	/**
	 * Set Log Level
	 *
	 * @param log_level
	 */
	public void setLogLevel(String log_level)
	{
		settings.put("log_level", log_level);
	}

	/**
	 * Get App Id
	 *
	 * @param app_id
	 * @return String
	 */
	public String getAppId(String app_id)
	{
		if( settings.containsKey("app_id") ){
			return settings.get("app_id");
		}

		return "";
	}

	/**
	 * Get Verify Token
	 *
	 * @param verify_token
	 * @return String
	 */
	public String getVerifyToken(String verify_token)
	{
		if( settings.containsKey("verify_token") ){
			return settings.get("verify_token");
		}

		return "";
	}

	/**
	 * Get Page Access Token
	 *
	 * @param page_access_token
	 * @return String
	 */
	public String getPageAccessToken(String page_access_token)
	{
		if( settings.containsKey("page_access_token") ){
			return settings.get("page_access_token");
		}

		return "";
	}

	/**
	 * Get Log Level
	 *
	 * @param log_level
	 * @return String
	 */
	public String getLogLevel(String log_level)
	{
		if( settings.containsKey("log_level") ){
			return settings.get("log_level");
		}

		return "";
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
            prop.setProperty("page_access_token", settings.get("page_access_token"));
            prop.setProperty("verify_token", settings.get("verify_token"));
            prop.setProperty("log_level", settings.get("log_level"));
            prop.store(output, null);

            return true;

        } catch (IOException io) {
        	// Log Error

            return false;
        }
	}
}