/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.contract.templates;


/**
 * Receiver Template Interface
 */
public interface ReceiverTemplate {

    /**
     * Set User ID
     *
     * @param user_id
     */
    public void setUserId(String user_id);

    /**
     * Set Page ID or Receiver ID
     *
     * @param page_id
     */
    public void setPageId(String page_id);

    /**
     * Check if User ID Exist
     *
     * @return Boolean
     */
    public Boolean hasUserId();

    /**
     * Check if Page ID Exist
     *
     * @return Boolean
     */
    public Boolean hasPageId();

    /**
     * Get User ID
     *
     * @return String
     */
    public String getUserId();

    /**
     * Get Page ID
     *
     * @return String
     */
    public String getPageId();
}