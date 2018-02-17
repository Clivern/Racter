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
package com.clivern.racter.contract.templates;

/**
 * Sender Template Interface
 *
 * @since 1.0.0
 */
public interface SenderTemplate {

    /**
     * Set Recipient ID
     *
     * @param recipient_id the recipient id
     */
    public void setRecipientId(String recipient_id);

    /**
     * Get Recipient ID
     *
     * @return String the recipient id
     */
    public String getRecipientId();

    /**
     * Build and get message as a string
     *
     * @return String the final message
     */
    public String build();

    /**
     * Set or override message
     *
     * @param message_string the final message
     */
    public void setMessageString(String message_string);

    /**
     * Get message as a string
     *
     * @return String the final message
     */
    public String getMessageString();
}