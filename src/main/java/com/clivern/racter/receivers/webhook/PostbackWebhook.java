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
 */
public class PostbackWebhook implements ReceiverTemplate {

    /*
    {
        "sender":{
            "id":"USER_ID"
        },
        "recipient":{
            "id":"PAGE_ID"
        },
        "timestamp":1458692752478,
        "postback":{
            "payload": USER_DEFINED_PAYLOAD,
            "referral": {
                "ref": USER_DEFINED_REFERRAL_PARAM,
                "source": "SHORTLINK",
                "type": "OPEN_THREAD",
            }
        }
    }
    */

    protected String user_id;

    protected String page_id;

    protected Long timestamp;

    protected Map<String, String> postback = new HashMap<String, String>();


    /**
     * Set User ID
     *
     * @param user_id
     */
    public void setUserId(String user_id)
    {
        this.user_id = user_id;
    }

    /**
     * Set Page ID or Receiver ID
     *
     * @param page_id
     */
    public void setPageId(String page_id)
    {
        this.page_id = page_id;
    }

    /**
     * Set Timestamp
     *
     * @param timestamp
     */
    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * Set Postback
     *
     * @param key
     * @param value
     */
    public void setPostback(String key, String value)
    {
        postback.put(key, value);
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
     * Check if Postback Exist
     *
     * @return Boolean
     */
    public Boolean hasPostback()
    {
        return !this.postback.isEmpty();
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
     * Get Postback
     *
     * @return Map<String, String>
     */
    public Map<String, String> getPostback()
    {
        return this.postback;
    }
}