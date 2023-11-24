package com.smart.pay.models.output;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetAllFashionOffersModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("offers")
    @Expose
    private ArrayList<Offer> offers = null;

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

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }


    public class Offer {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("subcategory_id")
        @Expose
        private String subcategoryId;
        @SerializedName("child_category_id")
        @Expose
        private String childCategoryId;
        @SerializedName("child_cat_id")
        @Expose
        private String childCatId;
        @SerializedName("child_cat_name")
        @Expose
        private String childCatName;
        @SerializedName("child_cat_image")
        @Expose
        private Object childCatImage;
        @SerializedName("added_date")
        @Expose
        private String addedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getSubcategoryId() {
            return subcategoryId;
        }

        public void setSubcategoryId(String subcategoryId) {
            this.subcategoryId = subcategoryId;
        }

        public String getChildCategoryId() {
            return childCategoryId;
        }

        public void setChildCategoryId(String childCategoryId) {
            this.childCategoryId = childCategoryId;
        }

        public String getChildCatId() {
            return childCatId;
        }

        public void setChildCatId(String childCatId) {
            this.childCatId = childCatId;
        }

        public String getChildCatName() {
            return childCatName;
        }

        public void setChildCatName(String childCatName) {
            this.childCatName = childCatName;
        }

        public Object getChildCatImage() {
            return childCatImage;
        }

        public void setChildCatImage(Object childCatImage) {
            this.childCatImage = childCatImage;
        }

        public String getAddedDate() {
            return addedDate;
        }

        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

    }
}
