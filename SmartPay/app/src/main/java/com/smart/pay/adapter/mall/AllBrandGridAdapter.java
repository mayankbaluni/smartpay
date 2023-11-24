package com.smart.pay.adapter.mall;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.activity.mall.ProductsActivity;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.GetBrandsOutput;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class AllBrandGridAdapter extends BaseAdapter {

    ArrayList<GetBrandsOutput.Brands> bean;
    AppCompatActivity main;


    public AllBrandGridAdapter(AppCompatActivity activity, ArrayList<GetBrandsOutput.Brands> bean) {
        this.main = activity;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) main.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.brands_layout_item, null);


            viewHolder = new ViewHolder();

            viewHolder.imageBrand = (ImageView) convertView.findViewById(R.id.imageBrand);


            convertView.setTag(viewHolder);


        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        final GetBrandsOutput.Brands bean = (GetBrandsOutput.Brands) getItem(position);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //       Toast.makeText(main, "item clicked" + bean.getId(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(main, ProductsActivity.class);

                i.putExtra("subcat_id", "null");
                i.putExtra("subcat_name", bean.getBrandName());
                i.putExtra("brand_id", bean.getBrandId());

                main.startActivity(i);
            }
        });


        Picasso.with(main)
                .load(Constants.PRODUCT_IMAGE_PATH + bean.getBrandImage())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.imageBrand);


        return convertView;


    }

    private class ViewHolder {
        ImageView imageBrand;


    }


}
