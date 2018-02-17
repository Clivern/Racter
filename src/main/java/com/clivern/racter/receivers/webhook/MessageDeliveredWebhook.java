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
import java.util.ArrayList;

/**
 * Message Delivered Webhook Class
 *
 * @since 1.0.0
 */
public class MessageDeliveredWebhook implements ReceiverTemplate {

    protected String user_id;

    protected String page_id;

    protected Long watermark;

    protected Integer seq;

    protected ArrayList<String> mids = new ArrayList<String>();


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
     * Set Watermark
     *
     * @param watermark the watermark
     */
    public void setWatermark(Long watermark)
    {
        this.watermark = watermark;
    }

    /**
     * Set Seq
     *
     * @param seq the seq value
     */
    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }

    /**
     * Set Mid
     *
     * @param mid the mid value
     */
    public void setMid(String mid)
    {
        this.mids.add(mid);
    }

    /**
     * Check if User ID Exist
     *
     * @return Boolean whether has user id
     */
    public Boolean hasUserId()
    {
        return (this.user_id != null);
    }

    /**
     * Check if Page ID Exist
     *
     * @return Boolean whether has page id
     */
    public Boolean hasPageId()
    {
        return (this.page_id != null);
    }

    /**
     * Check if Watermark Exist
     *
     * @return Boolean whether has watermark
     */
    public Boolean hasWatermark()
    {
        return (this.watermark != null);
    }

    /**
     * Check if Seq Exist
     *
     * @return Boolean whether has seq.
     */
    public Boolean hasSeq()
    {
        return (this.seq != null);
    }

    /**
     * Check if Mids Exist
     *
     * @return Boolean whether has Mids.
     */
    public Boolean hasMids()
    {
        return (this.mids.size() > 0);
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
     * Get Watermark
     *
     * @return Long the watermark
     */
    public Long getWatermark()
    {
        return this.watermark;
    }

    /**
     * Get Seq
     *
     * @return Integer the seq
     */
    public Integer getSeq()
    {
        return this.seq;
    }

    /**
     * Get Mids
     *
     * @return ArrayList a list of Mids.
     */
    public ArrayList<String> getMids()
    {
        return this.mids;
    }
}