/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.webhook;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Postback Webhook
 */
public class Postback {
/*
{
  "sender":{
    "id":"USER_ID"
  },
  "recipient":{
    "id":"PAGE_ID"
  },
  "timestamp":1458692752478,
  "postback":{
    "payload": USER_DEFINED_PAYLOAD,
    "referral": {
      "ref": USER_DEFINED_REFERRAL_PARAM,
      "source": "SHORTLINK",
      "type": "OPEN_THREAD",
    }
  }
}  */
	private static Postback instance;

	/**
	 * Constructor
	 */
	protected Postback() { }

	/**
	 * Get Instance
	 *
	 * @return Postback
	 */
	public static Postback getInstance() {
	    if(instance == null) {
	        instance = new Postback();
	    }
	    return instance;
	}
}