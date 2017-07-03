/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.integrations;

/**
 * This Class Take Current Request Data from Spark Framework and
 * Pass it to the Bot Platform
 */
public class Spark {

	private static Spark instance;

	/**
	 * Constructor
	 */
	protected Spark() { }

	/**
	 * Get Instance
	 *
	 * @return Spark
	 */
	public static Spark getInstance() {
	    if(instance == null) {
	        instance = new Spark();
	    }
	    return instance;
	}

}