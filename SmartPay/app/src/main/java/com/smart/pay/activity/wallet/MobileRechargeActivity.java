package com.smart.pay.activity.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.input.MobileRechargeModel;
import com.smart.pay.models.output.CommonOutput;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;
import static com.smart.pay.utils.DataVaultManager.KEY_WALLET_ID;

public class MobileRechargeActivity extends AppCompatActivity {

    MyTextView payButton;

    Switch rechargeSwitch;

    MyEditText edtphone_number;
    MyEditText edtOperator;
    MyEditText edtAmount;

    MyTextView btnSeePlans;

    String strPhone, strOperatorCode, strAmount, strPlan, recharge_type = "1";

    ProgressDialog newProgressDialog;
    MainAPIInterface mainAPIInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recharge_activity);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();


        payButton = (MyTextView) findViewById(R.id.payButton);
        rechargeSwitch = (Switch) findViewById(R.id.rechargeSwitch);

        edtphone_number = (MyEditText) findViewById(R.id.edtphone_number);
        edtOperator = (MyEditText) findViewById(R.id.edtOperator);
        edtAmount = (MyEditText) findViewById(R.id.edtAmount);

        btnSeePlans = (MyTextView) findViewById(R.id.btnSeePlans);

        rechargeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                Log.v("Switch State=", "" + isChecked);

                if (isChecked) {
                    recharge_type = "2";
                } else {
                    recharge_type = "1";
                }

            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strPhone = edtphone_number.getText().toString();
                strOperatorCode = edtOperator.getText().toString();
                strAmount = edtAmount.getText().toString();

                if (strPhone.length() == 0) {
                    edtphone_number.setError("Enter mobile number");
                    edtphone_number.setFocusable(true);
                } else if (strOperatorCode.length() == 0) {
                    edtOperator.setFocusable(true);
                    edtOperator.setError("Select Operator");
                } else if (strAmount.length() == 0) {
                    edtAmount.setFocusable(true);
                    edtAmount.setError("Enter the amount");
                } else {

                    placeRechargeRequest();
                }

            }
        });

    }

    private void placeRechargeRequest() {

        String xAccessToken = "mykey";

        String user_id = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        newProgressDialog = new ProgressDialog(MobileRechargeActivity.this);

        newProgressDialog.setMessage("Placing your request.");

        newProgressDialog.show();


        MobileRechargeModel mobileRechargeModel = new MobileRechargeModel();
        mobileRechargeModel.setUser_id(user_id);
        mobileRechargeModel.setAmount(strAmount);
        mobileRechargeModel.setOperatorCode(strOperatorCode);
        mobileRechargeModel.setOperatorName("Airtel");
        mobileRechargeModel.setRechargeType(recharge_type);
        mobileRechargeModel.setRechargeNumber(strPhone);
        mobileRechargeModel.setPlanId("1");
        mobileRechargeModel.setPaymentType("1");
        mobileRechargeModel.setPaymentStatus("1");


        mainAPIInterface.placeMobileRechargeRequest(xAccessToken, mobileRechargeModel).enqueue(new Callback<CommonOutput>() {
            @Override
            public void onResponse(Call<CommonOutput> call, Response<CommonOutput> response) {

                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(MobileRechargeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MobileRechargeActivity.this, ThankYouRechargeDone.class);
                        startActivity(intent);
                        finish();


                    }


                }
            }

            @Override
            public void onFailure(Call<CommonOutput> call, Throwable t) {
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


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Phone Recharge");

        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);

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
