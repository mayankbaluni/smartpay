package com.smart.pay.models.output;

import java.util.ArrayList;

public class TheaterListOutput {

    String theaterName;
    String theaterDistance;

    ArrayList<ShowTime> showTimeArrayList;

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterDistance(String theaterDistance) {
        this.theaterDistance = theaterDistance;
    }

    public ArrayList<ShowTime> getShowTimeArrayList() {
        return showTimeArrayList;
    }

    public void setShowTimeArrayList(ArrayList<ShowTime> showTimeArrayList) {
        this.showTimeArrayList = showTimeArrayList;
    }

    public String getTheaterDistance() {
        return theaterDistance;
    }

    public static class ShowTime {

        String showTime;

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }
    }

}
