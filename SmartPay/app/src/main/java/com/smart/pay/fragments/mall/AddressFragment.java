package com.smart.pay.fragments.mall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.CartActivity;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetAccountDetailsOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_EMAIL;
import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 07-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class AddressFragment extends Fragment {

    View mView;

    MyTextView saveAndContinue;

    MyEditText customerFullName, address1, address2, city, state, zipcode, country, phoneNumber;

    String strCustomerFirstname, strCustomerLastname, strCustomerZipcode, strCustomerCity, strCustomerState, strCustomerCountry, strCustomerAddress, strPhoneNo, strEmailAddress;

    private ProgressDialog newProgressDialog;

    MainAPIInterface mainAPIInterface;

    ArrayList<GetAccountDetailsOutputModel.Profile> profileArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.address_fragment, container, false);

        saveAndContinue = (MyTextView) mView.findViewById(R.id.saveAndContinue);

        customerFullName = (MyEditText) mView.findViewById(R.id.customerFullName);
        address1 = (MyEditText) mView.findViewById(R.id.addL1);
        address2 = (MyEditText) mView.findViewById(R.id.addL2);
        city = (MyEditText) mView.findViewById(R.id.city);
        state = (MyEditText) mView.findViewById(R.id.state);
        zipcode = (MyEditText) mView.findViewById(R.id.zipcode);
        country = (MyEditText) mView.findViewById(R.id.country);
        phoneNumber = (MyEditText) mView.findViewById(R.id.phoneNumber);


        saveAndContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strCustomerFirstname = customerFullName.getText().toString();
                strCustomerLastname = ".";
                strCustomerAddress = address1.getText().toString() + "," + address2.getText().toString();
                strCustomerCity = city.getText().toString();
                strCustomerZipcode = zipcode.getText().toString();
                strCustomerState = state.getText().toString();
                strCustomerCountry = country.getText().toString();
                strPhoneNo = phoneNumber.getText().toString();
                strEmailAddress = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_EMAIL);

                if (strCustomerAddress.equalsIgnoreCase("") || strCustomerFirstname == null) {

                    customerFullName.setError("Enter name here");
                    customerFullName.setFocusable(true);

                } else if (strCustomerAddress.equalsIgnoreCase("") || strCustomerAddress == null) {
                    address1.setFocusable(true);
                    address1.setError("Please enter your address");

                } else if (strCustomerCity.equalsIgnoreCase("") || strCustomerCity == null) {
                    city.setError("Please enter your city.");
                    city.setFocusable(true);

                } else if (strCustomerState.equalsIgnoreCase("") || strCustomerState == null) {

                    state.setFocusable(true);
                    state.setError("This is required.");

                } else if (strCustomerZipcode.equalsIgnoreCase("") || strCustomerZipcode == null) {
                    zipcode.setFocusable(true);
                    zipcode.setError("This is required.");
                } else if (strCustomerCountry.equalsIgnoreCase("") || strCustomerCountry == null) {
                    country.setFocusable(true);
                    country.setError("This is required.");
                } else if (strPhoneNo.equalsIgnoreCase("") || strPhoneNo == null) {
                    phoneNumber.setFocusable(true);
                    phoneNumber.setError("Phone no is required.");
                } else {


                    CartActivity.strCustomerFirstname = strCustomerFirstname;
                    CartActivity.strCustomerLastname = strCustomerLastname;
                    CartActivity.strCustomerAddress = strCustomerAddress;
                    CartActivity.strCustomerCity = strCustomerCity;
                    CartActivity.strCustomerZipcode = strCustomerZipcode;
                    CartActivity.strCustomerCountry = strCustomerCountry;
                    CartActivity.strCustomerState = strCustomerState;
                    CartActivity.strPhoneNo = strPhoneNo;
                    CartActivity.strEmailAddress = strEmailAddress;

                    Fragment fragment = new PaymentFragment();

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.category_container, fragment);
                    fragmentTransaction.commit();

                }


            }
        });

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();

        mainAPIInterface = ApiUtils.getAPIService();


        if (SmartPayApplication.isNetworkAvailable(getActivity())) {

            getAccountDetailsRequest();

        } else {
            Toast.makeText(getActivity(), "Please check your internet connection.!", Toast.LENGTH_SHORT).show();

        }

    }

    private void getAccountDetailsRequest() {

        String xAccessToken = "mykey";

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);


        newProgressDialog = new ProgressDialog(getActivity());

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

                        customerFullName.setText(profileArrayList.get(0).getFirstname());
                        //    profile_email.setText(profileArrayList.get(0).getEmailId());

                        address1.setText(profileArrayList.get(0).getAddressLine1());
                        address2.setText(profileArrayList.get(0).getAddressLine2());
                        city.setText(profileArrayList.get(0).getCity());
                        state.setText(profileArrayList.get(0).getState());
                        zipcode.setText(profileArrayList.get(0).getZipcode());
                        country.setText(profileArrayList.get(0).getCountry());
                        phoneNumber.setText(profileArrayList.get(0).getPhoneNo());

                    } else {

                        Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
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
}
