package com.smart.pay.models.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllChildCategoryOutput {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("child_category")
    @Expose
    private ArrayList<ChildCategory> childCategory = null;

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

    public ArrayList<ChildCategory> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(ArrayList<ChildCategory> childCategory) {
        this.childCategory = childCategory;
    }


    public class ChildCategory {

        @SerializedName("child_cat_id")
        @Expose
        private String childCatId;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("subcategory_id")
        @Expose
        private String subcategoryId;
        @SerializedName("child_cat_name")
        @Expose
        private String childCatName;
        @SerializedName("child_cat_image")
        @Expose
        private String childCatImage;
        @SerializedName("added_date")
        @Expose
        private String addedDate;

        public String getChildCatId() {
            return childCatId;
        }

        public void setChildCatId(String childCatId) {
            this.childCatId = childCatId;
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

        public String getChildCatName() {
            return childCatName;
        }

        public void setChildCatName(String childCatName) {
            this.childCatName = childCatName;
        }

        public String getChildCatImage() {
            return childCatImage;
        }

        public void setChildCatImage(String childCatImage) {
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