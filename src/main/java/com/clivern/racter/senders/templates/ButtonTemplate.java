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
 * Button Template Class
 *
 * @since 1.0.0
 */
public class ButtonTemplate implements SenderTemplate {

    protected String recipient_id;

    protected String message_text;

    protected ArrayList<HashMap<String, String>> buttons = new ArrayList<HashMap<String, String>>();

    protected String message_string;


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
     * Set Message Text
     *
     * @param message_text the message text
     */
    public void setMessageText(String message_text)
    {
        this.message_text = message_text;
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
     * Get Recipient ID
     *
     * @return String the recipient id
     */
    public String getRecipientId()
    {
        return this.recipient_id;
    }

    /**
     * Get message text
     *
     * @return String the message text
     */
    public String getMessageText()
    {
        return this.message_text;
    }

    /**
     * Get Buttons
     *
     * @return ArrayList the list of buttons
     */
    public ArrayList<HashMap<String, String>> getButton()
    {
        return this.buttons;
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