package com.smart.pay.activity.mall;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.smart.pay.R;
import com.smart.pay.adapter.mall.AllBrandGridAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetBrandsOutput;
import com.smart.pay.views.ExpandableHeightGridView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBrandsActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    ArrayList<GetBrandsOutput.Brands> brandsArrayList;

    ExpandableHeightGridView all_brands_grid;

    AllBrandGridAdapter allBrandGridAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainAPIInterface = ApiUtils.getAPIService();
        setContentView(R.layout.all_brands_activity);

        setupActionBar();
        all_brands_grid = (ExpandableHeightGridView) findViewById(R.id.all_brands_grid);

        getAllBrands();

    }

    private void getAllBrands() {

        String xAccessToken = "mykey";


        mainAPIInterface.getAllBrands(xAccessToken).enqueue(new Callback<GetBrandsOutput>() {
            @Override
            public void onResponse(Call<GetBrandsOutput> call, Response<GetBrandsOutput> response) {
                if (response.isSuccessful()) {

                    brandsArrayList = response.body().getCategories();

                    allBrandGridAdapter = new AllBrandGridAdapter(AllBrandsActivity.this, brandsArrayList);

                    all_brands_grid.setExpanded(true);

                    all_brands_grid.setAdapter(allBrandGridAdapter);

                }
            }

            @Override
            public void onFailure(Call<GetBrandsOutput> call, Throwable t) {
                //     dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
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

        toolbarTitle.setText("All Brands");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("All Brands");

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
