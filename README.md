Racter
=========

Racter is A Java Framework for Building Bots on Facebook's Messenger Platform.

*Under Development*

[![Build Status](https://travis-ci.org/Clivern/Racter.svg?branch=master)](https://travis-ci.org/Clivern/Racter)

Installation
------------
To add a dependency using Maven, use the following:
```
<dependency>
  <groupId>com.clivern</groupId>
  <artifactId>racter</artifactId>
  <version>1.0.0</version>
</dependency>
```

To add a dependency using Gradle, use the following:
```
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
log_file_path=src/main/java/resources/app.log
log_file_limit=1
log_file_count=200000
log_file_append=true or false
```

Then import all required classes

```
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

```
BotPlatform platform = BotPlatform.getInstance().loadConfigs("config.properties").configDependencies();
```

or Configure it manually
```
Map<String, String> options = new HashMap<String, String>();

options.put("app_id", "App ID Goes Here");
options.put("verify_token", "Verify Token Goes Here");
options.put("page_access_token", "Page Access Token Goes Here");
options.put("log_console_status", "true or false");
options.put("log_console_level", "ALL, CONFIG, FINE, FINER, FINEST, INFO, SEVERE, WARNING or OFF");
options.put("log_file_status", "true or false");
options.put("log_file_level", "ALL, CONFIG, FINE, FINER, FINEST, INFO, SEVERE, WARNING or OFF");
options.put("log_file_path", "src/main/java/resources/app.log");
options.put("log_file_limit", "1");
options.put("log_file_count", "200000");
options.put("log_file_append", "true or false");

BotPlatform platform = BotPlatform.getInstance().loadConfigs(options).configDependencies();
```

Setup Webhook
-------------
Create a route to verify your verify token, Facebook will perform a GET request to this route URL with some URL parameters to make sure that verify token is correct.

```
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

```
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
    }
}

```

Message Received
----------------


Send API
--------


Misc
====

Tutorials & Examples
--------------------
1. [Building Your Chat Bot with SparkJava Framework.]()
2. [Building Your Chat Bot with Spring Framework.]()
3. [Building Your Chat Bot with Java Servlets.]()
4. [Building Your Chat Bot with Play Framework.]()


Changelog
---------
Version 1.0.0 (coming soon):
```
initial release
```

Acknowledgements
----------------

© 2017, Clivern. Released under the [MIT License](http://www.opensource.org/licenses/mit-license.php).

**Racter** is authored and maintained by [@clivern](http://github.com/clivern).