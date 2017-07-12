/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Button Template
 */
public class ButtonTemplate {

    /*
        {
          "recipient":{
            "id":"USER_ID"
          },
          "message":{
            "attachment":{
              "type":"template",
              "payload":{
                "template_type":"button",
                "text":"What do you want to do next?",
                "buttons":[
                    {
                        "type":"web_url",
                        "url":"https://m.me/petershats?ref=take_quiz",
                        "title":"Take the Hat Quiz",
                    },
                    {
                        "type":"postback",
                        "title":"Bookmark Item",
                        "payload":"DEVELOPER_DEFINED_PAYLOAD"
                    },
                    {
                        "type":"phone_number",
                        "title":"Call Representative",
                        "payload":"+15105551234"
                    },
                    {
                        "type":"element_share"
                    },
                    {
                        "type": "account_link",
                        "url": "https://www.example.com/authorize"
                    },
                    {
                        "type": "account_unlink"
                    }
                ]
              }
            }
          }
        }
     */
    private String recipient_id;
    private String message_text;
    private ArrayList<HashMap<String, String>> buttons = new ArrayList<HashMap<String, String>>();
    private String message_string;

    public void setRecipientId(String recipient_id){
        this.recipient_id = recipient_id;
    }

    public void setMessageText(String message_text){
        this.message_text = message_text;
    }

    public void setButton(String type, String title, String url, String payload)
    {
        HashMap<String, String> button = new HashMap<String, String>();
        button.put("type", type);
        button.put("title", title);
        button.put("url", url);
        button.put("payload", payload);
        this.buttons.add(button);
    }

    public String getRecipientId(){
        return this.recipient_id;
    }

    public String getMessageText(){
        return this.message_text;
    }

    public ArrayList<HashMap<String, String>> getButton()
    {
        return this.buttons;
    }

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

    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    public String getMessageString()
    {
        return this.message_string;
    }
}