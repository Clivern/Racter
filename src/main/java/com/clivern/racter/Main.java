package com.clivern.racter;

import java.io.IOException;

import com.fasterxml.jackson.jr.ob.*;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;


public class Main {
    public static void main(String[] args) throws IOException, UnirestException
    {
        testJsonBuilder();
        testRequests();
    }

    public static void testJsonBuilder() throws IOException
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
        System.out.println(json);
    }

    public static void testRequests() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://mockbin.com/request?foo=bar&foo=baz")
          .header("cookie", "foo=bar; bar=baz")
          .header("accept", "application/json")
          .header("content-type", "application/json")
          .header("x-pretty-print", "2")
          .body("{\"foo\": \"bar\"}")
          .asString();

        System.out.println(response.getStatusText());
    }
}
