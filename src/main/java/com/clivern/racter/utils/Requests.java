/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.utils;

/**
 * Perform cURL Requests
 */
public class Requests {

	private static Requests instance;

	/**
	 * Constructor
	 */
	protected Requests() { }

	/**
	 * Get Instance
	 *
	 * @return Requests
	 */
	public static Requests getInstance() {
	    if(instance == null) {
	        instance = new Requests();
	    }
	    return instance;
	}

}