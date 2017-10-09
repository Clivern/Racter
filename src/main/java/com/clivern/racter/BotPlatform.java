/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import com.clivern.racter.utils.Config;
import com.clivern.racter.utils.Log;
import com.clivern.racter.receivers.BaseReceiver;
import com.clivern.racter.receivers.VerifyWebhook;
import com.clivern.racter.senders.BaseSender;

/**
 * Bot Platform Base Class
 */
public class BotPlatform {

    private Config configs;

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
        this.configs = new Config();
        this.configs.loadPropertiesFile(poperties_file_path);
        return instance;
    }

    /**
     * Config Class
     *
     * @return BotPlatform
     */
    public BotPlatform loadConfigs(Map<String, String> options)
    {
        this.configs = new Config();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            this.configs.set(entry.getKey(), options.get(entry.getKey()));
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
        this.log = new Log(this.configs);
        this.base_receiver = BaseReceiver.getInstance().config(this.configs, this.log);
        this.base_sender = BaseSender.getInstance().config(this.configs, this.log);
        this.verify_webhook = VerifyWebhook.getInstance().config(this.configs, this.log);

        return instance;
    }

    public Config getSettings()
    {
        return this.configs;
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

    public String getName()
    {
        return "Racter";
    }

    public void finish()
    {
        this.log.close();
    }
}
