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
package com.clivern.racter.receivers;

import com.clivern.racter.receivers.webhook.*;
import java.util.HashMap;
import java.util.Map;
import com.clivern.racter.utils.Config;
import org.pmw.tinylog.Logger;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Base Receiver Class
 *
 * @since 1.0.0
 */
public class BaseReceiver {

    protected String message_string;

    protected JSONObject message_object;

    protected Map<String, MessageReceivedWebhook> message_received_webhook = new HashMap<String, MessageReceivedWebhook>();

    protected Map<String, MessageDeliveredWebhook> message_delivered_webhook = new HashMap<String, MessageDeliveredWebhook>();

    protected Map<String, MessageEchoWebhook> message_echo_webhook = new HashMap<String, MessageEchoWebhook>();

    protected Map<String, MessageReadWebhook> message_read_webhook = new HashMap<String, MessageReadWebhook>();

    protected Map<String, PostbackWebhook> postback_webhook = new HashMap<String, PostbackWebhook>();

    protected Config configs;


    /**
     * Class Constructor
     *
     * @param configs an instance of configs class
     */
    public BaseReceiver(Config configs)
    {
        this.configs = configs;
    }

    /**
     * Set Incoming Message
     *
     * @param  message_string the incoming message string
     * @return BaseReceiver an instance of BaseReceiver class
     */
    public BaseReceiver set(String message_string)
    {
        this.message_string = message_string;
        this.message_object = new JSONObject(message_string);

        return this;
    }

    /**
     * Parse Incoming Message
     *
     * @return BaseReceiver an instance of BaseReceiver class
     */
    public BaseReceiver parse()
    {
        int i;
        int z = 1;

        if( !this.message_object.has("object")  || !this.message_object.getString("object").equals("page") ){
            return this;
        }
        if( !this.message_object.has("entry") ){
            return this;
        }

        JSONArray entry = this.message_object.getJSONArray("entry");

        for ( i = 0; i < entry.length(); i++ ) {

            JSONObject entry_obj = entry.getJSONObject(i);

            if( entry_obj.has("id") ){
                String page_id = entry_obj.getString("id");
            }

            if( entry_obj.has("time") ){
                Long time =  entry_obj.getLong("time");
            }

            if( entry_obj.has("messaging") ){

                for ( i = 0; i < entry_obj.getJSONArray("messaging").length(); i++ ) {

                    JSONObject messaging_item = entry_obj.getJSONArray("messaging").getJSONObject(i);

                    //-----------------
                    // Get Sender ID
                    //-----------------
                    String sender_id = null;
                    if( messaging_item.has("sender") ){
                        JSONObject sender = messaging_item.getJSONObject("sender");
                        sender_id = (sender.has("id")) ? sender.getString("id") : null;
                    }

                    //-----------------------------
                    // Get Recipient ID or Page ID
                    //-----------------------------
                    String recipient_id = null;
                    if( messaging_item.has("recipient") ){
                        JSONObject recipient = messaging_item.getJSONObject("recipient");
                        recipient_id = (recipient.has("id")) ? recipient.getString("id") : null;
                    }

                    //-----------------
                    // Get Timestamp
                    //-----------------
                    Long timestamp = null;
                    if( messaging_item.has("timestamp") ){
                        timestamp = messaging_item.getLong("timestamp");
                    }

                    //-----------------
                    // Incoming Message
                    //-----------------
                    if( messaging_item.has("message") ){
                        JSONObject message = messaging_item.getJSONObject("message");

                        if( message.has("is_echo") ){
                            //-----------------
                            // Message Echo
                            //-----------------

                        }else{

                            z += 1;

                            //-----------------
                            // Message
                            //-----------------
                            this.message_received_webhook.put("message." + z, new MessageReceivedWebhook());
                            this.message_received_webhook.get("message." + z).setUserId(sender_id);
                            this.message_received_webhook.get("message." + z).setPageId(recipient_id);
                            this.message_received_webhook.get("message." + z).setTimestamp(timestamp);

                            String mid = (message.has("mid")) ? message.getString("mid") : null;
                            this.message_received_webhook.get("message." + z).setMessageId(mid);

                            if ( message.has("text") ){
                                String text = message.getString("text");
                                this.message_received_webhook.get("message." + z).setMessageText(text);
                            }

                            if ( message.has("quick_reply") ){
                                JSONObject quick_reply = message.getJSONObject("quick_reply");
                                String payload = (quick_reply.has("payload")) ? quick_reply.getString("payload") : null;
                                this.message_received_webhook.get("message." + z).setQuickReplyPayload(payload);
                            }

                            if ( message.has("attachments") ){
                                JSONArray attachments = message.getJSONArray("attachments");

                                for ( i = 0; i < attachments.length(); i++ ) {

                                    JSONObject attachment = attachments.getJSONObject(i);

                                    String type = (attachment.has("type")) ? attachment.getString("type") : null;

                                    if( type.equals("audio") ){
                                        if( attachment.has("payload") ){
                                            JSONObject attachment_payload = attachment.getJSONObject("payload");
                                            String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;

                                            this.message_received_webhook.get("message." + z).setAttachment("audio", url);
                                        }

                                    /////////////////////////////////////////////////////////////////////////////////////////////
                                    // Unknown right now
                                    // }else if( type.equals("fallback") ){
                                    /////////////////////////////////////////////////////////////////////////////////////////////

                                    }else if( type.equals("file") ){

                                        if( attachment.has("payload") ){
                                            JSONObject attachment_payload = attachment.getJSONObject("payload");
                                            String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;
                                            this.message_received_webhook.get("message." + z).setAttachment("file", url);
                                        }

                                    }else if( type.equals("image") ){

                                        if( attachment.has("payload") ){
                                            JSONObject attachment_payload = attachment.getJSONObject("payload");
                                            String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;
                                            this.message_received_webhook.get("message." + z).setAttachment("image", url);
                                        }

                                    }else if( type.equals("video") ){

                                        if( attachment.has("payload") ){
                                            JSONObject attachment_payload = attachment.getJSONObject("payload");
                                            String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;
                                            this.message_received_webhook.get("message." + z).setAttachment("video", url);
                                        }

                                    }else if( type.equals("location") ){

                                        if( attachment.has("payload") ){
                                            JSONObject attachment_payload = attachment.getJSONObject("payload");
                                            Long coordinates_lat = (attachment_payload.has("coordinates.lat")) ? attachment_payload.getLong("coordinates.lat") : null;
                                            Long coordinates_long = (attachment_payload.has("coordinates.long")) ? attachment_payload.getLong("coordinates.long") : null;
                                            this.message_received_webhook.get("message." + z).setAttachment("location", coordinates_lat, coordinates_long);
                                        }

                                    }
                                }
                            }

                        }
                    }

                    //-----------------
                    // Message Delivery
                    //-----------------
                    if( messaging_item.has("delivery") ){
                        JSONObject delivery = messaging_item.getJSONObject("delivery");

                        z += 1;

                        this.message_delivered_webhook.put("message." + z, new MessageDeliveredWebhook());
                        this.message_delivered_webhook.get("message." + z).setUserId(sender_id);
                        this.message_delivered_webhook.get("message." + z).setPageId(recipient_id);
                        if( delivery.has("watermark") ){
                            this.message_delivered_webhook.get("message." + z).setWatermark(delivery.getLong("watermark"));
                        }
                        if( delivery.has("seq") ){
                            this.message_delivered_webhook.get("message." + z).setSeq(delivery.getInt("seq"));
                        }

                        //this.message_delivered_webhook.get("message." + z).setMid();

                    }

                    //-------------
                    // Message Read
                    //-------------
                    if( messaging_item.has("read") ){
                        JSONObject read = messaging_item.getJSONObject("read");

                        z += 1;

                        this.message_read_webhook.put("message." + z, new MessageReadWebhook());
                        this.message_read_webhook.get("message." + z).setUserId(sender_id);
                        this.message_read_webhook.get("message." + z).setPageId(recipient_id);
                        this.message_read_webhook.get("message." + z).setTimestamp(timestamp);
                        if( read.has("watermark") ){
                            this.message_read_webhook.get("message." + z).setWatermark(read.getLong("watermark"));
                        }
                        if( read.has("seq") ){
                            this.message_read_webhook.get("message." + z).setSeq(read.getInt("seq"));
                        }
                    }

                    //----------
                    // Post Back
                    //----------
                    if( messaging_item.has("postback") ){
                        JSONObject postback = messaging_item.getJSONObject("postback");

                        z += 1;

                        this.postback_webhook.put("message." + z, new PostbackWebhook());
                        this.postback_webhook.get("message." + z).setUserId(sender_id);
                        this.postback_webhook.get("message." + z).setPageId(recipient_id);
                        this.postback_webhook.get("message." + z).setTimestamp(timestamp);

                        //this.postback_webhook.get("message." + z).setPostback();

                    }
                }
            }
        }

        return this;
    }

    /**
     * Get parsed message
     *
     * @return Map a list of message received webhook
     */
    public Map<String, MessageReceivedWebhook> getMessages()
    {
        return this.message_received_webhook;
    }

    /**
     * Get delivered data
     *
     * @return Map a list message delivered webhook
     */
    public Map<String, MessageDeliveredWebhook> getDelivered()
    {
        return this.message_delivered_webhook;
    }

    /**
     * Get echo data
     *
     * @return Map a list of message echo webhook
     */
    public Map<String, MessageEchoWebhook> getEcho()
    {
        return this.message_echo_webhook;
    }

    /**
     * Get read data
     *
     * @return Map a list of message read webhook
     */
    public Map<String, MessageReadWebhook> getRead()
    {
        return this.message_read_webhook;
    }

    /**
     * Get postback data
     *
     * @return Map a list of post back webhook
     */
    public Map<String, PostbackWebhook> getPostback()
    {
        return this.postback_webhook;
    }

    /**
     * Get message as a string
     *
     * @return String the final message string
     */
    public String getMessageString()
    {
        return this.message_string;
    }

    /**
     * Get message as an object
     *
     * @return JSONObject the final message object
     */
    public JSONObject getMessageObject()
    {
        return this.message_object;
    }
}