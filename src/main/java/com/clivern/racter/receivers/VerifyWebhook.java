/*
 * Copyright (C) 2017 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.racter.receivers;

import com.clivern.racter.utils.Config;
import org.pmw.tinylog.Logger;

/**
 * Verify Webhook Class
 *
 * @since 1.0.0
 */
public class VerifyWebhook {

    protected String hub_mode;

    protected String hub_verify_token;

    protected String hub_challenge;

    protected Config configs;


    /**
     * Class Constructor
     *
     * @param  configs an instance of configs classs
     */
    public VerifyWebhook(Config configs)
    {
        this.configs = configs;
    }

    /**
     * Set Hub Mode
     *
     * @param hub_mode the hub mode
     */
    public void setHubMode(String hub_mode)
    {
        this.hub_mode = hub_mode;
    }

    /**
     * Set Hub Verify Token
     *
     * @param hub_verify_token the hub verify token
     */
    public void setHubVerifyToken(String hub_verify_token)
    {
        this.hub_verify_token = hub_verify_token;
    }

    /**
     * Set Hub Challenge
     *
     * @param hub_challenge the hub challenge
     */
    public void setHubChallenge(String hub_challenge)
    {
        this.hub_challenge = hub_challenge;
    }

    /**
     * Get Hub Mode
     *
     * @return String the hub mode
     */
    public String getHubMode()
    {
        return this.hub_mode;
    }

    /**
     * Get Hub Verify Token
     *
     * @return String the hub verify token
     */
    public String getHubVerifyToken()
    {
        return this.hub_verify_token;
    }

    /**
     * Get Hub Challenge
     *
     * @return String the hub challenge
     */
    public String getHubChallenge()
    {
        return this.hub_challenge;
    }

    /**
     * Verify Challenge Data
     *
     * @return boolean whether challenge passed or not
     */
    public Boolean challenge()
    {
        if( (this.hub_mode.equals("subscribe")) && (this.hub_verify_token.equals(this.configs.get("verify_token", ""))) ){
            Logger.info("Verify token validated successfully.");
            return true;
        }

        Logger.error("Error validating verify token.");
        return false;
    }
}