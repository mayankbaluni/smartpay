package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.adapter.mall.CategoryListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.fragments.mall.CategoriesFragment;
import com.smart.pay.fragments.mall.SubcategoryFragment;

import java.util.List;


/**
 * Created by Sandeep Londhe on 25-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class CategoryListActivity extends AppCompatActivity {

    private MainAPIInterface mainAPIInterface;

    RecyclerView categoriesListView;
    private ProgressDialog dialog;

    List<String> list1 = null;

    CategoryListAdapter categoryListAdapter;


    FrameLayout category_container;

    String strCatId;

    String from;

    Fragment fragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.categories_list_view);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        categoriesListView = (RecyclerView) findViewById(R.id.categoriesListView);
        category_container = (FrameLayout) findViewById(R.id.category_container);

        Intent i = getIntent();

        from = i.getExtras().getString("from");


        if (isNetworkAvailable(CategoryListActivity.this)) {


            if (from.equalsIgnoreCase("home")) {

                fragment = new CategoriesFragment();

            } else if (from.equalsIgnoreCase("cat")) {


                strCatId = i.getExtras().getString("cat_id");
                Bundle bundle = new Bundle();
                bundle.putString("cat_id", strCatId);

                fragment = new SubcategoryFragment();
                fragment.setArguments(bundle);

            }


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.category_container, fragment);
            fragmentTransaction.commit();


        } else {


            Toast.makeText(getApplicationContext(), "Check internet connection.!", Toast.LENGTH_SHORT).show();

        }


    }


    @Override
    public void onResume() {
        super.onResume();
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

        toolbarTitle.setText("Categories");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Categories");

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


    @Override
    public void onBackPressed() {

        try {


            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.category_container);

            if (currentFragment instanceof CategoriesFragment) {
                Log.d("message", "home fragment");

                finish();

            } else if (currentFragment instanceof SubcategoryFragment) {

              //  finish();

                fragment = new CategoriesFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.category_container, fragment);
                fragmentTransaction.commit();

            } else {
                Log.d("message", "popping backstack");

                getSupportFragmentManager().popBackStack();
                getSupportActionBar().show();

            }
        } catch (Exception e) {
        }

    }

}