package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.mall.YourOrderListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.YourOrderOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 01-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class YourOrderActivity extends AppCompatActivity {

    private MainAPIInterface mainAPIInterface;


    RecyclerView yourOrderList;

    private ProgressDialog dialog;

    public ArrayList<YourOrderOutputModel.Order> orderArrayList;

    public YourOrderListAdapter yourOrderListAdapter;

    LinearLayout ll_order_layout;
    MyTextView go_to_categories;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.your_order_activity);

        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        yourOrderList = (RecyclerView) findViewById(R.id.yourOrderList);
        ll_order_layout = (LinearLayout) findViewById(R.id.ll_order_layout);
        go_to_categories = (MyTextView) findViewById(R.id.go_to_categories);


        getAllOrders();
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

        toolbarTitle.setText("Your Orders");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Your Orders");

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


    public void getAllOrders() {

        String strUserId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        String xAccessToken = "mykey";

        dialog = new ProgressDialog(YourOrderActivity.this);

        dialog.setMessage("Getting All Orders.");
        dialog.show();

        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strUserId);


        mainAPIInterface.getAllYourOrders(xAccessToken, customer_id_body).enqueue(new Callback<YourOrderOutputModel>() {
            @Override
            public void onResponse(Call<YourOrderOutputModel> call, Response<YourOrderOutputModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    orderArrayList = response.body().getOrders();

                    if (orderArrayList.isEmpty()) {

                        yourOrderList.setVisibility(View.GONE);
                        ll_order_layout.setVisibility(View.VISIBLE);

                        go_to_categories.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent i = new Intent(YourOrderActivity.this, CategoryListActivity.class);
                                i.putExtra("from", "home");
                                startActivity(i);
                                finish();

                            }
                        });


                    } else {

                        yourOrderList.setVisibility(View.VISIBLE);
                        ll_order_layout.setVisibility(View.GONE);


                        yourOrderListAdapter = new YourOrderListAdapter(orderArrayList, YourOrderActivity.this);

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(YourOrderActivity.this, RecyclerView.VERTICAL, false);

                        yourOrderList.setLayoutManager(layoutManager);

                        yourOrderList.setItemAnimator(new DefaultItemAnimator());
                        yourOrderList.setAdapter(yourOrderListAdapter);

                        yourOrderListAdapter.notifyDataSetChanged();


                    }


                }
            }

            @Override
            public void onFailure(Call<YourOrderOutputModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });
    }
}
