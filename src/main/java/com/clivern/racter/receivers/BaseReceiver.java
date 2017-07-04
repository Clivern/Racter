/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers;

/**
 * Base Receiver Class
 */
public class BaseReceiver {

	private static BaseReceiver instance;

	/**
	 * Constructor
	 */
	protected BaseReceiver() { }

	/**
	 * Get Instance
	 *
	 * @return BaseReceiver
	 */
	public static BaseReceiver getInstance() {
	    if(instance == null) {
	        instance = new BaseReceiver();
	    }
	    return instance;
	}
}