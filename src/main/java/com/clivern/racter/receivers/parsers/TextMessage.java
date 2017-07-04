/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.parsers;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Text Message Parser
 */
public class TextMessage {

	private static TextMessage instance;

	private String message_string;
	private JSONObject message_object;
	private Map<String, String> data = new HashMap<String, String>();

	/**
	 * Constructor
	 */
	protected TextMessage() { }

	/**
	 * Get Instance
	 *
	 * @return TextMessage
	 */
	public static TextMessage getInstance() {
	    if(instance == null) {
	        instance = new TextMessage();
	    }
	    return instance;
	}

	public void setMessage(String message_string)
	{
		this.message_string = message_string;
		this.message_object = new JSONObject(message_string);
	}

	public String getMessageString()
	{
		return this.message_string;
	}

	public JSONObject getMessageObject()
	{
		return this.message_object;
	}
}