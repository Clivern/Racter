/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers;

import com.clivern.racter.configs.Settings;
import com.clivern.racter.utils.Log;

/**
 * Verify Webhook
 */
public class VerifyWebhook {

    private String hub_mode;
    private String hub_verify_token;
    private String hub_challenge;
    private Settings settings;
    private Log log;
    private static VerifyWebhook instance;

    /**
     * Constructor
     */
    protected VerifyWebhook() { }

    /**
     * Get Instance
     *
     * @return VerifyWebhook
     */
    public static VerifyWebhook getInstance() {
        if(instance == null) {
            instance = new VerifyWebhook();
        }
        return instance;
    }

    /**
     * Config Class
     *
     * @param  settings
     * @param  log
     * @return VerifyWebhook
     */
    public VerifyWebhook config(Settings settings, Log log)
    {
        this.settings = settings;
        this.log = log;

        return instance;
    }

    /**
     * Set Hub Mode
     *
     * @param hub_mode
     */
    public void setHubMode(String hub_mode)
    {
        this.hub_mode = hub_mode;
    }

    /**
     * Set Hub Verify Token
     *
     * @param hub_verify_token
     */
    public void setHubVerifyToken(String hub_verify_token)
    {
        this.hub_verify_token = hub_verify_token;
    }

    /**
     * Set Hub Challenge
     *
     * @param hub_challenge
     */
    public void setHubChallenge(String hub_challenge)
    {
        this.hub_challenge = hub_challenge;
    }

    /**
     * Get Hub Mode
     *
     * @return String
     */
    public String getHubMode()
    {
        return this.hub_mode;
    }

    /**
     * Get Hub Verify Token
     *
     * @return String
     */
    public String getHubVerifyToken()
    {
        return this.hub_verify_token;
    }

    /**
     * Get Hub Challenge
     *
     * @return String
     */
    public String getHubChallenge()
    {
        return this.hub_challenge;
    }

    /**
     * Verify Challenge Data
     *
     * @return boolean
     */
    public Boolean challenge()
    {
        if( (this.hub_mode.equals("subscribe")) && (this.hub_verify_token.equals(this.settings.get("verify_token", ""))) ){
            this.log.info("Verify token validated successfully.");
            return true;
        }

        this.log.severe("Error validating verify token.");
        return false;
    }
}