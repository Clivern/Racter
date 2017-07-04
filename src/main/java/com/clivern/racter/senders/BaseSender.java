/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders;

/**
 * Base Sender Class
 */
public class BaseSender {

	private static BaseSender instance;

	/**
	 * Constructor
	 */
	protected BaseSender() { }

	/**
	 * Get Instance
	 *
	 * @return BaseSender
	 */
	public static BaseSender getInstance() {
	    if(instance == null) {
	        instance = new BaseSender();
	    }
	    return instance;
	}
}