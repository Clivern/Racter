/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * List Template
 */
public class List {

	private static List instance;

	/**
	 * Constructor
	 */
	protected List() { }

	/**
	 * Get Instance
	 *
	 * @return List
	 */
	public static List getInstance() {
	    if(instance == null) {
	        instance = new List();
	    }
	    return instance;
	}
}