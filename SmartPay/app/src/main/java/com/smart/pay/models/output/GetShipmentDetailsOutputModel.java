package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 14-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetShipmentDetailsOutputModel {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("shipments")
    @Expose
    private Shipments shipments;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Shipments getShipments() {
        return shipments;
    }

    public void setShipments(Shipments shipments) {
        this.shipments = shipments;
    }


    public class Shipments {

        @SerializedName("shipment_id")
        @Expose
        private String shipmentId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("carrier_code")
        @Expose
        private String carrierCode;
        @SerializedName("carrier_name")
        @Expose
        private String carrierName;
        @SerializedName("tracking_number")
        @Expose
        private String trackingNumber;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("customer_address")
        @Expose
        private String customerAddress;
        @SerializedName("customer_name")
        @Expose
        private String customerName;
        @SerializedName("customer_email")
        @Expose
        private String customerEmail;
        @SerializedName("customer_phone")
        @Expose
        private String customerPhone;
        @SerializedName("date_of_delivery")
        @Expose
        private String dateOfDelivery;
        @SerializedName("order_create_time")
        @Expose
        private String orderCreateTime;
        @SerializedName("shipment_create_time")
        @Expose
        private String shipmentCreateTime;
        @SerializedName("status")
        @Expose
        private String status;

        public String getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(String shipmentId) {
            this.shipmentId = shipmentId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getCarrierCode() {
            return carrierCode;
        }

        public void setCarrierCode(String carrierCode) {
            this.carrierCode = carrierCode;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getTrackingNumber() {
            return trackingNumber;
        }

        public void setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getDateOfDelivery() {
            return dateOfDelivery;
        }

        public void setDateOfDelivery(String dateOfDelivery) {
            this.dateOfDelivery = dateOfDelivery;
        }

        public String getOrderCreateTime() {
            return orderCreateTime;
        }

        public void setOrderCreateTime(String orderCreateTime) {
            this.orderCreateTime = orderCreateTime;
        }

        public String getShipmentCreateTime() {
            return shipmentCreateTime;
        }

        public void setShipmentCreateTime(String shipmentCreateTime) {
            this.shipmentCreateTime = shipmentCreateTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
