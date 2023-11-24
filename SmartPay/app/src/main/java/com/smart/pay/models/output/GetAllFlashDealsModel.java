package com.smart.pay.models.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class GetAllFlashDealsModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("deals")
    @Expose
    private ArrayList<ArrayList<Deal>> deals = null;

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

    public ArrayList<ArrayList<Deal>> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<ArrayList<Deal>> deals) {
        this.deals = deals;
    }


    public class Deal {

        @SerializedName("deal_id")
        @Expose
        private String dealId;
        @SerializedName("deal_start_date")
        @Expose
        private String dealStartDate;
        @SerializedName("deal_end_date")
        @Expose
        private String dealEndDate;
        @SerializedName("deal_title")
        @Expose
        private String dealTitle;
        @SerializedName("deal_image")
        @Expose
        private String dealImage;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("deal_status")
        @Expose
        private String dealStatus;
        @SerializedName("added_date")
        @Expose
        private String addedDate;
        @SerializedName("product")
        @Expose
        private String product;
        @SerializedName("list_of_items_by_id")
        @Expose
        private String listOfItemsById;

        public String getDealId() {
            return dealId;
        }

        public void setDealId(String dealId) {
            this.dealId = dealId;
        }

        public String getDealStartDate() {
            return dealStartDate;
        }

        public void setDealStartDate(String dealStartDate) {
            this.dealStartDate = dealStartDate;
        }

        public String getDealEndDate() {
            return dealEndDate;
        }

        public void setDealEndDate(String dealEndDate) {
            this.dealEndDate = dealEndDate;
        }

        public String getDealTitle() {
            return dealTitle;
        }

        public void setDealTitle(String dealTitle) {
            this.dealTitle = dealTitle;
        }

        public String getDealImage() {
            return dealImage;
        }

        public void setDealImage(String dealImage) {
            this.dealImage = dealImage;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getDealStatus() {
            return dealStatus;
        }

        public void setDealStatus(String dealStatus) {
            this.dealStatus = dealStatus;
        }

        public String getAddedDate() {
            return addedDate;
        }

        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getListOfItemsById() {
            return listOfItemsById;
        }

        public void setListOfItemsById(String listOfItemsById) {
            this.listOfItemsById = listOfItemsById;
        }

    }
}
