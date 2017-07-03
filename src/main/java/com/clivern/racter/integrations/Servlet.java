/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.integrations;

/**
 * This Class Take Current Request Data from Java Servlet and
 * Pass it to the Bot Platform
 */
public class Servlet {

	private static Servlet instance;

	/**
	 * Constructor
	 */
	protected Servlet() { }

	/**
	 * Get Instance
	 *
	 * @return Servlet
	 */
	public static Servlet getInstance() {
	    if(instance == null) {
	        instance = new Servlet();
	    }
	    return instance;
	}

}