package com.smart.pay.models.output;

/**
 * Created by Sandeep Londhe on 04-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YourAppointmentOutputModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("booking")
    @Expose
    private ArrayList<Booking> booking = null;

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

    public ArrayList<Booking> getBooking() {
        return booking;
    }

    public void setBooking(ArrayList<Booking> booking) {
        this.booking = booking;
    }


    public class Booking {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("booking_id")
        @Expose
        private String bookingId;
        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("service_type")
        @Expose
        private String serviceType;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("customer_name")
        @Expose
        private String customerName;
        @SerializedName("customer_email")
        @Expose
        private String customerEmail;
        @SerializedName("customer_phone")
        @Expose
        private String customerPhone;
        @SerializedName("booking_date")
        @Expose
        private String bookingDate;
        @SerializedName("booking_time")
        @Expose
        private String bookingTime;
        @SerializedName("paypal_payment_id")
        @Expose
        private String paypalPaymentId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("booked_date")
        @Expose
        private String bookedDate;
        @SerializedName("service_name")
        @Expose
        private String serviceName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("service_image")
        @Expose
        private String serviceImage;
        @SerializedName("service_cat_id")
        @Expose
        private String serviceCatId;
        @SerializedName("service_sub_cat_id")
        @Expose
        private String serviceSubCatId;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("max_no_of_bookings")
        @Expose
        private String maxNoOfBookings;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("modified_date")
        @Expose
        private String modifiedDate;
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
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("registration_date")
        @Expose
        private String registrationDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
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

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getBookingTime() {
            return bookingTime;
        }

        public void setBookingTime(String bookingTime) {
            this.bookingTime = bookingTime;
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

        public String getBookedDate() {
            return bookedDate;
        }

        public void setBookedDate(String bookedDate) {
            this.bookedDate = bookedDate;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getServiceImage() {
            return serviceImage;
        }

        public void setServiceImage(String serviceImage) {
            this.serviceImage = serviceImage;
        }

        public String getServiceCatId() {
            return serviceCatId;
        }

        public void setServiceCatId(String serviceCatId) {
            this.serviceCatId = serviceCatId;
        }

        public String getServiceSubCatId() {
            return serviceSubCatId;
        }

        public void setServiceSubCatId(String serviceSubCatId) {
            this.serviceSubCatId = serviceSubCatId;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getMaxNoOfBookings() {
            return maxNoOfBookings;
        }

        public void setMaxNoOfBookings(String maxNoOfBookings) {
            this.maxNoOfBookings = maxNoOfBookings;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
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

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

    }
}
