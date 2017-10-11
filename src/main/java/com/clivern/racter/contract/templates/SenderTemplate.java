/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.contract.templates;


/**
 * Sender Template Interface
 */
public interface SenderTemplate {

    /**
     * Set Recipient ID
     */
    public void setRecipientId(String recipient_id);

    /**
     * Get Recipient ID
     *
     * @return String
     */
    public String getRecipientId();

    /**
     * Build and get message as a string
     *
     * @return String
     */
    public String build();

    /**
     * Set or override message
     *
     * @param message_string
     */
    public void setMessageString(String message_string);

    /**
     * Get message as a string
     *
     * @return String
     */
    public String getMessageString();
}