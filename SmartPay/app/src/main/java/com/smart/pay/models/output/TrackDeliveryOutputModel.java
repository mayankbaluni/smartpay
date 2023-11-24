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

import java.util.List;


public class TrackDeliveryOutputModel {


    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private Data data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("items")
        @Expose
        private List<Item> items = null;

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

    }


    public class Item {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("tracking_number")
        @Expose
        private String trackingNumber;
        @SerializedName("carrier_code")
        @Expose
        private String carrierCode;
        @SerializedName("order_create_time")
        @Expose
        private String orderCreateTime;
        @SerializedName("destination_code")
        @Expose
        private String destinationCode;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("original_country")
        @Expose
        private String originalCountry;
        @SerializedName("itemTimeLength")
        @Expose
        private Integer itemTimeLength;
        @SerializedName("stayTimeLength")
        @Expose
        private Integer stayTimeLength;
        @SerializedName("service_code")
        @Expose
        private String serviceCode;
        @SerializedName("substatus")
        @Expose
        private String substatus;
        @SerializedName("last_mile_tracking_supported")
        @Expose
        private String lastMileTrackingSupported;
        @SerializedName("origin_info")
        @Expose
        private OriginInfo originInfo;
        @SerializedName("destination_info")
        @Expose
        private String destinationInfo;
        @SerializedName("lastEvent")
        @Expose
        private String lastEvent;
        @SerializedName("lastUpdateTime")
        @Expose
        private String lastUpdateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getOrderCreateTime() {
            return orderCreateTime;
        }

        public void setOrderCreateTime(String orderCreateTime) {
            this.orderCreateTime = orderCreateTime;
        }

        public String getDestinationCode() {
            return destinationCode;
        }

        public void setDestinationCode(String destinationCode) {
            this.destinationCode = destinationCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOriginalCountry() {
            return originalCountry;
        }

        public void setOriginalCountry(String originalCountry) {
            this.originalCountry = originalCountry;
        }

        public Integer getItemTimeLength() {
            return itemTimeLength;
        }

        public void setItemTimeLength(Integer itemTimeLength) {
            this.itemTimeLength = itemTimeLength;
        }

        public Integer getStayTimeLength() {
            return stayTimeLength;
        }

        public void setStayTimeLength(Integer stayTimeLength) {
            this.stayTimeLength = stayTimeLength;
        }

        public String getServiceCode() {
            return serviceCode;
        }

        public void setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
        }

        public String getSubstatus() {
            return substatus;
        }

        public void setSubstatus(String substatus) {
            this.substatus = substatus;
        }

        public String getLastMileTrackingSupported() {
            return lastMileTrackingSupported;
        }

        public void setLastMileTrackingSupported(String lastMileTrackingSupported) {
            this.lastMileTrackingSupported = lastMileTrackingSupported;
        }

        public OriginInfo getOriginInfo() {
            return originInfo;
        }

        public void setOriginInfo(OriginInfo originInfo) {
            this.originInfo = originInfo;
        }

        public String getDestinationInfo() {
            return destinationInfo;
        }

        public void setDestinationInfo(String destinationInfo) {
            this.destinationInfo = destinationInfo;
        }

        public String getLastEvent() {
            return lastEvent;
        }

        public void setLastEvent(String lastEvent) {
            this.lastEvent = lastEvent;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

    }

    public class Meta {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("message")
        @Expose
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }


    public class OriginInfo {

        @SerializedName("ItemReceived")
        @Expose
        private String itemReceived;
        @SerializedName("ItemDispatched")
        @Expose
        private String itemDispatched;
        @SerializedName("DepartfromAirport")
        @Expose
        private String departfromAirport;
        @SerializedName("ArrivalfromAbroad")
        @Expose
        private String arrivalfromAbroad;
        @SerializedName("CustomsClearance")
        @Expose
        private String customsClearance;
        @SerializedName("DestinationArrived")
        @Expose
        private String destinationArrived;
        @SerializedName("weblink")
        @Expose
        private String weblink;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("carrier_code")
        @Expose
        private String carrierCode;
        @SerializedName("trackinfo")
        @Expose
        public List<Trackinfo> trackinfo = null;

        public String getItemReceived() {
            return itemReceived;
        }

        public void setItemReceived(String itemReceived) {
            this.itemReceived = itemReceived;
        }

        public String getItemDispatched() {
            return itemDispatched;
        }

        public void setItemDispatched(String itemDispatched) {
            this.itemDispatched = itemDispatched;
        }

        public String getDepartfromAirport() {
            return departfromAirport;
        }

        public void setDepartfromAirport(String departfromAirport) {
            this.departfromAirport = departfromAirport;
        }

        public String getArrivalfromAbroad() {
            return arrivalfromAbroad;
        }

        public void setArrivalfromAbroad(String arrivalfromAbroad) {
            this.arrivalfromAbroad = arrivalfromAbroad;
        }

        public Object getCustomsClearance() {
            return customsClearance;
        }

        public void setCustomsClearance(String customsClearance) {
            this.customsClearance = customsClearance;
        }

        public Object getDestinationArrived() {
            return destinationArrived;
        }

        public void setDestinationArrived(String destinationArrived) {
            this.destinationArrived = destinationArrived;
        }

        public String getWeblink() {
            return weblink;
        }

        public void setWeblink(String weblink) {
            this.weblink = weblink;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCarrierCode() {
            return carrierCode;
        }

        public void setCarrierCode(String carrierCode) {
            this.carrierCode = carrierCode;
        }

        public List<Trackinfo> getTrackinfo() {
            return trackinfo;
        }

        public void setTrackinfo(List<Trackinfo> trackinfo) {
            this.trackinfo = trackinfo;
        }

    }

    public class Trackinfo {

        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("StatusDescription")
        @Expose
        private String statusDescription;
        @SerializedName("Details")
        @Expose
        private String details;
        @SerializedName("checkpoint_status")
        @Expose
        private String checkpointStatus;
        @SerializedName("ItemNode")
        @Expose
        private String itemNode;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStatusDescription() {
            return statusDescription;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getCheckpointStatus() {
            return checkpointStatus;
        }

        public void setCheckpointStatus(String checkpointStatus) {
            this.checkpointStatus = checkpointStatus;
        }

        public String getItemNode() {
            return itemNode;
        }

        public void setItemNode(String itemNode) {
            this.itemNode = itemNode;
        }

    }

}
