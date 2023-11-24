package com.smart.pay;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetAppSettings;
import com.smart.pay.utils.ThreadHandeling;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartPayApplication extends Application {

    private static SmartPayApplication mInstance;
    Context context;
    public SharedPreferences preferences;
    public String prefName;

    MainAPIInterface mainAPIInterface;

    public static String TRACKING_MORE_API_KEY = "";
    public static String GOOGLE_MAP_API_KEY = "";
    public static String APP_SUPPORT_EMAIL = "";
    public static String APP_SUPPORT_PHONE = "";
    public static String ABOUT_APP = "";
    public static String CURRENCY_SYMBOL = "";
    public static String CURRENCY_CODE = "";
    public static String CURRENCY_NAME = "";
    public static String CURRENCY_EXCHANGE_RATE = "1";
    public static String PAYPAL_CLIENT_ID = "ATjxw8s9ZJG1t7fcfanIMtK52zy78O2pkSh-u-8AQvWLBBri44fsaoF5YKrtzsBrcMG8f8alla1J-bVM";
    public static String PAYPAL_SECRET = "ATjxw8s9ZJG1t7fcfanIMtK52zy78O2pkSh-u-8AQvWLBBri44fsaoF5YKrtzsBrcMG8f8alla1J-bVM";
    public static String PAYPAL_SANDBOX = "1";

    public static String CUSTOMER_MOBILE = "mobile";

    public SmartPayApplication() {
        mInstance = this;
    }

    public SmartPayApplication(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new ThreadHandeling());
        mInstance = this;
        prefName = getResources().getString(R.string.app_name);
        preferences = getSharedPreferences(prefName, MODE_PRIVATE);

        mainAPIInterface = ApiUtils.getAPIService();

        getAppSettingsRequest();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized SmartPayApplication getInstance() {
        return mInstance;
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean outcome = false;

        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

            for (NetworkInfo tempNetworkInfo : networkInfos) {


                /**
                 * Can also check if the user is in roaming
                 */
                if (tempNetworkInfo.isConnected()) {
                    outcome = true;
                    break;
                }
            }
        }

        return outcome;
    }

    public void getAppSettingsRequest() {
        String xAccessToken = "mykey";


        mainAPIInterface.getAppSettingsRequest(xAccessToken).enqueue(new Callback<GetAppSettings>() {
            @Override
            public void onResponse(Call<GetAppSettings> call, Response<GetAppSettings> response) {
                if (response.isSuccessful()) {

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {


                        TRACKING_MORE_API_KEY = response.body().getSettings().getTrackingMoreKey();
                        GOOGLE_MAP_API_KEY = response.body().getSettings().getGoogleMapKey();
                        APP_SUPPORT_EMAIL = response.body().getSettings().getSupportEmail();
                        APP_SUPPORT_PHONE = response.body().getSettings().getSupportPhone();
                        ABOUT_APP = response.body().getSettings().getAboutUs();

                        CURRENCY_SYMBOL = response.body().getCurrency().getListSymbol();
                        CURRENCY_CODE = response.body().getCurrency().getListCc();
                        CURRENCY_NAME = response.body().getCurrency().getListName();
                        CURRENCY_EXCHANGE_RATE = response.body().getCurrency().getExchangeRate();

                        PAYPAL_CLIENT_ID = response.body().getSettings().getPaypalClientId();
                        PAYPAL_SECRET = response.body().getSettings().getPaypalClientSecret();


                    }
                }
            }

            @Override
            public void onFailure(Call<GetAppSettings> call, Throwable t) {


                Log.i("tag", t.getMessage().toString());
            }
        });

    }

}
