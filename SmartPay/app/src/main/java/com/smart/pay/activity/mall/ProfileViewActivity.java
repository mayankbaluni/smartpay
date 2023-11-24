package com.smart.pay.activity.mall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.wallet.SignInActivity;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetAccountDetailsOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 08-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ProfileViewActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    private ProgressDialog newProgressDialog;

    ArrayList<GetAccountDetailsOutputModel.Profile> profileArrayList;

    TextView profile_name, profile_email;

    MyTextView address1, address2, txtCity, txtState, txtZipcode, txtCountry, txtPhoneNo;

    MyTextView accountChangePassword, accountLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view_activity);

        setupActionBar();
        mainAPIInterface = ApiUtils.getAPIService();

        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_email = (TextView) findViewById(R.id.profile_email);

        address1 = (MyTextView) findViewById(R.id.address1);
        address2 = (MyTextView) findViewById(R.id.address2);
        txtCity = (MyTextView) findViewById(R.id.txtCity);
        txtState = (MyTextView) findViewById(R.id.txtState);
        txtZipcode = (MyTextView) findViewById(R.id.txtZipcode);
        txtCountry = (MyTextView) findViewById(R.id.txtCountry);
        txtPhoneNo = (MyTextView) findViewById(R.id.txtPhoneNo);

        accountChangePassword = (MyTextView) findViewById(R.id.accountChangePassword);
        accountLogout = (MyTextView) findViewById(R.id.accountLogout);

        accountChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileViewActivity.this, ChangePasswordActivity.class);
                startActivity(i);

            }
        });

        accountLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileViewActivity.this, R.style.MyAlertDialogStyle);
                builder.setTitle("My3dBeauty");
                builder.setMessage("Are you sure to want to Logout ?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                logoutUserRequest();
                            }
                        });
                builder.setNegativeButton("No",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                builder.show();

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

        toolbarTitle.setText("Profile View");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Profile View");

        toolbar.setTitleTextColor(Color.WHITE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // action bar menu behaviour
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.edit_account:

                Intent intent = new Intent(ProfileViewActivity.this, EditCustomerAccountActivity.class);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getAccountDetailsRequest();
    }


    private void getAccountDetailsRequest() {

        String xAccessToken = "mykey";

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        newProgressDialog = new ProgressDialog(ProfileViewActivity.this);

        newProgressDialog.setMessage("Getting account details.");

        newProgressDialog.show();

        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);

        mainAPIInterface.getUserAccountDetails(xAccessToken, customer_id_body).enqueue(new Callback<GetAccountDetailsOutputModel>() {
            @Override
            public void onResponse(Call<GetAccountDetailsOutputModel> call, Response<GetAccountDetailsOutputModel> response) {


                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        profileArrayList = response.body().getProfile();

                        profile_name.setText(profileArrayList.get(0).getFirstname());
                        profile_email.setText(profileArrayList.get(0).getEmailId());

                        address1.setText(profileArrayList.get(0).getAddressLine1());
                        address2.setText(profileArrayList.get(0).getAddressLine2());
                        txtCity.setText(profileArrayList.get(0).getCity());
                        txtState.setText(profileArrayList.get(0).getState());
                        txtZipcode.setText(profileArrayList.get(0).getZipcode());
                        txtCountry.setText(profileArrayList.get(0).getCountry());
                        txtPhoneNo.setText(profileArrayList.get(0).getPhoneNo());

                    } else {

                        Toast.makeText(ProfileViewActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<GetAccountDetailsOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });

    }

    private void logoutUserRequest() {

        DataVaultManager.getInstance(ProfileViewActivity.this).saveName("");
        DataVaultManager.getInstance(ProfileViewActivity.this).saveUserEmail("");
        DataVaultManager.getInstance(ProfileViewActivity.this).saveUserPassword("");
        DataVaultManager.getInstance(ProfileViewActivity.this).saveUserId("");

        Intent i = new Intent(ProfileViewActivity.this, SignInActivity.class);
        startActivity(i);
        finish();

    }


}
