/*
 * Copyright (C) 2017 Clivern. <https://clivern.com>
 */
package com.clivern.racter.senders.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Receipt Template
 */
public class ReceiptTemplate {

    private String recipient_id;
    private String recipient_name;
    private String order_number;
    private String currency;
    private String payment_method;
    private String order_url;
    private String timestamp;
    private String message_string;
    private ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> adjustments = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> address = new HashMap<String, String>();
    private HashMap<String, String> summary = new HashMap<String, String>();

    public void setRecipientId(String recipient_id){
        this.recipient_id = recipient_id;
    }

    public void setRecipientName(String recipient_name){
        this.recipient_name = recipient_name;
    }

    public void setOrderNumber(String order_number){
        this.order_number = order_number;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public void setPaymentMethod(String payment_method){
        this.payment_method = payment_method;
    }

    public void setOrderUrl(String order_url){
        this.order_url = order_url;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public void setElement(String title, String subtitle, String quantity, String price, String currency, String image_url)
    {
        HashMap<String, String> element = new HashMap<String, String>();
        element.put("title", title);
        element.put("subtitle", subtitle);
        element.put("quantity", quantity);
        element.put("price", price);
        element.put("currency", currency);
        element.put("image_url", image_url);
        this.elements.add(element);
    }

    public void setAddress(String street_1, String street_2, String city, String postal_code, String state, String country)
    {
        this.address.put("street_1", street_1);
        this.address.put("street_2", street_2);
        this.address.put("city", city);
        this.address.put("postal_code", postal_code);
        this.address.put("state", state);
        this.address.put("country", country);
    }

    public void setSummary(String subtotal, String shipping_cost, String total_tax, String total_cost)
    {
        this.summary.put("subtotal", subtotal);
        this.summary.put("shipping_cost", shipping_cost);
        this.summary.put("total_tax", total_tax);
        this.summary.put("total_cost", total_cost);
    }

    public void setAdjustment(String name, String amount)
    {
        HashMap<String, String> adjustment = new HashMap<String, String>();
        adjustment.put("name", name);
        adjustment.put("amount", amount);
        this.adjustments.add(adjustment);
    }

    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    public String getRecipientId(){
        return this.recipient_id;
    }

    public String getRecipientName(){
        return this.recipient_name;
    }

    public String getOrderNumber(){
        return this.order_number;
    }

    public String getCurrency(){
        return this.currency;
    }

    public String getPaymentMethod(){
        return this.payment_method;
    }

    public String getOrderUrl(){
        return this.order_url;
    }

    public String getTimestamp(){
        return this.timestamp;
    }

    public ArrayList<HashMap<String, String>> getElements()
    {
        return this.elements;
    }

    public ArrayList<HashMap<String, String>> getAdjustments()
    {
        return this.adjustments;
    }

    public HashMap<String, String> getAddress()
    {
        return this.address;
    }

    public HashMap<String, String> getSummary()
    {
        return this.summary;
    }

    public String getMessageString()
    {
        return this.message_string;
    }

    public String build()
    {
        this.message_string  = "{";

            if( this.recipient_id != null ){
                this.message_string += "\"recipient\": {\"id\": \"" + this.recipient_id + "\"},";
            }

            this.message_string += "\"message\": {";

                this.message_string += "\"attachment\":{";

                    this.message_string += "\"type\":\"template\",";
                    this.message_string += "\"payload\":{";

                        this.message_string += "\"template_type\":\"receipt\",";

                        if( this.recipient_name != null ){
                            this.message_string += "\"recipient_name\":\"" + this.recipient_name + "\",";
                        }
                        if( this.order_number != null ){
                            this.message_string += "\"order_number\":\"" + this.order_number + "\",";
                        }
                        if( this.currency != null ){
                            this.message_string += "\"currency\":\"" + this.currency + "\",";
                        }
                        if( this.payment_method != null ){
                            this.message_string += "\"payment_method\":\"" + this.payment_method + "\",";
                        }
                        if( this.order_url != null ){
                            this.message_string += "\"order_url\":\"" + this.order_url + "\",";
                        }
                        if( this.timestamp != null ){
                            this.message_string += "\"timestamp\":\"" + this.timestamp + "\",";
                        }

                        if( !this.elements.isEmpty() ){
                            this.message_string += "\"elements\":[";
                            for ( int j = 0 ; j < this.elements.size(); j++ ) {
                                HashMap<String, String> element = this.elements.get(j);

                                this.message_string += "{";
                                    if( !element.get("title").equals("") ){
                                        this.message_string += "\"title\":\"" + element.get("title") + "\",";
                                    }
                                    if( !element.get("subtitle").equals("") ){
                                        this.message_string += "\"subtitle\":\"" + element.get("subtitle") + "\",";
                                    }
                                    if( !element.get("quantity").equals("") ){
                                        this.message_string += "\"quantity\":" + element.get("quantity") + ",";
                                    }
                                    if( !element.get("price").equals("") ){
                                        this.message_string += "\"price\":" + element.get("price") + ",";
                                    }
                                    if( !element.get("currency").equals("") ){
                                        this.message_string += "\"currency\":\"" + element.get("currency") + "\",";
                                    }
                                    if( !element.get("image_url").equals("") ){
                                        this.message_string += "\"image_url\":\"" + element.get("image_url") + "\",";
                                    }

                                    this.message_string = this.message_string.replaceAll(",$", "");
                                this.message_string += "},";

                            }
                            this.message_string = this.message_string.replaceAll(",$", "");
                            this.message_string += "],";
                        }

                        if( !this.address.isEmpty() ){
                            this.message_string += "\"address\":{";

                                if( !this.address.get("street_1").equals("") ){
                                    this.message_string += "\"street_1\":\"" + this.address.get("street_1") + "\",";
                                }
                                if( !this.address.get("street_2").equals("") ){
                                    this.message_string += "\"street_2\":\"" + this.address.get("street_2") + "\",";
                                }
                                if( !this.address.get("city").equals("") ){
                                    this.message_string += "\"city\":\"" + this.address.get("city") + "\",";
                                }
                                if( !this.address.get("postal_code").equals("") ){
                                    this.message_string += "\"postal_code\":\"" + this.address.get("postal_code") + "\",";
                                }
                                if( !this.address.get("state").equals("") ){
                                    this.message_string += "\"state\":\"" + this.address.get("state") + "\",";
                                }
                                if( !this.address.get("country").equals("") ){
                                    this.message_string += "\"country\":\"" + this.address.get("country") + "\",";
                                }

                                this.message_string = this.message_string.replaceAll(",$", "");

                            this.message_string += "},";
                        }

                        if( !this.summary.isEmpty() ){
                            this.message_string += "\"summary\":{";

                                if( !this.summary.get("subtotal").equals("") ){
                                    this.message_string += "\"subtotal\":" + this.summary.get("subtotal") + ",";
                                }
                                if( !this.summary.get("shipping_cost").equals("") ){
                                    this.message_string += "\"shipping_cost\":" + this.summary.get("shipping_cost") + ",";
                                }
                                if( !this.summary.get("total_tax").equals("") ){
                                    this.message_string += "\"total_tax\":" + this.summary.get("total_tax") + ",";
                                }
                                if( !this.summary.get("total_cost").equals("") ){
                                    this.message_string += "\"total_cost\":" + this.summary.get("total_cost") + ",";
                                }

                                this.message_string = this.message_string.replaceAll(",$", "");

                            this.message_string += "},";
                        }
                        if( !this.adjustments.isEmpty() ){
                            this.message_string += "\"adjustments\":[";
                            for ( int j = 0 ; j < this.adjustments.size(); j++ ) {
                                HashMap<String, String> adjustment = this.adjustments.get(j);

                                this.message_string += "{";
                                    if( !adjustment.get("name").equals("") ){
                                        this.message_string += "\"name\":\"" + adjustment.get("name") + "\",";
                                    }
                                    if( !adjustment.get("amount").equals("") ){
                                        this.message_string += "\"amount\":" + adjustment.get("amount") + ",";
                                    }
                                    this.message_string = this.message_string.replaceAll(",$", "");
                                this.message_string += "},";

                            }
                            this.message_string = this.message_string.replaceAll(",$", "");
                            this.message_string += "],";
                        }

                        this.message_string = this.message_string.replaceAll(",$", "");

                    this.message_string += "}";

                this.message_string += "}";

            this.message_string += "}";

        this.message_string += "}";

        return this.message_string;
    }
}