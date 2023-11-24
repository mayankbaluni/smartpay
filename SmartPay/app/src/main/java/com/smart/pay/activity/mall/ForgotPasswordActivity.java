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
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 08-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    private ProgressDialog newProgressDialog;


    MyTextView btnSendPassword;
    MyEditText edtForgotPassword;

    MyTextView passwordSuccess, passwordFail;

    String strEmail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        btnSendPassword = (MyTextView) findViewById(R.id.btnSendPassword);
        passwordSuccess = (MyTextView) findViewById(R.id.passwordSuccess);
        passwordFail = (MyTextView) findViewById(R.id.passwordFail);
        edtForgotPassword = (MyEditText) findViewById(R.id.edtForgotPassword);

        btnSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strEmail = edtForgotPassword.getText().toString();

                if (strEmail.equalsIgnoreCase("") || strEmail == null) {
                    edtForgotPassword.setFocusable(true);
                    edtForgotPassword.setError("Email cannot be empty.");
                } else if (!strEmail.matches(emailPattern)) {
                    edtForgotPassword.setFocusable(true);
                    edtForgotPassword.setError("Not a valid email");

                } else {

                    if (SmartPayApplication.isNetworkAvailable(ForgotPasswordActivity.this)) {

                        forgotPasswordRequest();
                    } else {

                        Toast.makeText(ForgotPasswordActivity.this, "Please check Internet Connection.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    private void forgotPasswordRequest() {

        passwordSuccess.setVisibility(View.GONE);
        passwordFail.setVisibility(View.GONE);

        newProgressDialog = new ProgressDialog(ForgotPasswordActivity.this);

        newProgressDialog.setMessage("Checking our database.");

        newProgressDialog.show();

        String xAccessToken = "mykey";

        MultipartBody.Part email_body = MultipartBody.Part.createFormData("email_id", strEmail);


        mainAPIInterface.forgotPasswordRequest(xAccessToken, email_body).enqueue(new Callback<ForgotPasswordOutputModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordOutputModel> call, Response<ForgotPasswordOutputModel> response) {
                if (response.isSuccessful()) {
                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        passwordSuccess.setVisibility(View.VISIBLE);
                        passwordFail.setVisibility(View.GONE);

                    } else {

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                        passwordSuccess.setVisibility(View.GONE);
                        passwordFail.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();

                passwordSuccess.setVisibility(View.GONE);
                passwordFail.setVisibility(View.GONE);
                Log.i("tag", t.getMessage().toString());
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

        toolbarTitle.setText("Forgot Password");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Forgot Password");

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

}
