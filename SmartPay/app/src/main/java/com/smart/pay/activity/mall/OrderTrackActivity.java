package com.smart.pay.activity.mall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.adapter.mall.TrackingListAdapter;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.Constants;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.GetShipmentDetailsOutputModel;
import com.smart.pay.models.output.TrackDeliveryInputModel;
import com.smart.pay.models.output.TrackDeliveryOutputModel;
import com.smart.pay.views.MyTextView;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderTrackActivity extends AppCompatActivity {

    MainAPIInterface mainAPIInterface;
    String order_id, product_id;

    ProgressDialog newProgressDialog;

    String strTrackingNumber, strCarrierCode, strCarrierName, strOrderId;


    RecyclerView trackingDataList;
    MyTextView go_back;

    TrackingListAdapter trackingListAdapter;
    List<TrackDeliveryOutputModel.Trackinfo> trackDeliveryOutputModelList;


    LinearLayout ll_empty_tracking_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_tracking_activity);

        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        Intent intent = getIntent();

        order_id = intent.getExtras().getString("order_id");
        product_id = intent.getExtras().getString("product_id");

        System.out.println("INSIDE TRACKING==" + "ORDER_ID==" + "\n" + order_id + "\n" + "PRODUCT_ID==" + product_id);

        trackingDataList = (RecyclerView) findViewById(R.id.trackingDataList);
        ll_empty_tracking_list = (LinearLayout) findViewById(R.id.ll_empty_tracking_list);
        go_back = (MyTextView) findViewById(R.id.go_back);


        getShipmentDetailRequest();
    }


    private void getShipmentDetailRequest() {
        newProgressDialog = new ProgressDialog(OrderTrackActivity.this);

        newProgressDialog.setMessage("Getting shipment details.");

        newProgressDialog.show();

        String xAccessToken = "mykey";

        MultipartBody.Part order_id_body = MultipartBody.Part.createFormData("order_id", order_id);
        MultipartBody.Part product_id_body = MultipartBody.Part.createFormData("product_id", product_id);


        mainAPIInterface.getShipmentDetails(xAccessToken, order_id_body, product_id_body).enqueue(new Callback<GetShipmentDetailsOutputModel>() {
            @Override
            public void onResponse(Call<GetShipmentDetailsOutputModel> call, Response<GetShipmentDetailsOutputModel> response) {
                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {


                        strCarrierCode = response.body().getShipments().getCarrierCode();
                        strTrackingNumber = response.body().getShipments().getTrackingNumber();
                        strCarrierName = response.body().getShipments().getCarrierName();

                        getDeliveryTrackingDetails();

                    } else {

                        String message = response.body().getMessage();
                        Toast.makeText(OrderTrackActivity.this, message, Toast.LENGTH_LONG).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<GetShipmentDetailsOutputModel> call, Throwable t) {

                newProgressDialog.dismiss();

                Log.i("tag", t.getMessage().toString());
            }
        });

    }


    private void getDeliveryTrackingDetails() {

        newProgressDialog = new ProgressDialog(OrderTrackActivity.this);

        newProgressDialog.setMessage("Getting Tracking details.");

        newProgressDialog.show();


        String applicationJson = "application/json";
        String trackingMoreApiKey = SmartPayApplication.TRACKING_MORE_API_KEY;
        String language = "en";

        TrackDeliveryInputModel trackDeliveryInputModel = new TrackDeliveryInputModel();
        trackDeliveryInputModel.setCarrierCode(strCarrierCode);
        trackDeliveryInputModel.setDestinationCode("");
        trackDeliveryInputModel.setTrackingNumber(strTrackingNumber);
        trackDeliveryInputModel.setOrder(order_id);
        trackDeliveryInputModel.setOrderCreateTime("");


        mainAPIInterface.getRealTimeTrackingData(applicationJson, trackingMoreApiKey, trackDeliveryInputModel, Constants.GET_REAL_TIME_DELIVERY_TRACKING).enqueue(new Callback<TrackDeliveryOutputModel>() {
            @Override
            public void onResponse(Call<TrackDeliveryOutputModel> call, Response<TrackDeliveryOutputModel> response) {
                if (response.isSuccessful()) {

                    newProgressDialog.dismiss();

                    if (response.body() != null) {

                        if (response.body().getMeta().getCode() == 429) {

                            Toast.makeText(OrderTrackActivity.this, "No tracking details found.", Toast.LENGTH_LONG).show();

                            trackingDataList.setVisibility(View.GONE);
                            ll_empty_tracking_list.setVisibility(View.VISIBLE);


                        } else if (response.body().getMeta().getCode() == 200) {


                            TrackDeliveryOutputModel.Data newData = response.body().getData();

                            List<TrackDeliveryOutputModel.Item> newListItems = newData.getItems();

                            String trackingNumber = newListItems.get(0).getTrackingNumber();
                            String deliveryStatus = newListItems.get(0).getStatus();
                            TrackDeliveryOutputModel.OriginInfo newOriginInfo = newListItems.get(0).getOriginInfo();
                            String destinationInfo = newListItems.get(0).getDestinationInfo();

                            String itemRecivedAtCourier = newOriginInfo.getItemReceived();


                            if (newData.getItems().get(0).getStatus().equalsIgnoreCase("notfound")) {

                                trackingDataList.setVisibility(View.GONE);
                                ll_empty_tracking_list.setVisibility(View.VISIBLE);


                            } else {

                                trackDeliveryOutputModelList = newOriginInfo.getTrackinfo();

                                trackingDataList.setVisibility(View.VISIBLE);
                                ll_empty_tracking_list.setVisibility(View.GONE);

                                trackingListAdapter = new TrackingListAdapter(trackDeliveryOutputModelList, OrderTrackActivity.this);

                                LinearLayoutManager layoutManager
                                        = new LinearLayoutManager(OrderTrackActivity.this, RecyclerView.VERTICAL, false);

                                trackingDataList.setLayoutManager(layoutManager);

                                trackingDataList.setItemAnimator(new DefaultItemAnimator());
                                trackingDataList.setAdapter(trackingListAdapter);

                                trackingListAdapter.notifyDataSetChanged();


                            }
                        }


                    } else {

                        Toast.makeText(OrderTrackActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<TrackDeliveryOutputModel> call, Throwable t) {

                newProgressDialog.dismiss();

                Log.i("tag", t.getMessage().toString());
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

        toolbarTitle.setText("Track Order");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Track Order");

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
}
