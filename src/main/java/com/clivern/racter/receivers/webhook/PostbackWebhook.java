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
 * Postback Webhook
 *
 * @since 1.0.0
 */
public class PostbackWebhook implements ReceiverTemplate {

    protected String user_id;

    protected String page_id;

    protected Long timestamp;

    protected Map<String, String> postback = new HashMap<String, String>();


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
     * Set Postback
     *
     * @param key the postback key
     * @param value the postback value
     */
    public void setPostback(String key, String value)
    {
        postback.put(key, value);
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
     * Check if Postback Exist
     *
     * @return Boolean whether has a postback
     */
    public Boolean hasPostback()
    {
        return !this.postback.isEmpty();
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
     * Get Postback
     *
     * @return Map the postback data
     */
    public Map<String, String> getPostback()
    {
        return this.postback;
    }
}