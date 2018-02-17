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
import java.util.Map;

/**
 * Text Message Template Class
 *
 * @since 1.0.0
 */
public class MessageTemplate implements SenderTemplate {

    protected String recipient_id;

    protected String message_text;

    protected Map<String, String> message_attachment = new HashMap<String, String>();

    protected ArrayList<HashMap<String, String>> message_quick_replies = new ArrayList<HashMap<String, String>>();

    protected String message_metadata;

    protected String sender_action;

    protected String notification_type;

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
     * Set Message Meta Data
     *
     * @param message_metadata the message meta data
     */
    public void setMessageMetadata(String message_metadata)
    {
        this.message_metadata = message_metadata;
    }

    /**
     * Set Sender Action
     *
     * @param sender_action the sender action
     */
    public void setSenderAction(String sender_action)
    {
        this.sender_action = sender_action;
    }

    /**
     * Set Notifications Type
     *
     * @param notification_type the notification type
     */
    public void setNotificationType(String notification_type)
    {
        this.notification_type = notification_type;
    }

    /**
     * Set Attachment
     *
     * @param type the attachment type
     * @param url the attachment url
     * @param is_reusable whether to be reusable or not
     */
    public void setAttachment(String type, String url, Boolean is_reusable)
    {
        this.message_attachment.put("type", type);
        this.message_attachment.put("url", url);
        this.message_attachment.put("is_reusable", String.valueOf(is_reusable));
    }

    /**
     * Set Quick Reply
     *
     * @param content_type the content type
     * @param title the title
     * @param payload the payload flag
     * @param image_url the image URL
     */
    public void setQuickReply(String content_type, String title, String payload, String image_url)
    {
        HashMap<String, String> quick_reply = new HashMap<String, String>();
        quick_reply.put("content_type", content_type);
        quick_reply.put("title", title);
        quick_reply.put("payload", payload);
        quick_reply.put("image_url", image_url);
        this.message_quick_replies.add(quick_reply);
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
     * Get Message Text
     *
     * @return String the message text
     */
    public String getMessageText()
    {
        return this.message_text;
    }

    /**
     * Get Message Meta Data
     *
     * @return String the message meta data
     */
    public String getMessageMetadata()
    {
        return this.message_metadata;
    }

    /**
     * Get Sender Action
     *
     * @return String the sender action
     */
    public String getSenderAction()
    {
        return this.sender_action;
    }

    /**
     * Get Notification Type
     *
     * @return String the notification type
     */
    public String getNotificationType()
    {
        return this.notification_type;
    }

    /**
     * Get Attachment
     *
     * @return Map a list of attachments
     */
    public Map<String, String> getAttachment()
    {
        return this.message_attachment;
    }

    /**
     * Get Quick Reply
     *
     * @return ArrayList a list of quick reply
     */
    public ArrayList<HashMap<String, String>> getQuickReply()
    {
        return this.message_quick_replies;
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

        if( ( this.message_text != null ) || ( !this.message_attachment.isEmpty() ) || ( !this.message_quick_replies.isEmpty() ) || ( this.message_metadata != null ) ){

            this.message_string += "\"message\": {";

            if( this.message_text != null ){
                this.message_string += "\"text\": \"" + this.message_text + "\",";
            }

            if( !this.message_attachment.isEmpty() ){
                this.message_string += "\"attachment\":{\"type\":\"" + this.message_attachment.get("type") + "\",\"payload\":{\"url\":\"" + this.message_attachment.get("url") + "\",\"is_reusable\": " + this.message_attachment.get("is_reusable") + "}},";
            }

            if( !this.message_quick_replies.isEmpty() ){
                this.message_string += "\"quick_replies\":[";
                for ( int j = 0 ; j < this.message_quick_replies.size(); j++ ) {
                    HashMap<String, String> quick_reply = this.message_quick_replies.get(j);

                    this.message_string += "{";
                    if( !quick_reply.get("content_type").equals("") ){
                        this.message_string += "\"content_type\":\"" + quick_reply.get("content_type") + "\",";
                    }
                    if( !quick_reply.get("title").equals("") ){
                        this.message_string += "\"title\":\"" + quick_reply.get("title") + "\",";
                    }
                    if( !quick_reply.get("payload").equals("") ){
                        this.message_string += "\"payload\":\"" + quick_reply.get("payload") + "\",";
                    }
                    if( !quick_reply.get("image_url").equals("") ){
                        this.message_string += "\"image_url\":\"" + quick_reply.get("image_url") + "\",";
                    }
                    this.message_string = this.message_string.replaceAll(",$", "");
                    this.message_string += "},";

                }
                this.message_string = this.message_string.replaceAll(",$", "");
                this.message_string += "],";
            }


            if( this.message_metadata != null ){
                this.message_string += "\"metadata\": \"" + this.message_metadata + "\",";
            }
            this.message_string = this.message_string.replaceAll(",$", "");
            this.message_string += "},";
        }

        if( this.sender_action != null ){
            this.message_string += "\"sender_action\":\"" + this.sender_action + "\",";
        }
        if( this.notification_type != null ){
            this.message_string += "\"notification_type\":\"" + this.notification_type + "\",";
        }

        this.message_string = this.message_string.replaceAll(",$", "");

        this.message_string += "}";

        return this.message_string;
    }

    /**
     * Set or override message
     *
     * @param message_string the message text
     */
    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    /**
     * Get message as a string
     *
     * @return String the message text
     */
    public String getMessageString()
    {
        return this.message_string;
    }
}