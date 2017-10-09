/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic Template Class
 */
public class GenericTemplate {

    /**
     * @var String
     */
    protected String recipient_id;

    /**
     * @var String
     */
    protected String message_string;

    /**
     * @var ArrayList
     */
    protected ArrayList<HashMap<String, Object>> elements = new ArrayList<HashMap<String, Object>>();


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
     * Get Recipient ID
     *
     * @return String
     */
    public String getRecipientId()
    {
        return this.recipient_id;
    }

    /**
     * Set Element
     *
     * @param  title
     * @param  image_url
     * @param  subtitle
     * @return Integer
     */
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

    /**
     * Set Element Default Action
     *
     * @param index
     * @param type
     * @param url
     * @param messenger_extensions
     * @param webview_height_ratio
     * @param fallback_url
     * @return void
     */
    public void setElementDefaultAction(Integer index, String type, String url, Boolean messenger_extensions, String webview_height_ratio, String fallback_url)
    {
        this.elements.get(index).put("default_action_type", type);
        this.elements.get(index).put("default_action_url", url);
        this.elements.get(index).put("default_action_messenger_extensions", String.valueOf(messenger_extensions));
        this.elements.get(index).put("default_action_webview_height_ratio", webview_height_ratio);
        this.elements.get(index).put("default_action_fallback_url", fallback_url);
    }

    /**
     * Set Element Button
     *
     * @param index
     * @param title
     * @param type
     * @param url
     * @param messenger_extensions
     * @param webview_height_ratio
     * @param fallback_url
     * @return void
     */
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

        if( !this.elements.isEmpty() ){

            this.message_string += "\"message\": {";

                this.message_string += "\"attachment\": {";

                    this.message_string += "\"type\": \"template\",";

                    this.message_string += "\"payload\": {";

                        this.message_string += "\"template_type\":\"generic\",";

                        if( !this.elements.isEmpty() ){

                            this.message_string += "\"elements\":[";
                            for ( int j = 0 ; j < this.elements.size(); j++ ) {
                                HashMap<String, Object> element = this.elements.get(j);

                                this.message_string += "{";
                                if( !element.get("title").equals("") ){
                                    this.message_string += "\"title\":\"" + element.get("title") + "\",";
                                }
                                if( !element.get("image_url").equals("") ){
                                    this.message_string += "\"image_url\":\"" + element.get("image_url") + "\",";
                                }
                                if( !element.get("subtitle").equals("") ){
                                    this.message_string += "\"subtitle\":\"" + element.get("subtitle") + "\",";
                                }

                                if( !element.get("default_action_type").equals("") || !element.get("default_action_url").equals("") || !element.get("default_action_messenger_extensions").equals("") || !element.get("default_action_webview_height_ratio").equals("") || !element.get("default_action_fallback_url").equals("") ){
                                    this.message_string += "\"default_action\":{";
                                        if( !element.get("default_action_type").equals("") ){
                                            this.message_string += "\"type\":\"" + element.get("default_action_type") + "\",";
                                        }
                                        if( !element.get("default_action_url").equals("") ){
                                            this.message_string += "\"url\":\"" + element.get("default_action_url") + "\",";
                                        }
                                        if( !element.get("default_action_messenger_extensions").equals("") ){
                                            this.message_string += "\"messenger_extensions\":\"" + element.get("default_action_messenger_extensions") + "\",";
                                        }
                                        if( !element.get("default_action_webview_height_ratio").equals("") ){
                                            this.message_string += "\"webview_height_ratio\":\"" + element.get("default_action_webview_height_ratio") + "\",";
                                        }
                                        if( !element.get("default_action_fallback_url").equals("") ){
                                            this.message_string += "\"fallback_url\":\"" + element.get("default_action_fallback_url") + "\",";
                                        }
                                        this.message_string = this.message_string.replaceAll(",$", "");
                                    this.message_string += "},";
                                }


                                if( element.containsKey("buttons") ){
                                    ArrayList<HashMap<String, String>> buttons = (ArrayList<HashMap<String, String>>) element.get("buttons");
                                    if( !buttons.isEmpty() ){

                                        this.message_string += "\"buttons\": [";

                                            for ( int k = 0 ; k < buttons.size(); k++ ) {
                                                HashMap<String, String> button = buttons.get(k);
                                                this.message_string += "{";
                                                    if( !button.get("title").equals("") ){
                                                        this.message_string += "\"title\":\"" + button.get("title") + "\",";
                                                    }
                                                    if( !button.get("type").equals("") ){
                                                        this.message_string += "\"type\":\"" + button.get("type") + "\",";
                                                    }
                                                    if( !button.get("url").equals("") ){
                                                        this.message_string += "\"url\":\"" + button.get("url") + "\",";
                                                    }
                                                    if( !button.get("messenger_extensions").equals("") ){
                                                        this.message_string += "\"messenger_extensions\":\"" + button.get("messenger_extensions") + "\",";
                                                    }
                                                    if( !button.get("webview_height_ratio").equals("") ){
                                                        this.message_string += "\"webview_height_ratio\":\"" + button.get("webview_height_ratio") + "\",";
                                                    }
                                                    if( !button.get("fallback_url").equals("") ){
                                                        this.message_string += "\"fallback_url\":\"" + button.get("fallback_url") + "\",";
                                                    }
                                                this.message_string = this.message_string.replaceAll(",$", "");
                                                this.message_string += "},";
                                            }

                                            this.message_string = this.message_string.replaceAll(",$", "");
                                        this.message_string += "]";
                                    }
                                }

                                this.message_string = this.message_string.replaceAll(",$", "");
                                this.message_string += "},";
                            }

                            this.message_string = this.message_string.replaceAll(",$", "");
                            this.message_string += "]";
                        }

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