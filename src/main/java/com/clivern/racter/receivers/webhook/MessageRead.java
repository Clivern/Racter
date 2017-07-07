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
public class MessageRead {


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
	private static MessageRead instance;

	/**
	 * Constructor
	 */
	protected MessageRead() { }

	/**
	 * Get Instance
	 *
	 * @return MessageRead
	 */
	public static MessageRead getInstance() {
	    if(instance == null) {
	        instance = new MessageRead();
	    }
	    return instance;
	}
}