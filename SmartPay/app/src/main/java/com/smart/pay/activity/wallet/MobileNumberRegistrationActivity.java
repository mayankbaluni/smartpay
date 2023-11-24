package com.smart.pay.activity.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hbb20.CountryCodePicker;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.NormalResponseBody;
import com.smart.pay.models.output.UserProfileOutput;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileNumberRegistrationActivity extends AppCompatActivity {


    CountryCodePicker edtCustomerCountryCode;
    MyEditText edtCustomerMobileNumber, edtOtpNumber;

    LinearLayout llVerification, llVerification2;

    FloatingActionButton send_customer_otp, confirm_otp;

    MyTextView btnResendOtp;
    MyTextView txtTimer;
    ProgressDialog dialog;
    MainAPIInterface mainAPIInterface;
    int seconds = 60;
    int minutes = 0;

    //Declare the timer
    Timer newTimer;

    MyTextView customer_login;
    String strCustomerPhone;
    String strCustomerCountryCode;

    String strOtp, sentOtp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_mobile_verification);

        mainAPIInterface = ApiUtils.getAPIService();

        edtCustomerCountryCode = (CountryCodePicker) findViewById(R.id.edtCustomerCountryCode);

        send_customer_otp = (FloatingActionButton) findViewById(R.id.send_customer_otp);
        confirm_otp = (FloatingActionButton) findViewById(R.id.confirm_otp);

        edtCustomerMobileNumber = (MyEditText) findViewById(R.id.edtCustomerMobileNumber);
        edtOtpNumber = (MyEditText) findViewById(R.id.edtOtpNumber);

        customer_login = (MyTextView) findViewById(R.id.customer_login);

        btnResendOtp = (MyTextView) findViewById(R.id.btnResendOtp);

        llVerification = (LinearLayout) findViewById(R.id.llVerification);
        llVerification2 = (LinearLayout) findViewById(R.id.llVerification2);

        newTimer = new Timer();
        txtTimer = (MyTextView) findViewById(R.id.txtTimer);


        customer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(MobileNumberRegistrationActivity.this, SignInActivity.class);
                startActivity(newIntent);
                finish();

            }
        });

        send_customer_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strCustomerPhone = edtCustomerMobileNumber.getText().toString();
                strCustomerCountryCode = edtCustomerCountryCode.getSelectedCountryCode();

                if (strCustomerPhone.length() < 5) {

                    edtCustomerMobileNumber.setFocusable(true);
                    edtCustomerMobileNumber.setError("Please enter a valid phone no.");

                } else {

                    sendCustomerOtpRequest();
                }
            }
        });


        btnResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reSendCustomerOtpRequest();

            }
        });


        confirm_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                strOtp = edtOtpNumber.getText().toString();

                if (strOtp.length() < 6) {
                    edtOtpNumber.setFocusable(true);
                    edtOtpNumber.setError("Please enter OTP");
                } else {

                    confirmOtpRequest();
                }

            }
        });

    }

    private void sendCustomerOtpRequest() {

        String xAccessToken = "mykey";

        dialog = new ProgressDialog(MobileNumberRegistrationActivity.this);

        dialog.setMessage("Sending OTP.");
        dialog.show();

        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", strCustomerPhone);
        MultipartBody.Part country_code_body = MultipartBody.Part.createFormData("country_code", strCustomerCountryCode);


        mainAPIInterface.registerUserMobile(xAccessToken, phone_body, country_code_body).enqueue(new Callback<UserProfileOutput>() {
            @Override
            public void onResponse(Call<UserProfileOutput> call, Response<UserProfileOutput> response) {

                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {


                        sentOtp = response.body().getProfile().getOtp();

                        edtOtpNumber.setText(sentOtp);

                        llVerification.setVisibility(View.GONE);
                        llVerification2.setVisibility(View.VISIBLE);

                        //Set the schedule function and rate
                        newTimer.scheduleAtFixedRate(new TimerTask() {

                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        txtTimer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                        seconds -= 1;

                                        if (seconds == 0) {
                                            txtTimer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

                                            seconds = 60;
                                            minutes = minutes - 1;

                                        }


                                    }

                                });
                            }

                        }, 0, 1000);


                    } else if (response.body().getSuccess().equalsIgnoreCase("2")) {

                        sentOtp = response.body().getProfile().getOtp();

                        edtOtpNumber.setText(sentOtp);

                        llVerification.setVisibility(View.GONE);
                        llVerification2.setVisibility(View.VISIBLE);

                        //Set the schedule function and rate
                        newTimer.scheduleAtFixedRate(new TimerTask() {

                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        txtTimer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                        seconds -= 1;

                                        if (seconds == 0) {
                                            txtTimer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

                                            seconds = 60;
                                            minutes = minutes - 1;

                                        }


                                    }

                                });
                            }

                        }, 0, 1000);


                    }

                }
            }

            @Override
            public void onFailure(Call<UserProfileOutput> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }


    private void reSendCustomerOtpRequest() {

        String xAccessToken = "mykey";

        dialog = new ProgressDialog(MobileNumberRegistrationActivity.this);

        dialog.setMessage("Sending OTP.");
        dialog.show();

        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", strCustomerPhone);
        MultipartBody.Part country_code_body = MultipartBody.Part.createFormData("country_code", strCustomerPhone);


        mainAPIInterface.registerUserMobile(xAccessToken, phone_body, country_code_body).enqueue(new Callback<UserProfileOutput>() {
            @Override
            public void onResponse(Call<UserProfileOutput> call, Response<UserProfileOutput> response) {

                if (response.isSuccessful()) {

                    dialog.dismiss();

                    llVerification.setVisibility(View.GONE);
                    llVerification2.setVisibility(View.VISIBLE);

                    //Set the schedule function and rate
                    newTimer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    txtTimer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                    seconds -= 1;

                                    if (seconds == 0) {
                                        txtTimer.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

                                        seconds = 60;
                                        minutes = minutes - 1;

                                    }


                                }

                            });
                        }

                    }, 0, 1000);


                }


            }

            @Override
            public void onFailure(Call<UserProfileOutput> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void confirmOtpRequest() {

        String xAccessToken = "mykey";

        dialog = new ProgressDialog(MobileNumberRegistrationActivity.this);

        dialog.setMessage("Confirming your OTP.");
        dialog.show();

        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", strCustomerPhone);

        MultipartBody.Part otp_body = MultipartBody.Part.createFormData("otp", strOtp);


        mainAPIInterface.verifyUserMobile(xAccessToken, phone_body, otp_body).enqueue(new Callback<NormalResponseBody>() {
            @Override
            public void onResponse(Call<NormalResponseBody> call, Response<NormalResponseBody> response) {

                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        SmartPayApplication.CUSTOMER_MOBILE = strCustomerPhone;

                        Intent intent = new Intent(MobileNumberRegistrationActivity.this, SignupAcitivity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(MobileNumberRegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(MobileNumberRegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<NormalResponseBody> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }
}
