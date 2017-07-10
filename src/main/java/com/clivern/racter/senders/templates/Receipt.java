/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Receipt Template
 */
public class Receipt {

	private static Receipt instance;

	/**
	 * Constructor
	 */
	protected Receipt() { }

	/**
	 * Get Instance
	 *
	 * @return Receipt
	 */
	public static Receipt getInstance() {
	    if(instance == null) {
	        instance = new Receipt();
	    }
	    return instance;
	}
}