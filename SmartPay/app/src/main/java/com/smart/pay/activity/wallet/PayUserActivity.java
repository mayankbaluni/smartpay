package com.smart.pay.activity.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.IsSellerExist;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_WALLET_ID;

public class PayUserActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    String phone_no;

    MyTextView txtMobile;

    MyTextView btnPayNow;
    MyEditText edtAmount;

    ProgressDialog dialog;
    String strAmount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pay_user_activity);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        Intent value = getIntent();

        phone_no = value.getExtras().getString("phone_no");

        txtMobile = (MyTextView) findViewById(R.id.txtMobile);
        btnPayNow = (MyTextView) findViewById(R.id.btnPayNow);
        edtAmount = (MyEditText) findViewById(R.id.edtAmount);

        txtMobile.setText(phone_no);


        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strAmount = edtAmount.getText().toString();

                if (strAmount.length() < 0) {

                    edtAmount.setFocusable(true);
                    edtAmount.setError("Please enter the amount");
                } else {

                    sendMoneyToUserRequest();
                }

            }
        });
    }


    private void sendMoneyToUserRequest() {

        dialog = new ProgressDialog(PayUserActivity.this);

        dialog.setMessage("Sending money to user.");
        dialog.show();

        String xAccessToken = "mykey";
        String strWalletId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_WALLET_ID);

        MultipartBody.Part sender_wallet_id_body = MultipartBody.Part.createFormData("sender_wallet_id", strWalletId);
        MultipartBody.Part phone_no_body = MultipartBody.Part.createFormData("phone_no", phone_no);
        MultipartBody.Part amount_body = MultipartBody.Part.createFormData("amount", strAmount);


        mainAPIInterface.sendMoneyToWallet(xAccessToken, sender_wallet_id_body, phone_no_body, amount_body).enqueue(new Callback<IsSellerExist>() {
            @Override
            public void onResponse(Call<IsSellerExist> call, Response<IsSellerExist> response) {
                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess() == 1) {

                        finish();
                        Toast.makeText(PayUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(PayUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<IsSellerExist> call, Throwable t) {
                dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Pay Now");

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
