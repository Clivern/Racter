/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

import com.clivern.racter.configs.Settings;
import com.clivern.racter.utils.Log;

import com.clivern.racter.integrations.Play;
import com.clivern.racter.integrations.Servlet;
import com.clivern.racter.integrations.Spark;
import com.clivern.racter.integrations.Spring;

import com.clivern.racter.receivers.TextMessage;
import com.clivern.racter.receivers.VerifyWebhook;

/**
 * Bot Platform Base Class
 */
public class BotPlatform {

	private Settings settings;
	private Log log;
	private Play play;
	private Servlet servlet;
	private Spark spark;
	private Spring spring;
	private TextMessage text_message;
	private VerifyWebhook verify_webhook;
	private static BotPlatform instance;

	/**
	 * Constructor
	 */
	protected BotPlatform() { }

	/**
	 * Get Instance
	 *
	 * @return BotPlatform
	 */
	public static BotPlatform getInstance() {
	    if(instance == null) {
	        instance = new BotPlatform();
	    }
	    return instance;
	}

	/**
	 * Config Class
	 *
	 * @return BotPlatform
	 */
	public BotPlatform loadConfigs(String poperties_file_path) throws IOException
	{
		this.settings.loadPropertiesFile(poperties_file_path);
		return instance;
	}

	/**
	 * Config Class
	 *
	 * @return BotPlatform
	 */
	public BotPlatform loadConfigs(Map<String, String> options)
	{
		for (Map.Entry<String, String> entry : options.entrySet()) {
			this.settings.set(entry.getKey(), options.get(entry.getKey()));
		}
		return instance;
	}

	/**
	 * Config Dependencies
	 *
	 * @return BotPlatform
	 */
	public BotPlatform configDependencies() throws IOException
	{
		this.settings = Settings.getInstance();
		this.log = Log.getInstance().config(this.settings);
		this.play = Play.getInstance();
		this.servlet = Servlet.getInstance();
		this.spark = Spark.getInstance();
		this.spring = Spring.getInstance();
		this.text_message = TextMessage.getInstance();
		this.verify_webhook = VerifyWebhook.getInstance().config(this.settings, this.log);

		return instance;
	}

	public VerifyWebhook getVerifyWebhook()
	{
		return this.verify_webhook;
	}

    public boolean someLibraryMethod() {
        return true;
    }

    public void finish()
    {
    	this.log.close();
    }
}
