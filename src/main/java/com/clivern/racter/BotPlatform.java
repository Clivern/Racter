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
package com.clivern.racter;

import java.util.Map;
import java.io.IOException;
import com.clivern.racter.utils.Config;
import com.clivern.racter.receivers.BaseReceiver;
import com.clivern.racter.receivers.VerifyWebhook;
import com.clivern.racter.senders.BaseSender;

/**
 * Bot Platform Base Class
 *
 * @since 1.0.0
 */
public class BotPlatform {

    protected Config configs;

    protected VerifyWebhook verify_webhook;

    protected BaseReceiver base_receiver;

    protected BaseSender base_sender;


    /**
     * Class Constructor
     *
     * @param  poperties_file_path realtive path to .properties file
     * @throws IOException May throw IOException if it cannot open configs file
     */
    public BotPlatform(String poperties_file_path) throws IOException
    {
        this.configs = new Config();
        this.configs.loadPropertiesFile(poperties_file_path);
        configs.configLogger();
        this.base_receiver = new BaseReceiver(this.configs);
        this.base_sender = new BaseSender(this.configs);
        this.verify_webhook = new VerifyWebhook(this.configs);
    }

    /**
     * Class Constructor
     *
     * @param  options racter package configs
     * @throws IOException May throw IOException if it cannot open configs file
     */
    public BotPlatform(Map<String, String> options) throws IOException
    {
        this.configs = new Config();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            this.configs.set(entry.getKey(), options.get(entry.getKey()));
        }
        configs.configLogger();
        this.base_receiver = new BaseReceiver(this.configs);
        this.base_sender = new BaseSender(this.configs);
        this.verify_webhook = new VerifyWebhook(this.configs);
    }

    /**
     * Get Configs
     *
     * @return an instance of config class
     */
    public Config getConfigs()
    {
        return this.configs;
    }

    /**
     * Get Verify Webhook
     *
     * @return VerifyWebhook an instance of verify webhook
     */
    public VerifyWebhook getVerifyWebhook()
    {
        return this.verify_webhook;
    }

    /**
     * Get Base Receiver
     *
     * @return BaseReceiver an instance of base receiver
     */
    public BaseReceiver getBaseReceiver()
    {
        return this.base_receiver;
    }

    /**
     * Get Base Sender
     *
     * @return BaseSender an instance of base sender
     */
    public BaseSender getBaseSender()
    {
        return this.base_sender;
    }

    /**
     * Get Package Name
     *
     * @return String the package name
     */
    public String getName()
    {
        return "Racter";
    }
}
