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

then pass this file to bot platform
```
import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.webhook.*;

import com.clivern.racter.senders.*;
import com.clivern.racter.senders.templates.*;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

// ...

BotPlatform platform = BotPlatform.getInstance().loadConfigs("configs/config.properties").configDependencies();

```

or Configure it manually
```
import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.webhook.*;

import com.clivern.racter.senders.*;
import com.clivern.racter.senders.templates.*;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

// ...

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


Misc
====

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