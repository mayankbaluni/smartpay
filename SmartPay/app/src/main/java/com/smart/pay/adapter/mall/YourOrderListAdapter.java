package com.smart.pay.adapter.mall;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.OrderTrackActivity;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.YourOrderOutputModel;
import com.smart.pay.views.MyTextView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Sandeep Londhe on 01-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class YourOrderListAdapter extends RecyclerView.Adapter<YourOrderListAdapter.MyViewHolder> {


    public ArrayList<YourOrderOutputModel.Order> orderArrayList;
    Activity activity;

    public YourOrderListAdapter(ArrayList<YourOrderOutputModel.Order> orderArrayList, Activity activity) {
        this.orderArrayList = orderArrayList;
        this.activity = activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyTextView ordername;
        public MyTextView orderday;
        public MyTextView idnumber;
        public ProgressBar progressBar1;
        public ProgressBar progressBar2;
        public ProgressBar progressBar3;
        public ProgressBar progressBar4;
        public ImageView productImageView;
        public MyTextView orderPrice;
        public MyTextView trackOrder;
        public MyTextView rateOrderNow;
        public MyTextView txtCancelOrder;
        public MyTextView shipped;
        public MyTextView delivered;
        public MyTextView paymentType;
        public MyTextView qt;
        public MyTextView estimatedDate;

        public MyViewHolder(View view) {
            super(view);

            ordername = (MyTextView) view.findViewById(R.id.ordername);
            orderday = (MyTextView) view.findViewById(R.id.orderday);
            idnumber = (MyTextView) view.findViewById(R.id.idnumber);
            progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
            progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
            progressBar3 = (ProgressBar) view.findViewById(R.id.progressBar3);
            progressBar4 = (ProgressBar) view.findViewById(R.id.progressBar4);
            productImageView = (ImageView) view.findViewById(R.id.productImageView);
            orderPrice = (MyTextView) view.findViewById(R.id.orderPrice);
            estimatedDate = (MyTextView) view.findViewById(R.id.estimatedDate);

            shipped = (MyTextView) view.findViewById(R.id.shipped);
            delivered = (MyTextView) view.findViewById(R.id.delivered);

            trackOrder = (MyTextView) view.findViewById(R.id.trackOrder);
            txtCancelOrder = (MyTextView) view.findViewById(R.id.txtCancelOrder);
            rateOrderNow = (MyTextView) view.findViewById(R.id.rateOrderNow);

            paymentType = (MyTextView) view.findViewById(R.id.paymentType);
            qt = (MyTextView) view.findViewById(R.id.qt);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_order_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final YourOrderOutputModel.Order order = orderArrayList.get(position);


        holder.trackOrder.setVisibility(View.INVISIBLE);
        holder.rateOrderNow.setVisibility(View.GONE);

        holder.ordername.setText(order.getProductName());
        holder.idnumber.setText(order.getId());

        String strNewDate = order.getDateTime();

        holder.orderday.setText(strNewDate);

        holder.estimatedDate.setText(order.getData_of_delivery());

        if (order.getPaymentType().equalsIgnoreCase("")) {
            holder.paymentType.setText("via Paypal");

        } else if (order.getPaymentType().equalsIgnoreCase("2")) {

            holder.paymentType.setText("Cash on delivery");
        }

        holder.qt.setText(order.getQuantity() + " Pair");

        if (order.getStatus().equalsIgnoreCase("1")) {
            holder.progressBar1.setProgress(100);
        } else if (order.getStatus().equalsIgnoreCase("2")) {
            holder.trackOrder.setVisibility(View.VISIBLE);
            holder.progressBar2.setProgress(100);
            holder.progressBar3.setProgress(100);
            holder.shipped.setTextColor(activity.getResources().getColor(R.color.colorGreenLight));

        } else if (order.getStatus().equalsIgnoreCase("3")) {

            holder.trackOrder.setVisibility(View.GONE);
            holder.rateOrderNow.setVisibility(View.VISIBLE);
            holder.txtCancelOrder.setText("Refund");

            holder.progressBar2.setProgress(100);
            holder.progressBar3.setProgress(100);
            holder.progressBar3.setProgress(100);
            holder.progressBar4.setProgress(100);
            holder.delivered.setTextColor(activity.getResources().getColor(R.color.colorGreenLight));

        }

        Picasso.with(activity)
                .load(Constants.PRODUCT_IMAGE_PATH + order.getProductImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.productImageView);

        holder.orderPrice.setText(SmartPayApplication.CURRENCY_SYMBOL + order.getPrice());


        holder.trackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("INSIDE ADAPTER==" +
                        "ORDER_ID==" + "\n" + order.getId() + "\n" + "PRODUCT_ID==" + order.getProductId());

                Intent intent = new Intent(activity, OrderTrackActivity.class);
                intent.putExtra("order_id", order.getId());
                intent.putExtra("product_id", order.getProductId());
                activity.startActivity(intent);
            }
        });


        holder.rateOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        holder.txtCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (order.getStatus().equalsIgnoreCase("3")) {

                    //For Refund

                } else {

                    //For Cancellation
                }

            }
        });


//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent i2 = new Intent(activity, ProductDetailActivity.class);
////                i2.putExtra("product_id", order.getProductId());
////                activity.startActivity(i2);
//            }
//        });

    }


    private String getDate(long time) {
        Date date = new Date(time * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy "); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return sdf.format(date);
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
