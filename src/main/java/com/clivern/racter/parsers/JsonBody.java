/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.parsers;

/**
 * Parse Json fom request body
 */
public class JsonBody {

	private static JsonBody instance;

	/**
	 * Constructor
	 */
	protected JsonBody() { }

	/**
	 * Get Instance
	 *
	 * @return JsonBody
	 */
	public static JsonBody getInstance() {
	    if(instance == null) {
	        instance = new JsonBody();
	    }
	    return instance;
	}
}