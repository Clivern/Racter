/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.webhook;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Message Delivered Webhook
 */
public class MessageDeliveredWebhook {


/*{
   "sender":{
      "id":"USER_ID"
   },
   "recipient":{
      "id":"PAGE_ID"
   },
   "delivery":{
      "mids":[
         "mid.1458668856218:ed81099e15d3f4f233"
      ],
      "watermark":1458668856253,
      "seq":37
   }
}  */

	private static MessageDeliveredWebhook instance;

	/**
	 * Constructor
	 */
	protected MessageDeliveredWebhook() { }

	/**
	 * Get Instance
	 *
	 * @return MessageDeliveredWebhook
	 */
	public static MessageDeliveredWebhook getInstance() {
	    if(instance == null) {
	        instance = new MessageDeliveredWebhook();
	    }
	    return instance;
	}
}