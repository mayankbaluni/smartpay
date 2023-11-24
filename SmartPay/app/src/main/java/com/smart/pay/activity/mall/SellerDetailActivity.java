package com.smart.pay.activity.mall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.smart.pay.R;
import com.smart.pay.adapter.mall.SellerServiceListAdapter;
import com.smart.pay.adapter.mall.ViewPagerAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.Constants;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.NewServiceListModel;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Sandeep Londhe on 11-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class SellerDetailActivity extends AppCompatActivity {

    RecyclerView services_list_recyclerview;


    MainAPIInterface mainAPIInterface;

    private List<NewServiceListModel.Service> productList;


    SellerServiceListAdapter mAdapter;

    String sellerId, sellerName, sellerLogo, sellerCity;

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_detail_layout);

        services_list_recyclerview = (RecyclerView) findViewById(R.id.services_list_recyclerview);

//        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();


        Intent i = getIntent();

        sellerId = i.getExtras().getString("sellerId");
        sellerName = i.getExtras().getString("sellerName");
        sellerLogo = i.getExtras().getString("sellerLogo");
        sellerCity = i.getExtras().getString("sellerCity");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Services"));
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.MODE_FIXED);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), sellerId);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Get Toolbar component.
        Toolbar toolbar = (Toolbar) findViewById(R.id.collapsing_toolbar);
        // Use Toolbar to replace default activity action bar.
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Display home menu item.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        collapsingToolbarLayout.setTitle("Seller Details");


        TextView txtSellerName = toolbar.findViewById(R.id.txtSellerName);
        txtSellerName.setText(sellerName);

        TextView txt_seller_address = findViewById(R.id.seller_address);
        txt_seller_address.setText(sellerCity);

        de.hdodenhof.circleimageview.CircleImageView imgSellerLogo = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.sellerLogo);


        Picasso.with(SellerDetailActivity.this)
                .load(Constants.SELLER_IMAGE_PATH + sellerLogo)
                .placeholder(R.drawable.profile)
                .into(imgSellerLogo);


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

        toolbarTitle.setText("Seller Detail");


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
}
