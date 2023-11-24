package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 07-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SellerDetails {

    @SerializedName("success")
    @Expose
    public int success;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("profile")
    @Expose
    public List<Profile> profile = null;

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

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public class Profile {

        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("seller_name")
        @Expose
        private String sellerName;
        @SerializedName("seller_logo")
        @Expose
        private String sellerLogo;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("contact_email")
        @Expose
        private String contactEmail;
        @SerializedName("seller_address")
        @Expose
        private String sellerAddress;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("payment_type")
        @Expose
        private String paymentType;
        @SerializedName("bank name")
        @Expose
        private String bankName;
        @SerializedName("bank_ifsc")
        @Expose
        private String bankIfsc;
        @SerializedName("bank_account_no")
        @Expose
        private String bankAccountNo;
        @SerializedName("bank_branch")
        @Expose
        private String bankBranch;
        @SerializedName("paypal_email")
        @Expose
        private String paypalEmail;
        @SerializedName("updated_date")
        @Expose
        private String updatedDate;

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerLogo() {
            return sellerLogo;
        }

        public void setSellerLogo(String sellerLogo) {
            this.sellerLogo = sellerLogo;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getSellerAddress() {
            return sellerAddress;
        }

        public void setSellerAddress(String sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankIfsc() {
            return bankIfsc;
        }

        public void setBankIfsc(String bankIfsc) {
            this.bankIfsc = bankIfsc;
        }

        public String getBankAccountNo() {
            return bankAccountNo;
        }

        public void setBankAccountNo(String bankAccountNo) {
            this.bankAccountNo = bankAccountNo;
        }

        public String getBankBranch() {
            return bankBranch;
        }

        public void setBankBranch(String bankBranch) {
            this.bankBranch = bankBranch;
        }

        public String getPaypalEmail() {
            return paypalEmail;
        }

        public void setPaypalEmail(String paypalEmail) {
            this.paypalEmail = paypalEmail;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

    }
}