/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Button Template
 */
public class Button {

	private static Button instance;

	/**
	 * Constructor
	 */
	protected Button() { }

	/**
	 * Get Instance
	 *
	 * @return Button
	 */
	public static Button getInstance() {
	    if(instance == null) {
	        instance = new Button();
	    }
	    return instance;
	}
}