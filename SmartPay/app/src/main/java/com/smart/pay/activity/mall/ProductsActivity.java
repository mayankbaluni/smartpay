package com.smart.pay.activity.mall;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.smart.pay.R;
import com.smart.pay.adapter.mall.DiscountListViewAdapter;
import com.smart.pay.adapter.mall.GridviewAdapter;
import com.smart.pay.adapter.mall.GridviewAdapter2;
import com.smart.pay.adapter.mall.ListviewAdapter;
import com.smart.pay.adapter.mall.ListviewAdapter2;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.DiscountClassModel;
import com.smart.pay.models.output.FlashDealsProductsOutputModel;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.views.ExpandableHeightGridView;
import com.smart.pay.views.ExpandableHeightListView;
import com.smart.pay.views.MyTextView;
import com.smart.pay.views.RangeBar;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 27-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ProductsActivity extends AppCompatActivity {

    String subcat_name, subcat_id, child_category_id, brand_id;

    //cat_id


    MainAPIInterface mainAPIInterface;


    ProgressDialog dialog;

    private List<ProductsListModel.Product> productList;

    private ExpandableHeightGridView gridview;
    private ArrayList<ProductsListModel.Product> beanclassArrayList;
    private ArrayList<ArrayList<FlashDealsProductsOutputModel.Product>> productArrayList = new ArrayList<ArrayList<FlashDealsProductsOutputModel.Product>>();

    private ArrayList<FlashDealsProductsOutputModel.Product> flashDealArrayList;
    private FlashDealsProductsOutputModel.Product newProduct1;

    private GridviewAdapter gridviewAdapter;
    private GridviewAdapter2 gridviewAdapter2;


    private ExpandableHeightListView listview;
    private ArrayList<ProductsListModel.Product> Bean;
    private ListviewAdapter baseAdapter;
    private ListviewAdapter2 baseAdapter2;

    ImageView gridviewicon, listviewicon;

    ListView filter_listview;
    ExpandableHeightGridView filter_gridview;


    RelativeLayout rl_no_products_found;
    MyTextView btn_go_back;

    MyTextView txtRefineBy, txtSortBy;

    private ListView discountview;

    TextView categories, brands, colour, price, discount;

    LinearLayout brandlist, pricelist, colourlist, discountlist;

    RangeBar rangebar1;

    private String[] DISCOUNT = {"0 - 10 %", "10 - 15 %", "20 - 30 %", "40 - 50 %"};

    private ArrayList<DiscountClassModel> discountArrayList;
    private DiscountListViewAdapter discountAdapter;

    String strProductIds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);


        Intent i = getIntent();
        //    cat_id = i.getExtras().getString("cat_id");

        subcat_id = i.getExtras().getString("subcat_id");

        subcat_name = i.getExtras().getString("subcat_name");


        setupActionBar();
        mainAPIInterface = ApiUtils.getAPIService();

        System.out.println("Subcategory ID=" + subcat_id);

        gridview = (ExpandableHeightGridView) findViewById(R.id.gridview);
        listview = (ExpandableHeightListView) findViewById(R.id.listview);


//        ***********Grid - list view **********

        gridviewicon = (ImageView) findViewById(R.id.gridviewicon);
        listviewicon = (ImageView) findViewById(R.id.listviewicon);

        rl_no_products_found = (RelativeLayout) findViewById(R.id.rl_no_products_found);
        btn_go_back = (MyTextView) findViewById(R.id.btn_go_back);


        txtSortBy = (MyTextView) findViewById(R.id.txtSortBy);
        txtRefineBy = (MyTextView) findViewById(R.id.txtRefineBy);


        if (isNetworkAvailable(ProductsActivity.this)) {

            if (i.getExtras().getString("products") != null) {

                strProductIds = i.getExtras().getString("products");

                System.out.println("Product IDS===" + strProductIds);

                getProductsFromDeal();

            } else if (i.getExtras().getString("child_category_id") != null) {

                child_category_id = i.getExtras().getString("child_category_id");

                getProductsFromChildCategoryRequest();

            } else if (i.getExtras().getString("brand_id") != null) {

                brand_id = i.getExtras().getString("brand_id");
                getProductsByBrand();

            } else {
                getProductsRequest();

            }

        }


        discountArrayList = new ArrayList<DiscountClassModel>();

        for (int j = 0; j < DISCOUNT.length; j++) {

            DiscountClassModel bean1 = new DiscountClassModel(DISCOUNT[j]);
            discountArrayList.add(bean1);
        }


        txtRefineBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(ProductsActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.fileter_dialog);
                dialog.show();


                Button t5 = (Button) dialog.findViewById(R.id.rang1);
                Button t6 = (Button) dialog.findViewById(R.id.rang2);

                filter_listview = (ListView) dialog.findViewById(R.id.filter_listview);
                filter_gridview = (ExpandableHeightGridView) dialog.findViewById(R.id.filter_gridview);
                discountview = (ListView) dialog.findViewById(R.id.discountview);


                categories = (TextView) dialog.findViewById(R.id.categories);

                brands = (TextView) dialog.findViewById(R.id.brands);
                brandlist = (LinearLayout) dialog.findViewById(R.id.brandlist);

                colour = (TextView) dialog.findViewById(R.id.colour);
                colourlist = (LinearLayout) dialog.findViewById(R.id.colourlist);

                price = (TextView) dialog.findViewById(R.id.price);
                pricelist = (LinearLayout) dialog.findViewById(R.id.pricelist);


                discount = (TextView) dialog.findViewById(R.id.discount);
                discountlist = (LinearLayout) dialog.findViewById(R.id.discountlist);


                discountAdapter = new DiscountListViewAdapter(ProductsActivity.this, discountArrayList);

                discountview.setAdapter(discountAdapter);


                rangebar1 = (RangeBar) dialog.findViewById(R.id.rangebar1);

                final Button leftIndexValue = (Button) dialog.findViewById(R.id.rang1);
                final Button rightIndexValue = (Button) dialog.findViewById(R.id.rang2);

                // Sets the display values of the indices
                rangebar1.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
                    @Override
                    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                                      int rightPinIndex,
                                                      String leftPinValue, String rightPinValue) {
                        leftIndexValue.setText("$" + leftPinIndex);
                        rightIndexValue.setText("$" + rightPinIndex);
                    }

                });


                ImageView closeFilter = dialog.findViewById(R.id.closeFilter);

                closeFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                brands.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        clickb("1");

                    }
                });
                colour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        clickb("2");

                    }
                });
                price.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        clickb("3");

                    }
                });
                discount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        clickb("4");

                    }
                });

            }
        });


        txtSortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


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


    private void clickb(String s) {
        brands.setTextColor(Color.parseColor("#353944"));
        brandlist.setVisibility(View.GONE);
        colour.setTextColor(Color.parseColor("#353944"));
        colourlist.setVisibility(View.GONE);
        price.setTextColor(Color.parseColor("#353944"));
        pricelist.setVisibility(View.GONE);
        discount.setTextColor(Color.parseColor("#353944"));
        discountlist.setVisibility(View.GONE);


        if (s.equalsIgnoreCase("1")) {

            brandlist.setVisibility(View.VISIBLE);
            brands.setTextColor(Color.parseColor("#ff5254"));
        } else if (s.equalsIgnoreCase("2")) {

            colourlist.setVisibility(View.VISIBLE);
            colour.setTextColor(Color.parseColor("#ff5254"));
        } else if (s.equalsIgnoreCase("3")) {

            pricelist.setVisibility(View.VISIBLE);
            price.setTextColor(Color.parseColor("#ff5254"));
        } else if (s.equalsIgnoreCase("4")) {

            discountlist.setVisibility(View.VISIBLE);
            discount.setTextColor(Color.parseColor("#ff5254"));
        }

    }

    private void getProductsRequest() {

        dialog = new ProgressDialog(ProductsActivity.this);

        dialog.setMessage("Getting Products.");
        dialog.show();


        String xAccessToken = "mykey";
        MultipartBody.Part seller_id = MultipartBody.Part.createFormData("subcategory_id", subcat_id);


        mainAPIInterface.getAllProducts(xAccessToken, seller_id).enqueue(new Callback<ProductsListModel>() {
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

                        gridviewAdapter = new GridviewAdapter(ProductsActivity.this, beanclassArrayList);

                        gridview.setExpanded(true);

                        gridview.setAdapter(gridviewAdapter);

                        System.out.println("Product List Size==" + beanclassArrayList.size());


//        /        ********LISTVIEW***********


                        baseAdapter = new ListviewAdapter(ProductsActivity.this, beanclassArrayList);

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

    private void getProductsFromChildCategoryRequest() {

        dialog = new ProgressDialog(ProductsActivity.this);

        dialog.setMessage("Getting Products.");
        dialog.show();


        String xAccessToken = "mykey";
        MultipartBody.Part seller_id = MultipartBody.Part.createFormData("child_category_id", child_category_id);


        mainAPIInterface.getAllProductsFromChildCategory(xAccessToken, seller_id).enqueue(new Callback<ProductsListModel>() {
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

                        gridviewAdapter = new GridviewAdapter(ProductsActivity.this, beanclassArrayList);

                        gridview.setExpanded(true);

                        gridview.setAdapter(gridviewAdapter);

                        System.out.println("Product List Size==" + beanclassArrayList.size());


//        /        ********LISTVIEW***********


                        baseAdapter = new ListviewAdapter(ProductsActivity.this, beanclassArrayList);

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

    private void getProductsFromDeal() {
        dialog = new ProgressDialog(ProductsActivity.this);

        dialog.setMessage("Getting Products.");
        dialog.show();


        String xAccessToken = "mykey";
        MultipartBody.Part products_id = MultipartBody.Part.createFormData("products", strProductIds);


        mainAPIInterface.getAllFlashDealProducts(xAccessToken, products_id).enqueue(new Callback<FlashDealsProductsOutputModel>() {
            @Override
            public void onResponse(Call<FlashDealsProductsOutputModel> call, Response<FlashDealsProductsOutputModel> response) {
                if (response.isSuccessful()) {

                    dialog.dismiss();

                    productArrayList = response.body().getProducts();
                    flashDealArrayList = new ArrayList<>();

                    System.out.println("ORIGINAL SIZE==" + productArrayList.size());

                    for (int i = 0; i < productArrayList.size(); i++) {
                        System.out.println(productArrayList.get(i));

                        flashDealArrayList.add(productArrayList.get(i).get(0));

                    }


                    if (flashDealArrayList.isEmpty()) {

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

                        gridviewAdapter2 = new GridviewAdapter2(ProductsActivity.this, flashDealArrayList);

                        gridview.setExpanded(true);

                        gridview.setAdapter(gridviewAdapter2);

                        System.out.println("Product List Size==" + flashDealArrayList.size());


//        /        ********LISTVIEW***********


                        baseAdapter2 = new ListviewAdapter2(ProductsActivity.this, flashDealArrayList);

                        listview.setAdapter(baseAdapter2);


                    }


                }
            }

            @Override
            public void onFailure(Call<FlashDealsProductsOutputModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("Error", t.getMessage().toString());
            }
        });


    }

    private void getProductsByBrand() {

        dialog = new ProgressDialog(ProductsActivity.this);

        dialog.setMessage("Getting Products.");
        dialog.show();


        String xAccessToken = "mykey";
        MultipartBody.Part seller_id = MultipartBody.Part.createFormData("brand_id", brand_id);


        mainAPIInterface.getAllProductsFromBrand(xAccessToken, seller_id).enqueue(new Callback<ProductsListModel>() {
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

                        gridviewAdapter = new GridviewAdapter(ProductsActivity.this, beanclassArrayList);

                        gridview.setExpanded(true);

                        gridview.setAdapter(gridviewAdapter);

                        System.out.println("Product List Size==" + beanclassArrayList.size());


//        /        ********LISTVIEW***********


                        baseAdapter = new ListviewAdapter(ProductsActivity.this, beanclassArrayList);

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

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);

        ImageView appLogo = toolbar.findViewById(R.id.appLogo);
        appLogo.setVisibility(View.GONE);

        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText(subcat_name + " Products");


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

}
