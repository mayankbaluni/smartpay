package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.adapter.mall.GridviewAdapter;
import com.smart.pay.adapter.mall.ListviewAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.views.ExpandableHeightGridView;
import com.smart.pay.views.ExpandableHeightListView;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 10-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ViewTopProductActivity extends AppCompatActivity {


    MainAPIInterface mainAPIInterface;


    ProgressDialog dialog;

    private List<ProductsListModel.Product> productList;

    private ExpandableHeightGridView gridview;
    private ArrayList<ProductsListModel.Product> beanclassArrayList;
    private GridviewAdapter gridviewAdapter;


    private ExpandableHeightListView listview;
    private ArrayList<ProductsListModel.Product> Bean;
    private ListviewAdapter baseAdapter;

    ImageView gridviewicon, listviewicon;


    RelativeLayout rl_no_products_found;
    MyTextView btn_go_back;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_top_product_activity);

        setupActionBar();
        mainAPIInterface = ApiUtils.getAPIService();


        gridview = (ExpandableHeightGridView) findViewById(R.id.gridview);
        listview = (ExpandableHeightListView) findViewById(R.id.listview);


//        ***********Grid - list view **********

        gridviewicon = (ImageView) findViewById(R.id.gridviewicon);
        listviewicon = (ImageView) findViewById(R.id.listviewicon);

        rl_no_products_found = (RelativeLayout) findViewById(R.id.rl_no_products_found);
        btn_go_back = (MyTextView) findViewById(R.id.btn_go_back);


        if (isNetworkAvailable(ViewTopProductActivity.this)) {
            getProductsRequest();
        }


        gridviewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listviewicon.setVisibility(View.VISIBLE);
                gridviewicon.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                gridview.setVisibility(View.GONE);


            }
        });

        listviewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listviewicon.setVisibility(View.GONE);
                gridviewicon.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);
                gridview.setVisibility(View.VISIBLE);


            }
        });


    }

    private void getProductsRequest() {

        dialog = new ProgressDialog(ViewTopProductActivity.this);

        dialog.setMessage("Getting Products.");
        dialog.show();


        String xAccessToken = "mykey";

        mainAPIInterface.getAllTrendingProducts(xAccessToken).enqueue(new Callback<ProductsListModel>() {
            @Override
            public void onResponse(Call<ProductsListModel> call, Response<ProductsListModel> response) {
                if (response.isSuccessful()) {

                    dialog.dismiss();

                    beanclassArrayList = response.body().getProducts();


                    if (beanclassArrayList.isEmpty()) {

                        gridview.setVisibility(View.GONE);
                        listview.setVisibility(View.GONE);
                        gridviewicon.setEnabled(false);
                        listviewicon.setEnabled(false);
                        rl_no_products_found.setVisibility(View.VISIBLE);

                        btn_go_back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                finish();
                            }
                        });

                    } else {

                        gridview.setVisibility(View.VISIBLE);
                        listview.setVisibility(View.GONE);
                        gridviewicon.setEnabled(true);
                        listviewicon.setEnabled(true);
                        rl_no_products_found.setVisibility(View.GONE);

                        gridviewAdapter = new GridviewAdapter(ViewTopProductActivity.this, beanclassArrayList);

                        gridview.setExpanded(true);

                        gridview.setAdapter(gridviewAdapter);

                        System.out.println("Product List Size==" + beanclassArrayList.size());


//        /        ********LISTVIEW***********


                        baseAdapter = new ListviewAdapter(ViewTopProductActivity.this, beanclassArrayList);

                        listview.setAdapter(baseAdapter);




                    }


                }
            }

            @Override
            public void onFailure(Call<ProductsListModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
            }
        });


    }


    public static boolean isNetworkAvailable(Context mContext) {
        Context context = mContext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
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

        toolbarTitle.setText("Top Products");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Top Products");

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
