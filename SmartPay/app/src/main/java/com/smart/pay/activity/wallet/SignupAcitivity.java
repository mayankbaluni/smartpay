package com.smart.pay.activity.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.UserProfileOutput;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupAcitivity extends AppCompatActivity {

    MyTextView btnSignUp;
    MyTextView btnSignIn;

    MyEditText txtUserName;
    MyEditText txtUserEmail;
    MyEditText txtUserPassword;

    CheckBox checkbocremember;

    ProgressDialog dialog;

    MainAPIInterface mainAPIInterface;

    String strUserName, strUserEmail, strUserPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_detail_layout);

        mainAPIInterface = ApiUtils.getAPIService();

        btnSignUp = (MyTextView) findViewById(R.id.btnSignUp);
        btnSignIn = (MyTextView) findViewById(R.id.btnSignIn);

        txtUserName = (MyEditText) findViewById(R.id.txtUserName);
        txtUserEmail = (MyEditText) findViewById(R.id.txtUserEmail);
        txtUserPassword = (MyEditText) findViewById(R.id.txtUserPassword);


        checkbocremember = (CheckBox) findViewById(R.id.checkbocremember);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strUserName = txtUserName.getText().toString();
                strUserEmail = txtUserEmail.getText().toString();
                strUserPassword = txtUserPassword.getText().toString();

                if (strUserName.length() < 5) {
                    txtUserName.setFocusable(true);
                    txtUserName.setError("Enter name");
                } else if (strUserEmail.length() < 4) {
                    txtUserEmail.setFocusable(true);
                    txtUserEmail.setError("Enter email");
                } else if (strUserPassword.length() < 6) {
                    txtUserPassword.setFocusable(true);
                    txtUserPassword.setError("Enter password,with atleast 6 digit");
                } else {
                    userSignupRequest();
                }

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(SignupAcitivity.this, SignInActivity.class);
                startActivity(newIntent);
                finish();

            }
        });

    }


    private void userSignupRequest() {

        String xAccessToken = "mykey";

        dialog = new ProgressDialog(SignupAcitivity.this);

        dialog.setMessage("Adding your details.");
        dialog.show();

        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", SmartPayApplication.CUSTOMER_MOBILE);
        MultipartBody.Part email_body = MultipartBody.Part.createFormData("email", strUserEmail);
        MultipartBody.Part username_body = MultipartBody.Part.createFormData("username", strUserName);

        MultipartBody.Part password_body = MultipartBody.Part.createFormData("password", strUserPassword);


        mainAPIInterface.userSignup(xAccessToken, email_body, phone_body, username_body, password_body).enqueue(new Callback<UserProfileOutput>() {
            @Override
            public void onResponse(Call<UserProfileOutput> call, Response<UserProfileOutput> response) {

                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(SignupAcitivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        DataVaultManager.getInstance(SignupAcitivity.this).saveName(response.body().getProfile().getUsername());
                        DataVaultManager.getInstance(SignupAcitivity.this).saveUserEmail(response.body().getProfile().getEmail());
                        DataVaultManager.getInstance(SignupAcitivity.this).saveUserPassword(strUserPassword);
                        DataVaultManager.getInstance(SignupAcitivity.this).saveUserMobile(response.body().getProfile().getMobile());
                        DataVaultManager.getInstance(SignupAcitivity.this).saveUserId(response.body().getProfile().getUserId());
                        DataVaultManager.getInstance(SignupAcitivity.this).saveWalletId(response.body().getProfile().getWallet_id());

                        Intent i = new Intent(SignupAcitivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();


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
}
