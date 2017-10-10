/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers.webhook;

import java.util.HashMap;
import java.util.Map;
import java.lang.*;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Message Webhook Class
 */
public class MessageReceivedWebhook {

    /**
     * @var String
     */
    protected String user_id;

    /**
     * @var String
     */
    protected String page_id;

    /**
     * @var Long
     */
    protected Long timestamp;

    /**
     * @var String
     */
    protected String message_id;

    /**
     * @var String
     */
    protected String message_text;

    /**
     * @var String
     */
    protected String quick_reply_payload;

    /**
     * @var Map<String, String>
     */
    protected Map<String, String> attachments = new HashMap<String, String>();


    /**
     * Set User ID
     *
     * @param user_id
     * @return void
     */
    public void setUserId(String user_id)
    {
        this.user_id = user_id;
    }

    /**
     * Set Page ID or Receiver ID
     *
     * @param page_id
     * @return void
     */
    public void setPageId(String page_id)
    {
        this.page_id = page_id;
    }

    /**
     * Set Timestamp
     *
     * @param timestamp
     * @return void
     */
    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * Set Message ID
     *
     * @param message_id
     * @return void
     */
    public void setMessageId(String message_id)
    {
        this.message_id = message_id;
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
     * Set Quick Reply Payload
     *
     * @param quick_reply_payload
     * @return void
     */
    public void setQuickReplyPayload(String quick_reply_payload)
    {
        this.quick_reply_payload = quick_reply_payload;
    }

    /**
     * Set Attachment
     *
     * @param type
     * @param payload
     * @return void
     */
    public void setAttachment(String type, String payload)
    {
        attachments.put(type, payload);
    }

    /**
     * Set Attachment
     *
     * @param type
     * @param loc_lat
     * @param loc_long
     * @return void
     */
    public void setAttachment(String type, Long loc_lat, Long loc_long)
    {
        attachments.put(type, Long.toString(loc_lat) + "," + Long.toString(loc_long));
    }

    /**
     * Check if User ID Exist
     *
     * @return Boolean
     */
    public Boolean hasUserId()
    {
        return (this.user_id != null);
    }

    /**
     * Check if Page ID Exist
     *
     * @return Boolean
     */
    public Boolean hasPageId()
    {
        return (this.page_id != null);
    }

    /**
     * Check if Timestamp Exist
     *
     * @return Boolean
     */
    public Boolean hasTimestamp()
    {
        return (this.timestamp != null);
    }

    /**
     * Check if Message ID Exist
     *
     * @return Boolean
     */
    public Boolean hasMessageId()
    {
        return (this.message_id != null);
    }

    /**
     * Check if Message Text Exist
     *
     * @return Boolean
     */
    public Boolean hasMessageText()
    {
        return (this.message_text != null);
    }

    /**
     * Check if Quick Reply Payload Exist
     *
     * @return Boolean
     */
    public Boolean hasQuickReplyPayload()
    {
        return (this.quick_reply_payload != null);
    }

    /**
     * Check if Attachment Exist
     *
     * @return Boolean
     */
    public Boolean hasAttachment()
    {
        return !this.attachments.isEmpty();
    }

    /**
     * Get User ID
     *
     * @return String
     */
    public String getUserId()
    {
        return this.user_id;
    }

    /**
     * Get Page ID
     *
     * @return String
     */
    public String getPageId()
    {
        return this.page_id;
    }

    /**
     * Get Timestamp
     *
     * @return Long
     */
    public Long getTimestamp()
    {
        return this.timestamp;
    }

    /**
     * Get Message ID
     *
     * @return String
     */
    public String getMessageId()
    {
        return this.message_id;
    }

    /**
     * Get Message Text
     *
     * @return String
     */
    public String getMessageText()
    {
        return this.message_text;
    }

    /**
     * Get Quick Reply Payload
     *
     * @return String
     */
    public String getQuickReplyPayload()
    {
        return this.quick_reply_payload;
    }

    /**
     * Get Attachment
     *
     * @return Map<String, String>
     */
    public Map<String, String> getAttachment()
    {
        return this.attachments;
    }
}