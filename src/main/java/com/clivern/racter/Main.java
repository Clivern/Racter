/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import static spark.Spark.*;
import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.webhook.*;

import com.clivern.racter.senders.*;
import com.clivern.racter.senders.templates.*;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException
    {
        // Verify Token Route
        get("/", (request, response) -> {
            BotPlatform platform = BotPlatform.getInstance().loadConfigs("src/main/java/resources/config.properties").configDependencies();
            platform.getVerifyWebhook().setHubMode(( request.queryParams("hub.mode") != null ) ? request.queryParams("hub.mode") : "");
            platform.getVerifyWebhook().setHubVerifyToken(( request.queryParams("hub.verify_token") != null ) ? request.queryParams("hub.verify_token") : "");
            platform.getVerifyWebhook().setHubChallenge(( request.queryParams("hub.challenge") != null ) ? request.queryParams("hub.challenge") : "");

            if( platform.getVerifyWebhook().challenge() ){
                platform.finish();
                response.status(200);
                return ( request.queryParams("hub.challenge") != null ) ? request.queryParams("hub.challenge") : "";
            }

            platform.finish();
            response.status(403);
            return "Verification token mismatch";

        });

        // ---------------------------------
        // Test Case
        // curl -X POST -H "Content-Type: application/json" -d '{"object":"page","entry":[{"id":"pageid829292","time":1458692752478,"messaging":[{"sender":{"id":"userid83992"},"recipient":{"id":"pageid032"},"timestamp":1458692752478,"message":{"mid":"mid.1457764197618:41d102a3e1ae206a38","text":"hello, world!","attachments":[{"type":"image","payload":{"url":"http://clivern.com"}}]}}]}]}' "http://localhost:4567"
        // ---------------------------------
        post("/", (request, response) -> {
            String body = request.body();
            BotPlatform platform = BotPlatform.getInstance().loadConfigs("src/main/java/resources/config.properties").configDependencies();
            platform.getBaseReceiver().set(body).parse();
            HashMap<String, MessageReceivedWebhook> messages = (HashMap<String, MessageReceivedWebhook>) platform.getBaseReceiver().getMessages();
            for (MessageReceivedWebhook message : messages.values()) {

                String user_id = (message.hasUserId()) ? message.getUserId() : "";
                String page_id = (message.hasPageId()) ? message.getPageId() : "";
                String message_id = (message.hasMessageId()) ? message.getMessageId() : "";
                String message_text = (message.hasMessageText()) ? message.getMessageText() : "";
                String quick_reply_payload = (message.hasQuickReplyPayload()) ? message.getQuickReplyPayload() : "";
                Long timestamp = (message.hasTimestamp()) ? message.getTimestamp() : 0;
                Map<String, String> attachments = (message.hasAttachment()) ? message.getAttachment() : new HashMap<String, String>();

                String text = message.getMessageText();
                MessageTemplate message_tpl = BotPlatform.getInstance().getBaseSender().getMessageTemplate();
                if( text.equals("text") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Hello World");
                    message_tpl.setNotificationType("REGULAR");
                }else if( text.equals("image") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("image", "http://techslides.com/demos/samples/sample.jpg", false);
                    message_tpl.setNotificationType("SILENT_PUSH");
                }else if( text.equals("file") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("file", "http://techslides.com/demos/samples/sample.pdf", false);
                    message_tpl.setNotificationType("NO_PUSH");
                }else if( text.equals("video") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("video", "http://techslides.com/demos/samples/sample.mp4", false);
                }else if( text.equals("audio") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("audio", "http://techslides.com/demos/samples/sample.mp3", false);
                }else if( text.equals("mark_seen") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setSenderAction("mark_seen");
                }else if( text.equals("typing_on") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setSenderAction("typing_on");
                }else if( text.equals("typing_off") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setSenderAction("typing_off");
                }else if( text.equals("quick_text_reply") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Select a Color!");
                    message_tpl.setQuickReply("text", "Red", "text_reply_red_click", "");
                    message_tpl.setQuickReply("text", "Green", "text_reply_green_click", "");
                    message_tpl.setQuickReply("text", "Black", "text_reply_black_click", "");
                }else if( text.equals("quick_text_image_reply") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Select a Color!");
                    message_tpl.setQuickReply("text", "Red", "text_reply_red_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                    message_tpl.setQuickReply("text", "Green", "text_reply_green_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                    message_tpl.setQuickReply("text", "Black", "text_reply_black_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                }else if( text.equals("quick_location_reply") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Please share your location!");
                    message_tpl.setQuickReply("location", "", "", "");
                }

                if( quick_reply_payload.equals("text_reply_red_click") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Red Clicked");
                }else if( quick_reply_payload.equals("text_reply_green_click") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Green Clicked");
                }else if( quick_reply_payload.equals("text_reply_black_click") ){
                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Black Clicked");
                }

                BotPlatform.getInstance().getBaseSender().sendMessageTemplate(message_tpl);

                BotPlatform.getInstance().getLogger().info(message.getUserId());
                BotPlatform.getInstance().getLogger().info(message.getMessageText());

                return "ok";
            }
            return "bla";
        });
    }
}