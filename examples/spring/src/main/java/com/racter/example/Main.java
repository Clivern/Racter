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
package com.racter.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.webhook.*;

import com.clivern.racter.senders.*;
import com.clivern.racter.senders.templates.*;

import org.pmw.tinylog.Logger;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@EnableAutoConfiguration
public class Main {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    String verifyToken(@RequestParam(value="hub.mode", defaultValue="") String hub_mode, @RequestParam(value="hub.verify_token", defaultValue="") String hub_verify_token, @RequestParam(value="hub.challenge", defaultValue="") String hub_challenge ) throws IOException {

        BotPlatform platform = new BotPlatform("src/main/java/resources/config.properties");
        platform.getVerifyWebhook().setHubMode(hub_mode);
        platform.getVerifyWebhook().setHubVerifyToken(hub_verify_token);
        platform.getVerifyWebhook().setHubChallenge(hub_challenge);

        if( platform.getVerifyWebhook().challenge() ){
            return ( hub_challenge != "" ) ? hub_challenge : "";
        }

        return "Verification token mismatch";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ResponseBody
    String webHook(@RequestBody String body) throws IOException, UnirestException {
        BotPlatform platform = new BotPlatform("src/main/java/resources/config.properties");
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

            Logger.info("User ID#:" + user_id);
            Logger.info("Page ID#:" + page_id);
            Logger.info("Message ID#:" + message_id);
            Logger.info("Message Text#:" + message_text);
            Logger.info("Quick Reply Payload#:" + quick_reply_payload);

            for (String attachment : attachments.values()) {
                Logger.info("Attachment#:" + attachment);
            }

            String text = message.getMessageText();
            MessageTemplate message_tpl = platform.getBaseSender().getMessageTemplate();
            ButtonTemplate button_message_tpl = platform.getBaseSender().getButtonTemplate();
            ListTemplate list_message_tpl = platform.getBaseSender().getListTemplate();
            GenericTemplate generic_message_tpl = platform.getBaseSender().getGenericTemplate();
            ReceiptTemplate receipt_message_tpl = platform.getBaseSender().getReceiptTemplate();

            if( text.equals("text") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Hello World");
                message_tpl.setNotificationType("REGULAR");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("image") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setAttachment("image", "http://techslides.com/demos/samples/sample.jpg", false);
                message_tpl.setNotificationType("SILENT_PUSH");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("file") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setAttachment("file", "http://techslides.com/demos/samples/sample.pdf", false);
                message_tpl.setNotificationType("NO_PUSH");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("video") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setAttachment("video", "http://techslides.com/demos/samples/sample.mp4", false);
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("audio") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setAttachment("audio", "http://techslides.com/demos/samples/sample.mp3", false);
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("mark_seen") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setSenderAction("mark_seen");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("typing_on") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setSenderAction("typing_on");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("typing_off") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setSenderAction("typing_off");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("quick_text_reply") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Select a Color!");
                message_tpl.setQuickReply("text", "Red", "text_reply_red_click", "");
                message_tpl.setQuickReply("text", "Green", "text_reply_green_click", "");
                message_tpl.setQuickReply("text", "Black", "text_reply_black_click", "");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("quick_text_image_reply") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Select a Color!");
                message_tpl.setQuickReply("text", "Red", "text_reply_red_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                message_tpl.setQuickReply("text", "Green", "text_reply_green_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                message_tpl.setQuickReply("text", "Black", "text_reply_black_click", "http://static.wixstatic.com/media/f0a6df_9ae4c70963244e16ba0d89d021407335.png");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("quick_location_reply") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Please share your location!");
                message_tpl.setQuickReply("location", "", "", "");
                platform.getBaseSender().send(message_tpl);

            }else if( text.equals("web_url_button") ){

                button_message_tpl.setRecipientId(message.getUserId());
                button_message_tpl.setMessageText("Click Below!");
                button_message_tpl.setButton("web_url", "Take the Hat Quiz", "https://m.me/petershats?ref=take_quiz", "");
                platform.getBaseSender().send(button_message_tpl);

            }else if( text.equals("postback_button") ){

                button_message_tpl.setRecipientId(message.getUserId());
                button_message_tpl.setMessageText("Click Below!");
                button_message_tpl.setButton("postback", "Bookmark Item", "", "DEVELOPER_DEFINED_PAYLOAD");
                platform.getBaseSender().send(button_message_tpl);

            }else if( text.equals("phone_number_button") ){

                button_message_tpl.setRecipientId(message.getUserId());
                button_message_tpl.setMessageText("Click Below!");
                button_message_tpl.setButton("phone_number", "Call Representative", "", "+15105551234");
                platform.getBaseSender().send(button_message_tpl);

            }else if( text.equals("account_link_button") ){

                button_message_tpl.setRecipientId(message.getUserId());
                button_message_tpl.setMessageText("Click Below!");
                button_message_tpl.setButton("account_link", "", "https://www.example.com/authorize", "");
                platform.getBaseSender().send(button_message_tpl);

            }else if( text.equals("account_unlink_button") ){

                button_message_tpl.setRecipientId(message.getUserId());
                button_message_tpl.setMessageText("Click Below!");
                button_message_tpl.setButton("account_unlink", "", "", "");
                platform.getBaseSender().send(button_message_tpl);

            }else if( text.equals("list_template") ){

                list_message_tpl.setRecipientId(message.getUserId());
                list_message_tpl.setElementStyle("compact");

                // Element
                Integer element_index = list_message_tpl.setElement("Classic T-Shirt Collection", "https://peterssendreceiveapp.ngrok.io/img/collection.png", "See all our colors");
                list_message_tpl.setElementDefaultAction(element_index, "web_url", "https://peterssendreceiveapp.ngrok.io/view?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");
                list_message_tpl.setElementButton(element_index, "Shop Now", "web_url", "https://peterssendreceiveapp.ngrok.io/shop?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");

                // Element
                element_index = list_message_tpl.setElement("Classic T-Shirt Collection", "https://peterssendreceiveapp.ngrok.io/img/collection.png", "See all our colors");
                list_message_tpl.setElementDefaultAction(element_index, "web_url", "https://peterssendreceiveapp.ngrok.io/view?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");
                list_message_tpl.setElementButton(element_index, "Shop Now", "web_url", "https://peterssendreceiveapp.ngrok.io/shop?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");

                // Set Button
                list_message_tpl.setButton("postback", "View More", "", "payload");
                platform.getBaseSender().send(list_message_tpl);

            }else if( text.equals("generic_template") ){

                generic_message_tpl.setRecipientId(message.getUserId());

                // Element
                Integer element_index = generic_message_tpl.setElement("Classic T-Shirt Collection", "https://peterssendreceiveapp.ngrok.io/img/collection.png", "See all our colors");
                generic_message_tpl.setElementDefaultAction(element_index, "web_url", "https://peterssendreceiveapp.ngrok.io/view?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");
                generic_message_tpl.setElementButton(element_index, "Shop Now", "web_url", "https://peterssendreceiveapp.ngrok.io/shop?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");

                // Element
                element_index = generic_message_tpl.setElement("Classic T-Shirt Collection", "https://peterssendreceiveapp.ngrok.io/img/collection.png", "See all our colors");
                generic_message_tpl.setElementDefaultAction(element_index, "web_url", "https://peterssendreceiveapp.ngrok.io/view?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");
                generic_message_tpl.setElementButton(element_index, "Shop Now", "web_url", "https://peterssendreceiveapp.ngrok.io/shop?item=102", true, "tall", "https://peterssendreceiveapp.ngrok.io/");

                platform.getBaseSender().send(generic_message_tpl);

            }else if( text.equals("receipt_template") ){

                receipt_message_tpl.setRecipientId(message.getUserId());
                receipt_message_tpl.setRecipientName("Stephane Crozatier");
                receipt_message_tpl.setOrderNumber("12345678902");
                receipt_message_tpl.setCurrency("USD");
                receipt_message_tpl.setPaymentMethod("Visa 2345");
                receipt_message_tpl.setOrderUrl("http://petersapparel.parseapp.com/order?order_id=123456");
                receipt_message_tpl.setTimestamp("1428444852");
                receipt_message_tpl.setElement("Classic White T-Shirt", "100% Soft and Luxurious Cotton", "2", "50", "USD", "https://image.spreadshirtmedia.com/image-server/v1/products/1001491830/views/1,width=800,height=800,appearanceId=2,version=1473664654/black-rap-nation-t-shirt-men-s-premium-t-shirt.png");
                receipt_message_tpl.setElement("Classic Gray T-Shirt", "100% Soft and Luxurious Cotton", "2", "50", "USD", "https://static1.squarespace.com/static/57a088e05016e13b82b0beac/t/584fe89720099e4b5211c624/1481631899763/darts-is-my-religion-ally-pally-is-my-church-t-shirt-maenner-maenner-t-shirt.png");
                receipt_message_tpl.setAddress("1 Hacker Way", "", "Menlo Park", "94025", "CA", "US");
                receipt_message_tpl.setSummary("75.00", "4.95", "6.19", "56.14");
                receipt_message_tpl.setAdjustment("New Customer Discount", "20");
                receipt_message_tpl.setAdjustment("$10 Off Coupon", "10");
                platform.getBaseSender().send(receipt_message_tpl);

            }


            if( quick_reply_payload.equals("text_reply_red_click") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Red Clicked");
                platform.getBaseSender().send(message_tpl);

            }else if( quick_reply_payload.equals("text_reply_green_click") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Green Clicked");
                platform.getBaseSender().send(message_tpl);

            }else if( quick_reply_payload.equals("text_reply_black_click") ){

                message_tpl.setRecipientId(message.getUserId());
                message_tpl.setMessageText("Black Clicked");
                platform.getBaseSender().send(message_tpl);

            }

            return "ok";
        }
        return "bla";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}