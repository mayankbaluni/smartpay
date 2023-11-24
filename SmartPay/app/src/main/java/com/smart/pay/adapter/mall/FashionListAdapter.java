package com.smart.pay.adapter.mall;


import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smart.pay.R;
import com.smart.pay.activity.mall.ProductsActivity;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.GetAllFashionOffersModel;
import com.smart.pay.views.MyTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Sandeep Londhe on 11-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class FashionListAdapter extends RecyclerView.Adapter<FashionListAdapter.MyViewHolder> {

    ArrayList<GetAllFashionOffersModel.Offer> serviceArrayList;
    Activity activity;

    public FashionListAdapter(ArrayList<GetAllFashionOffersModel.Offer> serviceArrayList, Activity activity) {
        this.serviceArrayList = serviceArrayList;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView offer_image;
        public MyTextView offer_name;


        public MyViewHolder(View view) {
            super(view);

            offer_image = (ImageView) view.findViewById(R.id.offer_image);
            offer_name = (MyTextView) view.findViewById(R.id.offer_name);

        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flash_deals_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GetAllFashionOffersModel.Offer service = serviceArrayList.get(position);

        holder.offer_name.setText(service.getChildCatName());

        Picasso.with(activity)
                .load(Constants.UPLOAD_IMAGE_FOLDER + service.getChildCatImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.offer_image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, ProductsActivity.class);

                i.putExtra("subcat_id", "null");
                i.putExtra("subcat_name", service.getChildCatName());
                i.putExtra("child_category_id", service.getChildCatId());
                activity.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return serviceArrayList.size();
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


