package com.clivern.racter;

import java.io.IOException;
import com.fasterxml.jackson.jr.ob.*;


public class Main {
    public static void main(String[] args) throws IOException
    {
        testJsonBuilder();
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
}
