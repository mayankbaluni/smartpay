package com.smart.pay.activity.wallet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.ProductsActivity;
import com.smart.pay.adapter.mall.GridviewAdapter;
import com.smart.pay.adapter.mall.ListviewAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.IsSellerExist;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_NAME;
import static com.smart.pay.utils.DataVaultManager.KEY_WALLET_ID;

public class AddMoneyToWallet extends AppCompatActivity {

    String strCustomerId, amountSum, strWalletId;
    MainAPIInterface mainAPIInterface;

    LinearLayout llAddMoneyToWallet;
    MyEditText edtAmount;

    ProgressDialog dialog;

    PayPalConfiguration config;

    double price = 0;
    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;

    String paypal_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money_to_wallet);

        mainAPIInterface = ApiUtils.getAPIService();

        setupActionBar();

        if (SmartPayApplication.PAYPAL_SANDBOX.equalsIgnoreCase("1")) {

            System.out.println(SmartPayApplication.CURRENCY_CODE);


            config = new PayPalConfiguration()
                    // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                    // or live (ENVIRONMENT_PRODUCTION)
                    .rememberUser(true)
                    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                    .clientId(SmartPayApplication.PAYPAL_CLIENT_ID);

        } else {


            System.out.println(SmartPayApplication.CURRENCY_CODE);

            config = new PayPalConfiguration()
                    // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                    // or live (ENVIRONMENT_PRODUCTION)
                    .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
                    .rememberUser(true)
                    .clientId(SmartPayApplication.PAYPAL_CLIENT_ID);

        }


        llAddMoneyToWallet = (LinearLayout) findViewById(R.id.llAddMoneyToWallet);
        edtAmount = (MyEditText) findViewById(R.id.edtAmount);

        llAddMoneyToWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amountSum = edtAmount.getText().toString();


                if (amountSum.length() == 0) {
                    edtAmount.setFocusable(true);
                    edtAmount.setError("Please enter valid amount");
                } else {
                    price = Double.valueOf(amountSum);

                    initiatePayment();
                }

            }
        });

    }

    private void initiatePayment() {

        price = price / Double.valueOf(SmartPayApplication.CURRENCY_EXCHANGE_RATE);

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(price)), "USD", "Pay to " + AddMoneyToWallet.this.getString(R.string.app_name),
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(AddMoneyToWallet.this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        Toast.makeText(AddMoneyToWallet.this, "Thanks For Making Payment", Toast.LENGTH_LONG).show();

                        JSONObject newJsonArray = new JSONObject(paymentDetails);

                        JSONObject newJsonObject2 = newJsonArray.getJSONObject("response");

                        paypal_id = newJsonObject2.getString("id");

                        System.out.print("PAYPAL ID==" + paypal_id);


                        addMoneyToWalletRequest();


                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }


    private void addMoneyToWalletRequest() {
        dialog = new ProgressDialog(AddMoneyToWallet.this);

        dialog.setMessage("Getting Products.");
        dialog.show();


        String xAccessToken = "mykey";
        String strWalletId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_WALLET_ID);

        MultipartBody.Part wallet_id_body = MultipartBody.Part.createFormData("wallet_id", strWalletId);
        MultipartBody.Part amount_body = MultipartBody.Part.createFormData("amount", String.valueOf(price));


        mainAPIInterface.addMoneyToWallet(xAccessToken, wallet_id_body, amount_body).enqueue(new Callback<IsSellerExist>() {
            @Override
            public void onResponse(Call<IsSellerExist> call, Response<IsSellerExist> response) {
                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess() == 1) {

                        finish();
                        Toast.makeText(AddMoneyToWallet.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(AddMoneyToWallet.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Add Money");
        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Add Money");

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
