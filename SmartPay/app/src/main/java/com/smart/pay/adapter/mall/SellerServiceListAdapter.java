package com.smart.pay.adapter.mall;

/**
 * Created by Sandeep Londhe on 12-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.BookAppointmentActivity;
import com.smart.pay.models.output.NewServiceListModel;

import java.util.List;


public class SellerServiceListAdapter extends RecyclerView.Adapter<SellerServiceListAdapter.MyViewHolder> {

    private List<NewServiceListModel.Service> productList;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtServiceName;
        public TextView txtServicePrice;
        public TextView btnBookService;


        public MyViewHolder(View view) {
            super(view);

            txtServiceName = (TextView) view.findViewById(R.id.txtServiceName);
            txtServicePrice = (TextView) view.findViewById(R.id.txtServicePrice);
            btnBookService = (TextView) view.findViewById(R.id.btnBookService);


        }
    }


    public SellerServiceListAdapter(List<NewServiceListModel.Service> productList, Activity activity) {
        this.productList = productList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seller_service_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final NewServiceListModel.Service product = productList.get(position);
//        holder.txtCatName.setText(deliveryResponse.getServiceName());

        holder.txtServiceName.setText(product.getServiceName());
        holder.txtServicePrice.setText(SmartPayApplication.CURRENCY_SYMBOL + product.getPrice());


        holder.btnBookService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(activity, BookAppointmentActivity.class);
                i.putExtra("serviceId", product.getServiceId());
                i.putExtra("sellerId", product.getSellerId());
                i.putExtra("price", product.getPrice());
                i.putExtra("serviceName", product.getServiceName());

                activity.startActivity(i);
                activity.finish();


            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
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

