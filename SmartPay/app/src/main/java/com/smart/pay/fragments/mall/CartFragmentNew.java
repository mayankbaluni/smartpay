package com.smart.pay.fragments.mall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.mall.CartListAdapterNew;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetOnlineCartOutput;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.GIFView;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


public class CartFragmentNew extends Fragment {

    RelativeLayout ll_cart_layout;
    MyTextView go_to_homepage;

    RecyclerView cartListView;
    GIFView gifImageView;

    MyTextView btnPlaceOrder;

    View mView;

    ProgressDialog dialog;
    MainAPIInterface mainAPIInterface;

    CartListAdapterNew cartListAdapterNew;

    ArrayList<GetOnlineCartOutput.CartProduct> cartProductArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.cart_list_view, container, false);
        mainAPIInterface = ApiUtils.getAPIService();

        cartListView = (RecyclerView) mView.findViewById(R.id.cartListView);

        btnPlaceOrder = (MyTextView) mView.findViewById(R.id.btnPlaceOrder);

        go_to_homepage = (MyTextView) mView.findViewById(R.id.go_to_homepage);
        ll_cart_layout = (RelativeLayout) mView.findViewById(R.id.ll_cart_layout);
        gifImageView = (GIFView) mView.findViewById(R.id.gifImageView);

        getAllCartProducts();


        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PlaceOrderFragment();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.category_container, fragment);
                fragmentTransaction.commit();

            }
        });

        return mView;

    }

    private void getAllCartProducts() {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Getting Products.");
        dialog.show();

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        System.out.println("CUSTOMER ID==" + strCustomerId);

        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);


        mainAPIInterface.getOnlineCartList(xAccessToken, customer_id_body).enqueue(new Callback<GetOnlineCartOutput>() {
            @Override
            public void onResponse(Call<GetOnlineCartOutput> call, Response<GetOnlineCartOutput> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    cartProductArrayList = response.body().getCartProducts();

                    if (cartProductArrayList.isEmpty()) {

                        cartListView.setVisibility(View.GONE);
                        ll_cart_layout.setVisibility(View.VISIBLE);
                        btnPlaceOrder.setVisibility(View.GONE);
                        gifImageView.setVisibility(View.VISIBLE);

                        gifImageView.loadGIFResource(getActivity(), R.drawable.anim_cart);


                        go_to_homepage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                getActivity().finish();
                            }
                        });


                    } else {

                        btnPlaceOrder.setVisibility(View.VISIBLE);
                        cartListView.setVisibility(View.VISIBLE);
                        ll_cart_layout.setVisibility(View.GONE);
                        gifImageView.setVisibility(View.GONE);


                        cartListAdapterNew = new CartListAdapterNew(cartProductArrayList, (AppCompatActivity) getActivity());

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                        cartListView.setLayoutManager(layoutManager);

                        cartListView.setItemAnimator(new DefaultItemAnimator());
                        cartListView.setAdapter(cartListAdapterNew);


                        cartListAdapterNew.notifyDataSetChanged();
                    }

//                    categoriesListView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), categoriesListView, new RecyclerTouchListener.ClickListener() {
//                        @Override
//                        public void onClick(View view, int position) {
//
//                        }
//
//                        @Override
//                        public void onLongClick(View view, int position) {
//
//                        }
//                    }));

                }
            }

            @Override
            public void onFailure(Call<GetOnlineCartOutput> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }


}
