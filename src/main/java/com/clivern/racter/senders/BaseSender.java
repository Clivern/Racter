/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders;

import com.clivern.racter.senders.templates.*;
import com.clivern.racter.utils.Config;
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

    /**
     * @var String
     */
    protected String remote_url = "https://graph.facebook.com/v2.6/me/messages?access_token=";

    /**
     * @var Config an instance of config class
     */
    protected Config configs;

    /**
     * @var Log an instance of log class
     */
    protected Log log;


    /**
     * Class Constructor
     *
     * @param configs
     * @param log
     * @return void
     */
    public BaseSender(Config configs, Log log)
    {
        this.configs = configs;
        this.log = log;
    }

    /**
     * Get Default Message Template Instance
     *
     * @return MessageTemplate
     */
    public MessageTemplate getMessageTemplate()
    {
        return new MessageTemplate();
    }

    /**
     * Get Button Message Template Instance
     *
     * @return ButtonTemplate
     */
    public ButtonTemplate getButtonTemplate()
    {
        return new ButtonTemplate();
    }

    /**
     * Get List Message Template Instance
     *
     * @return ListTemplate
     */
    public ListTemplate getListTemplate()
    {
        return new ListTemplate();
    }

    /**
     * Get Generic Message Template Instance
     *
     * @return GenericTemplate
     */
    public GenericTemplate getGenericTemplate()
    {
        return new GenericTemplate();
    }

    /**
     * Get Receipt Message Template Instance
     *
     * @return ReceiptTemplate
     */
    public ReceiptTemplate getReceiptTemplate()
    {
        return new ReceiptTemplate();
    }

    /**
     * Send Default Message
     *
     * @param  template
     * @return Boolean
     * @throws UnirestException
     */
    public Boolean send(MessageTemplate template) throws UnirestException
    {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    /**
     * Send Button Message
     *
     * @param  template
     * @return Boolean
     * @throws UnirestException
     */
    public Boolean send(ButtonTemplate template) throws UnirestException
    {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    /**
     * Send List Message
     *
     * @param  template
     * @return Boolean
     * @throws UnirestException
     */
    public Boolean send(ListTemplate template) throws UnirestException
    {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    /**
     * Send Generic Message
     *
     * @param  template
     * @return Boolean
     * @throws UnirestException
     */
    public Boolean send(GenericTemplate template) throws UnirestException
    {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    /**
     * Send Receipt Message
     *
     * @param  template
     * @return Boolean
     * @throws UnirestException
     */
    public Boolean send(ReceiptTemplate template) throws UnirestException
    {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }

    /**
     * Send Plain Message JSON
     *
     * @param  body
     * @return Boolean
     * @throws UnirestException
     */
    public Boolean send(String body) throws UnirestException
    {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        this.log.info("curl -X POST -H \"Content-Type: application/json\" -d '" + body + "' \"" + url + "\"");
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(body).asString();

        return true;
    }
}