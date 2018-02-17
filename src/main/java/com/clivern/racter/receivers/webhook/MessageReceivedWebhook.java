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
package com.clivern.racter.receivers.webhook;

import com.clivern.racter.contract.templates.ReceiverTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Message Webhook Class
 *
 * @since 1.0.0
 */
public class MessageReceivedWebhook implements ReceiverTemplate {

    protected String user_id;

    protected String page_id;

    protected Long timestamp;

    protected String message_id;

    protected String message_text;

    protected String quick_reply_payload;

    protected Map<String, String> attachments = new HashMap<String, String>();


    /**
     * Set User ID
     *
     * @param user_id the user id
     */
    public void setUserId(String user_id)
    {
        this.user_id = user_id;
    }

    /**
     * Set Page ID or Receiver ID
     *
     * @param page_id the page id
     */
    public void setPageId(String page_id)
    {
        this.page_id = page_id;
    }

    /**
     * Set Timestamp
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * Set Message ID
     *
     * @param message_id the message id
     */
    public void setMessageId(String message_id)
    {
        this.message_id = message_id;
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
     * Set Quick Reply Payload
     *
     * @param quick_reply_payload the quick reply payload
     */
    public void setQuickReplyPayload(String quick_reply_payload)
    {
        this.quick_reply_payload = quick_reply_payload;
    }

    /**
     * Set Attachment
     *
     * @param type the attachment type
     * @param payload the attachment payload
     */
    public void setAttachment(String type, String payload)
    {
        attachments.put(type, payload);
    }

    /**
     * Set Attachment
     *
     * @param type the attachment type
     * @param loc_lat the attachment location latitude
     * @param loc_long the attachment location longitude
     */
    public void setAttachment(String type, Long loc_lat, Long loc_long)
    {
        attachments.put(type, Long.toString(loc_lat) + "," + Long.toString(loc_long));
    }

    /**
     * Check if User ID Exist
     *
     * @return Boolean whether has a user id
     */
    public Boolean hasUserId()
    {
        return (this.user_id != null);
    }

    /**
     * Check if Page ID Exist
     *
     * @return Boolean whether has a page id
     */
    public Boolean hasPageId()
    {
        return (this.page_id != null);
    }

    /**
     * Check if Timestamp Exist
     *
     * @return Boolean whether has a timestamp
     */
    public Boolean hasTimestamp()
    {
        return (this.timestamp != null);
    }

    /**
     * Check if Message ID Exist
     *
     * @return Boolean whether has a message id
     */
    public Boolean hasMessageId()
    {
        return (this.message_id != null);
    }

    /**
     * Check if Message Text Exist
     *
     * @return Boolean whether has a message text
     */
    public Boolean hasMessageText()
    {
        return (this.message_text != null);
    }

    /**
     * Check if Quick Reply Payload Exist
     *
     * @return Boolean whether has a quick reply payload
     */
    public Boolean hasQuickReplyPayload()
    {
        return (this.quick_reply_payload != null);
    }

    /**
     * Check if Attachment Exist
     *
     * @return Boolean whether has an attachment
     */
    public Boolean hasAttachment()
    {
        return !this.attachments.isEmpty();
    }

    /**
     * Get User ID
     *
     * @return String the user id
     */
    public String getUserId()
    {
        return this.user_id;
    }

    /**
     * Get Page ID
     *
     * @return String the page id
     */
    public String getPageId()
    {
        return this.page_id;
    }

    /**
     * Get Timestamp
     *
     * @return Long the timestamp
     */
    public Long getTimestamp()
    {
        return this.timestamp;
    }

    /**
     * Get Message ID
     *
     * @return String the message id
     */
    public String getMessageId()
    {
        return this.message_id;
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
     * Get Quick Reply Payload
     *
     * @return String the quick reply payload
     */
    public String getQuickReplyPayload()
    {
        return this.quick_reply_payload;
    }

    /**
     * Get Attachment
     *
     * @return Map the attachments
     */
    public Map<String, String> getAttachment()
    {
        return this.attachments;
    }
}