package com.smart.pay.models.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetHomepageSliderOutputModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sliders")
    @Expose
    private List<Slider> sliders = null;

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

    public List<Slider> getSliders() {
        return sliders;
    }

    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
    }

    public class Slider {

        @SerializedName("slide_id")
        @Expose
        private String slideId;
        @SerializedName("slider_image")
        @Expose
        private String sliderImage;
        @SerializedName("slider_text")
        @Expose
        private String sliderText;
        @SerializedName("slider_category_id")
        @Expose
        private String sliderCategoryId;
        @SerializedName("slider_subcategory_id")
        @Expose
        private String sliderSubcategoryId;
        @SerializedName("added_date")
        @Expose
        private String addedDate;

        public String getSlideId() {
            return slideId;
        }

        public void setSlideId(String slideId) {
            this.slideId = slideId;
        }

        public String getSliderImage() {
            return sliderImage;
        }

        public void setSliderImage(String sliderImage) {
            this.sliderImage = sliderImage;
        }

        public String getSliderText() {
            return sliderText;
        }

        public void setSliderText(String sliderText) {
            this.sliderText = sliderText;
        }

        public String getSliderCategoryId() {
            return sliderCategoryId;
        }

        public void setSliderCategoryId(String sliderCategoryId) {
            this.sliderCategoryId = sliderCategoryId;
        }

        public String getSliderSubcategoryId() {
            return sliderSubcategoryId;
        }

        public void setSliderSubcategoryId(String sliderSubcategoryId) {
            this.sliderSubcategoryId = sliderSubcategoryId;
        }

        public String getAddedDate() {
            return addedDate;
        }

        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

    }
}
