package com.smart.pay.fragments.mall;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.smart.pay.R;
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
public class CategoriesFragment extends Fragment {


    private MainAPIInterface mainAPIInterface;

    RecyclerView categoriesListView;
    private ProgressDialog dialog;

    List<String> list1 = null;

    CategoryListAdapter categoryListAdapter;


    FrameLayout category_container;

    String strCatId;

    View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.category_list_fragment, container, false);

        mainAPIInterface = ApiUtils.getAPIService();

        categoriesListView = (RecyclerView) mView.findViewById(R.id.categoriesListView);

        if (isNetworkAvailable(getActivity())) {


            getCategoriesRequest();

        } else {


            Toast.makeText(getActivity(), "Check internet connection.!", Toast.LENGTH_SHORT).show();

        }

        return mView;
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


                    categoryListAdapter = new CategoryListAdapter(newCatList, getActivity());

                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

                    categoriesListView.setLayoutManager(layoutManager);

                    categoriesListView.setItemAnimator(new DefaultItemAnimator());
                    categoriesListView.setAdapter(categoryListAdapter);


                    categoryListAdapter.notifyDataSetChanged();


                    categoriesListView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), categoriesListView, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            if (isNetworkAvailable(getActivity())) {
                                Bundle bundle = new Bundle();
                                bundle.putString("cat_id", newCatList.get(position).getId());

                                Fragment fragment = new SubcategoryFragment();

                                fragment.setArguments(bundle);

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.replace(R.id.category_container, fragment);
                                fragmentTransaction.commit();


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
