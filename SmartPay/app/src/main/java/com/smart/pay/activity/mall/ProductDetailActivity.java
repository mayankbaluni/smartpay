package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.mall.GridviewAdapter;
import com.smart.pay.adapter.mall.ProductRatingListViewAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.Constants;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.db.DatabaseClient;
import com.smart.pay.db.Product;
import com.smart.pay.events.Events;
import com.smart.pay.events.GlobalBus;
import com.smart.pay.fragments.mall.ImageSlideShowDialogFragment;
import com.smart.pay.models.output.AddProductToCartModel;
import com.smart.pay.models.output.DetailProductInputModel;
import com.smart.pay.models.output.IsSellerExist;
import com.smart.pay.models.output.ProductReviewListInputModel;
import com.smart.pay.models.output.ProductReviewListOutputModel;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.ChildAnimationExample;
import com.smart.pay.views.ExpandableHeightGridView;
import com.smart.pay.views.MyTextView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.greenrobot.eventbus.Subscribe;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 27-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ProductDetailActivity extends AppCompatActivity {

    String product_id, product_name;

    int cart_number = 0;

    ProgressDialog dialog;

    MainAPIInterface mainAPIInterface;

    int strProductId;

    String strSellerId, strProductName, strCatId, strSubCatId, strChildCatId, strChildCatName, strProductDes, strProductPrice,
            strProductImg;

    MyTextView txtProductName, txtNewPrice, txtOldPrice, txtInStock, btnAddToCart, txtProductDescription;

    RatingBar inside_ratingbar;
    MyTextView no_of_ratings;


    MyTextView txtAddToCart, plus, cartno, minus;
    ImageView productImageView;
    float oldPrice, newPrice, discountPrice;

    MyTextView productReview;

    MyTextView btnRateProduct;

    int numberOfRows;

    String strTotalDiscount;

    String strProductQuantity;

    //For product review list section

    ProgressBar productReviewProgressBar;
    LinearLayout mainReviewLinearLayout;
    RelativeLayout empty_review_product_layout;
    TextView averageProductRating;
    MyTextView review_rating_text;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;

    TextView starCount5, starCount4, starCount3, starCount2, starCount1;

    String noOfRatings, noOfReviews;

    RecyclerView productRatingList;

    ArrayList<ProductReviewListOutputModel.Reviews> reviewsArrayList;

    ProductRatingListViewAdapter productRatingListViewAdapter;

    TextView notificationBadge;

    ArrayList<ProductsListModel.ProductImage> productImageArrayList;

    //For image pager

    SliderLayout mImageSlider;

    PagerIndicator custom_indicator;

    MyTextView gurantee_text;

    int alreadyAddedProductCount, alreadyAddedProductPrice;

    ExpandableHeightGridView similar_products_grid;
    ProgressBar similar_product_progress;
    GridviewAdapter similarProductGridViewAdapter;

    ArrayList<ProductsListModel.Product> similarProductsArrayList;

    MyTextView view_all_similar_products;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);

        setupActionBar();
        mainAPIInterface = ApiUtils.getAPIService();

        GlobalBus.getBus().register(this);

        Intent i = getIntent();
        product_id = i.getExtras().getString("product_id");

        System.out.println("Product Id=" + product_id);

        initializeItems();

        new GetNumberOfCartCount().execute();


    }

    private void initializeItems() {

        productRatingList = (RecyclerView) findViewById(R.id.productRatingList);
        productReviewProgressBar = (ProgressBar) findViewById(R.id.productReviewProgressBar);
        mainReviewLinearLayout = (LinearLayout) findViewById(R.id.mainReviewLinearLayout);
        empty_review_product_layout = (RelativeLayout) findViewById(R.id.empty_review_product_layout);
        averageProductRating = (TextView) findViewById(R.id.averageProductRating);
        review_rating_text = (MyTextView) findViewById(R.id.review_rating_text);
        gurantee_text = (MyTextView) findViewById(R.id.gurantee_text);

        similar_products_grid = (ExpandableHeightGridView) findViewById(R.id.similar_products_grid);
        similar_product_progress = (ProgressBar) findViewById(R.id.similar_product_progress);
        view_all_similar_products = (MyTextView) findViewById(R.id.view_all_similar_products);

        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar5 = (ProgressBar) findViewById(R.id.progressBar5);

        starCount1 = (TextView) findViewById(R.id.starCount1);
        starCount2 = (TextView) findViewById(R.id.starCount2);
        starCount3 = (TextView) findViewById(R.id.starCount3);
        starCount4 = (TextView) findViewById(R.id.starCount4);
        starCount5 = (TextView) findViewById(R.id.starCount5);

        productReview = (MyTextView) findViewById(R.id.productReview);

        btnAddToCart = (MyTextView) findViewById(R.id.btnAddToCart);
        btnRateProduct = (MyTextView) findViewById(R.id.btnRateProduct);


        //For Image Pager
        mImageSlider = (SliderLayout) findViewById(R.id.mImageSlider);
        custom_indicator = (PagerIndicator) findViewById(R.id.custom_indicator);

        txtNewPrice = (MyTextView) findViewById(R.id.txtNewPrice);
        txtOldPrice = (MyTextView) findViewById(R.id.txtOldPrice);
        txtInStock = (MyTextView) findViewById(R.id.txtInStock);
        txtProductDescription = (MyTextView) findViewById(R.id.txtProductDescription);
        cartno = (MyTextView) findViewById(R.id.cartno);


        txtProductName = (MyTextView) findViewById(R.id.txtProductName);
        inside_ratingbar = (RatingBar) findViewById(R.id.inside_ratingbar);
        no_of_ratings = (MyTextView) findViewById(R.id.no_of_ratings);


        productImageView = (ImageView) findViewById(R.id.productImageView);


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SaveProductToCart().execute();

                addProductToOnlineCartRequest();


            }
        });


        btnRateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProductDetailActivity.this, ProductReviewActivity.class);
                intent.putExtra("product_id", product_id);
                startActivity(intent);
            }
        });

        gurantee_text.setText(getResources().getString(R.string.app_name) + " Gurantee");


        view_all_similar_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProductDetailActivity.this, ProductsActivity.class);

                i.putExtra("subcat_id", "null");
                i.putExtra("subcat_name", "Similar");
                i.putExtra("child_category_id", strChildCatId);
                startActivity(i);


            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        if (SmartPayApplication.isNetworkAvailable(ProductDetailActivity.this)) {

            getProductReviewRequest();

        } else {
            Toast.makeText(ProductDetailActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
        }

        getProductDetailRequest();


        new GetNumberOfCartCount().execute();

        new GetAlreadyAddedProductCount().execute();

        new GetAlreadyAddedProductPrice().execute();


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }

    @Subscribe
    public void getMessage(Events.AdapterToActivity adapterToActivity) {

//        Toast.makeText(getApplicationContext(), adapterToActivity.getMessage(), Toast.LENGTH_SHORT).show();

        if (adapterToActivity.getMessage().equalsIgnoreCase("UPDATE_QUERY")) {
            new GetNumberOfCartCount().execute();
            new GetAlreadyAddedProductCount().execute();
            new GetAlreadyAddedProductPrice().execute();


        }
    }

    class GetNumberOfCartCount extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            //adding to database
            numberOfRows = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .daoProduct()
                    .getNumberOfRows();

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (numberOfRows == 0) {

                        notificationBadge.setVisibility(View.GONE);

                    } else if (numberOfRows >= 1) {

                        notificationBadge.setText(Integer.toString(numberOfRows));
                        notificationBadge.setVisibility(View.VISIBLE);

                    }
                }
            }, 1000);

        }
    }

    public void increaseInteger(View view) {
        cart_number = cart_number + 1;
        display(cart_number);

    }

    public void decreaseInteger(View view) {
        cart_number = cart_number - 1;
        display(cart_number);
    }

    private void display(int number) {
        cartno.setText("" + number);
    }

    private void getProductDetailRequest() {

        dialog = new ProgressDialog(ProductDetailActivity.this);

        dialog.setMessage("Getting Details.");
        dialog.show();

        String xAccessToken = "mykey";
        //  MultipartBody.Part seller_id = MultipartBody.Part.createFormData("product_id", product_id);

        DetailProductInputModel detailProductInputModel = new DetailProductInputModel();
        detailProductInputModel.setProductId(product_id);

        mainAPIInterface.getProductDetail(xAccessToken, detailProductInputModel).enqueue(new Callback<ProductsListModel>() {
            @Override
            public void onResponse(Call<ProductsListModel> call, Response<ProductsListModel> response) {

                if (response.isSuccessful()) {

                    dialog.dismiss();

                    Log.d("response", response.body().getProducts().toString());


                    List<ProductsListModel.Product> myProductList = response.body().getProducts();


                    for (int i = 0; i < myProductList.size(); i++) {

                        productImageArrayList = myProductList.get(i).getProductImages();


                        if (productImageArrayList.isEmpty()) {
                            mImageSlider.setVisibility(View.GONE);
                            custom_indicator.setVisibility(View.GONE);
                            productImageView.setVisibility(View.VISIBLE);

                        } else {

                            mImageSlider.setVisibility(View.VISIBLE);
                            custom_indicator.setVisibility(View.VISIBLE);
                            productImageView.setVisibility(View.GONE);

                            ProductsListModel.ProductImage productImage = productImageArrayList.get(i);


                            System.out.println("Image List Size==" + productImageArrayList.size());

                            productImage.setImageId("-1");
                            productImage.setProductImage(myProductList.get(i).productImage);
                            productImage.setAddedDate("none");
                            productImage.setSellerId("0");


                            productImageArrayList.add(productImage);

                            for (int j = 0; j < productImageArrayList.size(); j++) {

                                System.out.println("Inside for loop");

                                TextSliderView textSliderView = new TextSliderView(ProductDetailActivity.this);
                                // initialize a SliderLayout
                                final int finalJ = j;

                                textSliderView
                                        //  .description(name)
                                        .image(Constants.PRODUCT_IMAGE_PATH + productImageArrayList.get(j).getProductImage())
                                        .setScaleType(BaseSliderView.ScaleType.CenterInside)
                                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                            @Override
                                            public void onSliderClick(BaseSliderView slider) {


                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("images", (Serializable) productImageArrayList);
                                                bundle.putInt("position", finalJ);
                                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                                ImageSlideShowDialogFragment newFragment = ImageSlideShowDialogFragment.newInstance();
                                                newFragment.setArguments(bundle);
                                                newFragment.show(ft, "slideshow");


                                            }
                                        });


                                System.out.println("Images Path==" + Constants.PRODUCT_IMAGE_PATH + productImageArrayList.get(j).getProductImage());
//
//                                textSliderView.bundle(new Bundle());
//                                textSliderView.getBundle().putString("extra", productImageArrayList.get(j).getProductImage());

                                mImageSlider.addSlider(textSliderView);

                            }


                            mImageSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                            mImageSlider.setCustomIndicator(custom_indicator);
//                            mImageSlider.setCurrentPosition(-1);
                            //     mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mImageSlider.setPadding(0, 0, 0, 5);
                            mImageSlider.setCustomAnimation(new ChildAnimationExample());
                            mImageSlider.stopAutoCycle();

                        }


                        strProductId = Integer.valueOf(myProductList.get(i).id);

                        strCatId = myProductList.get(i).categoryId;
                        strSubCatId = myProductList.get(i).subcategoryId;
                        strChildCatId = myProductList.get(i).childCategoryId;
                        strProductDes = myProductList.get(i).description;
                        strProductImg = myProductList.get(i).productImage;
                        strProductPrice = myProductList.get(i).price;
                        strProductName = myProductList.get(i).productName;
                        strSellerId = myProductList.get(i).sellerId;

                        System.out.print("PRODUCT ID==" + strProductId + "\nSeller ID==" + strSellerId);

                        getSimilarProductsFromChildCat();

                        if (myProductList.get(i).discount.equalsIgnoreCase("") || myProductList.get(i).discount == null) {

                            System.out.print("PRICE CHECKED IN NULL PRICE");

                            oldPrice = Float.valueOf(myProductList.get(i).price);
                            newPrice = Float.valueOf(myProductList.get(i).price);


                        } else if (Float.valueOf(myProductList.get(i).discount) > 0) {

                            System.out.print("PRICE CHECKED IN NULL PRICE >0");

                            oldPrice = Float.valueOf(myProductList.get(i).price);

                            discountPrice = oldPrice * Float.valueOf(myProductList.get(i).discount) / 100;


                            newPrice = oldPrice - discountPrice;


                            if (myProductList.get(i).discount != null) {
                                strTotalDiscount = myProductList.get(i).discount;

                            } else if (Integer.valueOf(myProductList.get(i).discount) != 0) {

                                strTotalDiscount = myProductList.get(i).discount;

                            } else {

                                strTotalDiscount = "0";
                            }

                        } else {

                            System.out.print("PRICE CHECKED IN NULL PRICE ALL");


                            oldPrice = Float.valueOf(myProductList.get(i).price);
                            newPrice = Float.valueOf(myProductList.get(i).price);

                        }


                        if (myProductList.get(i).getRating() != null) {

                            inside_ratingbar.setRating(Float.valueOf(myProductList.get(i).getRating()));

                        } else {

                            inside_ratingbar.setRating(0);
                        }

                        LayerDrawable stars = (LayerDrawable) inside_ratingbar.getProgressDrawable();
                        stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);

                        inside_ratingbar.setClickable(false);

                        no_of_ratings.setText(myProductList.get(i).getNoOfRating());
                        productReview.setText("(" + myProductList.get(i).getNoOfReviews() + ")");


                        txtProductName.setText(strProductName);
                        txtNewPrice.setText(SmartPayApplication.CURRENCY_SYMBOL + newPrice);
                        txtOldPrice.setText(SmartPayApplication.CURRENCY_SYMBOL + oldPrice);
                        txtInStock.setVisibility(View.VISIBLE);
                        txtProductDescription.setText(strProductDes);

                        txtOldPrice.setPaintFlags(txtOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        Picasso.with(ProductDetailActivity.this)
                                .load(Constants.PRODUCT_IMAGE_PATH + strProductImg)
                                .placeholder(R.drawable.placeholder)
                                .into(productImageView);
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

    private void getProductReviewRequest() {
        String xAccessToken = "mykey";

        final ProductReviewListInputModel productReviewListInputModel = new ProductReviewListInputModel();
        productReviewListInputModel.setProductId(product_id);

        productReviewProgressBar.setVisibility(View.VISIBLE);
        empty_review_product_layout.setVisibility(View.GONE);
        mainReviewLinearLayout.setVisibility(View.GONE);

        mainAPIInterface.getProductReviewList(xAccessToken, productReviewListInputModel).enqueue(new Callback<ProductReviewListOutputModel>() {
            @Override
            public void onResponse(Call<ProductReviewListOutputModel> call, Response<ProductReviewListOutputModel> response) {

                if (response.isSuccessful()) {

                    if (response.body().getSuccess().equalsIgnoreCase("0")) {

                        productReviewProgressBar.setVisibility(View.GONE);
                        empty_review_product_layout.setVisibility(View.VISIBLE);
                        mainReviewLinearLayout.setVisibility(View.GONE);


                    } else if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        productReviewProgressBar.setVisibility(View.GONE);
                        empty_review_product_layout.setVisibility(View.GONE);
                        mainReviewLinearLayout.setVisibility(View.VISIBLE);


                        double value = Double.valueOf(response.body().getAverageRating());
                        double valueRounded = Math.round(value * 100D) / 100D;

                        averageProductRating.setText(String.valueOf(valueRounded));

                        starCount1.setText(String.valueOf(response.body().getRatingOne()));
                        starCount2.setText(String.valueOf(response.body().getRatingTwo()));
                        starCount3.setText(String.valueOf(response.body().getRatingThree()));
                        starCount4.setText(String.valueOf(response.body().getRatingFour()));
                        starCount5.setText(String.valueOf(response.body().getRatingFive()));

                        noOfRatings = String.valueOf(response.body().getRating());
                        noOfReviews = String.valueOf(response.body().getReviews());

                        review_rating_text.setText(noOfRatings + " ratings and " + noOfReviews + " reviews");

//                        empty_review_layout.setVisibility(View.GONE);
                        productRatingList.setVisibility(View.VISIBLE);

                        reviewsArrayList = response.body().getData();


                        productRatingListViewAdapter = new ProductRatingListViewAdapter(reviewsArrayList, ProductDetailActivity.this);

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(ProductDetailActivity.this, RecyclerView.VERTICAL, false);

                        productRatingList.setLayoutManager(layoutManager);

                        productRatingList.setItemAnimator(new DefaultItemAnimator());
                        productRatingList.setAdapter(productRatingListViewAdapter);

                        productRatingListViewAdapter.notifyDataSetChanged();


                    } else {

                        productReviewProgressBar.setVisibility(View.GONE);
                        empty_review_product_layout.setVisibility(View.GONE);
                        mainReviewLinearLayout.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ProductReviewListOutputModel> call, Throwable t) {
                dialog.dismiss();
                productReviewProgressBar.setVisibility(View.GONE);
                empty_review_product_layout.setVisibility(View.GONE);
                mainReviewLinearLayout.setVisibility(View.GONE);
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

        toolbarTitle.setText("Product Detail");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_detail_menu, menu);

        return super.onCreateOptionsMenu(menu);
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
    public boolean onPrepareOptionsMenu(Menu menu) {


        MenuItem item = menu.findItem(R.id.go_to_cart);

        FrameLayout rootView = (FrameLayout) item.getActionView();

        notificationBadge = (TextView) rootView.findViewById(R.id.cart_badge);

        notificationBadge.setVisibility(View.GONE);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (numberOfRows == 0) {

                    notificationBadge.setVisibility(View.GONE);

                } else if (numberOfRows >= 1) {

                    notificationBadge.setText(Integer.toString(numberOfRows));
                    notificationBadge.setVisibility(View.VISIBLE);

                }
            }
        }, 1000);


        //And from here you can do whatever you want with your control

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToCart = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(goToCart);
                invalidateOptionsMenu();

            }
        });


        return true;
    }

    class SaveProductToCart extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            int priceAddition = alreadyAddedProductPrice + (int) newPrice;


            Product product = new Product();

            product.setId(strProductId);
            product.setName(strProductName);
            product.setCatid(strCatId);
            product.setSubcatid(strSubCatId);
            product.setDesc(strProductDes);
            product.setImg(strProductImg);
            product.setPrice(String.valueOf(priceAddition));
            product.setSeller_id(strSellerId);
            product.setStock(alreadyAddedProductCount + 1);


            //adding to database
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .daoProduct()
                    .insert(product);

            System.out.println("PRODUCT ID WHILE SAVING BY==" + strProductId);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Events.AdapterToActivity adapterToActivity = new Events.AdapterToActivity("UPDATE_QUERY");
            GlobalBus.getBus().post(adapterToActivity);

//            Toast.makeText(ProductDetailActivity.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

        }
    }

    class GetAlreadyAddedProductCount extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            //adding to database
            alreadyAddedProductCount = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .daoProduct()
                    .getStockForProductInCart(strProductId);


            System.out.println("PRODUCT ID PASSING BY==" + strProductId);

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }
    }

    class GetAlreadyAddedProductPrice extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //adding new product

            //adding to database
            alreadyAddedProductPrice = DatabaseClient.getInstance(SmartPayApplication.getInstance()).getAppDatabase()
                    .daoProduct()
                    .getPriceForProductInCart(strProductId);


            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            System.out.println("Already added price==" + alreadyAddedProductPrice);


//            viewHolder.txtProductPrice.setText("Price : " + LykaaApplication.CURRENCY_SYMBOL + alreadyAddedProductPrice);


        }
    }

    private void addProductToOnlineCartRequest() {

        dialog = new ProgressDialog(ProductDetailActivity.this);

        dialog.setMessage("Adding To Cart.");
        dialog.show();

        if (strTotalDiscount == null) {
            strTotalDiscount = "0";
        }


        String xAccessToken = "mykey";
        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        AddProductToCartModel addProductToCartModel = new AddProductToCartModel();

        addProductToCartModel.setCustomerId(strCustomerId);
        addProductToCartModel.setSellerId(strSellerId);
        addProductToCartModel.setProductId(strProductId);
        addProductToCartModel.setProductName(strProductName);
        addProductToCartModel.setProductImage(strProductImg);
        addProductToCartModel.setQuantity("1");
        addProductToCartModel.setPrice(String.valueOf(newPrice));
        addProductToCartModel.setDiscount(strTotalDiscount);
        addProductToCartModel.setOld_price(String.valueOf(oldPrice));


        mainAPIInterface.addProductToCart(xAccessToken, addProductToCartModel).enqueue(new Callback<IsSellerExist>() {
            @Override
            public void onResponse(Call<IsSellerExist> call, Response<IsSellerExist> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess() == 1) {


                        Toast.makeText(ProductDetailActivity.this, "Product added successfully ==" + alreadyAddedProductCount,
                                Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(ProductDetailActivity.this, response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<IsSellerExist> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }


    private void getSimilarProductsFromChildCat() {

        similar_product_progress.setVisibility(View.VISIBLE);
        similar_products_grid.setVisibility(View.GONE);

        String xAccessToken = "mykey";
        MultipartBody.Part seller_id = MultipartBody.Part.createFormData("child_category_id", strChildCatId);


        mainAPIInterface.getAllProductsFromChildCategoryLimited(xAccessToken, seller_id).enqueue(new Callback<ProductsListModel>() {
            @Override
            public void onResponse(Call<ProductsListModel> call, Response<ProductsListModel> response) {
                if (response.isSuccessful()) {


                    similar_product_progress.setVisibility(View.GONE);
                    similar_products_grid.setVisibility(View.VISIBLE);

                    similarProductsArrayList = response.body().getProducts();

//                    similarProductsArrayList.remove(new ProductsListModel.Product(String.valueOf(strProductId)));

                    Iterator<ProductsListModel.Product> itr = similarProductsArrayList.iterator();

                    // remove all even numbers
                    while (itr.hasNext()) {
                        ProductsListModel.Product product = itr.next();

                        if (product.getId().equalsIgnoreCase(String.valueOf(strProductId))) {
                            itr.remove();
                        }
                    }


                    if (similarProductsArrayList.isEmpty()) {

                        similar_products_grid.setVisibility(View.GONE);

                        // rl_no_products_found.setVisibility(View.VISIBLE);

//                        btn_go_back.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                                finish();
//                            }
//                        });

                    } else {

                        similar_products_grid.setVisibility(View.VISIBLE);
                        //   rl_no_products_found.setVisibility(View.GONE);

                        similarProductGridViewAdapter = new GridviewAdapter(ProductDetailActivity.this, similarProductsArrayList);

                        similar_products_grid.setExpanded(true);

                        similar_products_grid.setAdapter(similarProductGridViewAdapter);

                        System.out.println("Product List Size==" + similarProductsArrayList.size());


                    }


                }
            }

            @Override
            public void onFailure(Call<ProductsListModel> call, Throwable t) {
                similar_product_progress.setVisibility(View.GONE);
                similar_products_grid.setVisibility(View.VISIBLE);
                Log.i("Error", t.getMessage().toString());
            }
        });


    }


}
