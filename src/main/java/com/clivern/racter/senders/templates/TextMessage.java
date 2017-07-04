/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

/**
 * Text Message Template
 */
public class TextMessage {

	private static TextMessage instance;

	/**
	 * Constructor
	 */
	protected TextMessage() { }

	/**
	 * Get Instance
	 *
	 * @return TextMessage
	 */
	public static TextMessage getInstance() {
	    if(instance == null) {
	        instance = new TextMessage();
	    }
	    return instance;
	}
}