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
 * List Template
 */
public class ListTemplate {

/*
{
  "recipient":{
    "id":"RECIPIENT_ID"
  },
  "message": {
    "attachment": {
        "type": "template",
        "payload": {
            "template_type": "list",
            add "top_element_style": "compact", for Plain list
            "elements": [
                {
                    "title": "Classic T-Shirt Collection",
                    "image_url": "https://peterssendreceiveapp.ngrok.io/img/collection.png",
                    "subtitle": "See all our colors",
                    "default_action": {
                        "type": "web_url",
                        "url": "https://peterssendreceiveapp.ngrok.io/shop_collection",
                        "messenger_extensions": true,
                        "webview_height_ratio": "tall",
                        "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                    },
                    "buttons": [
                        {
                            "title": "View",
                            "type": "web_url",
                            "url": "https://peterssendreceiveapp.ngrok.io/collection",
                            "messenger_extensions": true,
                            "webview_height_ratio": "tall",
                            "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                        }
                    ]
                },
                {
                    "title": "Classic White T-Shirt",
                    "image_url": "https://peterssendreceiveapp.ngrok.io/img/white-t-shirt.png",
                    "subtitle": "100% Cotton, 200% Comfortable",
                    "default_action": {
                        "type": "web_url",
                        "url": "https://peterssendreceiveapp.ngrok.io/view?item=100",
                        "messenger_extensions": true,
                        "webview_height_ratio": "tall",
                        "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                    },
                    "buttons": [
                        {
                            "title": "Shop Now",
                            "type": "web_url",
                            "url": "https://peterssendreceiveapp.ngrok.io/shop?item=100",
                            "messenger_extensions": true,
                            "webview_height_ratio": "tall",
                            "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                        }
                    ]
                },
                {
                    "title": "Classic Blue T-Shirt",
                    "image_url": "https://peterssendreceiveapp.ngrok.io/img/blue-t-shirt.png",
                    "subtitle": "100% Cotton, 200% Comfortable",
                    "default_action": {
                        "type": "web_url",
                        "url": "https://peterssendreceiveapp.ngrok.io/view?item=101",
                        "messenger_extensions": true,
                        "webview_height_ratio": "tall",
                        "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                    },
                    "buttons": [
                        {
                            "title": "Shop Now",
                            "type": "web_url",
                            "url": "https://peterssendreceiveapp.ngrok.io/shop?item=101",
                            "messenger_extensions": true,
                            "webview_height_ratio": "tall",
                            "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                        }
                    ]
                },
                {
                    "title": "Classic Black T-Shirt",
                    "image_url": "https://peterssendreceiveapp.ngrok.io/img/black-t-shirt.png",
                    "subtitle": "100% Cotton, 200% Comfortable",
                    "default_action": {
                        "type": "web_url",
                        "url": "https://peterssendreceiveapp.ngrok.io/view?item=102",
                        "messenger_extensions": true,
                        "webview_height_ratio": "tall",
                        "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                    },
                    "buttons": [
                        {
                            "title": "Shop Now",
                            "type": "web_url",
                            "url": "https://peterssendreceiveapp.ngrok.io/shop?item=102",
                            "messenger_extensions": true,
                            "webview_height_ratio": "tall",
                            "fallback_url": "https://peterssendreceiveapp.ngrok.io/"
                        }
                    ]
                }
            ],
             "buttons": [
                {
                    "title": "View More",
                    "type": "postback",
                    "payload": "payload"
                }
            ]
        }
    }
}

}

 */

	private String recipient_id;
	private String element_style; // compact or cover
    private String message_string;
    private ArrayList<HashMap<String, Object>> elements = new ArrayList<HashMap<String, Object>>();
    private ArrayList<HashMap<String, String>> buttons = new ArrayList<HashMap<String, String>>();

    public void setRecipientId(String recipient_id){
        this.recipient_id = recipient_id;
    }

    public void setElementStyle(String element_style){
        this.element_style = element_style;
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

    public Integer setElement(String title, String image_url, String subtitle)
    {
        Integer index = 0;
        HashMap<String, Object> element = new HashMap<String, Object>();

        element.put("title", title);
        element.put("image_url", image_url);
        element.put("subtitle", subtitle);
        this.elements.add(element);

        return this.elements.size() - 1;
    }

    public void setElementDefaultAction(Integer index, String type, String url, Boolean messenger_extensions, String webview_height_ratio, String fallback_url)
    {
        this.elements.get(index).put("default_action_type", type);
        this.elements.get(index).put("default_action_url", url);
        this.elements.get(index).put("default_action_messenger_extensions", String.valueOf(messenger_extensions));
        this.elements.get(index).put("default_action_webview_height_ratio", webview_height_ratio);
        this.elements.get(index).put("default_action_fallback_url", fallback_url);
    }

    public void setElementButton(Integer index, String title, String type, String url, Boolean messenger_extensions, String webview_height_ratio, String fallback_url)
    {
        if( this.elements.get(index).containsKey("buttons") ){
            HashMap<String, String> button = new HashMap<String, String>();
            button.put("title", title);
            button.put("type", type);
            button.put("url", url);
            button.put("messenger_extensions", String.valueOf(messenger_extensions));
            button.put("webview_height_ratio", webview_height_ratio);
            button.put("fallback_url", fallback_url);

            @SuppressWarnings("unchecked")
            ArrayList<HashMap<String, String>> element_buttons = (ArrayList<HashMap<String, String>>) this.elements.get(index).get("buttons");
            element_buttons.add(button);

            this.elements.get(index).put("buttons", element_buttons);
        }else{
            ArrayList<HashMap<String, String>> element_buttons = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> button = new HashMap<String, String>();
            button.put("title", title);
            button.put("type", type);
            button.put("url", url);
            button.put("messenger_extensions", String.valueOf(messenger_extensions));
            button.put("webview_height_ratio", webview_height_ratio);
            button.put("fallback_url", fallback_url);
            element_buttons.add(button);
            this.elements.get(index).put("buttons", element_buttons);
        }
    }

    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    public String getRecipientId(){
        return this.recipient_id;
    }

    public String getElementStyle(){
        return this.element_style;
    }

    public String getMessageString()
    {
        return this.message_string;
    }

    public String build()
    {




/*  "message": {
        "attachment": {
            "type": "template",
            "payload": {
                "template_type": "list",
                add "top_element_style": "compact", for Plain list

            }
        }
    }
*/
        this.message_string  = "{";

        if( this.recipient_id != null ){
            this.message_string += "\"recipient\": {\"id\": \"" + this.recipient_id + "\"},";
        }

        if( !this.elements.isEmpty() ){

            this.message_string += "\"message\": {";

                this.message_string += "\"attachment\": {";

                    this.message_string += "\"type\": \"template\",";

                    this.message_string += "\"payload\": {";

                        if( this.element_style.equals("compact") ){
                            this.message_string += "\"top_element_style\":\"" + this.element_style + "\",";
                        }

                        //------------------
                        // ELEMENT GOES HERE
                        //------------------


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

        this.message_string += "}";

        return this.message_string;
    }
}