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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.EditUserAccountInputModel;
import com.smart.pay.models.output.GetAccountDetailsOutputModel;
import com.smart.pay.models.output.PostSellerReviewOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;

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
public class EditCustomerAccountActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    Button btnUpdateProfile;

    private ProgressDialog newProgressDialog;

    String strCustomerId, strFirstName, strPhoneNo, strEmaild, strBirthdate, strGender, strAddress1,
            strAddress2, strCity, strState, strZipcode, strCountry;

    EditText full_name, customer_email;

    MyEditText address1, address2, txtCity, txtState, txtZipcode, txtCountry, txtPhoneNo;

    ArrayList<GetAccountDetailsOutputModel.Profile> profileArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer_account);

        setupActionBar();
        mainAPIInterface = ApiUtils.getAPIService();

        btnUpdateProfile = (Button) findViewById(R.id.btnUpdateProfile);

        full_name = (EditText) findViewById(R.id.full_name);
        customer_email = (EditText) findViewById(R.id.customer_email);

        address1 = (MyEditText) findViewById(R.id.address1);
        address2 = (MyEditText) findViewById(R.id.address2);
        txtCity = (MyEditText) findViewById(R.id.txtCity);
        txtState = (MyEditText) findViewById(R.id.txtState);
        txtZipcode = (MyEditText) findViewById(R.id.txtZipcode);
        txtCountry = (MyEditText) findViewById(R.id.txtCountry);
        txtPhoneNo = (MyEditText) findViewById(R.id.txtPhoneNo);

        getAccountDetailsRequest();


        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strFirstName = full_name.getText().toString();
                strEmaild = customer_email.getText().toString();

                strAddress1 = address1.getText().toString();
                strAddress2 = address2.getText().toString();
                strCity = txtCity.getText().toString();
                strState = txtState.getText().toString();
                strZipcode = txtZipcode.getText().toString();
                strCountry = txtCountry.getText().toString();
                strPhoneNo = txtPhoneNo.getText().toString();

                if (strFirstName.length() < 5) {
                    full_name.setFocusable(true);
                    full_name.setError("Name should be more than 5 characters1");
                } else if (strEmaild.equalsIgnoreCase("") || strEmaild == null) {
                    customer_email.setFocusable(true);
                    customer_email.setError("Email is mandatory");
                } else if (strAddress1.equalsIgnoreCase("") || strAddress1 == null) {
                    address1.setFocusable(true);
                    address1.setError("Address cannot be empty");
                } else if (strCity.equalsIgnoreCase("") || strCity == null) {
                    txtCity.setFocusable(true);
                    txtCity.setError("City cannot be empty");
                } else if (strState.equalsIgnoreCase("") || strState == null) {
                    txtState.setFocusable(true);
                    txtState.setError("State cannot be empty");
                } else if (strZipcode.equalsIgnoreCase("") || strZipcode == null) {
                    txtZipcode.setFocusable(true);
                    txtZipcode.setError("Zipcode cannot be empty");
                } else if (strCountry.equalsIgnoreCase("") || strCountry == null) {
                    txtCountry.setFocusable(true);
                    txtCountry.setError("Country cannot be empty");
                } else if (strPhoneNo.equalsIgnoreCase("") || strPhoneNo == null) {
                    txtPhoneNo.setFocusable(true);
                    txtPhoneNo.setError("Phone number cannot be empty");
                } else {

                    updateUserAccountRequest();
                }


            }
        });

    }


    private void getAccountDetailsRequest() {

        String xAccessToken = "mykey";

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        newProgressDialog = new ProgressDialog(EditCustomerAccountActivity.this);

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

                        full_name.setText(profileArrayList.get(0).getFirstname());
                        customer_email.setText(profileArrayList.get(0).getEmailId());

                        address1.setText(profileArrayList.get(0).getAddressLine1());
                        address2.setText(profileArrayList.get(0).getAddressLine2());
                        txtCity.setText(profileArrayList.get(0).getCity());
                        txtState.setText(profileArrayList.get(0).getState());
                        txtZipcode.setText(profileArrayList.get(0).getZipcode());
                        txtCountry.setText(profileArrayList.get(0).getCountry());
                        txtPhoneNo.setText(profileArrayList.get(0).getPhoneNo());

                    } else {

                        Toast.makeText(EditCustomerAccountActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
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


    private void updateUserAccountRequest() {
        String xAccessToken = "mykey";

        strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        newProgressDialog = new ProgressDialog(EditCustomerAccountActivity.this);

        newProgressDialog.setMessage("Updating account details.");

        newProgressDialog.show();


        EditUserAccountInputModel editUserAccountInputModel = new EditUserAccountInputModel();

        editUserAccountInputModel.setUserId(strCustomerId);
        editUserAccountInputModel.setFirstname(strFirstName);
        editUserAccountInputModel.setEmailId(strEmaild);
        editUserAccountInputModel.setAddressLine1(strAddress1);
        editUserAccountInputModel.setAddressLine2(strAddress2);
        editUserAccountInputModel.setCity(strCity);
        editUserAccountInputModel.setState(strState);
        editUserAccountInputModel.setZipcode(strZipcode);
        editUserAccountInputModel.setCountry(strCountry);
        editUserAccountInputModel.setPhoneNo(strPhoneNo);
        editUserAccountInputModel.setBirthdate("");
        editUserAccountInputModel.setGender("");


        mainAPIInterface.postEditUserAccount(xAccessToken, editUserAccountInputModel).enqueue(new Callback<PostSellerReviewOutputModel>() {
            @Override
            public void onResponse(Call<PostSellerReviewOutputModel> call, Response<PostSellerReviewOutputModel> response) {


                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(EditCustomerAccountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        finish();

                    } else {

                        Toast.makeText(EditCustomerAccountActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<PostSellerReviewOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();
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

        toolbarTitle.setText("Edit Account");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Edit Account");

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
