package com.smart.pay.fragments.bottom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.HorizontalGridView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.AllBrandsActivity;
import com.smart.pay.activity.mall.CartActivity;
import com.smart.pay.activity.mall.CategoryDetailActivity;
import com.smart.pay.activity.mall.CategoryListActivity;
import com.smart.pay.activity.mall.HelplineActivity;
import com.smart.pay.activity.mall.HomeScreen;
import com.smart.pay.activity.mall.ProductsActivity;
import com.smart.pay.activity.mall.ProfileViewActivity;
import com.smart.pay.activity.mall.SearchActivity;
import com.smart.pay.activity.wallet.ProfileActivity;
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
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.utils.RecyclerTouchListener;
import com.smart.pay.views.ChildAnimationExample;
import com.smart.pay.views.ExpandableHeightGridView;
import com.smart.pay.views.MyTextView;
import com.smart.pay.views.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_EMAIL;
import static com.smart.pay.utils.DataVaultManager.KEY_NAME;

public class MallFragment extends Fragment {

    View mView;


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

    ImageView toolbarProfile, toolbarCart, toolbarSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.mall_fragment, container, false);


        initializations(mView);
        handleClicks();
        // setupDrawer(savedInstanceState);

        if (SmartPayApplication.isNetworkAvailable(getActivity())) {

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

            Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_SHORT).show();
        }


        return mView;
    }


    private void initializations(View view) {


        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        mDemoSlider2 = (SliderLayout) view.findViewById(R.id.slider2);

        custom_indicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);
        custom_indicator2 = (PagerIndicator) view.findViewById(R.id.custom_indicator2);

        btnViewAllBrands = (MyTextView) view.findViewById(R.id.btnViewAllBrands);


        toolbarProfile = (ImageView) view.findViewById(R.id.toolbarProfile);
        toolbarCart = (ImageView) view.findViewById(R.id.toolbarCart);
        toolbarSearch = (ImageView) view.findViewById(R.id.toolbarSearch);


        menu_icon = (ImageView) view.findViewById(R.id.menu_icon);

        mainAPIInterface = ApiUtils.getAPIService();

        horizontalServicesList = (RecyclerView) view.findViewById(R.id.horizontalServicesList);
        brandsRecyclerView = (RecyclerView) view.findViewById(R.id.brandsRecyclerView);

        categoryList = (RecyclerView) view.findViewById(R.id.categoryList);

        fashionList = (RecyclerView) view.findViewById(R.id.fashionList);

        topShoppingOffersGridView = (ExpandableHeightGridView) view.findViewById(R.id.topShoppingOffersGridView);
        topOfferProgressBar = (ProgressBar) view.findViewById(R.id.topOfferProgressBar);

        topOfTheDayGridView = (ExpandableHeightGridView) view.findViewById(R.id.topOfTheDayGridView);

        flashDealsGridView = (ExpandableHeightGridView) view.findViewById(R.id.flashDealsGridView);

        trendingproducts = (TextView) view.findViewById(R.id.trendingproducts);
        searchtext = (EditText) view.findViewById(R.id.searchtext);

        topSellerGridView = (HorizontalGridView) view.findViewById(R.id.topSellerGridView);
        trendingProductsGridView = (RecyclerView) view.findViewById(R.id.trendingProductsGridView);


        strUserName = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_NAME);
        strUserEmail = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_EMAIL);


        txtMessageUs = (TextView) view.findViewById(R.id.txtMessageUs);
        txtHelpCenter = (TextView) view.findViewById(R.id.txtHelpCenter);

        cat1 = (LinearLayout) view.findViewById(R.id.cat1);
        cat2 = (LinearLayout) view.findViewById(R.id.cat2);
        cat3 = (LinearLayout) view.findViewById(R.id.cat3);
        cat4 = (LinearLayout) view.findViewById(R.id.cat4);
        cat5 = (LinearLayout) view.findViewById(R.id.cat5);


        fonts1 = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/OpenSans-Regular.ttf");
        fonts2 = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/OpenSans-Semibold.ttf");

        fonts3 = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Roboto-Medium.ttf");

        fonts4 = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Roboto-Regular.ttf");
        searchtext.setTypeface(fonts1);
//        groomingproducts.setTypeface(fonts2);
        trendingproducts.setTypeface(fonts2);
        //  topbrands.setTypeface(fonts2);


        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent newIntent = new Intent(getActivity(), SearchActivity.class);
                startActivity(newIntent);

            }
        });

        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(getActivity(), CartActivity.class);
                startActivity(newIntent);

            }
        });

        toolbarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(newIntent);

            }
        });


    }


    private void handleClicks() {


        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "2");

                startActivity(i);


            }
        });


        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "5");

                startActivity(i);
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "1");

                startActivity(i);

            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), CategoryListActivity.class);
                i.putExtra("from", "cat");
                i.putExtra("cat_id", "7");

                startActivity(i);

            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), CategoryListActivity.class);
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
                    Toast.makeText(getActivity(), "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


        txtHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentHelpine = new Intent(getActivity(), HelplineActivity.class);
                startActivity(intentHelpine);


            }
        });


        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });

        btnViewAllBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent allbrands = new Intent(getActivity(), AllBrandsActivity.class);
                startActivity(allbrands);

            }
        });

    }

    private void getCategoriesRequest() {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Getting Categories.");
        dialog.show();

        mainAPIInterface.getCategories(xAccessToken).enqueue(new Callback<CategoryListModel>() {
            @Override
            public void onResponse(Call<CategoryListModel> call, Response<CategoryListModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();
                    final List<CategoryListModel.Cat> newCatList = response.body().getCat();


                    frontCategoryListAdapter = new FrontCategoryListAdapter(newCatList, getActivity());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

                    categoryList.setLayoutManager(layoutManager);

                    categoryList.setItemAnimator(new DefaultItemAnimator());
                    categoryList.setAdapter(frontCategoryListAdapter);


                    frontCategoryListAdapter.notifyDataSetChanged();


                    categoryList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), categoryList, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            if (SmartPayApplication.isNetworkAvailable(getActivity())) {

                                Intent newIntent = new Intent(getActivity(), CategoryDetailActivity.class);
                                newIntent.putExtra("strCategoryName", newCatList.get(position).getServiceName());
                                newIntent.putExtra("strCategoryId", newCatList.get(position).getId());
                                startActivity(newIntent);


                            } else {

                                Toast.makeText(getActivity(), "Check internet connection.!", Toast.LENGTH_SHORT).show();
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

                    topShoppingOfferAdapter = new TopShoppingOfferAdapter((AppCompatActivity) getActivity(), offerArrayList);

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

                    servicesHorizontalAdapter = new ServicesHorizontalAdapter(newCatList, getActivity());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

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

                    fashionListAdapter = new FashionListAdapter(fashionOfferList, getActivity());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

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

                    flashDealListAdapter = new FlashDealListAdapter((AppCompatActivity) getActivity(), dealArrayList);

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

                    topOfTheDayGridAdapter = new TopOfTheDayGridAdapter((AppCompatActivity) getActivity(), topOfTheDayArrayList);

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

    private void getHomePageSliderRequest() {
        String xAccessToken = "mykey";


        mainAPIInterface.getHomePageSlider(xAccessToken).enqueue(new Callback<GetHomepageSliderOutputModel>() {
            @Override
            public void onResponse(Call<GetHomepageSliderOutputModel> call, Response<GetHomepageSliderOutputModel> response) {


                if (response.isSuccessful()) {

                    sliderList = response.body().getSliders();


                    for (int j = 0; j < sliderList.size(); j++) {
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        // initialize a SliderLayout
                        final int finalJ = j;
                        textSliderView
                                //  .description(name)
                                .image(sliderList.get(j).getSliderImage())
                                .setScaleType(BaseSliderView.ScaleType.CenterInside)
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {

                                        Intent i = new Intent(getActivity(), ProductsActivity.class);
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
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        // initialize a SliderLayout
                        final int finalJ = j;
                        textSliderView
                                //  .description(name)
                                .image(sliderList2.get(j).getSliderImage())
                                .setScaleType(BaseSliderView.ScaleType.CenterInside)
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {

                                        Intent i = new Intent(getActivity(), ProductsActivity.class);
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

                    brandRecyclerAdapter = new BrandRecyclerAdapter(brandsArrayList, (AppCompatActivity) getActivity());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

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

    private void getAllNearbySellersRequest() {


        String xAccessToken = "mykey";


        mainAPIInterface.getAllNearbySellers(xAccessToken).enqueue(new Callback<GetSellerModel>() {
            @Override
            public void onResponse(Call<GetSellerModel> call, Response<GetSellerModel> response) {
                if (response.isSuccessful()) {

                    topSellerAdapter = new TopSellerAdapter(getActivity(), response.body().getSellers());

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

    private void getAllTrendingProducts() {

        String xAccessToken = "mykey";


        mainAPIInterface.getAllTrendingProducts(xAccessToken).enqueue(new Callback<ProductsListModel>() {
            @Override
            public void onResponse(Call<ProductsListModel> call, Response<ProductsListModel> response) {
                if (response.isSuccessful()) {


                    trendingListAdapter = new TrendingListAdapter(getActivity(), response.body().getProducts());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

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


    @Override
    public void onResume() {
        super.onResume();
        //   Toolbar toolbar = mView.findViewById(R.id.mall_toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        // ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    }


    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

}
