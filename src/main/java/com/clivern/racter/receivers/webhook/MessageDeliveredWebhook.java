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
 * Message Delivered Webhook Class
 */
public class MessageDeliveredWebhook {

    /*
    {
        "sender":{
            "id":"USER_ID"
        },
        "recipient":{
            "id":"PAGE_ID"
        },
        "delivery":{
            "mids":[
                "mid.1458668856218:ed81099e15d3f4f233"
            ],
            "watermark":1458668856253,
            "seq":37
        }
    }
    */

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
    protected Long watermark;

    /**
     * @var Integer
     */
    protected Integer seq;

    /**
     * @var ArrayList
     */
    protected ArrayList<String> mids = new ArrayList<String>();


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
     * Set Watermark
     *
     * @param watermark
     * @return void
     */
    public void setWatermark(Long watermark)
    {
        this.watermark = watermark;
    }

    /**
     * Set Seq
     *
     * @param seq
     * @return void
     */
    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }

    /**
     * Set Mid
     *
     * @param mid
     * @return void
     */
    public void setMid(String mid)
    {
        this.mids.add(mid);
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
     * Check if Watermark Exist
     *
     * @return Boolean
     */
    public Boolean hasWatermark()
    {
        return (this.watermark != null);
    }

    /**
     * Check if Seq Exist
     *
     * @return Boolean
     */
    public Boolean hasSeq()
    {
        return (this.seq != null);
    }

    /**
     * Check if Mids Exist
     *
     * @return Boolean
     */
    public Boolean hasMids()
    {
        return (this.mids.size() > 0);
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
     * Get Watermark
     *
     * @return Long
     */
    public Long getWatermark()
    {
        return this.watermark;
    }

    /**
     * Get Seq
     *
     * @return Integer
     */
    public Integer getSeq()
    {
        return this.seq;
    }

    /**
     * Get Mids
     *
     * @return ArrayList
     */
    public ArrayList<String> getMids()
    {
        return this.mids;
    }
}