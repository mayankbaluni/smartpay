package com.smart.pay.fragments.mall;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.smart.pay.R;
import com.smart.pay.adapter.mall.SellerServiceListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.NewServiceListModel;

import java.util.List;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 13-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
@SuppressLint("ValidFragment")
public class SellerDetailServiceListFragment extends Fragment {

    View mView;

    RecyclerView services_list_recyclerview;


    RelativeLayout empty_layout;

    MainAPIInterface mainAPIInterface;

    private List<NewServiceListModel.Service> productList;


    SellerServiceListAdapter mAdapter;

    String sellerId;

    ProgressBar sellerListProgressBar;

    @SuppressLint("ValidFragment")
    public SellerDetailServiceListFragment(String sellerId) {
        this.sellerId = sellerId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.seller_detail_services_list, container, false);

        services_list_recyclerview = (RecyclerView) mView.findViewById(R.id.services_list_recyclerview);

        empty_layout = (RelativeLayout) mView.findViewById(R.id.empty_layout);

        mainAPIInterface = ApiUtils.getAPIService();

        sellerListProgressBar = (ProgressBar) mView.findViewById(R.id.sellerListProgressBar);


        getServicesRequest();

        return mView;

    }


    private void getServicesRequest() {

        services_list_recyclerview.setVisibility(View.GONE);
        sellerListProgressBar.setVisibility(View.VISIBLE);

        String xAccessToken = "mykey";
        MultipartBody.Part seller_id = MultipartBody.Part.createFormData("seller_id", sellerId);


        mainAPIInterface.getAllServices(xAccessToken, seller_id).enqueue(new Callback<NewServiceListModel>() {
            @Override
            public void onResponse(Call<NewServiceListModel> call, Response<NewServiceListModel> response) {


                if (response.isSuccessful()) {


                    productList = response.body().getServices();

                    if (productList.isEmpty()) {

                        services_list_recyclerview.setVisibility(View.GONE);
                        empty_layout.setVisibility(View.VISIBLE);
                        sellerListProgressBar.setVisibility(View.GONE);

                    } else {

                        services_list_recyclerview.setVisibility(View.VISIBLE);
                        empty_layout.setVisibility(View.GONE);
                        sellerListProgressBar.setVisibility(View.GONE);


                        mAdapter = new SellerServiceListAdapter(productList, getActivity());

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                        services_list_recyclerview.setLayoutManager(layoutManager);

                        services_list_recyclerview.setItemAnimator(new DefaultItemAnimator());
                        services_list_recyclerview.setAdapter(mAdapter);

                    }


                }
            }

            @Override
            public void onFailure(Call<NewServiceListModel> call, Throwable t) {
                Log.i("Error", t.getMessage().toString());
                sellerListProgressBar.setVisibility(View.GONE);

            }
        });

    }


}
