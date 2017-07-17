/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders;

import com.clivern.racter.senders.templates.*;
import com.clivern.racter.configs.Settings;
import com.clivern.racter.utils.Log;

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
    private Settings settings;
    private Log log;

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

    public BaseSender config(Settings settings, Log log)
    {
        this.settings = settings;
        this.log = log;

        return instance;
    }

    public MessageTemplate getMessageTemplate(){
        return new MessageTemplate();
    }

    public ButtonTemplate getButtonTemplate(){
        return new ButtonTemplate();
    }

    public ListTemplate getListTemplate(){
        return new ListTemplate();
    }

    public GenericTemplate getGenericTemplate(){
        return new GenericTemplate();
    }

    public ReceiptTemplate getReceiptTemplate(){
        return new ReceiptTemplate();
    }

    public Boolean send(MessageTemplate template) throws UnirestException {
        String url = this.remote_url + this.settings.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    public Boolean send(ButtonTemplate template) throws UnirestException {
        String url = this.remote_url + this.settings.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    public Boolean send(ListTemplate template) throws UnirestException {
        String url = this.remote_url + this.settings.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    public Boolean send(GenericTemplate template) throws UnirestException {
        String url = this.remote_url + this.settings.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    public Boolean send(ReceiptTemplate template) throws UnirestException {
        String url = this.remote_url + this.settings.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    public Boolean send(String body) throws UnirestException {
        String url = this.remote_url + this.settings.get("page_access_token", "");
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }
}