/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import com.clivern.racter.configs.Settings;
import com.clivern.racter.utils.Log;
import com.clivern.racter.receivers.BaseReceiver;
import com.clivern.racter.receivers.VerifyWebhook;
import com.clivern.racter.senders.BaseSender;

/**
 * Bot Platform Base Class
 */
public class BotPlatform {

	private Settings settings;

	private VerifyWebhook verify_webhook;

	private BaseReceiver base_receiver;

	private BaseSender base_sender;

	private Log log;

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
		this.settings = Settings.getInstance();
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
		this.settings = Settings.getInstance();
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
		this.log = Log.getInstance().config(this.settings);
		this.base_receiver = BaseReceiver.getInstance().config(this.settings, this.log);
		this.base_sender = BaseSender.getInstance().config(this.settings, this.log);
		this.verify_webhook = VerifyWebhook.getInstance().config(this.settings, this.log);

		return instance;
	}

	public Settings getSettings()
	{
		return this.settings;
	}

	public VerifyWebhook getVerifyWebhook()
	{
		return this.verify_webhook;
	}

	public BaseReceiver getBaseReceiver()
	{
		return this.base_receiver;
	}

	public BaseSender getBaseSender()
	{
		return this.base_sender;
	}

	public Log getLogger()
	{
		return this.log;
	}

    public boolean someLibraryMethod()
    {
        return true;
    }

    public void finish()
    {
    	this.log.close();
    }
}
