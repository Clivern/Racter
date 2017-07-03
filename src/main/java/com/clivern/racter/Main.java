/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import static spark.Spark.*;
import com.clivern.racter.BotPlatform;

/*import org.json.JSONObject;
import org.json.JSONArray;

import com.fasterxml.jackson.jr.ob.*;
*/
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
                response.status(200);
                return ( request.queryParams("hub.challenge") != null ) ? request.queryParams("hub.challenge") : "";
            }

            platform.finish();
            response.status(403);
            return "Verification token mismatch";
        });
        post("/", (request, response) -> {
            String body = request.body();
            return body;
        });
    }
}