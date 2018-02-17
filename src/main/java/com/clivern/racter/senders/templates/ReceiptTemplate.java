/*
 * Copyright (C) 2017 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.racter.senders.templates;

import com.clivern.racter.contract.templates.SenderTemplate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Receipt Template Class
 *
 * @since 1.0.0
 */
public class ReceiptTemplate implements SenderTemplate {

    protected String recipient_id;

    protected String recipient_name;

    protected String order_number;

    protected String currency;

    protected String payment_method;

    protected String order_url;

    protected String timestamp;

    protected String message_string;

    protected ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();

    protected ArrayList<HashMap<String, String>> adjustments = new ArrayList<HashMap<String, String>>();

    protected HashMap<String, String> address = new HashMap<String, String>();

    protected HashMap<String, String> summary = new HashMap<String, String>();


    /**
     * Set Recipient ID
     *
     * @param recipient_id the recipient id
     */
    public void setRecipientId(String recipient_id)
    {
        this.recipient_id = recipient_id;
    }

    /**
     * Set Recipient Name
     *
     * @param recipient_name the recipient name
     */
    public void setRecipientName(String recipient_name)
    {
        this.recipient_name = recipient_name;
    }

    /**
     * Set Order Number
     *
     * @param order_number the order number
     */
    public void setOrderNumber(String order_number)
    {
        this.order_number = order_number;
    }

    /**
     * Set Currency
     *
     * @param currency the currency
     */
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    /**
     * Set Payment Method
     *
     * @param payment_method the payment method
     */
    public void setPaymentMethod(String payment_method)
    {
        this.payment_method = payment_method;
    }

    /**
     * Set Order URL
     *
     * @param order_url the order url
     */
    public void setOrderUrl(String order_url)
    {
        this.order_url = order_url;
    }

    /**
     * Set Timestamp
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * Set Element
     *
     * @param title the receipt element title
     * @param subtitle the receipt element subtitle
     * @param quantity the receipt element quantity
     * @param price the receipt element price
     * @param currency the receipt element currency
     * @param image_url the receipt element image url
     */
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

    /**
     * Set Address
     *
     * @param street_1 the street address 1
     * @param street_2 the street address 2
     * @param city the city 
     * @param postal_code the postal code
     * @param state the state
     * @param country the country
     */
    public void setAddress(String street_1, String street_2, String city, String postal_code, String state, String country)
    {
        this.address.put("street_1", street_1);
        this.address.put("street_2", street_2);
        this.address.put("city", city);
        this.address.put("postal_code", postal_code);
        this.address.put("state", state);
        this.address.put("country", country);
    }

    /**
     * Set Summary
     *
     * @param subtotal the receipt sub-total
     * @param shipping_cost the receipt shipping cost
     * @param total_tax the receipt total tax
     * @param total_cost the receipt total cost
     */
    public void setSummary(String subtotal, String shipping_cost, String total_tax, String total_cost)
    {
        this.summary.put("subtotal", subtotal);
        this.summary.put("shipping_cost", shipping_cost);
        this.summary.put("total_tax", total_tax);
        this.summary.put("total_cost", total_cost);
    }

    /**
     * Set Adjustment
     *
     * @param name the adjustment name
     * @param amount the adjustment amount
     */
    public void setAdjustment(String name, String amount)
    {
        HashMap<String, String> adjustment = new HashMap<String, String>();
        adjustment.put("name", name);
        adjustment.put("amount", amount);
        this.adjustments.add(adjustment);
    }

    /**
     * Get Recipient ID
     *
     * @return String the recipient id
     */
    public String getRecipientId()
    {
        return this.recipient_id;
    }

    /**
     * Get Recipient Name
     *
     * @return String the recipient name
     */
    public String getRecipientName()
    {
        return this.recipient_name;
    }

    /**
     * Get Order Number
     *
     * @return String the order number
     */
    public String getOrderNumber()
    {
        return this.order_number;
    }

    /**
     * Get Currency
     *
     * @return String the currency
     */
    public String getCurrency()
    {
        return this.currency;
    }

    /**
     * Get Payment Method
     *
     * @return String the payment method
     */
    public String getPaymentMethod()
    {
        return this.payment_method;
    }

    /**
     * Get Order URL
     *
     * @return String the order url
     */
    public String getOrderUrl()
    {
        return this.order_url;
    }

    /**
     * Get Timestamp
     *
     * @return String the timestamp
     */
    public String getTimestamp()
    {
        return this.timestamp;
    }

    /**
     * Get Elements
     *
     * @return ArrayList a list of all elements
     */
    public ArrayList<HashMap<String, String>> getElements()
    {
        return this.elements;
    }

    /**
     * Get Adjustments
     *
     * @return ArrayList a list of adjustment data
     */
    public ArrayList<HashMap<String, String>> getAdjustments()
    {
        return this.adjustments;
    }

    /**
     * Get Address
     *
     * @return HashMap the address
     */
    public HashMap<String, String> getAddress()
    {
        return this.address;
    }

    /**
     * Get Summary
     *
     * @return HashMap the summary
     */
    public HashMap<String, String> getSummary()
    {
        return this.summary;
    }

    /**
     * Build and get message as a string
     *
     * @return String the final message body
     */
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

    /**
     * Set or override message
     *
     * @param message_string the message body
     */
    public void setMessageString(String message_string)
    {
        this.message_string = message_string;
    }

    /**
     * Get message as a string
     *
     * @return String the message body
     */
    public String getMessageString()
    {
        return this.message_string;
    }
}