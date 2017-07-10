/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Text Message Template
 * <pre>
 *	{
 *	  	"recipient": {
 *	    	"id": "USER_ID"
 *	  	},
 *	  	"message": {
 *	    	"text": "hello, world!",
 *			"attachment":{
 *	      		"type":"audio", # file, audio, image, video
 *	      		"payload":{
 *	        		"url":"https://petersapparel.com/bin/clip.mp3",
 *	        		"is_reusable": true # Optional You can optimize sending multimedia by reusing attachments. If you're sending the same attachments repeatedly, you should consider reusing them. Attachment reuse works with sending images, audio clips, videos and files.
 *	      		}
 *	    	},
 *	    	"quick_replies":[
 *
 *	    	],
 *	    	"metadata": "" # Custom string that is delivered as a message echo.
 *	  	}
 *
 *	  	"sender_action":"typing_on", # typing_on, typing_off, mark_seen
 *	  	"notification_type": "REGULAR" # REGULAR, SILENT_PUSH, NO_PUSH
 *	}
 * </pre>
 */
public class Message {

	private String recipient_id;
	private String message_text;
	private Map<String, String> message_attachment = new HashMap<String, String>();
	private ArrayList<HashMap<String, String>> message_quick_replies = new ArrayList<HashMap<String, String>>();
	private String message_metadata;
	private String sender_action;
	private String notification_type;
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

	public void setRecipientId(String recipient_id){
		this.recipient_id = recipient_id;
	}

	public void setMessageText(String message_text){
		this.message_text = message_text;
	}

	public void setMessageMetadata(String message_metadata){
		this.message_metadata = message_metadata;
	}

	public void setSenderAction(String sender_action){
		this.sender_action = sender_action;
	}

	public void setNotificationType(String notification_type){
		this.notification_type = notification_type;
	}

	public void setAttachment(String type, String url, Boolean is_reusable=false)
	{
		this.message_attachment.put("type", type);
		this.message_attachment.put("url", url);
		this.message_attachment.put("is_reusable", String.valueOf(is_reusable));
	}

	public void setQuickReply(String content_type, String title, String payload, String image_url)
	{
	    HashMap<String, String> quick_reply = new HashMap<String, String>();
	    quick_reply.put("content_type", content_type);
	    quick_reply.put("title", title);
	    quick_reply.put("payload", payload);
	    quick_reply.put("image_url", image_url);
		this.message_quick_replies.add(quick_reply);
	}

	public void getRecipientId(){
		return this.recipient_id;
	}

	public void getMessageText(){
		return this.message_text;
	}

	public void getMessageMetadata(){
		return this.message_metadata;
	}

	public void getSenderAction(){
		return this.sender_action;
	}

	public void getNotificationType(){
		return this.notification_type;
	}

	public Map<String, String> getAttachment()
	{
		return this.message_attachment;
	}

	public ArrayList<HashMap<String, String>> getQuickReply()
	{
		return this.message_quick_replies;
	}

	public Boolean build()
	{

	}

	public String getMessageString()
	{

	}
}