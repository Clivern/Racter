/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.webhook;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Message Read Webhook
 */
public class MessageReadWebhook {


/*{
   "sender":{
      "id":"USER_ID"
   },
   "recipient":{
      "id":"PAGE_ID"
   },
   "timestamp":1458668856463,
   "read":{
      "watermark":1458668856253,
      "seq":38
   }
}
*/
    private static MessageReadWebhook instance;

    /**
     * Constructor
     */
    protected MessageReadWebhook() { }

    /**
     * Get Instance
     *
     * @return MessageReadWebhook
     */
    public static MessageReadWebhook getInstance() {
        if(instance == null) {
            instance = new MessageReadWebhook();
        }
        return instance;
    }
}