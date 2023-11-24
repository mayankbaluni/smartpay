package com.smart.pay.activity.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.pay.R;
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

public class SignInActivity extends AppCompatActivity {

    MyTextView signup;
    MyTextView signin1;

    MyEditText edtMobile, edtPassword;

    String strMobile, strPassword;

    ProgressDialog dialog;
    MainAPIInterface mainAPIInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        mainAPIInterface = ApiUtils.getAPIService();

        signup = (MyTextView) findViewById(R.id.signup);
        signin1 = (MyTextView) findViewById(R.id.signin1);

        edtMobile = (MyEditText) findViewById(R.id.edtMobile);
        edtPassword = (MyEditText) findViewById(R.id.edtPassword);


        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strMobile = edtMobile.getText().toString();
                strPassword = edtPassword.getText().toString();

                if (strMobile.length() < 5) {
                    edtMobile.setFocusable(true);
                    edtMobile.setError("Eneter mobile number");
                } else if (strPassword.length() < 5) {
                    edtPassword.setFocusable(true);
                    edtPassword.setError("Enter password");
                } else {
                    userLoginRequest();
                }


            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(SignInActivity.this, MobileNumberRegistrationActivity.class);
                startActivity(newIntent);
                finish();

            }
        });
    }


    private void userLoginRequest() {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(SignInActivity.this);

        dialog.setMessage("Verifying your details.");
        dialog.show();

        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", strMobile);

        MultipartBody.Part password_body = MultipartBody.Part.createFormData("password", strPassword);


        mainAPIInterface.userLogin(xAccessToken, phone_body, password_body).enqueue(new Callback<UserProfileOutput>() {
            @Override
            public void onResponse(Call<UserProfileOutput> call, Response<UserProfileOutput> response) {

                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(SignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        DataVaultManager.getInstance(SignInActivity.this).saveName(response.body().getProfile().getUsername());
                        DataVaultManager.getInstance(SignInActivity.this).saveUserEmail(response.body().getProfile().getEmail());
                        DataVaultManager.getInstance(SignInActivity.this).saveUserPassword(strPassword);
                        DataVaultManager.getInstance(SignInActivity.this).saveUserMobile(response.body().getProfile().getMobile());
                        DataVaultManager.getInstance(SignInActivity.this).saveUserId(response.body().getProfile().getUserId());
                        DataVaultManager.getInstance(SignInActivity.this).saveWalletId(response.body().getProfile().getWallet_id());


                        Intent i = new Intent(SignInActivity.this, HomeActivity.class);
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
