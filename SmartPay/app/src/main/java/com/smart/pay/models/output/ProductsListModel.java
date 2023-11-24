package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 23-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ProductsListModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;
    @SerializedName("message")
    @Expose
    private String message;

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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Product {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("seller_id")
        @Expose
        public String sellerId;
        @SerializedName("product_name")
        @Expose
        public String productName;
        @SerializedName("product_image")
        @Expose
        public String productImage;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("category_id")
        @Expose
        public String categoryId;
        @SerializedName("subcategory_id")
        @Expose
        public String subcategoryId;
        @SerializedName("child_category_id")
        @Expose
        public String childCategoryId;
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("discount")
        @Expose
        public String discount;
        @SerializedName("stock")
        @Expose
        public String stock;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("is_trending")
        @Expose
        public String isTrending;
        @SerializedName("added_date")
        @Expose
        public String addedDate;
        @SerializedName("modified_date")
        @Expose
        public String modifiedDate;
        @SerializedName("rating")
        @Expose
        public String rating;
        @SerializedName("no_of_rating")
        @Expose
        public String noOfRating;
        @SerializedName("no_of_reviews")
        @Expose
        public String noOfReviews;
        @SerializedName("product_images")
        @Expose
        public ArrayList<ProductImage> productImages = null;

        public Product(String id) {
            this.id = id;
        }

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

        public String getNoOfRating() {
            return noOfRating;
        }

        public void setNoOfRating(String noOfRating) {
            this.noOfRating = noOfRating;
        }

        public String getNoOfReviews() {
            return noOfReviews;
        }

        public void setNoOfReviews(String noOfReviews) {
            this.noOfReviews = noOfReviews;
        }

        public ArrayList<ProductImage> getProductImages() {
            return productImages;
        }

        public void setProductImages(ArrayList<ProductImage> productImages) {
            this.productImages = productImages;
        }

    }

    public class ProductImage {

        @SerializedName("image_id")
        @Expose
        private String imageId;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("added_date")
        @Expose
        private String addedDate;

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getAddedDate() {
            return addedDate;
        }

        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

    }
}
