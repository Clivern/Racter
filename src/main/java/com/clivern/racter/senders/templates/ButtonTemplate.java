/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Button Template Class
 */
public class ButtonTemplate {

    /**
     * @var String
     */
    protected String recipient_id;

    /**
     * @var String
     */
    protected String message_text;

    /**
     * @var ArrayList
     */
    protected ArrayList<HashMap<String, String>> buttons = new ArrayList<HashMap<String, String>>();

    /**
     * @var String
     */
    protected String message_string;


    /**
     * Set Recipient ID
     *
     * @return void
     */
    public void setRecipientId(String recipient_id)
    {
        this.recipient_id = recipient_id;
    }

    /**
     * Set Message Text
     *
     * @param message_text
     * @return void
     */
    public void setMessageText(String message_text)
    {
        this.message_text = message_text;
    }

    /**
     * Set Button
     *
     * @param type
     * @param title
     * @param url
     * @param payload
     * @return void
     */
    public void setButton(String type, String title, String url, String payload)
    {
        HashMap<String, String> button = new HashMap<String, String>();
        button.put("type", type);
        button.put("title", title);
        button.put("url", url);
        button.put("payload", payload);
        this.buttons.add(button);
    }

    /**
     * Get Recipient ID
     *
     * @return String
     */
    public String getRecipientId()
    {
        return this.recipient_id;
    }

    /**
     * Get message text
     *
     * @return String
     */
    public String getMessageText()
    {
        return this.message_text;
    }

    /**
     * Get Buttons
     *
     * @return ArrayList
     */
    public ArrayList<HashMap<String, String>> getButton()
    {
        return this.buttons;
    }

    /**
     * Build and get message as a string
     *
     * @return String
     */
    public String build()
    {
        this.message_string  = "{";

        if( this.recipient_id != null ){
            this.message_string += "\"recipient\": {\"id\": \"" + this.recipient_id + "\"},";
        }

        if( (this.message_text != null) && !(this.message_text.equals("")) && !(this.buttons.isEmpty()) ){
            this.message_string += "\"message\": {";
            this.message_string += "\"attachment\": {";
            this.message_string += "\"type\": \"template\",";
            this.message_string += "\"payload\": {";
            this.message_string += "\"template_type\": \"button\",";
            this.message_string += "\"text\": \"" + this.message_text + "\",";
            this.message_string += "\"buttons\":[";
            for ( int j = 0 ; j < this.buttons.size(); j++ ) {
                HashMap<String, String> button = this.buttons.get(j);
                this.message_string += "{";
                if( !button.get("type").equals("") ){
                    this.message_string += "\"type\":\"" + button.get("type") + "\",";
                }
                if( !button.get("title").equals("") ){
                    this.message_string += "\"title\":\"" + button.get("title") + "\",";
                }
                if( !button.get("url").equals("") ){
                    this.message_string += "\"url\":\"" + button.get("url") + "\",";
                }
                if( !button.get("payload").equals("") ){
                    this.message_string += "\"payload\":\"" + button.get("payload") + "\",";
                }
                this.message_string = this.message_string.replaceAll(",$", "");
                this.message_string += "},";
            }
            this.message_string = this.message_string.replaceAll(",$", "");
            this.message_string += "]";
            this.message_string += "}";
            this.message_string += "}";
            this.message_string += "}";
        }

        this.message_string = this.message_string.replaceAll(",$", "");

        this.message_string += "}";

        return this.message_string;
    }

    /**
     * Set or override message
     *
     * @param message_string
     * @return void
     */
    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    /**
     * Get message as a string
     *
     * @return String
     */
    public String getMessageString()
    {
        return this.message_string;
    }
}