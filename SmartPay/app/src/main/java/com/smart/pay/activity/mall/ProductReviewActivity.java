package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.PostSellerReviewOutputModel;
import com.smart.pay.models.output.RateProductInputModel;
import com.smart.pay.utils.DataVaultManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_NAME;
import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 07-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ProductReviewActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;

    String product_id;

    private ProgressDialog newProgressDialog;
    String strCustomerId, strCustomerName;

    Button btnSubmitProductReview;
    RatingBar product_ratingbar;

    EditText edtReviewComment2, edtReviewTitle2;

    String strReviewTitle, strReviewComment, strRating;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_review_list_layout);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        Intent i = getIntent();
        product_id = i.getExtras().getString("product_id");

        btnSubmitProductReview = (Button) findViewById(R.id.btnSubmitProductReview);
        product_ratingbar = (RatingBar) findViewById(R.id.product_ratingbar);
        edtReviewComment2 = (EditText) findViewById(R.id.edtReviewComment2);
        edtReviewTitle2 = (EditText) findViewById(R.id.edtReviewTitle2);


        product_ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Float ratingVal = (Float) rating;
                Float ratingvalue = (Float) product_ratingbar.getRating();

                strRating = String.valueOf(ratingvalue);

                Toast.makeText(ProductReviewActivity.this, " Ratings : " + String.valueOf(ratingVal) + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(ProductReviewActivity.this, " Ratings1 : " + ratingvalue + "", Toast.LENGTH_SHORT).show();


            }
        });

        btnSubmitProductReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strReviewTitle = edtReviewTitle2.getText().toString();
                strReviewComment = edtReviewComment2.getText().toString();

                rateProductRequest();
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

        toolbarTitle.setText("Reviews");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Reviews");

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


    public void rateProductRequest() {

        newProgressDialog = new ProgressDialog(ProductReviewActivity.this);

        newProgressDialog.setMessage("Adding your review.");
        newProgressDialog.show();

        String xAccessToken = "mykey";

        strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);
        strCustomerName = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_NAME);


        RateProductInputModel rateProductInputModel = new RateProductInputModel();
        rateProductInputModel.setCustomerId(strCustomerId);
        rateProductInputModel.setCustomerName(strCustomerName);
        rateProductInputModel.setOrderId("");
        rateProductInputModel.setComments(strReviewComment);
        rateProductInputModel.setProductId(product_id);
        rateProductInputModel.setRating(strRating);
        rateProductInputModel.setTitle(strReviewTitle);

        mainAPIInterface.postProductReview(xAccessToken, rateProductInputModel).enqueue(new Callback<PostSellerReviewOutputModel>() {
            @Override
            public void onResponse(Call<PostSellerReviewOutputModel> call, Response<PostSellerReviewOutputModel> response) {


                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        newProgressDialog.dismiss();

                        Toast.makeText(ProductReviewActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                        ProductReviewActivity.this.finish();


                    } else {

                        Toast.makeText(ProductReviewActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();

                    }


                }
            }

            @Override
            public void onFailure(Call<PostSellerReviewOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });

    }

}
