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

    protected Config configs;

    protected VerifyWebhook verify_webhook;

    protected BaseReceiver base_receiver;

    protected BaseSender base_sender;

    protected Log log;


    /**
     * Class Constructor
     *
     * @param  poperties_file_path
     */
    public BotPlatform(String poperties_file_path) throws IOException
    {
        this.configs = new Config();
        this.configs.loadPropertiesFile(poperties_file_path);
        this.log = new Log(this.configs);
        this.base_receiver = new BaseReceiver(this.configs, this.log);
        this.base_sender = new BaseSender(this.configs, this.log);
        this.verify_webhook = new VerifyWebhook(this.configs, this.log);
    }

    /**
     * Class Constructor
     *
     * @param  options
     */
    public BotPlatform(Map<String, String> options) throws IOException
    {
        this.configs = new Config();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            this.configs.set(entry.getKey(), options.get(entry.getKey()));
        }
        this.log = new Log(this.configs);
        this.base_receiver = new BaseReceiver(this.configs, this.log);
        this.base_sender = new BaseSender(this.configs, this.log);
        this.verify_webhook = new VerifyWebhook(this.configs, this.log);
    }

    /**
     * Get Configs
     *
     * @return Config
     */
    public Config getConfigs()
    {
        return this.configs;
    }

    /**
     * Get Verify Webhook
     *
     * @return VerifyWebhook
     */
    public VerifyWebhook getVerifyWebhook()
    {
        return this.verify_webhook;
    }

    /**
     * Get Base Receiver
     *
     * @return BaseReceiver
     */
    public BaseReceiver getBaseReceiver()
    {
        return this.base_receiver;
    }

    /**
     * Get Base Sender
     *
     * @return BaseSender
     */
    public BaseSender getBaseSender()
    {
        return this.base_sender;
    }

    /**
     * Get Logger
     *
     * @return Log
     */
    public Log getLogger()
    {
        return this.log;
    }

    /**
     * Get Package Name
     *
     * @return String
     */
    public String getName()
    {
        return "Racter";
    }

    /**
     * Close any connections
     */
    public void finish()
    {
        this.log.close();
    }
}
