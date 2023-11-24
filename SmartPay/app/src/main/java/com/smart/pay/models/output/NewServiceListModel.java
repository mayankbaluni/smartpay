package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 11-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewServiceListModel {

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("products")
    @Expose
    public List<Service> services = null;
    @SerializedName("message")
    @Expose
    public String message;

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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Service {

        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("service_name")
        @Expose
        private String serviceName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("service_image")
        @Expose
        private String serviceImage;
        @SerializedName("service_cat_id")
        @Expose
        private String serviceCatId;
        @SerializedName("service_sub_cat_id")
        @Expose
        private String serviceSubCatId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("max_no_of_bookings")
        @Expose
        private String maxNoOfBookings;
        @SerializedName("service_type")
        @Expose
        private String serviceType;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("modified_date")
        @Expose
        private String modifiedDate;

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getServiceImage() {
            return serviceImage;
        }

        public void setServiceImage(String serviceImage) {
            this.serviceImage = serviceImage;
        }

        public String getServiceCatId() {
            return serviceCatId;
        }

        public void setServiceCatId(String serviceCatId) {
            this.serviceCatId = serviceCatId;
        }

        public String getServiceSubCatId() {
            return serviceSubCatId;
        }

        public void setServiceSubCatId(String serviceSubCatId) {
            this.serviceSubCatId = serviceSubCatId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getMaxNoOfBookings() {
            return maxNoOfBookings;
        }

        public void setMaxNoOfBookings(String maxNoOfBookings) {
            this.maxNoOfBookings = maxNoOfBookings;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

    }


}
