package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 07-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductReviewListOutputModel {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("average_rating")
    @Expose
    private String averageRating;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("reviews")
    @Expose
    private Integer reviews;
    @SerializedName("rating_one")
    @Expose
    private Integer ratingOne;
    @SerializedName("rating_two")
    @Expose
    private Integer ratingTwo;
    @SerializedName("rating_three")
    @Expose
    private Integer ratingThree;
    @SerializedName("rating_four")
    @Expose
    private Integer ratingFour;
    @SerializedName("rating_five")
    @Expose
    private Integer ratingFive;
    @SerializedName("data")
    @Expose
    private ArrayList<Reviews> data = null;

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

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public Integer getRatingOne() {
        return ratingOne;
    }

    public void setRatingOne(Integer ratingOne) {
        this.ratingOne = ratingOne;
    }

    public Integer getRatingTwo() {
        return ratingTwo;
    }

    public void setRatingTwo(Integer ratingTwo) {
        this.ratingTwo = ratingTwo;
    }

    public Integer getRatingThree() {
        return ratingThree;
    }

    public void setRatingThree(Integer ratingThree) {
        this.ratingThree = ratingThree;
    }

    public Integer getRatingFour() {
        return ratingFour;
    }

    public void setRatingFour(Integer ratingFour) {
        this.ratingFour = ratingFour;
    }

    public Integer getRatingFive() {
        return ratingFive;
    }

    public void setRatingFive(Integer ratingFive) {
        this.ratingFive = ratingFive;
    }

    public ArrayList<Reviews> getData() {
        return data;
    }

    public void setData(ArrayList<Reviews> data) {
        this.data = data;
    }


    public class Reviews {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("customer_name")
        @Expose
        private String customerName;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("date_added")
        @Expose
        private String dateAdded;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

    }

}
