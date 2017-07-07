/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.webhook;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Message Webhook
 */
public class Message {

	private static Message instance;

	/**
	 * Constructor
	 */
	protected Message() { }

	/**
	 * Get Instance
	 *
	 * @return Message
	 */
	public static Message getInstance() {
	    if(instance == null) {
	        instance = new Message();
	    }
	    return instance;
	}
}