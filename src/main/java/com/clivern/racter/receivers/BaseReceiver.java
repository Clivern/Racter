/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.receivers;

import com.clivern.racter.receivers.webhook.*;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Base Receiver Class
 */
public class BaseReceiver {

	private static BaseReceiver instance;

	private String message_string;
	private JSONObject message_object;
	private Map<String, String> data = new HashMap<String, String>();

	/**
	 * Constructor
	 */
	protected BaseReceiver() { }

	/**
	 * Get Instance
	 *
	 * @return BaseReceiver
	 */
	public static BaseReceiver getInstance() {
	    if(instance == null) {
	        instance = new BaseReceiver();
	    }
	    return instance;
	}

	public BaseReceiver set(String message_string)
	{
		this.message_string = message_string;
		this.message_object = new JSONObject(message_string);

		return instance;
	}

	public BaseReceiver parse()
	{
		int i = 0;

		if( !this.message_object.has("object")  || !this.message_object.getString("object").equals("page") ){
			return instance;
		}

		if( !this.message_object.has("entry") ){
			return instance;
		}

		JSONArray entry = this.message_object.getJSONArray("entry");

		for ( i = 0; i < entry.length(); i++ ) {

			JSONObject entry_obj = entry.getJSONObject(i);

			if( entry_obj.has("id") ){
				String page_id = entry_obj.getString("id");
			}

			if( entry_obj.has("time") ){
				Long time =  entry_obj.getLong("time");
			}

			if( entry_obj.has("messaging") ){

				for ( i = 0; i < entry_obj.getJSONArray("messaging").length(); i++ ) {

					JSONObject messaging_item = entry_obj.getJSONArray("messaging").getJSONObject(i);

					if( messaging_item.has("sender") ){
						JSONObject sender = messaging_item.getJSONObject("sender");
						// USER_ID
						String sender_id = (sender.has("id")) ? sender.getString("id") : "";
					}

					if( messaging_item.has("recipient") ){
						JSONObject recipient = messaging_item.getJSONObject("recipient");
						// PAGE_ID
						String recipient_id = (recipient.has("id")) ? recipient.getString("id") : "";
					}

					if( messaging_item.has("timestamp") ){
						Long timestamp = messaging_item.getLong("timestamp");
					}

					if( messaging_item.has("message") ){
						JSONObject message = messaging_item.getJSONObject("message");
						String mid = (message.has("mid")) ? message.getString("mid") : "";

						if ( message.has("text") ){
							String text = message.getString("text");
						}

						if ( message.has("quick_reply") ){
							JSONObject quick_reply = message.getJSONObject("quick_reply");
							String payload = (quick_reply.has("payload")) ? quick_reply.getString("payload") : "";
						}

						if ( message.has("attachments") ){
							JSONArray attachments = message.getJSONArray("attachments");

							for ( i = 0; i < attachments.length(); i++ ) {

								JSONObject attachment = attachments.getJSONObject(i);

								String type = (attachment.has("type")) ? attachment.getString("type") : "";

								if( type.equals("audio") ){
									if( attachment.has("payload") ){
										JSONObject attachment_payload = attachment.getJSONObject("payload");
										String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : "";
									}

								/////////////////////////////////////////////////////////////////////////////////////////////
								// Unknown right now
								// }else if( type.equals("fallback") ){
								/////////////////////////////////////////////////////////////////////////////////////////////

								}else if( type.equals("file") ){

									if( attachment.has("payload") ){
										JSONObject attachment_payload = attachment.getJSONObject("payload");
										String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : "";
									}

								}else if( type.equals("image") ){

									if( attachment.has("payload") ){
										JSONObject attachment_payload = attachment.getJSONObject("payload");
										String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : "";
									}

								}else if( type.equals("video") ){

									if( attachment.has("payload") ){
										JSONObject attachment_payload = attachment.getJSONObject("payload");
										String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : "";
									}

								}else if( type.equals("location") ){

									if( attachment.has("payload") ){
										JSONObject attachment_payload = attachment.getJSONObject("payload");
										Long coordinates_lat = (attachment_payload.has("coordinates.lat")) ? attachment_payload.getLong("coordinates.lat") : 0;
										Long coordinates_long = (attachment_payload.has("coordinates.long")) ? attachment_payload.getLong("coordinates.long") : 0;
									}

								}
							}
						}
					}
				}
			}
		}

		return instance;
	}

	public Map<String, String> get()
	{
		return this.data;
	}

	public String getMessageString()
	{
		return this.message_string;
	}

	public JSONObject getMessageObject()
	{
		return this.message_object;
	}
}