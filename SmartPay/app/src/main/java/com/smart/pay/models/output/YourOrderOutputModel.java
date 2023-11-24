package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 01-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YourOrderOutputModel {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orders")
    @Expose
    private ArrayList<Order> orders = null;

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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }


    public class Order {

        @SerializedName("order_id")
        @Expose
        private String id;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("subcat_id")
        @Expose
        private String subcatId;
        @SerializedName("date_time")
        @Expose
        private String dateTime;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("customer_firstname")
        @Expose
        private String customerFirstname;
        @SerializedName("customer_lastname")
        @Expose
        private String customerLastname;
        @SerializedName("customer_zipcode")
        @Expose
        private String customerZipcode;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("customer_country")
        @Expose
        private String customerCountry;
        @SerializedName("customer_city")
        @Expose
        private String customerCity;
        @SerializedName("customer_address")
        @Expose
        private String customerAddress;
        @SerializedName("customer_phone_number")
        @Expose
        private String customerPhoneNumber;
        @SerializedName("customer_email_address")
        @Expose
        private String customerEmailAddress;
        @SerializedName("total_amount")
        @Expose
        private String totalAmount;
        @SerializedName("payment_type")
        @Expose
        private String paymentType;
        @SerializedName("paypal_payment_id")
        @Expose
        private String paypalPaymentId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("description")
        @Expose
        private String description;
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
        @SerializedName("is_trending")
        @Expose
        private String isTrending;
        @SerializedName("added_date")
        @Expose
        private String addedDate;
        @SerializedName("modified_date")
        @Expose
        private String modifiedDate;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("data_of_delivery")
        @Expose
        private String data_of_delivery;

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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getSubcatId() {
            return subcatId;
        }

        public void setSubcatId(String subcatId) {
            this.subcatId = subcatId;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerFirstname() {
            return customerFirstname;
        }

        public void setCustomerFirstname(String customerFirstname) {
            this.customerFirstname = customerFirstname;
        }

        public String getCustomerLastname() {
            return customerLastname;
        }

        public void setCustomerLastname(String customerLastname) {
            this.customerLastname = customerLastname;
        }

        public String getCustomerZipcode() {
            return customerZipcode;
        }

        public void setCustomerZipcode(String customerZipcode) {
            this.customerZipcode = customerZipcode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCustomerCountry() {
            return customerCountry;
        }

        public void setCustomerCountry(String customerCountry) {
            this.customerCountry = customerCountry;
        }

        public String getCustomerCity() {
            return customerCity;
        }

        public void setCustomerCity(String customerCity) {
            this.customerCity = customerCity;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
        }

        public String getCustomerPhoneNumber() {
            return customerPhoneNumber;
        }

        public void setCustomerPhoneNumber(String customerPhoneNumber) {
            this.customerPhoneNumber = customerPhoneNumber;
        }

        public String getCustomerEmailAddress() {
            return customerEmailAddress;
        }

        public void setCustomerEmailAddress(String customerEmailAddress) {
            this.customerEmailAddress = customerEmailAddress;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPaypalPaymentId() {
            return paypalPaymentId;
        }

        public void setPaypalPaymentId(String paypalPaymentId) {
            this.paypalPaymentId = paypalPaymentId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
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

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getData_of_delivery() {
            return data_of_delivery;
        }

        public void setData_of_delivery(String data_of_delivery) {
            this.data_of_delivery = data_of_delivery;
        }
    }

}
