package com.smart.pay.adapter.mall;

/**
 * Created by Sandeep Londhe on 25-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.smart.pay.R;
import com.smart.pay.activity.mall.NewServiceListActivity;
import com.smart.pay.models.output.ServiceListModel;
import com.smart.pay.views.MyTextView;
import com.squareup.picasso.Picasso;

public class ServicesHorizontalAdapter extends RecyclerView.Adapter<ServicesHorizontalAdapter.MyViewHolder> {

    private ArrayList<ServiceListModel.Service> deliveryResponseList;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyTextView service_name;
        public ImageView service_image;
        public LinearLayout serviceLayout;

        public MyViewHolder(View view) {
            super(view);
            service_name = (MyTextView) view.findViewById(R.id.service_name);
            service_image = (ImageView) view.findViewById(R.id.service_image);
            serviceLayout = (LinearLayout) view.findViewById(R.id.serviceLayout);

        }
    }


    public ServicesHorizontalAdapter(ArrayList<ServiceListModel.Service> deliveryResponseList, Activity activity) {
        this.deliveryResponseList = deliveryResponseList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_horizontal_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ServiceListModel.Service deliveryResponse = deliveryResponseList.get(position);
        holder.service_name.setText(deliveryResponse.getCatName());

        Picasso.with(activity)
                .load(deliveryResponse.getCatImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.service_image);

        holder.serviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, NewServiceListActivity.class);
                intent.putExtra("cat_id", deliveryResponse.getCatId());
                intent.putExtra("cat_name", deliveryResponse.getCatName());

                activity.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return deliveryResponseList.size();
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

