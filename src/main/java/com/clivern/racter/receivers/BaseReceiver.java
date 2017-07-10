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
	private Map<String, MessageReceived> message_webhook = new HashMap<String, MessageReceived>();

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

	/**
	 * Set Incoming webhook data
	 *
	 * @param  message_string
	 * @return BaseReceiver
	 */
	public BaseReceiver set(String message_string)
	{
		this.message_string = message_string;
		this.message_object = new JSONObject(message_string);

		return instance;
	}

	/**
	 * Parse Webhook
	 *
	 * @return BaseReceiver
	 */
	public BaseReceiver parse()
	{
		int i;
		int z = 1;

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

					String sender_id = null;
					if( messaging_item.has("sender") ){
						JSONObject sender = messaging_item.getJSONObject("sender");
						// USER_ID
						sender_id = (sender.has("id")) ? sender.getString("id") : null;
					}

					String recipient_id = null;
					if( messaging_item.has("recipient") ){
						JSONObject recipient = messaging_item.getJSONObject("recipient");
						// PAGE_ID
						recipient_id = (recipient.has("id")) ? recipient.getString("id") : null;
					}

					Long timestamp = null;
					if( messaging_item.has("timestamp") ){
						timestamp = messaging_item.getLong("timestamp");
					}

					if( messaging_item.has("message") ){
						JSONObject message = messaging_item.getJSONObject("message");


						if( message.has("is_echo") ){
							//-----------------
							// Message Echo
							//-----------------

						}else{

							z += 1;

							//-----------------
							// Message
							//-----------------
							this.message_webhook.put("message." + z, MessageReceived.getInstance());
							this.message_webhook.get("message." + z).setUserId(sender_id);
							this.message_webhook.get("message." + z).setPageId(recipient_id);
							this.message_webhook.get("message." + z).setTimestamp(timestamp);

							String mid = (message.has("mid")) ? message.getString("mid") : null;
							this.message_webhook.get("message." + z).setMessageId(mid);

							if ( message.has("text") ){
								String text = message.getString("text");
								this.message_webhook.get("message." + z).setMessageText(text);
							}

							if ( message.has("quick_reply") ){
								JSONObject quick_reply = message.getJSONObject("quick_reply");
								String payload = (quick_reply.has("payload")) ? quick_reply.getString("payload") : null;
								this.message_webhook.get("message." + z).setQuickReplyPayload(payload);
							}

							if ( message.has("attachments") ){
								JSONArray attachments = message.getJSONArray("attachments");

								for ( i = 0; i < attachments.length(); i++ ) {

									JSONObject attachment = attachments.getJSONObject(i);

									String type = (attachment.has("type")) ? attachment.getString("type") : null;

									if( type.equals("audio") ){
										if( attachment.has("payload") ){
											JSONObject attachment_payload = attachment.getJSONObject("payload");
											String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;

											this.message_webhook.get("message." + z).setAttachment("audio", url);
										}

									/////////////////////////////////////////////////////////////////////////////////////////////
									// Unknown right now
									// }else if( type.equals("fallback") ){
									/////////////////////////////////////////////////////////////////////////////////////////////

									}else if( type.equals("file") ){

										if( attachment.has("payload") ){
											JSONObject attachment_payload = attachment.getJSONObject("payload");
											String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;
											this.message_webhook.get("message." + z).setAttachment("file", url);
										}

									}else if( type.equals("image") ){

										if( attachment.has("payload") ){
											JSONObject attachment_payload = attachment.getJSONObject("payload");
											String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;
											this.message_webhook.get("message." + z).setAttachment("image", url);
										}

									}else if( type.equals("video") ){

										if( attachment.has("payload") ){
											JSONObject attachment_payload = attachment.getJSONObject("payload");
											String url = (attachment_payload.has("url")) ? attachment_payload.getString("url") : null;
											this.message_webhook.get("message." + z).setAttachment("video", url);
										}

									}else if( type.equals("location") ){

										if( attachment.has("payload") ){
											JSONObject attachment_payload = attachment.getJSONObject("payload");
											Long coordinates_lat = (attachment_payload.has("coordinates.lat")) ? attachment_payload.getLong("coordinates.lat") : null;
											Long coordinates_long = (attachment_payload.has("coordinates.long")) ? attachment_payload.getLong("coordinates.long") : null;
											this.message_webhook.get("message." + z).setAttachment("location", coordinates_lat, coordinates_long);
										}

									}
								}
							}

						}
					}

					//-----------------
					// Message Delivery
					//-----------------
					if( messaging_item.has("delivery") ){


					}

					//-------------
					// Message Read
					//-------------
					if( messaging_item.has("read") ){


					}

					//----------
					// Post Back
					//----------
					if( messaging_item.has("postback") ){


					}
				}
			}
		}

		return instance;
	}

	/**
	 * Get Messages
	 *
	 * @return Map
	 */
	public Map<String, MessageReceived> getMessages()
	{
		return this.message_webhook;
	}

	/**
	 * Get Received Webhook data
	 *
	 * @return String
	 */
	public String getMessageString()
	{
		return this.message_string;
	}

	/**
	 * Get Received Webhook data
	 *
	 * @return JSONObject
	 */
	public JSONObject getMessageObject()
	{
		return this.message_object;
	}
}