package com.smart.pay.activity.mall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.leanback.widget.HorizontalGridView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.wallet.SignInActivity;
import com.smart.pay.adapter.mall.BrandRecyclerAdapter;
import com.smart.pay.adapter.mall.FashionListAdapter;
import com.smart.pay.adapter.mall.FlashDealListAdapter;
import com.smart.pay.adapter.mall.FrontCategoryListAdapter;
import com.smart.pay.adapter.mall.ServicesHorizontalAdapter;
import com.smart.pay.adapter.mall.TopOfTheDayGridAdapter;
import com.smart.pay.adapter.mall.TopSellerAdapter;
import com.smart.pay.adapter.mall.TopShoppingOfferAdapter;
import com.smart.pay.adapter.mall.TrendingListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.CategoryListModel;
import com.smart.pay.models.output.GetAllFashionOffersModel;
import com.smart.pay.models.output.GetAllFlashDealsModel;
import com.smart.pay.models.output.GetBrandsOutput;
import com.smart.pay.models.output.GetHomepageSliderOutputModel;
import com.smart.pay.models.output.GetSellerModel;
import com.smart.pay.models.output.GetTopOfTheDayOutputModel;
import com.smart.pay.models.output.GetTopShoppingOffersModel;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.models.output.ServiceListModel;
import com.smart.pay.utils.RecyclerTouchListener;
import com.smart.pay.views.ExpandableHeightGridView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import com.smart.pay.R;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.ChildAnimationExample;
import com.smart.pay.views.MyTextView;
import com.smart.pay.views.SliderLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_EMAIL;
import static com.smart.pay.utils.DataVaultManager.KEY_NAME;

/**
 * Created by Sandeep Londhe on 31-08-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */


public class HomeScreen extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {

    SliderLayout mDemoSlider, mDemoSlider2;
    private RecyclerView rv, rv2, rv3;
    private Context context;
    Typeface fonts1, fonts2, fonts3, fonts4;
    TextView eshop, shirt1, jeans1, shoes1, slippers1, goggles1, groomingproducts, trendingproducts, topbrands;
    EditText searchtext;


    LinearLayout cat1, cat2, cat3, cat4, cat5;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    ImageView menu_icon;

    private boolean mSlideState = false;

    int numberOfRows;


    List<GetHomepageSliderOutputModel.Slider> sliderList;
    List<GetHomepageSliderOutputModel.Slider> sliderList2;

    MainAPIInterface mainAPIInterface;

    HorizontalGridView topSellerGridView;

    RecyclerView trendingProductsGridView;

    TrendingListAdapter trendingListAdapter;

    TopSellerAdapter topSellerAdapter;

    TextView notificationBadge;


    TextView txtMessageUs, txtHelpCenter;

    String strUserName, strUserEmail;

    PagerIndicator custom_indicator, custom_indicator2;

    RecyclerView categoryList;

    ProgressDialog dialog;

    FrontCategoryListAdapter frontCategoryListAdapter;

    ExpandableHeightGridView topShoppingOffersGridView;

    TopShoppingOfferAdapter topShoppingOfferAdapter;
    ProgressBar topOfferProgressBar;


    ArrayList<GetTopShoppingOffersModel.Offer> offerArrayList;

    RecyclerView horizontalServicesList;

    ArrayList<ServiceListModel.Service> newCatList;

    ServicesHorizontalAdapter servicesHorizontalAdapter;

    //For Fashion List

    RecyclerView fashionList;

    ArrayList<GetAllFashionOffersModel.Offer> fashionOfferList;
    FashionListAdapter fashionListAdapter;

    //Flash Deals

    ExpandableHeightGridView flashDealsGridView;

    ArrayList<GetAllFlashDealsModel.Deal> dealArrayList;

    FlashDealListAdapter flashDealListAdapter;

    //For Top Of the day

    ExpandableHeightGridView topOfTheDayGridView;

    TopOfTheDayGridAdapter topOfTheDayGridAdapter;

    ArrayList<GetTopOfTheDayOutputModel.Offer> topOfTheDayArrayList;

    RecyclerView brandsRecyclerView;
    ArrayList<GetBrandsOutput.Brands> brandsArrayList;
    BrandRecyclerAdapter brandRecyclerAdapter;

    MyTextView btnViewAllBrands;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_homescreen_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        initializations();
        handleClicks();
        setupDrawer(savedInstanceState);

        if (SmartPayApplication.isNetworkAvailable(HomeScreen.this)) {

            getHomePageSliderRequest();
            getCategoriesRequest();
            getAllNearbySellersRequest();
            getAllTrendingProducts();
            getTopShoppingOffersRequest();
            getAllServices();
            getAllFashionOffers();
            getAllFlashDealRequest();
            getTopOfTheDayRequest();
            getAllBrands();

        } else {

            Toast.makeText(HomeScreen.this, "No internet connection!", Toast.LENGTH_SHORT).show();
        }


    }

    private void initializations() {


        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        mDemoSlider2 = (SliderLayout) findViewById(R.id.slider2);

        custom_indicator = (PagerIndicator) findViewById(R.id.custom_indicator);
        custom_indicator2 = (PagerIndicator) findViewById(R.id.custom_indicator2);

        btnViewAllBrands = (MyTextView) findViewById(R.id.btnViewAllBrands);


        menu_icon = (ImageView) findViewById(R.id.menu_icon);

        mainAPIInterface = ApiUtils.getAPIService();

        horizontalServicesList = (RecyclerView) findViewById(R.id.horizontalServicesList);
        brandsRecyclerView = (RecyclerView) findViewById(R.id.brandsRecyclerView);

        categoryList = (RecyclerView) findViewById(R.id.categoryList);

        fashionList = (RecyclerView) findViewById(R.id.fashionList);

        topShoppingOffersGridView = (ExpandableHeightGridView) findViewById(R.id.topShoppingOffersGridView);
        topOfferProgressBar = (ProgressBar) findViewById(R.id.topOfferProgressBar);

        topOfTheDayGridView = (ExpandableHeightGridView) findViewById(R.id.topOfTheDayGridView);

        flashDealsGridView = (ExpandableHeightGridView) findViewById(R.id.flashDealsGridView);

        trendingproducts = (TextView) findViewById(R.id.trendingproducts);
        searchtext = (EditText) findViewById(R.id.searchtext);

        topSellerGridView = (HorizontalGridView) findViewById(R.id.topSellerGridView);
        trendingProductsGridView = (RecyclerView) findViewById(R.id.trendingProductsGridView);


        strUserName = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_NAME);
        strUserEmail = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_EMAIL);


        txtMessageUs = (TextView) findViewById(R.id.txtMessageUs);
        txtHelpCenter = (TextView) findViewById(R.id.txtHelpCenter);

        cat1 = (LinearLayout) findViewById(R.id.cat1);
        cat2 = (LinearLayout) findViewById(R.id.cat2);
        cat3 = (LinearLayout) findViewById(R.id.cat3);
        cat4 = (LinearLayout) findViewById(R.id.cat4);
        cat5 = (LinearLayout) findViewById(R.id.cat5);


        fonts1 = Typeface.createFromAsset(getAssets(),
                "fonts/OpenSans-Regular.ttf");
        fonts2 = Typeface.createFromAsset(getAssets(),
                "fonts/OpenSans-Semibold.ttf");

        fonts3 = Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-Medium.ttf");

        fonts4 = Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-Regular.ttf");
        searchtext.setTypeface(fonts1);
//        groomingproducts.setTypeface(fonts2);
        trendingproducts.setTypeface(fonts2);
        //  topbrands.setTypeface(fonts2);

    }

    private void handleClicks() {


        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeScreen.this, CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "2");

                startActivity(i);


            }
        });


        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeScreen.this, CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "5");

                startActivity(i);
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeScreen.this, CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "1");

                startActivity(i);

            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeScreen.this, CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "7");

                startActivity(i);

            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeScreen.this, CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "11");

                startActivity(i);

            }
        });


        txtMessageUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "care@my3dbeauty.com");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My3DWorld Customer Inquiry");

                emailIntent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(emailIntent,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(HomeScreen.this, "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


        txtHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentHelpine = new Intent(HomeScreen.this, HelplineActivity.class);
                startActivity(intentHelpine);


            }
        });


        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        btnViewAllBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent allbrands = new Intent(HomeScreen.this, AllBrandsActivity.class);
                startActivity(allbrands);

            }
        });

    }

    private void setupDrawer(Bundle savedInstanceState) {

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.new_gradient)
                .addProfiles(
                        new ProfileDrawerItem().withName(strUserName).withEmail(strUserEmail).withIcon(R.mipmap.ic_profile)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {


//                        Toast.makeText(HomeScreen.this, "Profile click", Toast.LENGTH_SHORT).show();


                        Intent profileIntent = new Intent(HomeScreen.this, ProfileViewActivity.class);
                        startActivity(profileIntent);

                        return false;
                    }
                })
                .build();


        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSlideState) {
                    result.closeDrawer();
                } else {
                    result.openDrawer();
                }
            }
        });


        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View footerView = vi.inflate(R.layout.footer_layout, null);

        MyTextView btnLogout = (MyTextView) footerView.findViewById(R.id.btnLogout);
        MyTextView btnShare = (MyTextView) footerView.findViewById(R.id.btnShare);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this, R.style.MyAlertDialogStyle);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Are you sure to want to Logout ?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                logoutUserRequest();
                            }
                        });

                builder.setNegativeButton("No",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                builder.show();


            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String packageName = getApplication().getPackageName();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=" + packageName);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);


            }
        });


//        //Create the drawer
//        result = new DrawerBuilder()
//                .withActivity(this)
//                .withSelectedItem(-1)
//                //     .withToolbar(toolbar)
//                .withDisplayBelowStatusBar(true)
//                .withActionBarDrawerToggleAnimated(true)
//                .withSliderBackgroundColor(getResources().getColor(R.color.white))
//                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
//                .withFooter(footerView)
//                .addDrawerItems(
//
//                        new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.ic_home).withIdentifier(1).withTextColor(getResources().getColor(R.color.colorAccent)).withSelectable(true),
//
//                        new DividerDrawerItem(),
//
//                        new PrimaryDrawerItem().withName("Shop Products").withIcon(R.mipmap.ic_drawer_products).withIdentifier(2).withTextColor(getResources().getColor(R.color.colorAccent)),
//                        new PrimaryDrawerItem().withName("Shop Services").withIcon(R.mipmap.ic_drawer_services).withIdentifier(3).withTextColor(getResources().getColor(R.color.colorAccent)),
//
//                        new DividerDrawerItem(),
//
//                        new PrimaryDrawerItem().withName("View Top Sellers").withIcon(R.mipmap.ic_drawer_top_products).withIdentifier(4).withTextColor(getResources().getColor(R.color.colorAccent)),
//                        new PrimaryDrawerItem().withName("View Top Products").withIcon(R.mipmap.ic_drawer_top_products).withIdentifier(5).withTextColor(getResources().getColor(R.color.colorAccent)),
//
//                        new DividerDrawerItem(),
//
//                        new PrimaryDrawerItem().withName("My Account").withIcon(R.mipmap.ic_drawer_account).withIdentifier(6).withTextColor(getResources().getColor(R.color.colorAccent)),
//                        new PrimaryDrawerItem().withName("My Appointments").withIcon(R.mipmap.ic_drawer_appointment).withIdentifier(7).withTextColor(getResources().getColor(R.color.colorAccent)),
//                        new PrimaryDrawerItem().withName("My Orders").withIcon(R.mipmap.ic_drawer_orders).withIdentifier(8).withTextColor(getResources().getColor(R.color.colorAccent)),
//
//                        new DividerDrawerItem(),
//
//                        new PrimaryDrawerItem().withName("24*7 Help").withIcon(R.mipmap.ic_drawer_help).withIdentifier(9).withTextColor(getResources().getColor(R.color.colorAccent)),
//                        new PrimaryDrawerItem().withName("About Us").withIcon(R.mipmap.ic_drawer_about_us).withIdentifier(10).withTextColor(getResources().getColor(R.color.colorAccent))
//
//
////
//                ) // add the items we want to use with our Drawer
//
//
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        //check if the drawerItem is set.
//                        //there are different reasons for the drawerItem to be null
//                        //--> click on the header
//                        //--> click on the footer
//                        //those items don't contain a drawerItem
//                        view.setBackgroundColor(getResources().getColor(R.color.transparent));
//
//                        if (drawerItem != null) {
//                            Intent intent = null;
//
//                            String title = getString(R.string.app_name);
//
//                            if (drawerItem.getIdentifier() == 1) {
//
//
//                            } else if (drawerItem.getIdentifier() == 2) {
//
//                                Intent i = new Intent(HomeScreen.this, CategoryListActivity.class);
//                                i.putExtra("from", "home");
//                                startActivity(i);
//
//
//                            } else if (drawerItem.getIdentifier() == 3) {
//
//                                Intent i = new Intent(HomeScreen.this, ServiceCategoriesActivity.class);
//                                startActivity(i);
//
//
//                            } else if (drawerItem.getIdentifier() == 4) {
//
//                                //Top Sellers
//
//                                Intent i = new Intent(HomeScreen.this, ViewTopSellerActivity.class);
//                                startActivity(i);
//
//                            } else if (drawerItem.getIdentifier() == 5) {
//
//                                //Top Products
//
//                                Intent i = new Intent(HomeScreen.this, ViewTopProductActivity.class);
//                                startActivity(i);
//
//
//                            } else if (drawerItem.getIdentifier() == 6) {
//
//                                Intent i = new Intent(HomeScreen.this, ProfileViewActivity.class);
//                                startActivity(i);
//
//
//                            } else if (drawerItem.getIdentifier() == 7) {
//
//                                Intent i = new Intent(HomeScreen.this, YourAppointmentsActivity.class);
//                                startActivity(i);
//
//                            } else if (drawerItem.getIdentifier() == 8) {
//
//                                Intent i = new Intent(HomeScreen.this, YourOrderActivity.class);
//                                startActivity(i);
//                            }
//                        }
//                        return false;
//                    }
//                })
//                .withSavedInstance(savedInstanceState)
//                .withShowDrawerOnFirstLaunch(true)
//                .build();

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.home_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }

    private void getCategoriesRequest() {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(HomeScreen.this);

        dialog.setMessage("Getting Categories.");
        dialog.show();

        mainAPIInterface.getCategories(xAccessToken).enqueue(new Callback<CategoryListModel>() {
            @Override
            public void onResponse(Call<CategoryListModel> call, Response<CategoryListModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();
                    final List<CategoryListModel.Cat> newCatList = response.body().getCat();


                    frontCategoryListAdapter = new FrontCategoryListAdapter(newCatList, HomeScreen.this);

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(HomeScreen.this, RecyclerView.HORIZONTAL, false);

                    categoryList.setLayoutManager(layoutManager);

                    categoryList.setItemAnimator(new DefaultItemAnimator());
                    categoryList.setAdapter(frontCategoryListAdapter);


                    frontCategoryListAdapter.notifyDataSetChanged();


                    categoryList.addOnItemTouchListener(new RecyclerTouchListener(HomeScreen.this, categoryList, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            if (SmartPayApplication.isNetworkAvailable(HomeScreen.this)) {

                                Intent newIntent = new Intent(HomeScreen.this, CategoryDetailActivity.class);
                                newIntent.putExtra("strCategoryName", newCatList.get(position).getServiceName());
                                newIntent.putExtra("strCategoryId", newCatList.get(position).getId());
                                startActivity(newIntent);


                            } else {

                                Toast.makeText(HomeScreen.this, "Check internet connection.!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));

                }
            }

            @Override
            public void onFailure(Call<CategoryListModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void getTopShoppingOffersRequest() {
        String xAccessToken = "mykey";

        topOfferProgressBar.setVisibility(View.VISIBLE);
        topShoppingOffersGridView.setVisibility(View.GONE);

        mainAPIInterface.getTopShoppingOffers(xAccessToken).enqueue(new Callback<GetTopShoppingOffersModel>() {
            @Override
            public void onResponse(Call<GetTopShoppingOffersModel> call, Response<GetTopShoppingOffersModel> response) {


                if (response.isSuccessful()) {

                    topOfferProgressBar.setVisibility(View.GONE);
                    topShoppingOffersGridView.setVisibility(View.VISIBLE);

                    offerArrayList = response.body().getOffers();

                    topShoppingOfferAdapter = new TopShoppingOfferAdapter(HomeScreen.this, offerArrayList);

                    topShoppingOffersGridView.setExpanded(true);

                    topShoppingOffersGridView.setAdapter(topShoppingOfferAdapter);

                }
            }

            @Override
            public void onFailure(Call<GetTopShoppingOffersModel> call, Throwable t) {
                dialog.dismiss();
                topOfferProgressBar.setVisibility(View.GONE);
                topShoppingOffersGridView.setVisibility(View.VISIBLE);
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void getAllServices() {
        String xAccessToken = "mykey";


        mainAPIInterface.getServiceCategories(xAccessToken).enqueue(new Callback<ServiceListModel>() {
            @Override
            public void onResponse(Call<ServiceListModel> call, Response<ServiceListModel> response) {


                if (response.isSuccessful()) {

                    newCatList = response.body().getCat();

                    servicesHorizontalAdapter = new ServicesHorizontalAdapter(newCatList, HomeScreen.this);

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(HomeScreen.this, RecyclerView.HORIZONTAL, false);

                    horizontalServicesList.setLayoutManager(layoutManager);

                    horizontalServicesList.setItemAnimator(new DefaultItemAnimator());

                    horizontalServicesList.setAdapter(servicesHorizontalAdapter);


                }
            }

            @Override
            public void onFailure(Call<ServiceListModel> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void getAllFashionOffers() {
        String xAccessToken = "mykey";


        mainAPIInterface.getAllFationOffers(xAccessToken).enqueue(new Callback<GetAllFashionOffersModel>() {
            @Override
            public void onResponse(Call<GetAllFashionOffersModel> call, Response<GetAllFashionOffersModel> response) {


                if (response.isSuccessful()) {

                    fashionOfferList = response.body().getOffers();

                    fashionListAdapter = new FashionListAdapter(fashionOfferList, HomeScreen.this);

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(HomeScreen.this, RecyclerView.HORIZONTAL, false);

                    fashionList.setLayoutManager(layoutManager);

                    fashionList.setItemAnimator(new DefaultItemAnimator());

                    fashionList.setAdapter(fashionListAdapter);


                }
            }

            @Override
            public void onFailure(Call<GetAllFashionOffersModel> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });

    }

    private void getAllFlashDealRequest() {
        String xAccessToken = "mykey";


        mainAPIInterface.getAllFlashDeals(xAccessToken).enqueue(new Callback<GetAllFlashDealsModel>() {
            @Override
            public void onResponse(Call<GetAllFlashDealsModel> call, Response<GetAllFlashDealsModel> response) {


                if (response.isSuccessful()) {


                    final ArrayList<ArrayList<GetAllFlashDealsModel.Deal>> flashDeals = response.body().getDeals();

                    dealArrayList = flashDeals.get(0);

                    flashDealListAdapter = new FlashDealListAdapter(HomeScreen.this, dealArrayList);

                    flashDealsGridView.setExpanded(true);

                    flashDealsGridView.setAdapter(flashDealListAdapter);

                }
            }

            @Override
            public void onFailure(Call<GetAllFlashDealsModel> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void getTopOfTheDayRequest() {

        String xAccessToken = "mykey";


        mainAPIInterface.getAllTopOfTheDay(xAccessToken).enqueue(new Callback<GetTopOfTheDayOutputModel>() {
            @Override
            public void onResponse(Call<GetTopOfTheDayOutputModel> call, Response<GetTopOfTheDayOutputModel> response) {


                if (response.isSuccessful()) {


                    topOfTheDayArrayList = response.body().getOffers();

                    topOfTheDayGridAdapter = new TopOfTheDayGridAdapter(HomeScreen.this, topOfTheDayArrayList);

                    topOfTheDayGridView.setExpanded(true);

                    topOfTheDayGridView.setAdapter(topOfTheDayGridAdapter);

                }
            }

            @Override
            public void onFailure(Call<GetTopOfTheDayOutputModel> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

        // new GetNumberOfCartCount().execute();

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // action bar menu behaviour
//        switch (item.getItemId()) {
//            case R.id.go_to_notification:
//                Intent i = new Intent(HomeScreen.this, NotificationActivity.class);
//                startActivity(i);
//
//                return true;
//
//            case R.id.go_to_search:
//                Intent i2 = new Intent(HomeScreen.this, SearchActivity.class);
//                startActivity(i2);
//
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//
//        MenuItem item = menu.findItem(R.id.go_to_cart);
//
//        FrameLayout rootView = (FrameLayout) item.getActionView();
//
//        notificationBadge = (TextView) rootView.findViewById(R.id.cart_badge);
//
//        notificationBadge.setVisibility(View.GONE);
//
//        final Handler handler = new Handler();
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                if (numberOfRows == 0) {
//
//                    notificationBadge.setVisibility(View.GONE);
//
//                } else if (numberOfRows >= 1) {
//
//                    notificationBadge.setText(Integer.toString(numberOfRows));
//                    notificationBadge.setVisibility(View.VISIBLE);
//
//                }
//            }
//        }, 1000);
//
//
//        //And from here you can do whatever you want with your control
//
//        rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent goToCart = new Intent(HomeScreen.this, CartActivity.class);
//                startActivity(goToCart);
//                invalidateOptionsMenu();
//
//            }
//        });
//
//
//        return true;
//    }
//
//    class GetNumberOfCartCount extends AsyncTask<Void, Void, Void> {
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            //adding new product
//
//            //adding to database
//            numberOfRows = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
//                    .daoProduct()
//                    .getNumberOfRows();
//
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//        }
//    }

    private void getAllTrendingProducts() {

        String xAccessToken = "mykey";


        mainAPIInterface.getAllTrendingProducts(xAccessToken).enqueue(new Callback<ProductsListModel>() {
            @Override
            public void onResponse(Call<ProductsListModel> call, Response<ProductsListModel> response) {
                if (response.isSuccessful()) {


                    trendingListAdapter = new TrendingListAdapter(HomeScreen.this, response.body().getProducts());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(HomeScreen.this, RecyclerView.HORIZONTAL, false);

                    trendingProductsGridView.setLayoutManager(layoutManager);

                    trendingProductsGridView.setItemAnimator(new DefaultItemAnimator());


                    trendingProductsGridView.setAdapter(trendingListAdapter);


                }
            }

            @Override
            public void onFailure(Call<ProductsListModel> call, Throwable t) {
                //     dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
            }
        });

    }

    private void getAllNearbySellersRequest() {


        String xAccessToken = "mykey";


        mainAPIInterface.getAllNearbySellers(xAccessToken).enqueue(new Callback<GetSellerModel>() {
            @Override
            public void onResponse(Call<GetSellerModel> call, Response<GetSellerModel> response) {
                if (response.isSuccessful()) {

                    topSellerAdapter = new TopSellerAdapter(HomeScreen.this, response.body().getSellers());

                    topSellerGridView.setAdapter(topSellerAdapter);


                }
            }

            @Override
            public void onFailure(Call<GetSellerModel> call, Throwable t) {
                //     dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
            }
        });


    }

    private void getHomePageSliderRequest() {
        String xAccessToken = "mykey";


        mainAPIInterface.getHomePageSlider(xAccessToken).enqueue(new Callback<GetHomepageSliderOutputModel>() {
            @Override
            public void onResponse(Call<GetHomepageSliderOutputModel> call, Response<GetHomepageSliderOutputModel> response) {


                if (response.isSuccessful()) {

                    sliderList = response.body().getSliders();


                    for (int j = 0; j < sliderList.size(); j++) {
                        TextSliderView textSliderView = new TextSliderView(HomeScreen.this);
                        // initialize a SliderLayout
                        final int finalJ = j;
                        textSliderView
                                //  .description(name)
                                .image(sliderList.get(j).getSliderImage())
                                .setScaleType(BaseSliderView.ScaleType.CenterInside)
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {

                                        Intent i = new Intent(HomeScreen.this, ProductsActivity.class);
                                        i.putExtra("subcat_id", sliderList.get(finalJ).getSliderSubcategoryId());
                                        i.putExtra("subcat_name", sliderList.get(finalJ).getSliderText());
                                        startActivity(i);

                                    }
                                });


                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle().putString("subcat_id", sliderList.get(j).getSliderSubcategoryId());
                        textSliderView.getBundle().putString("subcat_name", sliderList.get(j).getSliderText());


                        mDemoSlider.addSlider(textSliderView);

                    }

                }


                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                mDemoSlider.setCustomIndicator(custom_indicator);
//                            mImageSlider.setCurrentPosition(-1);
                //     mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider.setPadding(0, 0, 0, 5);
                mDemoSlider.setCustomAnimation(new ChildAnimationExample());

                getHomePageSliderRequest2();

            }

            @Override
            public void onFailure(Call<GetHomepageSliderOutputModel> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void getHomePageSliderRequest2() {
        String xAccessToken = "mykey";


        mainAPIInterface.getHomePageSlider2(xAccessToken).enqueue(new Callback<GetHomepageSliderOutputModel>() {
            @Override
            public void onResponse(Call<GetHomepageSliderOutputModel> call, Response<GetHomepageSliderOutputModel> response) {


                if (response.isSuccessful()) {

                    sliderList2 = response.body().getSliders();


                    for (int j = 0; j < sliderList2.size(); j++) {
                        TextSliderView textSliderView = new TextSliderView(HomeScreen.this);
                        // initialize a SliderLayout
                        final int finalJ = j;
                        textSliderView
                                //  .description(name)
                                .image(sliderList2.get(j).getSliderImage())
                                .setScaleType(BaseSliderView.ScaleType.CenterInside)
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {

                                        Intent i = new Intent(HomeScreen.this, ProductsActivity.class);
                                        i.putExtra("subcat_id", sliderList2.get(finalJ).getSliderSubcategoryId());
                                        i.putExtra("subcat_name", sliderList2.get(finalJ).getSliderText());
                                        startActivity(i);

                                    }
                                });


                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle().putString("subcat_id", sliderList.get(j).getSliderSubcategoryId());
                        textSliderView.getBundle().putString("subcat_name", sliderList.get(j).getSliderText());


                        mDemoSlider2.addSlider(textSliderView);
                    }

                }


                mDemoSlider2.setPresetTransformer(SliderLayout.Transformer.Default);
                mDemoSlider2.setCustomIndicator(custom_indicator2);
//                            mImageSlider.setCurrentPosition(-1);
                //     mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider2.setPadding(0, 0, 0, 5);
                mDemoSlider2.setCustomAnimation(new ChildAnimationExample());


            }

            @Override
            public void onFailure(Call<GetHomepageSliderOutputModel> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    private void getAllBrands() {

        String xAccessToken = "mykey";


        mainAPIInterface.getAllTopBrands(xAccessToken).enqueue(new Callback<GetBrandsOutput>() {
            @Override
            public void onResponse(Call<GetBrandsOutput> call, Response<GetBrandsOutput> response) {
                if (response.isSuccessful()) {

                    brandsArrayList = response.body().getCategories();

                    brandRecyclerAdapter = new BrandRecyclerAdapter(brandsArrayList, HomeScreen.this);

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(HomeScreen.this, RecyclerView.HORIZONTAL, false);

                    brandsRecyclerView.setLayoutManager(layoutManager);

                    brandsRecyclerView.setItemAnimator(new DefaultItemAnimator());


                    brandsRecyclerView.setAdapter(brandRecyclerAdapter);


                }
            }

            @Override
            public void onFailure(Call<GetBrandsOutput> call, Throwable t) {
                //     dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
            }
        });

    }

    private void logoutUserRequest() {

        DataVaultManager.getInstance(HomeScreen.this).saveName("");
        DataVaultManager.getInstance(HomeScreen.this).saveUserEmail("");
        DataVaultManager.getInstance(HomeScreen.this).saveUserPassword("");
        DataVaultManager.getInstance(HomeScreen.this).saveUserId("");

        Intent i = new Intent(HomeScreen.this, SignInActivity.class);
        startActivity(i);
        finish();

    }


}
