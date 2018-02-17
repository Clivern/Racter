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
 * Receiver Template Interface
 *
 * @since 1.0.0
 */
public interface ReceiverTemplate {

    /**
     * Set User ID
     *
     * @param user_id the user id
     */
    public void setUserId(String user_id);

    /**
     * Set Page ID or Receiver ID
     *
     * @param page_id the page id
     */
    public void setPageId(String page_id);

    /**
     * Check if User ID Exist
     *
     * @return Boolean whether has user id
     */
    public Boolean hasUserId();

    /**
     * Check if Page ID Exist
     *
     * @return Boolean whether has page id
     */
    public Boolean hasPageId();

    /**
     * Get User ID
     *
     * @return String the user id
     */
    public String getUserId();

    /**
     * Get Page ID
     *
     * @return String the page id
     */
    public String getPageId();
}