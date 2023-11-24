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
import com.smart.pay.models.output.AllChildCategoryOutput;
import com.smart.pay.views.MyTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AllChildCatAdapter extends BaseAdapter {

    ArrayList<AllChildCategoryOutput.ChildCategory> bean;
    AppCompatActivity main;


    public AllChildCatAdapter(AppCompatActivity activity, ArrayList<AllChildCategoryOutput.ChildCategory> bean) {
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
            convertView = layoutInflater.inflate(R.layout.top_shopping_offers_grid_item, null);


            viewHolder = new ViewHolder();

            viewHolder.offer_image = (ImageView) convertView.findViewById(R.id.offer_image);
            viewHolder.offer_title = (MyTextView) convertView.findViewById(R.id.offer_title);


            convertView.setTag(viewHolder);


        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        final AllChildCategoryOutput.ChildCategory bean = (AllChildCategoryOutput.ChildCategory) getItem(position);

//        viewHolder.image.setImageResource(bean.getImage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //       Toast.makeText(main, "item clicked" + bean.getId(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(main, ProductsActivity.class);

                i.putExtra("subcat_id", "null");
                i.putExtra("subcat_name", bean.getChildCatName());
                i.putExtra("child_category_id", bean.getChildCatId());
                main.startActivity(i);
            }
        });


        Picasso.with(main)
                .load(Constants.UPLOAD_IMAGE_FOLDER + bean.getChildCatImage())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.offer_image);


        viewHolder.offer_title.setText(bean.getChildCatName());


        return convertView;


    }

    private class ViewHolder {
        ImageView offer_image;
        MyTextView offer_title;


    }


}

