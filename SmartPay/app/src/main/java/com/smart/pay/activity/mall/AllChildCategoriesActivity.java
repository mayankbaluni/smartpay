package com.smart.pay.activity.mall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.smart.pay.R;
import com.smart.pay.adapter.mall.AllChildCatAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.AllChildCategoryOutput;
import com.smart.pay.views.ExpandableHeightGridView;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllChildCategoriesActivity extends AppCompatActivity {

    String strCategoryName, strCategoryId;
    MainAPIInterface mainAPIInterface;

    ExpandableHeightGridView topCategoriesGridView;
    ProgressBar topCategoriesProgressBar;

    ArrayList<AllChildCategoryOutput.ChildCategory> childCategoryArrayList;

    AllChildCatAdapter allChildCatAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainAPIInterface = ApiUtils.getAPIService();
        setContentView(R.layout.all_child_categories);

        Intent value = getIntent();

        strCategoryId = value.getExtras().getString("strCategoryId");
        strCategoryName = value.getExtras().getString("strCategoryName");

        setupActionBar();

        topCategoriesGridView = (ExpandableHeightGridView) findViewById(R.id.topCategoriesGridView);
        topCategoriesProgressBar = (ProgressBar) findViewById(R.id.topCategoriesProgressBar);

        getCategoryDetails();
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

        toolbarTitle.setText(strCategoryName);


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle(strCategoryName);

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


    private void getCategoryDetails() {
        String xAccessToken = "mykey";

        topCategoriesProgressBar.setVisibility(View.VISIBLE);
        topCategoriesGridView.setVisibility(View.GONE);

        MultipartBody.Part category_id_body = MultipartBody.Part.createFormData("category_id", strCategoryId);


        mainAPIInterface.getAllChildCategories(xAccessToken, category_id_body).enqueue(new Callback<AllChildCategoryOutput>() {
            @Override
            public void onResponse(Call<AllChildCategoryOutput> call, Response<AllChildCategoryOutput> response) {


                if (response.isSuccessful()) {

                    topCategoriesProgressBar.setVisibility(View.GONE);
                    topCategoriesGridView.setVisibility(View.VISIBLE);


                    if (response.body().getSuccess().equalsIgnoreCase("1")) {


                        childCategoryArrayList = response.body().getChildCategory();

                        allChildCatAdapter = new AllChildCatAdapter(AllChildCategoriesActivity.this, childCategoryArrayList);

                        topCategoriesGridView.setExpanded(true);

                        topCategoriesGridView.setAdapter(allChildCatAdapter);


                    } else {

                        Toast.makeText(AllChildCategoriesActivity.this, response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<AllChildCategoryOutput> call, Throwable t) {

                topCategoriesGridView.setVisibility(View.GONE);
                topCategoriesGridView.setVisibility(View.VISIBLE);
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

}
