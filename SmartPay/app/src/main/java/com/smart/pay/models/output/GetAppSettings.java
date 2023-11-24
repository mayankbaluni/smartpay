package com.smart.pay.models.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAppSettings {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("settings")
    @Expose
    private Settings settings;
    @SerializedName("currency")
    @Expose
    private Currency currency;

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

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public class Currency {

        @SerializedName("currency_id")
        @Expose
        private String currencyId;
        @SerializedName("list_cc")
        @Expose
        private String listCc;
        @SerializedName("list_symbol")
        @Expose
        private String listSymbol;
        @SerializedName("list_name")
        @Expose
        private String listName;
        @SerializedName("exchange_rate")
        @Expose
        private String exchangeRate;
        @SerializedName("status")
        @Expose
        private String status;

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public String getListCc() {
            return listCc;
        }

        public void setListCc(String listCc) {
            this.listCc = listCc;
        }

        public String getListSymbol() {
            return listSymbol;
        }

        public void setListSymbol(String listSymbol) {
            this.listSymbol = listSymbol;
        }

        public String getListName() {
            return listName;
        }

        public void setListName(String listName) {
            this.listName = listName;
        }

        public String getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


    }

    public class Settings {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("favicon")
        @Expose
        private String favicon;
        @SerializedName("smtp_username")
        @Expose
        private String smtpUsername;
        @SerializedName("smtp_host")
        @Expose
        private String smtpHost;
        @SerializedName("smtp_password")
        @Expose
        private String smtpPassword;
        @SerializedName("mail_port")
        @Expose
        private String mailPort;
        @SerializedName("mail_encryption")
        @Expose
        private String mailEncryption;
        @SerializedName("mail_from_address")
        @Expose
        private String mailFromAddress;
        @SerializedName("mail_from_name")
        @Expose
        private String mailFromName;
        @SerializedName("admin_email")
        @Expose
        private String adminEmail;
        @SerializedName("payapal_email")
        @Expose
        private Object payapalEmail;
        @SerializedName("paypal_client_id")
        @Expose
        private String paypalClientId;
        @SerializedName("paypal_client_secret")
        @Expose
        private String paypalClientSecret;
        @SerializedName("deafult_currency_id")
        @Expose
        private Object deafultCurrencyId;
        @SerializedName("default_currency_symbol")
        @Expose
        private String defaultCurrencySymbol;
        @SerializedName("default_currency_name")
        @Expose
        private String defaultCurrencyName;
        @SerializedName("firebase_api_key")
        @Expose
        private String firebaseApiKey;
        @SerializedName("google_map_key")
        @Expose
        private String googleMapKey;
        @SerializedName("tracking_more_key")
        @Expose
        private String trackingMoreKey;
        @SerializedName("support_email")
        @Expose
        private String supportEmail;
        @SerializedName("support_phone")
        @Expose
        private String supportPhone;
        @SerializedName("about_us")
        @Expose
        private String aboutUs;
        @SerializedName("seller_commission")
        @Expose
        private String sellerCommission;
        @SerializedName("status")
        @Expose
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getFavicon() {
            return favicon;
        }

        public void setFavicon(String favicon) {
            this.favicon = favicon;
        }

        public String getSmtpUsername() {
            return smtpUsername;
        }

        public void setSmtpUsername(String smtpUsername) {
            this.smtpUsername = smtpUsername;
        }

        public String getSmtpHost() {
            return smtpHost;
        }

        public void setSmtpHost(String smtpHost) {
            this.smtpHost = smtpHost;
        }

        public String getSmtpPassword() {
            return smtpPassword;
        }

        public void setSmtpPassword(String smtpPassword) {
            this.smtpPassword = smtpPassword;
        }

        public String getMailPort() {
            return mailPort;
        }

        public void setMailPort(String mailPort) {
            this.mailPort = mailPort;
        }

        public String getMailEncryption() {
            return mailEncryption;
        }

        public void setMailEncryption(String mailEncryption) {
            this.mailEncryption = mailEncryption;
        }

        public String getMailFromAddress() {
            return mailFromAddress;
        }

        public void setMailFromAddress(String mailFromAddress) {
            this.mailFromAddress = mailFromAddress;
        }

        public String getMailFromName() {
            return mailFromName;
        }

        public void setMailFromName(String mailFromName) {
            this.mailFromName = mailFromName;
        }

        public String getAdminEmail() {
            return adminEmail;
        }

        public void setAdminEmail(String adminEmail) {
            this.adminEmail = adminEmail;
        }

        public Object getPayapalEmail() {
            return payapalEmail;
        }

        public void setPayapalEmail(Object payapalEmail) {
            this.payapalEmail = payapalEmail;
        }

        public String getPaypalClientId() {
            return paypalClientId;
        }

        public void setPaypalClientId(String paypalClientId) {
            this.paypalClientId = paypalClientId;
        }

        public String getPaypalClientSecret() {
            return paypalClientSecret;
        }

        public void setPaypalClientSecret(String paypalClientSecret) {
            this.paypalClientSecret = paypalClientSecret;
        }

        public Object getDeafultCurrencyId() {
            return deafultCurrencyId;
        }

        public void setDeafultCurrencyId(Object deafultCurrencyId) {
            this.deafultCurrencyId = deafultCurrencyId;
        }

        public String getDefaultCurrencySymbol() {
            return defaultCurrencySymbol;
        }

        public void setDefaultCurrencySymbol(String defaultCurrencySymbol) {
            this.defaultCurrencySymbol = defaultCurrencySymbol;
        }

        public String getDefaultCurrencyName() {
            return defaultCurrencyName;
        }

        public void setDefaultCurrencyName(String defaultCurrencyName) {
            this.defaultCurrencyName = defaultCurrencyName;
        }

        public String getFirebaseApiKey() {
            return firebaseApiKey;
        }

        public void setFirebaseApiKey(String firebaseApiKey) {
            this.firebaseApiKey = firebaseApiKey;
        }

        public String getGoogleMapKey() {
            return googleMapKey;
        }

        public void setGoogleMapKey(String googleMapKey) {
            this.googleMapKey = googleMapKey;
        }

        public String getTrackingMoreKey() {
            return trackingMoreKey;
        }

        public void setTrackingMoreKey(String trackingMoreKey) {
            this.trackingMoreKey = trackingMoreKey;
        }

        public String getSupportEmail() {
            return supportEmail;
        }

        public void setSupportEmail(String supportEmail) {
            this.supportEmail = supportEmail;
        }

        public String getSupportPhone() {
            return supportPhone;
        }

        public void setSupportPhone(String supportPhone) {
            this.supportPhone = supportPhone;
        }

        public String getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
        }

        public String getSellerCommission() {
            return sellerCommission;
        }

        public void setSellerCommission(String sellerCommission) {
            this.sellerCommission = sellerCommission;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
