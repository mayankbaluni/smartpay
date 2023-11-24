package com.smart.pay.fragments.mall;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.smart.pay.R;
import com.smart.pay.activity.mall.CartActivity;
import com.smart.pay.adapter.mall.CartListAdapter;
import com.smart.pay.db.DatabaseClient;
import com.smart.pay.db.Product;
import com.smart.pay.views.GIFView;
import com.smart.pay.views.MyTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Sandeep Londhe on 03-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class CartFragment extends Fragment {

    RecyclerView cartListView;
    GIFView gifImageView;


    CartListAdapter cartListAdapter;

    private View mView;

    MyTextView btnPlaceOrder;

    String strCategoryId, strSubcatId, strDateTime;

    ArrayList<String> strProductName = new ArrayList<>();

    ArrayList<String> strProductIds = new ArrayList<>();

    ArrayList<String> strTotalPayble = new ArrayList<>();

    RelativeLayout ll_cart_layout;
    MyTextView go_to_homepage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.cart_list_view, container, false);

        cartListView = (RecyclerView) mView.findViewById(R.id.cartListView);

        btnPlaceOrder = (MyTextView) mView.findViewById(R.id.btnPlaceOrder);

        go_to_homepage = (MyTextView) mView.findViewById(R.id.go_to_homepage);
        ll_cart_layout = (RelativeLayout) mView.findViewById(R.id.ll_cart_layout);
        gifImageView = (GIFView) mView.findViewById(R.id.gifImageView);


        new GetCartProducts().execute();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        strDateTime = formattedDate;



        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartActivity.strProductId = strProductIds;
                CartActivity.strProductName = strProductName;
                CartActivity.strCategoryId = strCategoryId;
                CartActivity.strSubcatId = strSubcatId;
                CartActivity.strDateTime = strDateTime;
                CartActivity.strTotalPayble = strTotalPayble;

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

    class GetCartProducts extends AsyncTask<Void, Void, List<Product>> {

        @Override
        protected void onPreExecute() {

//            deliveryResponseList.clear();
            Log.e("onPreExecutive", "called");
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            List<Product> productList = DatabaseClient
                    .getInstance(getActivity())
                    .getAppDatabase()
                    .daoProduct()
                    .getAll();


            return productList;


        }

        @Override
        protected void onPostExecute(List<Product> productsList) {
            super.onPostExecute(productsList);

            for (int i = 0; i < productsList.size(); i++) {

                strProductName.add(productsList.get(i).getName());
                strCategoryId = productsList.get(i).getCatid();
                strSubcatId = productsList.get(i).getSubcatid();
                strTotalPayble.add(productsList.get(i).getPrice());

                strProductIds.add(String.valueOf(productsList.get(i).getId()));

                System.out.println("Product Name==" + strProductName);
                System.out.println("Product Total Prize2==" + strTotalPayble);
                System.out.println("Product ID's" + strProductIds);

                System.out.print("List Size==" + productsList.size());

            }

            if (productsList.isEmpty()) {

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

                cartListAdapter = new CartListAdapter(productsList, getActivity());

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                cartListView.setLayoutManager(layoutManager);

                cartListView.setItemAnimator(new DefaultItemAnimator());
                cartListView.setAdapter(cartListAdapter);

                cartListAdapter.notifyDataSetChanged();

            }


        }
    }


}

