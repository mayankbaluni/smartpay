package com.smart.pay.activity.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.wallet.WalletTransactionAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetWalletTransactionsOutput;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_WALLET_ID;

public class MyPassbook extends AppCompatActivity {

    RecyclerView allRecentTransactions;

    ArrayList<GetWalletTransactionsOutput.Transaction> transactionArrayList;

    WalletTransactionAdapter walletTransactionAdapter;

    ProgressDialog newProgressDialog;

    MainAPIInterface mainAPIInterface;

    LinearLayout ll_empty_sales_layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_passbook_activity);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        allRecentTransactions = (RecyclerView) findViewById(R.id.allRecentTransactions);

        ll_empty_sales_layout = (LinearLayout) findViewById(R.id.ll_empty_sales_layout);


    }


    @Override
    public void onResume() {
        super.onResume();
        getAllTransactionsRequest();
    }


    private void getAllTransactionsRequest() {

        String xAccessToken = "mykey";

        String wallet_id = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_WALLET_ID);

        newProgressDialog = new ProgressDialog(MyPassbook.this);

        newProgressDialog.setMessage("Getting your wallet details.");

        newProgressDialog.show();

        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("wallet_id", wallet_id);

        mainAPIInterface.getWalletTransactions(xAccessToken, seller_id_body).enqueue(new Callback<GetWalletTransactionsOutput>() {
            @Override
            public void onResponse(Call<GetWalletTransactionsOutput> call, Response<GetWalletTransactionsOutput> response) {

                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        transactionArrayList = response.body().getTransactions();

                        if (transactionArrayList.isEmpty()) {

                            Toast.makeText(MyPassbook.this, "Inside Empty", Toast.LENGTH_SHORT).show();

                            allRecentTransactions.setVisibility(View.GONE);
                            ll_empty_sales_layout.setVisibility(View.VISIBLE);


                        } else {

                            Toast.makeText(MyPassbook.this, "Inside Non Empty", Toast.LENGTH_SHORT).show();

                            allRecentTransactions.setVisibility(View.VISIBLE);
                            ll_empty_sales_layout.setVisibility(View.GONE);


                            walletTransactionAdapter = new WalletTransactionAdapter(transactionArrayList, MyPassbook.this);

                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(MyPassbook.this, RecyclerView.VERTICAL, false);

                            allRecentTransactions.setLayoutManager(layoutManager);

                            allRecentTransactions.setItemAnimator(new DefaultItemAnimator());
                            allRecentTransactions.setAdapter(walletTransactionAdapter);

                            walletTransactionAdapter.notifyDataSetChanged();


                        }
                    }


                }
            }

            @Override
            public void onFailure(Call<GetWalletTransactionsOutput> call, Throwable t) {
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

        toolbarTitle.setText("My Passbook");

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
