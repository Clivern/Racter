/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.webhook;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Message Echo Webhook
 */
public class MessageEchoWebhook {

    private static MessageEchoWebhook instance;

    /**
     * Constructor
     */
    protected MessageEchoWebhook() { }

    /**
     * Get Instance
     *
     * @return MessageEchoWebhook
     */
    public static MessageEchoWebhook getInstance() {
        if(instance == null) {
            instance = new MessageEchoWebhook();
        }
        return instance;
    }
}