/*
 * Copyright (C) 2019 Clivern <http://clivern.com>
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
package com.clivern.racter.senders;

import com.clivern.racter.senders.templates.*;
import com.clivern.racter.utils.Config;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.pmw.tinylog.Logger;

import java.io.IOException;

/**
 * Base Sender Class
 *
 * @since 1.0.0
 */
public class BaseSender {

    public static final String NOTIFICATION_TYPE_REGULAR = "REGULAR";
    public static final String NOTIFICATION_TYPE_SILENT_PUSH = "SILENT_PUSH";
    public static final String NOTIFICATION_TYPE_NO_PUSH = "NO_PUSH";

    public static final String SENDER_ACTION_TYPING_ON = "typing_on";
    public static final String SENDER_ACTION_TYPING_OFF = "typing_off";
    public static final String SENDER_ACTION_MARK_SEEN = "mark_seen";

    public static final String ATTACHMENT_TYPE_IMAGE = "image";
    public static final String ATTACHMENT_TYPE_AUDIO = "audio";
    public static final String ATTACHMENT_TYPE_VIDEO = "video";
    public static final String ATTACHMENT_TYPE_FILE = "file";
    public static final String ATTACHMENT_TYPE_TEMPLATE = "template";

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    protected String remote_url = "https://graph.facebook.com/v2.6/me/messages?access_token=";

    protected Config configs;

    private OkHttpClient client;

    /**
     * Class Constructor
     *
     * @param configs an instance of configs class
     * @param httpClient
     */
    public BaseSender(Config configs, OkHttpClient httpClient) {
        this.configs = configs;
        this.client = httpClient;
    }

    /**
     * Get Default Message Template Instance
     *
     * @return MessageTemplate a new instance of message template
     */
    public MessageTemplate getMessageTemplate() {
        return new MessageTemplate();
    }

    /**
     * Get Button Message Template Instance
     *
     * @return ButtonTemplate a new instance of button template
     */
    public ButtonTemplate getButtonTemplate() {
        return new ButtonTemplate();
    }

    /**
     * Get List Message Template Instance
     *
     * @return ListTemplate a new instance of list template
     */
    public ListTemplate getListTemplate() {
        return new ListTemplate();
    }

    /**
     * Get Generic Message Template Instance
     *
     * @return GenericTemplate a new instance of generic template
     */
    public GenericTemplate getGenericTemplate() {
        return new GenericTemplate();
    }

    /**
     * Get Receipt Message Template Instance
     *
     * @return ReceiptTemplate a new instance of receipt template
     */
    public ReceiptTemplate getReceiptTemplate() {
        return new ReceiptTemplate();
    }

    /**
     * Send Default Message
     *
     * @param template an instance of message template
     * @return Boolean whether the message sent or not
     * @throws IOException Throws exception in case it fails to perform the request
     */
    public Boolean send(MessageTemplate template) throws IOException {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        Logger.info(
                "curl -X POST -H \"Content-Type: application/json\" -d '"
                        + body
                        + "' \""
                        + url
                        + "\"");

        post(url, body);

        return true;
    }

    /**
     * Send Button Message
     *
     * @param template an instance of button template
     * @return Boolean whether the message sent or not
     * @throws IOException Throws exception in case it fails to perform the request
     */
    public Boolean send(ButtonTemplate template) throws IOException {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        Logger.info(
                "curl -X POST -H \"Content-Type: application/json\" -d '"
                        + body
                        + "' \""
                        + url
                        + "\"");

        post(url, body);

        return true;
    }

    /**
     * Send List Message
     *
     * @param template an instance of list template
     * @return Boolean whether the message sent or not
     * @throws IOException Throws exception in case it fails to perform the request
     */
    public Boolean send(ListTemplate template) throws IOException {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        Logger.info(
                "curl -X POST -H \"Content-Type: application/json\" -d '"
                        + body
                        + "' \""
                        + url
                        + "\"");

        post(url, body);

        return true;
    }

    /**
     * Send Generic Message
     *
     * @param template an instance of generic template
     * @return Boolean whether the message sent or not
     * @throws IOException Throws exception in case it fails to perform the request
     */
    public Boolean send(GenericTemplate template) throws IOException {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        Logger.info(
                "curl -X POST -H \"Content-Type: application/json\" -d '"
                        + body
                        + "' \""
                        + url
                        + "\"");

        post(url, body);

        return true;
    }

    /**
     * Send Receipt Message
     *
     * @param template an instance of receipt template
     * @return Boolean whether the message sent or not
     * @throws IOException Throws exception in case it fails to perform the request
     */
    public Boolean send(ReceiptTemplate template) throws IOException {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        String body = template.build();
        Logger.info(
                "curl -X POST -H \"Content-Type: application/json\" -d '"
                        + body
                        + "' \""
                        + url
                        + "\"");

        post(url, body);

        return true;
    }

    /**
     * Send Plain Message JSON
     *
     * @param body the message body
     * @return Boolean whether the message sent or not
     * @throws IOException Throws exception in case it fails to perform the request
     */
    public Boolean send(String body) throws IOException {
        String url = this.remote_url + this.configs.get("page_access_token", "");
        Logger.info(
                "curl -X POST -H \"Content-Type: application/json\" -d '"
                        + body
                        + "' \""
                        + url
                        + "\"");

        post(url, body);

        return true;
    }

    private void post(String url, String body) throws IOException {
        Request request =
                new Request.Builder().url(url).post(RequestBody.create(body, JSON)).build();

        client.newCall(request).execute();
    }
}
