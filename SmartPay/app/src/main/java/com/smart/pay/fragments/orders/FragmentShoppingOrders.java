package com.smart.pay.fragments.orders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.CategoryListActivity;
import com.smart.pay.activity.mall.YourOrderActivity;
import com.smart.pay.adapter.mall.YourOrderListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.YourOrderOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;

public class FragmentShoppingOrders extends Fragment {

    View mView;

    private MainAPIInterface mainAPIInterface;

    RecyclerView yourOrderList;

    public ArrayList<YourOrderOutputModel.Order> orderArrayList;

    public YourOrderListAdapter yourOrderListAdapter;

    LinearLayout ll_order_layout;
    MyTextView go_to_categories;

    ProgressBar shoppingProgress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.shopping_orders_fragment, container, false);

        mainAPIInterface = ApiUtils.getAPIService();

        yourOrderList = (RecyclerView) mView.findViewById(R.id.yourOrderList);
        ll_order_layout = (LinearLayout) mView.findViewById(R.id.ll_order_layout);
        go_to_categories = (MyTextView) mView.findViewById(R.id.go_to_categories);
        shoppingProgress = (ProgressBar) mView.findViewById(R.id.shoppingProgress);

        getAllOrders();

        return mView;
    }


    public void getAllOrders() {

        shoppingProgress.setVisibility(View.VISIBLE);
        yourOrderList.setVisibility(View.GONE);

        String strUserId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        String xAccessToken = "mykey";


        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strUserId);


        mainAPIInterface.getAllYourOrders(xAccessToken, customer_id_body).enqueue(new Callback<YourOrderOutputModel>() {
            @Override
            public void onResponse(Call<YourOrderOutputModel> call, Response<YourOrderOutputModel> response) {


                if (response.isSuccessful()) {

                    shoppingProgress.setVisibility(View.GONE);
                    yourOrderList.setVisibility(View.VISIBLE);

                    orderArrayList = response.body().getOrders();

                    if (orderArrayList.isEmpty()) {

                        yourOrderList.setVisibility(View.GONE);
                        ll_order_layout.setVisibility(View.VISIBLE);

                        go_to_categories.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent i = new Intent(getActivity(), CategoryListActivity.class);
                                i.putExtra("from", "home");
                                startActivity(i);
                                getActivity().finish();

                            }
                        });


                    } else {

                        yourOrderList.setVisibility(View.VISIBLE);
                        ll_order_layout.setVisibility(View.GONE);


                        yourOrderListAdapter = new YourOrderListAdapter(orderArrayList, getActivity());

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                        yourOrderList.setLayoutManager(layoutManager);

                        yourOrderList.setItemAnimator(new DefaultItemAnimator());
                        yourOrderList.setAdapter(yourOrderListAdapter);

                        yourOrderListAdapter.notifyDataSetChanged();


                    }


                }
            }

            @Override
            public void onFailure(Call<YourOrderOutputModel> call, Throwable t) {

                shoppingProgress.setVisibility(View.GONE);
                yourOrderList.setVisibility(View.VISIBLE);

                Log.i("tag", t.getMessage().toString());
            }
        });
    }
}
