/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.parsers;

import com.clivern.racter.configs.Settings;
import com.clivern.racter.utils.Log;

/**
 * Chat Text Message Receiver
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