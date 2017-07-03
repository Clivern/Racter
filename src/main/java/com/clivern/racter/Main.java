/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import static spark.Spark.*;
import com.clivern.racter.BotPlatform;

/*import java.io.IOException;

import com.fasterxml.jackson.jr.ob.*;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;*/


public class Main {

    public static void main(String[] args)
    {
        get("/", (request, response) -> {
            BotPlatform platform = BotPlatform.getInstance().configDependencies().loadConfigs("src/main/java/resources/config.properties");
            platform.getVerifyWebhook().setHubMode(( request.queryParams("hub.mode") != null ) ? request.queryParams("hub.mode") : "");
            platform.getVerifyWebhook().setHubVerifyToken(( request.queryParams("hub.verify_token") != null ) ? request.queryParams("hub.verify_token") : "");
            platform.getVerifyWebhook().setHubChallenge(( request.queryParams("hub.challenge") != null ) ? request.queryParams("hub.challenge") : "");

            if( platform.getVerifyWebhook().challenge() ){

                platform.finish();

                return "Sucess";
            }

            platform.finish();
            return "Failure";
        });
        post("/", (request, response) -> {
            return "Viola!";
        });
    }


/*
    public static String testJsonBuilder() throws IOException
    {

        String json = JSON.std
            .with(JSON.Feature.PRETTY_PRINT_OUTPUT)
            .composeString()
            .startObject()
                .startObjectField("recipient")
                    .put("id", "USER_ID")
                .end()
                .startObjectField("message")
                    .startObjectField("attachment")
                        .put("type", "template")
                        .startObjectField("payload")
                            .put("template_type", "button")
                            .put("text", "What do you want to do next?")
                            .startArrayField("buttons")
                          .startObject()
                              .put("type", "web_url")
                              .put("url", "https://petersapparel.parseapp.com")
                              .put("title", "Show Website")
                          .end()
                          .startObject()
                              .put("type", "postback")
                              .put("title", "Start Chatting")
                              .put("payload", "USER_DEFINED_PAYLOAD")
                          .end()
                            .end()
                        .end()
                    .end()
                .end()
            .end()
            .finish();
        return json;
    }

    public static String testRequests() throws UnirestException {

        HttpResponse<String> response = Unirest.post("http://mockbin.com/request?foo=bar&foo=baz")
            .header("cookie", "foo=bar; bar=baz")
            .header("accept", "application/json")
            .header("content-type", "application/json")
            .header("x-pretty-print", "2")
            .body("{\"foo\": \"bar\"}")
            .asString();

        return response.getStatusText();
    }
*/
}