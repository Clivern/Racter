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
                //return message.getMessageText();
                MessageTemplate message_tpl = BotPlatform.getInstance().getBaseSender().getMessageTemplate();
                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText(message.getMessageText());
                BotPlatform.getInstance().getBaseSender().sendMessageTemplate(message_tpl);
                BotPlatform.getInstance().getLogger().info(message.getUserId());
                BotPlatform.getInstance().getLogger().info(message.getMessageText());

                return "ok";
            }
            return "bla";
        });
    }
}