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
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.mall.NewServiceListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.NewServiceListOutputModel;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 11-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class NewServiceListActivity extends AppCompatActivity {

    RecyclerView newServicesList;

    public String cat_name;

    public String cat_id;

    private MainAPIInterface mainAPIInterface;

    private ProgressDialog dialog;

    LinearLayout ll_services_layout;
    MyTextView go_to_homepage;

    NewServiceListAdapter newServiceListAdapter;
    ArrayList<NewServiceListOutputModel.Service> serviceArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_services_list_activity);

        Intent intent = getIntent();
        cat_id = intent.getExtras().getString("cat_id");
        cat_name = intent.getExtras().getString("cat_name");

        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        newServicesList = (RecyclerView) findViewById(R.id.newServicesList);

        go_to_homepage = (MyTextView) findViewById(R.id.go_to_homepage);
        ll_services_layout = (LinearLayout) findViewById(R.id.ll_services_layout);


        if (SmartPayApplication.isNetworkAvailable(NewServiceListActivity.this)) {
            getAllServiceList();
        } else {

            Toast.makeText(NewServiceListActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
        }

    }


    private void getAllServiceList() {

        String xAccessToken = "mykey";

        dialog = new ProgressDialog(NewServiceListActivity.this);

        dialog.setMessage("Getting Nearby Services.");
        dialog.show();

        MultipartBody.Part cat_idbody = MultipartBody.Part.createFormData("category_id", cat_id);


        mainAPIInterface.getAllServicesByCategory(xAccessToken, cat_idbody).enqueue(new Callback<NewServiceListOutputModel>() {
            @Override
            public void onResponse(Call<NewServiceListOutputModel> call, Response<NewServiceListOutputModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    serviceArrayList = response.body().getServices();

                    if (serviceArrayList.isEmpty()) {

                        newServicesList.setVisibility(View.GONE);
                        ll_services_layout.setVisibility(View.VISIBLE);

                        go_to_homepage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                finish();
                            }
                        });


                    } else {

                        newServicesList.setVisibility(View.VISIBLE);
                        ll_services_layout.setVisibility(View.GONE);

                        newServiceListAdapter = new NewServiceListAdapter(serviceArrayList, NewServiceListActivity.this);

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(NewServiceListActivity.this,
                                RecyclerView.VERTICAL, false);

                        newServicesList.setLayoutManager(layoutManager);

                        newServicesList.setItemAnimator(new DefaultItemAnimator());
                        newServicesList.setAdapter(newServiceListAdapter);

                        newServiceListAdapter.notifyDataSetChanged();

                    }


                }
            }

            @Override
            public void onFailure(Call<NewServiceListOutputModel> call, Throwable t) {
                dialog.dismiss();
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

        toolbarTitle.setText(cat_name);


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle(cat_name);
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
