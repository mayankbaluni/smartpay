package com.smart.pay.api;

import com.smart.pay.BuildConfig;


/**
 * Created by Sandeep on 28-12-2017.
 */

public class ApiUtils {


    private ApiUtils() {
    }


    public static final String BASE_URL = BuildConfig.BASE_URL;

    public static MainAPIInterface getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(MainAPIInterface.class);
    }

}
