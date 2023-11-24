package com.smart.pay.fragments.mall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetOnlineCartOutput;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 07-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class PlaceOrderFragment extends Fragment {

    View mView;

    MyTextView goToAddress;

    int strTotalAmount;

    MyTextView total, stotal, payable, txtDiscount;

    public ArrayList<String> paybleList;

    double price = 0;
    double old_price = 0;
    double discount = 0;

    ArrayList<GetOnlineCartOutput.CartProduct> cartProductArrayList;

    MainAPIInterface mainAPIInterface;

    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.place_order_fragment, container, false);
        mainAPIInterface = ApiUtils.getAPIService();

        goToAddress = (MyTextView) mView.findViewById(R.id.goToAddress);
        total = (MyTextView) mView.findViewById(R.id.total);
        stotal = (MyTextView) mView.findViewById(R.id.stotal);
        payable = (MyTextView) mView.findViewById(R.id.payable);
        txtDiscount = (MyTextView) mView.findViewById(R.id.discount);


        goToAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new AddressFragment();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.category_container, fragment);
                fragmentTransaction.commit();

            }
        });

        getAllCartProducts();

        return mView;
    }


    private void getAllCartProducts() {
        String xAccessToken = "mykey";


        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Getting Details.");
        dialog.show();

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);


        mainAPIInterface.getOnlineCartList(xAccessToken, customer_id_body).enqueue(new Callback<GetOnlineCartOutput>() {
            @Override
            public void onResponse(Call<GetOnlineCartOutput> call, Response<GetOnlineCartOutput> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();
                    cartProductArrayList = response.body().getCartProducts();

                    for (int i = 0; i < cartProductArrayList.size(); i++) {

                        price += Double.valueOf(cartProductArrayList.get(i).getPrice());
                        old_price += Double.valueOf(cartProductArrayList.get(i).getOld_price());

                    }

                    discount = old_price - price;


                    total.setText(SmartPayApplication.CURRENCY_SYMBOL + old_price);
                    txtDiscount.setText(SmartPayApplication.CURRENCY_SYMBOL + discount);

                    stotal.setText(SmartPayApplication.CURRENCY_SYMBOL + price);
                    payable.setText(SmartPayApplication.CURRENCY_SYMBOL + price);
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
