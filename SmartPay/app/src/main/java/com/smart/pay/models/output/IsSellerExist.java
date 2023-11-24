package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 04-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsSellerExist {

    @SerializedName("success")
    @Expose
    public int success;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
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

    @Override
    public String toString() {
        return "success" + success + "status" + status + "message";
    }
}