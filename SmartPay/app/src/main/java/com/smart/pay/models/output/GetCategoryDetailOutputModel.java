package com.smart.pay.models.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetCategoryDetailOutputModel {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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


    public class Product {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("subcategory_id")
        @Expose
        private String subcategoryId;
        @SerializedName("child_category_id")
        @Expose
        private String childCategoryId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("stock")
        @Expose
        private String stock;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("is_trending")
        @Expose
        private String isTrending;
        @SerializedName("added_date")
        @Expose
        private String addedDate;
        @SerializedName("modified_date")
        @Expose
        private String modifiedDate;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("no_of_rating")
        @Expose
        private Integer noOfRating;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsTrending() {
            return isTrending;
        }

        public void setIsTrending(String isTrending) {
            this.isTrending = isTrending;
        }

        public String getAddedDate() {
            return addedDate;
        }

        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public Integer getNoOfRating() {
            return noOfRating;
        }

        public void setNoOfRating(Integer noOfRating) {
            this.noOfRating = noOfRating;
        }

    }
}
