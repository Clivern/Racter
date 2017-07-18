Racter
=========

Racter is A Java Framework for Building Bots on Facebook's Messenger Platform.

*Under Development*

[![Build Status](https://travis-ci.org/Clivern/Racter.svg?branch=master)](https://travis-ci.org/Clivern/Racter)

Installation
------------
To add a dependency using Maven, use the following:
```xml
<dependency>
  <groupId>com.clivern</groupId>
  <artifactId>racter</artifactId>
  <version>1.0.0</version>
</dependency>
```

To add a dependency using Gradle, use the following:
```java
dependencies {
  compile 'com.clivern:racter:1.0.0'
}
```

Usage
-----
After adding the package as a dependency, Please read the following steps:

### Basic Configurations
In order to cofigure the package create `config.properties` file with the following data

```
app_id=App ID Goes Here
verify_token=Verify Token Goes Here
page_access_token=Page Access Token Goes Here
log_console_status=true or false
log_console_level=ALL, CONFIG, FINE, FINER, FINEST, INFO, SEVERE, WARNING or OFF
log_file_status=true or false
log_file_level=ALL, CONFIG, FINE, FINER, FINEST, INFO, SEVERE, WARNING or OFF
log_file_path=app.log
log_file_limit=1
log_file_count=200000
log_file_append=true or false
```

Then import all required classes

```java
import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.*;
import com.clivern.racter.receivers.webhook.*;

import com.clivern.racter.senders.*;
import com.clivern.racter.senders.templates.*;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
```

then pass the `config.properties` file to the bot platform instance

```java
BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();
```

or Configure it manually
```java
Map<String, String> options = new HashMap<String, String>();

options.put("app_id", "App ID Goes Here");
options.put("verify_token", "Verify Token Goes Here");
options.put("page_access_token", "Page Access Token Goes Here");
options.put("log_console_status", "true or false");
options.put("log_console_level", "ALL, CONFIG, FINE, FINER, FINEST, INFO, SEVERE, WARNING or OFF");
options.put("log_file_status", "true or false");
options.put("log_file_level", "ALL, CONFIG, FINE, FINER, FINEST, INFO, SEVERE, WARNING or OFF");
options.put("log_file_path", "app.log");
options.put("log_file_limit", "1");
options.put("log_file_count", "200000");
options.put("log_file_append", "true or false");

BotPlatform platform = BotPlatform.getInstance().loadConfigs(options).configDependencies();
```

Setup Webhook
-------------
Create a route to verify your verify token, Facebook will perform a GET request to this route URL with some URL parameters to make sure that verify token is correct.

```java
BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();

String hubMode = // Get hub.mode query parameter value from the current URL
String hubVerifyToken = // Get hub.verify_token query parameter value from the current URL
String hubChallenge = // Get hub.challenge query parameter value from the current URL


platform.getVerifyWebhook().setHubMode(hubMode);
platform.getVerifyWebhook().setHubVerifyToken(hubVerifyToken);
platform.getVerifyWebhook().setHubChallenge(hubChallenge);

if( platform.getVerifyWebhook().challenge() ){
    platform.finish();

    // Set Response to be hubChallenge value and status code is 200 like
    // response.status(200);
    // return ( hubChallenge != null ) ? hubChallenge : "";
}

platform.finish();

// Set Response to be 'Verification token mismatch' and status code is 403 like
// response.status(403);
// return "Verification token mismatch";
```

So let's say we use [Spark Java Framework](http://sparkjava.com/) for our bot, Our route and callback will look like the following:

```java
import static spark.Spark.*;
import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.*;
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
            BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();
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
    }
}
```

Message Received
----------------
In order to receive and parse messages, You will need to create another route that receives post requests from Facebook. Our Route should contain a code look like the following:

```java
String body = // Get current Request Body
BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();
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

}
```

So let's say we use [Spark Java Framework](http://sparkjava.com/) for our bot, Our route should look like the following:

```java
import static spark.Spark.*;
import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.*;
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
            BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();
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
            BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();
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

                // Use Logger To Log Incoming Data
                BotPlatform.getInstance().getLogger().info("User ID#:" + user_id);
                BotPlatform.getInstance().getLogger().info("Page ID#:" + page_id);
                BotPlatform.getInstance().getLogger().info("Message ID#:" + message_id);
                BotPlatform.getInstance().getLogger().info("Message Text#:" + message_text);
                BotPlatform.getInstance().getLogger().info("Quick Reply Payload#:" + quick_reply_payload);

                for (String attachment : attachments.values()) {
                    BotPlatform.getInstance().getLogger().info("Attachment#:" + attachment);
                }

                return "ok";
            }

            // ..
            // Other Receive Webhooks Goes Here
            // ..

            return "No Messages";
        });
    }
}
```

Send API
--------

### Sending Message

Let's create an empty message first and fill it with the required data. We can get a new message container from Bot Platform Instance:

```java
MessageTemplate message_tpl = BotPlatform.getInstance().getBaseSender().getMessageTemplate();

// Let's start to fill the required data here
// ..
```

Finally let's send the message
```java
BotPlatform.getInstance().getBaseSender().send(message_tpl);
```

### Sending Button Message

Let's create an empty message first and fill it with the required data. We can get a new message container from Bot Platform Instance:

```java
ButtonTemplate button_message_tpl = BotPlatform.getInstance().getBaseSender().getButtonTemplate();

// Let's start to fill the required data here
// ..
```

Finally let's send the message

```java
BotPlatform.getInstance().getBaseSender().send(button_message_tpl);
```

### Sending List Message

Let's create an empty list message first and fill it with the required data. We can get a new list message container from Bot Platform Instance:

```java
ListTemplate list_message_tpl = BotPlatform.getInstance().getBaseSender().getListTemplate();

// Let's start to fill the required data here
// ..
```

Finally let's send the message

```java
BotPlatform.getInstance().getBaseSender().send(list_message_tpl);
```

### Sending Generic Message

Let's create an empty generic message first and fill it with the required data. We can get a new generic message container from Bot Platform Instance:

```java
GenericTemplate generic_message_tpl = BotPlatform.getInstance().getBaseSender().getGenericTemplate();

// Let's start to fill the required data here
// ..
```

Finally let's send the message

```java
BotPlatform.getInstance().getBaseSender().send(generic_message_tpl);
```

### Sending Receipt Message

Let's create an empty receipt message first and fill it with the required data. We can get a new receipt message container from Bot Platform Instance:

```java
ReceiptTemplate receipt_message_tpl = BotPlatform.getInstance().getBaseSender().getReceiptTemplate();

// Let's start to fill the required data here
// ..
```

Finally let's send the message

```java
BotPlatform.getInstance().getBaseSender().send(receipt_message_tpl);
```

Misc
====

Tutorials & Examples
--------------------
I am still working on the following:

1. [Building Your Chat Bot with Racter & SparkJava Framework.](https://github.com/Clivern/Racter)
2. [Building Your Chat Bot with Racter & Spring Framework.](https://github.com/Clivern/Racter)
3. [Building Your Chat Bot with Racter & Java Servlets.](https://github.com/Clivern/Racter)
4. [Building Your Chat Bot with Racter & Play Framework.](https://github.com/Clivern/Racter)


Changelog
---------
Version 1.0.0 (coming soon):
```
Initial Release
```

Acknowledgements
----------------

© 2017, Clivern. Released under the [MIT License](http://www.opensource.org/licenses/mit-license.php).

**Racter** is authored and maintained by [@clivern](http://github.com/clivern).