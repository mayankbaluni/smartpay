package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 15-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TrackDeliveryInputModel {

    @SerializedName("tracking_number")
    @Expose
    private String trackingNumber;
    @SerializedName("carrier_code")
    @Expose
    private String carrierCode;
    @SerializedName("destination_code")
    @Expose
    private String destinationCode;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("order_create_time")
    @Expose
    private String orderCreateTime;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
