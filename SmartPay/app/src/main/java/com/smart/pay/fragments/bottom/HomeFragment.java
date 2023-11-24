package com.smart.pay.fragments.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.smart.pay.R;
import com.smart.pay.activity.wallet.AddMoneyToWallet;
import com.smart.pay.activity.wallet.HotelSearchActivity;
import com.smart.pay.activity.wallet.MobileRechargeActivity;
import com.smart.pay.activity.wallet.MovieTicketsActivity;
import com.smart.pay.activity.wallet.PayNowActivity;
import com.smart.pay.activity.wallet.PaymentsActivity;
import com.smart.pay.activity.wallet.TravelActivity;
import com.smart.pay.adapter.mall.TopShoppingOfferAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetTopShoppingOffersModel;
import com.smart.pay.views.ChildAnimationExample;
import com.smart.pay.views.ExpandableHeightGridView;
import com.smart.pay.views.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    SliderLayout slider_home, slider_home2;

    PagerIndicator custom_indicator_home, custom_indicator_home2;

    View mView;

    HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
    HashMap<String, Integer> file_maps2 = new HashMap<String, Integer>();

    LinearLayout llRecharge, llTrainTicket, llFlightTicket, llBus, llMovieTicket, llHotels;
    LinearLayout llPayments, llPayNow, llAddMoney;

    ExpandableHeightGridView topShoppingOffersGridView;

    TopShoppingOfferAdapter topShoppingOfferAdapter;
    ProgressBar topOfferProgressBar;

    MainAPIInterface mainAPIInterface;

    ArrayList<GetTopShoppingOffersModel.Offer> offerArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.home_fragment, container, false);

        mainAPIInterface = ApiUtils.getAPIService();

        slider_home = mView.findViewById(R.id.slider_home);
        custom_indicator_home = mView.findViewById(R.id.custom_indicator_home);

        slider_home2 = mView.findViewById(R.id.slider_home2);
        custom_indicator_home2 = mView.findViewById(R.id.custom_indicator_home2);

        llRecharge = mView.findViewById(R.id.llRecharge);
        llPayments = mView.findViewById(R.id.llPayments);
        llPayNow = mView.findViewById(R.id.llPayNow);
        llAddMoney = mView.findViewById(R.id.llAddMoney);

        llTrainTicket = mView.findViewById(R.id.llTrainTicket);
        llFlightTicket = mView.findViewById(R.id.llFlightTicket);
        llBus = mView.findViewById(R.id.llBus);
        llMovieTicket = mView.findViewById(R.id.llMovieTicket);
        llHotels = mView.findViewById(R.id.llHotels);

        topShoppingOffersGridView = (ExpandableHeightGridView) mView.findViewById(R.id.topShoppingOffersGridView);
        topOfferProgressBar = (ProgressBar) mView.findViewById(R.id.topOfferProgressBar);


        file_maps.put("Sale banner 1", R.drawable.small_banner1);
        file_maps.put("Sale banner 2", R.drawable.small_banner1);
        file_maps.put("Sale banner 3", R.drawable.small_banner1);

        file_maps2.put("Sale banner 1", R.mipmap.bb1);
        file_maps2.put("Sale banner 2", R.mipmap.bb2);
        file_maps2.put("Sale banner 3", R.mipmap.bb3);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            slider_home.addSlider(textSliderView);

            textSliderView.bundle(new Bundle());

            slider_home.addSlider(textSliderView);


        }

        slider_home.setPresetTransformer(SliderLayout.Transformer.Default);
        slider_home.setCustomIndicator(custom_indicator_home);
//                            mImageSlider.setCurrentPosition(-1);
        //     mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider_home.setPadding(0, 0, 0, 5);
        slider_home.setCustomAnimation(new ChildAnimationExample());


        //for slider 2


        for (String name : file_maps2.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps2.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            slider_home2.addSlider(textSliderView);

            textSliderView.bundle(new Bundle());

            slider_home2.addSlider(textSliderView);


        }

        slider_home2.setPresetTransformer(SliderLayout.Transformer.Default);
        slider_home2.setCustomIndicator(custom_indicator_home2);
//                            mImageSlider.setCurrentPosition(-1);
        //     mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider_home2.setPadding(0, 0, 0, 5);
        slider_home2.setCustomAnimation(new ChildAnimationExample());


        llRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MobileRechargeActivity.class);
                startActivity(intent);

            }
        });


        llPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PaymentsActivity.class);
                startActivity(intent);

            }
        });

        llTrainTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TravelActivity.class);
                intent.putExtra("travel", "train");
                startActivity(intent);

            }
        });

        llFlightTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TravelActivity.class);
                intent.putExtra("travel", "flight");
                startActivity(intent);

            }
        });

        llBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TravelActivity.class);
                intent.putExtra("travel", "bus");
                startActivity(intent);

            }
        });


        llMovieTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MovieTicketsActivity.class);
                startActivity(intent);

            }
        });

        llHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), HotelSearchActivity.class);
                startActivity(intent);

            }
        });

        llPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PayNowActivity.class);
                startActivity(intent);

            }
        });

        llAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AddMoneyToWallet.class);
                startActivity(intent);

            }
        });

        getTopShoppingOffersRequest();

        return mView;
    }

    private void getTopShoppingOffersRequest() {
        String xAccessToken = "mykey";

        topOfferProgressBar.setVisibility(View.VISIBLE);
        topShoppingOffersGridView.setVisibility(View.GONE);

        mainAPIInterface.getTopShoppingOffers(xAccessToken).enqueue(new Callback<GetTopShoppingOffersModel>() {
            @Override
            public void onResponse(Call<GetTopShoppingOffersModel> call, Response<GetTopShoppingOffersModel> response) {


                if (response.isSuccessful()) {

                    topOfferProgressBar.setVisibility(View.GONE);
                    topShoppingOffersGridView.setVisibility(View.VISIBLE);

                    offerArrayList = response.body().getOffers();

                    topShoppingOfferAdapter = new TopShoppingOfferAdapter((AppCompatActivity) getActivity(), offerArrayList);

                    topShoppingOffersGridView.setExpanded(true);

                    topShoppingOffersGridView.setAdapter(topShoppingOfferAdapter);

                }
            }

            @Override
            public void onFailure(Call<GetTopShoppingOffersModel> call, Throwable t) {
                topOfferProgressBar.setVisibility(View.GONE);
                topShoppingOffersGridView.setVisibility(View.VISIBLE);
                Log.i("tag", t.getMessage().toString());
            }
        });


    }


}
