package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.ForgotPasswordOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_EMAIL;
import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 10-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ChangePasswordActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    MyEditText edtOtp, edtOldPassword, edtNewPassword, edtConfirmNewPassword;

    MyTextView btnChangePassword, btnResendOtp;

    private ProgressDialog newProgressDialog;

    String strUserId, strOldPassword, strNewPassword, strConfirmPassword, strOtp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.change_password_layout);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        edtOtp = (MyEditText) findViewById(R.id.edtOtp);
        edtOldPassword = (MyEditText) findViewById(R.id.edtOldPassword);
        edtNewPassword = (MyEditText) findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = (MyEditText) findViewById(R.id.edtConfirmNewPassword);

        btnChangePassword = (MyTextView) findViewById(R.id.btnChangePassword);
        btnResendOtp = (MyTextView) findViewById(R.id.btnResendOtp);


        if (SmartPayApplication.isNetworkAvailable(ChangePasswordActivity.this)) {

            sendPasswordOTP();

        } else {

            Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_LONG).show();

        }


        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strOtp = edtOtp.getText().toString();
                strNewPassword = edtNewPassword.getText().toString();
                strOldPassword = edtOldPassword.getText().toString();
                strConfirmPassword = edtConfirmNewPassword.getText().toString();

                if (strOtp.equalsIgnoreCase("") || strOtp == null) {
                    edtOtp.setFocusable(true);
                    edtOtp.setError("OTP cannot be empty.");
                } else if (strNewPassword.equalsIgnoreCase("") || strNewPassword == null) {
                    edtNewPassword.setFocusable(true);
                    edtNewPassword.setError("Enter new password");
                } else if (strOldPassword.equalsIgnoreCase("") || strOldPassword == null) {

                    edtOldPassword.setFocusable(true);
                    edtOldPassword.setError("Enter new password");
                } else if (strNewPassword.length() < 6) {

                    edtNewPassword.setFocusable(true);
                    edtNewPassword.setError("Password should be atleast 6 characters.");
                } else if (strConfirmPassword.equalsIgnoreCase("") || strConfirmPassword == null) {
                    edtConfirmNewPassword.setFocusable(true);
                    edtConfirmNewPassword.setError("Enter new password");
                } else if (strConfirmPassword.length() < 6) {

                    edtConfirmNewPassword.setFocusable(true);
                    edtConfirmNewPassword.setError("Password should be atleast 6 characters.");

                } else {

                    if (strNewPassword.equalsIgnoreCase(strConfirmPassword)) {

                        if (SmartPayApplication.isNetworkAvailable(ChangePasswordActivity.this)) {

                            changePasswordRequest();

                        } else {

                            Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_LONG).show();

                        }


                    } else {

                        Toast.makeText(getApplicationContext(), "Please confirm the password correctly.", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });


        btnResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SmartPayApplication.isNetworkAvailable(ChangePasswordActivity.this)) {

                    sendPasswordOTP();

                } else {

                    Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_LONG).show();

                }


            }
        });


    }


    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);

        ImageView appLogo = toolbar.findViewById(R.id.appLogo);
        appLogo.setVisibility(View.GONE);

        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Change Password");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Change Password");

        toolbar.setTitleTextColor(Color.WHITE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // action bar menu behaviour
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void sendPasswordOTP() {

        String strUserId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        String strUserEmail = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_EMAIL);


        newProgressDialog = new ProgressDialog(ChangePasswordActivity.this);

        newProgressDialog.setMessage("Sending OTP to email.");

        newProgressDialog.show();

        String xAccessToken = "mykey";

        MultipartBody.Part user_id_body = MultipartBody.Part.createFormData("user_id", strUserId);
        MultipartBody.Part email_body = MultipartBody.Part.createFormData("email_id", strUserEmail);


        mainAPIInterface.sendPasswordChangeOtp(xAccessToken, user_id_body, email_body).enqueue(new Callback<ForgotPasswordOutputModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordOutputModel> call, Response<ForgotPasswordOutputModel> response) {
                if (response.isSuccessful()) {
                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();


                    }

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });

    }


    private void changePasswordRequest() {

        strUserId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        newProgressDialog = new ProgressDialog(ChangePasswordActivity.this);

        newProgressDialog.setMessage("Please wait while we are changing your password.");

        newProgressDialog.show();

        String xAccessToken = "mykey";

        MultipartBody.Part user_id_body = MultipartBody.Part.createFormData("user_id", strUserId);
        MultipartBody.Part user_old_password_body = MultipartBody.Part.createFormData("user_old_password", strOldPassword);
        MultipartBody.Part user_new_password_body = MultipartBody.Part.createFormData("user_new_password", strNewPassword);

        MultipartBody.Part user_otp_body = MultipartBody.Part.createFormData("otp", strOtp);


        mainAPIInterface.changePasswordRequest(xAccessToken, user_id_body, user_old_password_body, user_new_password_body, user_otp_body).enqueue(new Callback<ForgotPasswordOutputModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordOutputModel> call, Response<ForgotPasswordOutputModel> response) {
                if (response.isSuccessful()) {
                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {


                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();


                    }

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

}

