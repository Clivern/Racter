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
                HashMap<String, String> attachments = (message.hasAttachment()) ? (HashMap<String, String>) message.getAttachment() : new HashMap<String, String>();

                BotPlatform.getInstance().getLogger().info("User ID#:" + user_id);
                BotPlatform.getInstance().getLogger().info("Page ID#:" + page_id);
                BotPlatform.getInstance().getLogger().info("Message ID#:" + message_id);
                BotPlatform.getInstance().getLogger().info("Message Text#:" + message_text);
                BotPlatform.getInstance().getLogger().info("Quick Reply Payload#:" + quick_reply_payload);

                for (String attachment : attachments.values()) {
                    BotPlatform.getInstance().getLogger().info("Attachment#:" + attachment);
                }

                String text = message.getMessageText();
                MessageTemplate message_tpl = BotPlatform.getInstance().getBaseSender().getMessageTemplate();
                ButtonTemplate button_message_tpl = BotPlatform.getInstance().getBaseSender().getButtonTemplate();

                if( text.equals("text") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Hello World");
                    message_tpl.setNotificationType("REGULAR");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("image") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("image", "http://techslides.com/demos/samples/sample.jpg", false);
                    message_tpl.setNotificationType("SILENT_PUSH");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("file") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("file", "http://techslides.com/demos/samples/sample.pdf", false);
                    message_tpl.setNotificationType("NO_PUSH");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("video") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("video", "http://techslides.com/demos/samples/sample.mp4", false);
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("audio") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("audio", "http://techslides.com/demos/samples/sample.mp3", false);
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("mark_seen") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setSenderAction("mark_seen");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("typing_on") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setSenderAction("typing_on");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("typing_off") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setSenderAction("typing_off");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("quick_text_reply") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Select a Color!");
                    message_tpl.setQuickReply("text", "Red", "text_reply_red_click", "");
                    message_tpl.setQuickReply("text", "Green", "text_reply_green_click", "");
                    message_tpl.setQuickReply("text", "Black", "text_reply_black_click", "");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("quick_text_image_reply") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Select a Color!");
                    message_tpl.setQuickReply("text", "Red", "text_reply_red_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                    message_tpl.setQuickReply("text", "Green", "text_reply_green_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                    message_tpl.setQuickReply("text", "Black", "text_reply_black_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("quick_location_reply") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Please share your location!");
                    message_tpl.setQuickReply("location", "", "", "");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( text.equals("web_url_button") ){

                    button_message_tpl.setRecipientId(message.getUserId());
                    button_message_tpl.setMessageText("Click Below!");
                    button_message_tpl.setButton("web_url", "Take the Hat Quiz", "https://m.me/petershats?ref=take_quiz", "");
                    BotPlatform.getInstance().getBaseSender().send(button_message_tpl);

                }else if( text.equals("postback_button") ){

                    button_message_tpl.setRecipientId(message.getUserId());
                    button_message_tpl.setMessageText("Click Below!");
                    button_message_tpl.setButton("postback", "Bookmark Item", "", "DEVELOPER_DEFINED_PAYLOAD");
                    BotPlatform.getInstance().getBaseSender().send(button_message_tpl);

                }else if( text.equals("phone_number_button") ){

                    button_message_tpl.setRecipientId(message.getUserId());
                    button_message_tpl.setMessageText("Click Below!");
                    button_message_tpl.setButton("phone_number", "Call Representative", "", "+15105551234");
                    BotPlatform.getInstance().getBaseSender().send(button_message_tpl);

                }else if( text.equals("element_share_button") ){

                    button_message_tpl.setRecipientId(message.getUserId());
                    button_message_tpl.setMessageText("Click Below!");
                    button_message_tpl.setButton("element_share", "", "", "");
                    BotPlatform.getInstance().getBaseSender().send(button_message_tpl);

                }else if( text.equals("account_link_button") ){

                    button_message_tpl.setRecipientId(message.getUserId());
                    button_message_tpl.setMessageText("Click Below!");
                    button_message_tpl.setButton("account_link", "", "https://www.example.com/authorize", "");
                    BotPlatform.getInstance().getBaseSender().send(button_message_tpl);

                }else if( text.equals("account_unlink_button") ){

                    button_message_tpl.setRecipientId(message.getUserId());
                    button_message_tpl.setMessageText("Click Below!");
                    button_message_tpl.setButton("account_unlink", "", "", "");
                    BotPlatform.getInstance().getBaseSender().send(button_message_tpl);

                }

                if( quick_reply_payload.equals("text_reply_red_click") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Red Clicked");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( quick_reply_payload.equals("text_reply_green_click") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Green Clicked");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }else if( quick_reply_payload.equals("text_reply_black_click") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Black Clicked");
                    BotPlatform.getInstance().getBaseSender().send(message_tpl);

                }

                return "ok";
            }
            return "bla";
        });
    }
}