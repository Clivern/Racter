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
public class MessageEcho {

	private static MessageEcho instance;

	/**
	 * Constructor
	 */
	protected MessageEcho() { }

	/**
	 * Get Instance
	 *
	 * @return MessageEcho
	 */
	public static MessageEcho getInstance() {
	    if(instance == null) {
	        instance = new MessageEcho();
	    }
	    return instance;
	}
}