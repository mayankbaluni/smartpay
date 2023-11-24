package com.smart.pay.models.output;

import java.util.ArrayList;

public class TrainSearchResult {

    String trainName, trainArrivalTime, trainArrivalStation, trainDepartureTime, trainDepartureStation,
            trainTotalTravelTime;

    ArrayList<CoachType> coachTypeArrayList;


    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainArrivalTime() {
        return trainArrivalTime;
    }

    public void setTrainArrivalTime(String trainArrivalTime) {
        this.trainArrivalTime = trainArrivalTime;
    }

    public String getTrainArrivalStation() {
        return trainArrivalStation;
    }

    public void setTrainArrivalStation(String trainArrivalStation) {
        this.trainArrivalStation = trainArrivalStation;
    }

    public String getTrainDepartureTime() {
        return trainDepartureTime;
    }

    public void setTrainDepartureTime(String trainDepartureTime) {
        this.trainDepartureTime = trainDepartureTime;
    }

    public String getTrainDepartureStation() {
        return trainDepartureStation;
    }

    public void setTrainDepartureStation(String trainDepartureStation) {
        this.trainDepartureStation = trainDepartureStation;
    }


    public String getTrainTotalTravelTime() {
        return trainTotalTravelTime;
    }

    public void setTrainTotalTravelTime(String trainTotalTravelTime) {
        this.trainTotalTravelTime = trainTotalTravelTime;
    }

    public ArrayList<CoachType> getCoachTypeArrayList() {
        return coachTypeArrayList;
    }

    public void setCoachTypeArrayList(ArrayList<CoachType> coachTypeArrayList) {
        this.coachTypeArrayList = coachTypeArrayList;
    }

    public static class CoachType {

        String coachClass, seatPrice, strAvailability, strIsTatkal, strLastUpdatedTime;

        public String getCoachClass() {
            return coachClass;
        }

        public void setCoachClass(String coachClass) {
            this.coachClass = coachClass;
        }

        public String getSeatPrice() {
            return seatPrice;
        }

        public void setSeatPrice(String seatPrice) {
            this.seatPrice = seatPrice;
        }

        public String getStrAvailability() {
            return strAvailability;
        }

        public void setStrAvailability(String strAvailability) {
            this.strAvailability = strAvailability;
        }

        public String getStrIsTatkal() {
            return strIsTatkal;
        }

        public void setStrIsTatkal(String strIsTatkal) {
            this.strIsTatkal = strIsTatkal;
        }

        public String getStrLastUpdatedTime() {
            return strLastUpdatedTime;
        }

        public void setStrLastUpdatedTime(String strLastUpdatedTime) {
            this.strLastUpdatedTime = strLastUpdatedTime;
        }


    }

}
