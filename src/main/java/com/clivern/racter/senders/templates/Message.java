/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Text Message Template
 */
public class Message {

/*
	{
	  	"recipient": {
	    	"id": "USER_ID"
	  	},
	  	"message": {
	    	"text": "hello, world!",
			"attachment":{
	      		"type":"audio", # file, audio, image, video
	      		"payload":{
	        		"url":"https://petersapparel.com/bin/clip.mp3",
	        		"is_reusable": true # Optional You can optimize sending multimedia by reusing attachments. If you're sending the same attachments repeatedly, you should consider reusing them. Attachment reuse works with sending images, audio clips, videos and files.
	      		}
	    	},
	    	"quick_replies":[

	    	],
	    	"metadata": "" # Custom string that is delivered as a message echo.
	  	}

	  	"sender_action":"typing_on", # typing_on, typing_off, mark_seen
	  	"notification_type": "REGULAR" # REGULAR, SILENT_PUSH, NO_PUSH
	}
*/

	private static Message instance;

	/**
	 * Constructor
	 */
	protected Message() { }

	/**
	 * Get Instance
	 *
	 * @return Message
	 */
	public static Message getInstance() {
	    if(instance == null) {
	        instance = new Message();
	    }
	    return instance;
	}

	public Boolean send()
	{

	}

	public String getResponse()
	{

	}
}