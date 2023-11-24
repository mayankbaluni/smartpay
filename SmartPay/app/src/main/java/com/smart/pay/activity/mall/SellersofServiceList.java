package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.adapter.mall.SellerListViewAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.SellerServiceListModel;

import java.util.List;


/**
 * Created by Sandeep Londhe on 29-01-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class SellersofServiceList extends AppCompatActivity {

    RecyclerView sellers_list;

//    public String cat_name;
//
//    public String cat_id;

    private MainAPIInterface mainAPIInterface;

    private ProgressDialog dialog;

    public List<SellerServiceListModel.Seller> sellersofServiceList;

    public SellerListViewAdapter sellerListViewAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_of_service_list);


//        Intent intent = getIntent();
//        cat_id = intent.getExtras().getString("cat_id");
//        cat_name = intent.getExtras().getString("cat_name");

        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        sellers_list = (RecyclerView) findViewById(R.id.sellers_list);


//        getCategoriesRequest();


    }


//    private void getCategoriesRequest() {
//        String xAccessToken = "mykey";
//
//        dialog = new ProgressDialog(SellersofServiceList.this);
//
//        dialog.setMessage("Getting Nearby Sellers.");
//        dialog.show();
//
//        MultipartBody.Part cat_idbody = MultipartBody.Part.createFormData("cat_id", cat_id);
//
//
//        mainAPIInterface.getAllNearbySellers(xAccessToken, cat_idbody).enqueue(new Callback<SellerServiceListModel>() {
//            @Override
//            public void onResponse(Call<SellerServiceListModel> call, Response<SellerServiceListModel> response) {
//
//
//                if (response.isSuccessful()) {
//
//                    dialog.dismiss();
//
//                    sellersofServiceList = response.body().getSellers();
//
//
//                    sellerListViewAdapter = new SellerListViewAdapter(sellersofServiceList, SellersofServiceList.this);
//
//                    LinearLayoutManager layoutManager
//                            = new LinearLayoutManager(SellersofServiceList.this, LinearLayoutManager.VERTICAL, false);
//
//                    sellers_list.setLayoutManager(layoutManager);
//
//                    sellers_list.setItemAnimator(new DefaultItemAnimator());
//                    sellers_list.setAdapter(sellerListViewAdapter);
//
//                    sellerListViewAdapter.notifyDataSetChanged();
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SellerServiceListModel> call, Throwable t) {
//                dialog.dismiss();
//                Log.i("tag", t.getMessage().toString());
//            }
//        });
//
//
//    }


    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);

        ImageView appLogo = toolbar.findViewById(R.id.appLogo);
        appLogo.setVisibility(View.GONE);

        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Top Sellers");

        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Top Sellers");

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
