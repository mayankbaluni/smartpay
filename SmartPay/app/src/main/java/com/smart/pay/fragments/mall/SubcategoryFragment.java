package com.smart.pay.fragments.mall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.activity.mall.ProductsActivity;
import com.smart.pay.adapter.mall.CategoryListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.CategoryListModel;
import com.smart.pay.utils.RecyclerTouchListener;

import java.util.List;

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
public class SubcategoryFragment extends Fragment {

    private View mView;

    RecyclerView subcategoriesListView;

    String cat_id;

    private ProgressDialog dialog;

    CategoryListAdapter categoryListAdapter;

    private MainAPIInterface mainAPIInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.subcategory_fragment, container, false);

        subcategoriesListView = (RecyclerView) mView.findViewById(R.id.subcategoriesListView);

        mainAPIInterface = ApiUtils.getAPIService();

        Bundle bundle = getArguments();
        if (getArguments() != null) {
            cat_id = bundle.getString("cat_id");

        }

        System.out.println("cat id==" + cat_id);

        if (isNetworkAvailable(getActivity())) {
            getSubCategoriesRequest();
        }

        return mView;
    }

    private void getSubCategoriesRequest() {
        String xAccessToken = "mykey";


        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Getting Categories.");
        dialog.show();

        mainAPIInterface.getSubCategories(xAccessToken, cat_id).enqueue(new Callback<CategoryListModel>() {
            @Override
            public void onResponse(Call<CategoryListModel> call, Response<CategoryListModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {
                        final List<CategoryListModel.Cat> newCatList = response.body().getCat();

                        categoryListAdapter = new CategoryListAdapter(newCatList, getActivity());

                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                        subcategoriesListView.setLayoutManager(layoutManager);

                        subcategoriesListView.setItemAnimator(new DefaultItemAnimator());
                        subcategoriesListView.setAdapter(categoryListAdapter);

                        categoryListAdapter.notifyDataSetChanged();


                        subcategoriesListView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), subcategoriesListView, new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {

                                if (isNetworkAvailable(getActivity())) {

                                    Intent i = new Intent(getActivity(), ProductsActivity.class);

                                    i.putExtra("cat_id", cat_id);
                                    i.putExtra("subcat_id", newCatList.get(position).getId());
                                    i.putExtra("subcat_name", newCatList.get(position).getServiceName());
                                    startActivity(i);


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
            }

            @Override
            public void onFailure(Call<CategoryListModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


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
