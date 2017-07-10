/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Generic Template
 */
public class Generic {

	private static Generic instance;

	/**
	 * Constructor
	 */
	protected Generic() { }

	/**
	 * Get Instance
	 *
	 * @return Generic
	 */
	public static Generic getInstance() {
	    if(instance == null) {
	        instance = new Generic();
	    }
	    return instance;
	}
}