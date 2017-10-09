/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers;

import com.clivern.racter.utils.Config;
import com.clivern.racter.utils.Log;

/**
 * Verify Webhook Class
 */
public class VerifyWebhook {

    /**
     * @var String
     */
    protected String hub_mode;

    /**
     * @var String
     */
    protected String hub_verify_token;

    /**
     * @var String
     */
    protected String hub_challenge;

    /**
     * @var Config an instance of config class
     */
    protected Config configs;

    /**
     * @var Log an instance of log class
     */
    protected Log log;


    /**
     * Class Constructor
     *
     * @param  configs
     * @param  log
     * @return void
     */
    public VerifyWebhook(Config configs, Log log)
    {
        this.configs = configs;
        this.log = log;
    }

    /**
     * Set Hub Mode
     *
     * @param hub_mode
     * @return void
     */
    public void setHubMode(String hub_mode)
    {
        this.hub_mode = hub_mode;
    }

    /**
     * Set Hub Verify Token
     *
     * @param hub_verify_token
     * @return void
     */
    public void setHubVerifyToken(String hub_verify_token)
    {
        this.hub_verify_token = hub_verify_token;
    }

    /**
     * Set Hub Challenge
     *
     * @param hub_challenge
     * @return void
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
        if( (this.hub_mode.equals("subscribe")) && (this.hub_verify_token.equals(this.configs.get("verify_token", ""))) ){
            this.log.info("Verify token validated successfully.");
            return true;
        }

        this.log.severe("Error validating verify token.");
        return false;
    }
}