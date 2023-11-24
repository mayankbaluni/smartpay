package com.smart.pay.activity.mall;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.adapter.mall.SearchListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.SearchProductOutputModel;
import com.smart.pay.utils.GifAnimationDrawable;
import com.smart.pay.views.MyEditText;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 01-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class SearchActivity extends AppCompatActivity {

    InputMethodManager inputMethodManager;

    RecyclerView searchItemList;

    public String searchQuery;

    private MainAPIInterface mainAPIInterface;

    private ProgressDialog dialog;

    public ArrayList<SearchProductOutputModel.Product> productArrayList;

    public com.smart.pay.adapter.mall.SearchListAdapter SearchListAdapter;

    RelativeLayout rl_empty_search_result;

    ImageView gifImageView2;

    MyEditText searchtexttoolbar;

    MyTextView go_to_homepage;

    GifAnimationDrawable gif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_layout);

        setupActionBar();
        mainAPIInterface = ApiUtils.getAPIService();

        searchItemList = (RecyclerView) findViewById(R.id.searchItemList);

        gifImageView2 = (ImageView) findViewById(R.id.gifImageView2);

        rl_empty_search_result = (RelativeLayout) findViewById(R.id.rl_empty_search_result);
        go_to_homepage = (MyTextView) findViewById(R.id.go_to_homepage);


    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        searchtexttoolbar = toolbar.findViewById(R.id.searchtexttoolbar);

        final ImageView btnSearch = (ImageView) toolbar.findViewById(R.id.btnSearch);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchQuery = searchtexttoolbar.getText().toString();


                if (searchQuery == null || searchQuery.equalsIgnoreCase("")) {

                    Toast.makeText(SearchActivity.this, "Please enter the search keywords.", Toast.LENGTH_SHORT).show();

                } else {

                    performSearch(searchQuery);

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                }
            }
        });


        searchtexttoolbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // hide virtual keyboard

                    return true;
                }
                return false;

            }
        });


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public void performSearch(String searchQuery) {


        String xAccessToken = "mykey";

        dialog = new ProgressDialog(SearchActivity.this);

        dialog.setMessage("Getting Related Products.");
        dialog.show();

        MultipartBody.Part search_query_body = MultipartBody.Part.createFormData("search_query", searchQuery);


        mainAPIInterface.searchAllProducts(xAccessToken, search_query_body).enqueue(new Callback<SearchProductOutputModel>() {
            @SuppressLint("ResourceType")
            @Override
            public void onResponse(Call<SearchProductOutputModel> call, Response<SearchProductOutputModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    productArrayList = response.body().getProducts();


                    if (productArrayList.isEmpty()) {

                        rl_empty_search_result.setVisibility(View.VISIBLE);
                        searchItemList.setVisibility(View.GONE);

                        try {
                            gif = new GifAnimationDrawable(getResources().openRawResource(
                                    R.drawable.no_search_result_found));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        gif.setOneShot(false);
                        gifImageView2.setImageDrawable(gif);
                        gif.setVisible(true, true);


                        go_to_homepage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                searchtexttoolbar.setFocusable(true);

                            }
                        });

                    } else {

                        rl_empty_search_result.setVisibility(View.GONE);
                        searchItemList.setVisibility(View.VISIBLE);

                        SearchListAdapter = new SearchListAdapter(productArrayList, SearchActivity.this);

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);

                        searchItemList.setLayoutManager(layoutManager);

                        searchItemList.setItemAnimator(new DefaultItemAnimator());
                        searchItemList.setAdapter(SearchListAdapter);

                        SearchListAdapter.notifyDataSetChanged();

                    }


                }
            }

            @Override
            public void onFailure(Call<SearchProductOutputModel> call, Throwable t) {
                dialog.dismiss();


                Log.i("tag", t.getMessage().toString());
            }
        });
    }
}

