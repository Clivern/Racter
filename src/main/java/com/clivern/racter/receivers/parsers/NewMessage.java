/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.parsers;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * New Message Parser
 */
public class NewMessage {

	private static NewMessage instance;

	/**
	 * Constructor
	 */
	protected NewMessage() { }

	/**
	 * Get Instance
	 *
	 * @return NewMessage
	 */
	public static NewMessage getInstance() {
	    if(instance == null) {
	        instance = new NewMessage();
	    }
	    return instance;
	}
}