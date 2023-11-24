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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.adapter.mall.ServiceGridViewAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.ServiceListModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 21-11-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ServiceCategoriesActivity extends AppCompatActivity {

    private MainAPIInterface mainAPIInterface;

    GridView serviceGrid;

    private ProgressDialog dialog;

    ServiceGridViewAdapter gridviewAdapter;


    ArrayList<ServiceListModel.Service> newCatList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.service_categories);

        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();


        serviceGrid = (GridView) findViewById(R.id.serviceGrid);

        getCategoriesRequest();


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

        toolbarTitle.setText("Services");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Services");

        toolbar.setTitleTextColor(Color.WHITE);
    }




    private void getCategoriesRequest() {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(ServiceCategoriesActivity.this);

        dialog.setMessage("Getting Services.");
        dialog.show();

        mainAPIInterface.getServiceCategories(xAccessToken).enqueue(new Callback<ServiceListModel>() {
            @Override
            public void onResponse(Call<ServiceListModel> call, Response<ServiceListModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();
                    newCatList = response.body().getCat();


                    gridviewAdapter = new ServiceGridViewAdapter(ServiceCategoriesActivity.this, newCatList);
                    serviceGrid.setAdapter(gridviewAdapter);


                }
            }

            @Override
            public void onFailure(Call<ServiceListModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


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
