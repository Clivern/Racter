/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.integrations;

/**
 * This Class Take Current Request Data from Spring Framework and
 * Pass it to the Bot Platform
 */
public class Spring {

	private static Spring instance;

	/**
	 * Constructor
	 */
	protected Spring() { }

	/**
	 * Get Instance
	 *
	 * @return Spring
	 */
	public static Spring getInstance() {
	    if(instance == null) {
	        instance = new Spring();
	    }
	    return instance;
	}

}