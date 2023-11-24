package com.smart.pay.activity.mall;

import android.graphics.Color;
import android.os.Bundle;
//import androidx.core.app.Fragment;
//import androidx.core.app.FragmentManager;
//import androidx.core.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.smart.pay.R;
import com.smart.pay.adapter.mall.CartListAdapter;
import com.smart.pay.fragments.mall.CartFragmentNew;

import java.util.ArrayList;


/**
 * Created by Sandeep Londhe on 02-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class CartActivity extends AppCompatActivity {

    RecyclerView cartListView;

    CartListAdapter cartListAdapter;

    public static ArrayList<String> strProductId;
    public static ArrayList<String> strProductName;
    public static String strCategoryId;
    public static String strSubcatId;
    public static String strDateTime;
    public static String strCustomerFirstname;
    public static String strCustomerLastname;
    public static String strCustomerZipcode;
    public static String strCustomerState;
    public static String strCustomerCity;
    public static String strCustomerCountry;
    public static String strCustomerAddress;
    public static String strPhoneNo;
    public static String strEmailAddress;
    public static String strPaymentType;
    public static ArrayList<String> strTotalPayble;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cart_activity);
        setupActionBar();

        Fragment fragment = new CartFragmentNew();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.category_container, fragment);
        fragmentTransaction.commit();


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

        toolbarTitle.setText("Your Cart");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Your Cart");

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
    public void onBackPressed() {

        try {

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.category_container);

            if (currentFragment instanceof CartFragmentNew) {
                Log.d("message", "home fragment");

                finish();

            } else {
                Log.d("message", "popping backstack");

                getSupportFragmentManager().popBackStack();
                getSupportActionBar().show();

            }
        } catch (Exception e) {
        }

    }

}
