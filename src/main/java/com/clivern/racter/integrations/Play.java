/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.integrations;

/**
 * This Class Take Current Request Data from Play Framework and
 * Pass it to the Bot Platform
 */
public class Play {

	private static Play instance;

	/**
	 * Constructor
	 */
	protected Play() { }

	/**
	 * Get Instance
	 *
	 * @return Play
	 */
	public static Play getInstance() {
	    if(instance == null) {
	        instance = new Play();
	    }
	    return instance;
	}

}