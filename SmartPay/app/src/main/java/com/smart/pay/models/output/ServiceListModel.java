package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 21-11-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ServiceListModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cat")
    @Expose
    private ArrayList<Service> cat = null;

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

    public ArrayList<Service> getCat() {
        return cat;
    }

    public void setCat(ArrayList<Service> cat) {
        this.cat = cat;
    }


    public class Service {

        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("cat_image")
        @Expose
        private String catImage;

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getCatImage() {
            return catImage;
        }

        public void setCatImage(String catImage) {
            this.catImage = catImage;
        }

    }
}
