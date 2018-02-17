/*
 * Copyright (C) 2017 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.racter.senders.templates;

import com.clivern.racter.contract.templates.SenderTemplate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * List Template Class
 *
 * @since 1.0.0
 */
public class ListTemplate implements SenderTemplate {

    protected String recipient_id;

    protected String element_style; // compact or cover

    protected String message_string;

    protected ArrayList<HashMap<String, Object>> elements = new ArrayList<HashMap<String, Object>>();

    protected ArrayList<HashMap<String, String>> buttons = new ArrayList<HashMap<String, String>>();


    /**
     * Set Recipient ID
     *
     * @param recipient_id the recipient id
     */
    public void setRecipientId(String recipient_id)
    {
        this.recipient_id = recipient_id;
    }

    /**
     * Get Recipient ID
     *
     * @return String the recipient id
     */
    public String getRecipientId()
    {
        return this.recipient_id;
    }

    /**
     * Set Element Style
     *
     * @param element_style the element style
     */
    public void setElementStyle(String element_style)
    {
        this.element_style = element_style;
    }

    /**
     * Get Element Style
     *
     * @return String the element style
     */
    public String getElementStyle()
    {
        return this.element_style;
    }

    /**
     * Set Button
     *
     * @param type the button type
     * @param title the button title
     * @param url the button url
     * @param payload the button payload
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
     * Set Element
     *
     * @param  title the element title
     * @param  image_url the element image url
     * @param  subtitle the element subtitle
     * @return Integer index of the element
     */
    public Integer setElement(String title, String image_url, String subtitle)
    {
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
     * @param index the element index
     * @param type the element type
     * @param url the element url
     * @param messenger_extensions the messenger extensions
     * @param webview_height_ratio the webview height ratio
     * @param fallback_url the fallback url
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
     * @param index the element index
     * @param title the element title
     * @param type the element type
     * @param url the element url
     * @param messenger_extensions the messenger extensions
     * @param webview_height_ratio the webview height ratio
     * @param fallback_url the fallback url
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
     * @return String the final message
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

                        if( this.element_style.equals("compact") ){
                            this.message_string += "\"top_element_style\":\"" + this.element_style + "\",";
                        }

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
                            this.message_string += "],";
                        }

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
     * @param message_string the final message
     */
    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    /**
     * Get message as a string
     *
     * @return String the final message
     */
    public String getMessageString()
    {
        return this.message_string;
    }
}