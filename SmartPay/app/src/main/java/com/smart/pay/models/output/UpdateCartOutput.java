package com.smart.pay.models.output;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCartOutput {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("single_product_price")
    @Expose
    private double singleProductPrice;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("old_price")
    @Expose
    private double old_price;


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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSingleProductPrice() {
        return singleProductPrice;
    }

    public void setSingleProductPrice(double singleProductPrice) {
        this.singleProductPrice = singleProductPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }
}
