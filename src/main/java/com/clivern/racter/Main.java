/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter;

import static spark.Spark.*;

import java.io.IOException;

import com.fasterxml.jackson.jr.ob.*;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException, UnirestException
    {
        get("/", (request, response) -> {
            return properties();
            //testRequests();
        });
    }

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

    public static String properties() throws IOException
    {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("src/main/java/resources/config.properties");

            // set the properties value
            prop.setProperty("app_id", "24367772873");
            prop.setProperty("page_access_token", "EAAEPZCEjpQGYkG9zZCND86AGzK9n8WqmnMysAjlY2XZCbEbqmuj93yoX3PyzORRH53JzAd7qAGVjMOU6bd08fZAgAnapJz8EBPN5pkpfRnjGul6mDf6FyYllZAvZCo8kYk7mgfGdobyCc8y4MkmdmOv3Nek");
            prop.setProperty("verify_token", "djhwndgjxt");

            // save properties to project root folder
            prop.store(output, null);

            return "Done";

        } catch (IOException io) {
            return "Not Done";
        }
    }
}
