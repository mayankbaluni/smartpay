package com.smart.pay.fragments.mall;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.mall.SellerReviewListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.PostSellerReviewInputModel;
import com.smart.pay.models.output.PostSellerReviewOutputModel;
import com.smart.pay.models.output.SellerReviewListOutputModel;
import com.smart.pay.utils.DataVaultManager;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_NAME;
import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 13-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
@SuppressLint("ValidFragment")
public class SellerDetailReviewFragment extends Fragment {

    View mView;

    TextView add_review_button;
    RecyclerView reviewList;

    ProgressDialog dialog;

    View customView;

    Button btnSubmitSellerReview;

    RatingBar ratingbar1;
    EditText edtReviewTitle1, edtReviewComment1;
    String strReviewTitle, strReviewComment, strRating;
    String strCustomerId, strCustomerName, strSellerId;

    MainAPIInterface mainAPIInterface;

    private ProgressDialog newProgressDialog;

    public ArrayList<SellerReviewListOutputModel.Cat> sellerReviewList;

    public SellerReviewListAdapter sellerReviewListAdapter;

    RelativeLayout empty_review_layout;


    @SuppressLint("ValidFragment")
    public SellerDetailReviewFragment(String sellerId) {
        this.strSellerId = sellerId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.seller_detail_review, container, false);

        mainAPIInterface = ApiUtils.getAPIService();


        reviewList = (RecyclerView) mView.findViewById(R.id.reviewList);

        empty_review_layout = (RelativeLayout) mView.findViewById(R.id.empty_review_layout);


        add_review_button = mView.findViewById(R.id.add_review_button);

        customView = inflater.inflate(R.layout.add_seller_review_dialog, null);

        btnSubmitSellerReview = (Button) customView.findViewById(R.id.btnSubmitSellerReview);
        ratingbar1 = (RatingBar) customView.findViewById(R.id.ratingbar1);
        edtReviewTitle1 = (EditText) customView.findViewById(R.id.edtReviewTitle1);
        edtReviewComment1 = (EditText) customView.findViewById(R.id.edtReviewComment1);


        add_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (customView.getParent() != null) {
                    ((ViewGroup) customView.getParent()).removeView(customView); // <- fix
                } else {

//                    dialog = new MaterialStyledDialog.Builder(getActivity())
//                            .setCustomView(customView)
//                            .setCancelable(true)
//                            .build();
//
//
//                    dialog.show();
                }


            }
        });


        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Float ratingVal = (Float) rating;
                Float ratingvalue = (Float) ratingbar1.getRating();

                strRating = String.valueOf(ratingvalue);

                Toast.makeText(getActivity(), " Ratings : " + String.valueOf(ratingVal) + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), " Ratings1 : " + ratingvalue + "", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmitSellerReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strReviewComment = edtReviewComment1.getText().toString();
                strReviewTitle = edtReviewTitle1.getText().toString();

                updateReviewRequest();
            }
        });

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getReviewListRequest();


    }

    public void updateReviewRequest() {

        newProgressDialog = new ProgressDialog(getActivity());

        newProgressDialog.setMessage("Booking your appointment.");
        newProgressDialog.show();

        String xAccessToken = "mykey";

        strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);
        strCustomerName = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_NAME);

        PostSellerReviewInputModel postSellerReviewInputModel = new PostSellerReviewInputModel();
        postSellerReviewInputModel.setBookingId("");
        postSellerReviewInputModel.setComments(strReviewComment);
        postSellerReviewInputModel.setTitle(strReviewTitle);
        postSellerReviewInputModel.setCustomerId(strCustomerId);
        postSellerReviewInputModel.setCustomerName(strCustomerName);
        postSellerReviewInputModel.setSellerId(strSellerId);
        postSellerReviewInputModel.setRating(strRating);


        mainAPIInterface.postSellerReview(xAccessToken, postSellerReviewInputModel).enqueue(new Callback<PostSellerReviewOutputModel>() {
            @Override
            public void onResponse(Call<PostSellerReviewOutputModel> call, Response<PostSellerReviewOutputModel> response) {


                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        dialog.dismiss();

                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_LONG).show();

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

    public void getReviewListRequest() {
        String xAccessToken = "mykey";

        newProgressDialog = new ProgressDialog(getActivity());

        newProgressDialog.setMessage("Getting All Reviews.");

        newProgressDialog.show();

        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("seller_id", strSellerId);


        mainAPIInterface.getSellerReviewList(xAccessToken, seller_id_body).enqueue(new Callback<SellerReviewListOutputModel>() {
            @Override
            public void onResponse(Call<SellerReviewListOutputModel> call, Response<SellerReviewListOutputModel> response) {


                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    sellerReviewList = response.body().getCat();

                    System.out.print("SIZE"+sellerReviewList.size());

                    if (sellerReviewList.isEmpty()) {

                        System.out.print("EMPTY");

                        empty_review_layout.setVisibility(View.VISIBLE);
                        reviewList.setVisibility(View.GONE);

                    } else {
                        System.out.print(" NOT EMPTY");


                        empty_review_layout.setVisibility(View.GONE);
                        reviewList.setVisibility(View.VISIBLE);

                        sellerReviewListAdapter = new SellerReviewListAdapter(sellerReviewList, getActivity());

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                        reviewList.setLayoutManager(layoutManager);

                        reviewList.setItemAnimator(new DefaultItemAnimator());
                        reviewList.setAdapter(sellerReviewListAdapter);

                        sellerReviewListAdapter.notifyDataSetChanged();

                    }


                }
            }

            @Override
            public void onFailure(Call<SellerReviewListOutputModel> call, Throwable t) {
                newProgressDialog.dismiss();


                Log.i("tag", t.getMessage().toString());
            }
        });
    }

}
