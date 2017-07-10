/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders;

/**
 * Base Sender Class
 */
public class BaseSender {

	private static BaseSender instance;
	private String remote_url = 'https://graph.facebook.com/v2.6/me/messages?access_token=';
	private String access_token;

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