/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders;

import com.clivern.racter.senders.templates.*;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;

/**
 * Base Sender Class
 */
public class BaseSender {

	private static BaseSender instance;
	private String remote_url = "https://graph.facebook.com/v2.6/me/messages?access_token=";
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

	public BaseSender setAccessToken(String access_token){
		this.access_token = access_token;
		return instance;
	}

	public MessageTemplate getMessageTemplate(){
		return new MessageTemplate();
	}

	public Boolean sendMessageTemplate(MessageTemplate message_template) throws UnirestException {
		String url = this.remote_url + this.access_token;
		String body = message_template.build();
		HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

		return true;
	}
}