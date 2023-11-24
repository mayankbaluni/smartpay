package com.smart.pay.adapter.mall;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.activity.mall.ProductsActivity;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.GetBrandsOutput;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrandRecyclerAdapter extends RecyclerView.Adapter<BrandRecyclerAdapter.MyViewHolder> {


    ArrayList<GetBrandsOutput.Brands> brandsArrayList;
    AppCompatActivity appCompatActivity;

    public BrandRecyclerAdapter(ArrayList<GetBrandsOutput.Brands> brandsArrayList, AppCompatActivity appCompatActivity) {
        this.brandsArrayList = brandsArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageProduct, imageBrand;

        public MyViewHolder(View view) {
            super(view);

            imageProduct = (ImageView) view.findViewById(R.id.imageProduct);
            imageBrand = (ImageView) view.findViewById(R.id.imageBrand);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brands_layout_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final GetBrandsOutput.Brands brands = brandsArrayList.get(position);

        Picasso.with(appCompatActivity)
                .load(Constants.PRODUCT_IMAGE_PATH + brands.getBrandImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageBrand);


        Picasso.with(appCompatActivity)
                .load(Constants.PRODUCT_IMAGE_PATH + brands.getProduct_image())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageProduct);


        holder.imageBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(appCompatActivity, ProductsActivity.class);

                i.putExtra("subcat_id", "null");
                i.putExtra("subcat_name", brands.getBrandName());
                i.putExtra("brand_id", brands.getBrandId());

                appCompatActivity.startActivity(i);


            }
        });

    }


    @Override
    public int getItemCount() {
        return brandsArrayList.size();
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
