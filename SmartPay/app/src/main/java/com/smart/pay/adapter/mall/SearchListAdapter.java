package com.smart.pay.adapter.mall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.ProductDetailActivity;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.SearchProductOutputModel;
import com.smart.pay.utils.SmallBang;
import com.smart.pay.utils.SmallBangListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Sandeep Londhe on 01-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {

    Typeface fonts1, fonts2;

    float oldPrice, newPrice, discountPrice;


    public ArrayList<SearchProductOutputModel.Product> productArrayList;
    public Activity activity;
    SearchProductOutputModel.Product product;


    public SearchListAdapter(ArrayList<SearchProductOutputModel.Product> productArrayList, Activity activity) {
        this.productArrayList = productArrayList;
        this.activity = activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView price;
        TextView cutprice;
        TextView discount;
        TextView ratingtext;
        ImageView fav1;
        ImageView fav2;
        SmallBang mSmallBang;
        RatingBar ratingbar;


        public MyViewHolder(View convertView) {
            super(convertView);


            image = (ImageView) convertView.findViewById(R.id.image);
            title = (TextView) convertView.findViewById(R.id.title);
            price = (TextView) convertView.findViewById(R.id.price);
            cutprice = (TextView) convertView.findViewById(R.id.cutprice);
            discount = (TextView) convertView.findViewById(R.id.discount);
            ratingtext = (TextView) convertView.findViewById(R.id.ratingtext);
            fav1 = (ImageView) convertView.findViewById(R.id.fav1);
            fav2 = (ImageView) convertView.findViewById(R.id.fav2);
            ratingbar = (RatingBar) convertView.findViewById(R.id.ratingbar);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        product = productArrayList.get(position);


        fonts1 = Typeface.createFromAsset(activity.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        fonts2 = Typeface.createFromAsset(activity.getAssets(),
                "fonts/MavenPro-Regular.ttf");


        viewHolder.title.setTypeface(fonts1);
        viewHolder.price.setTypeface(fonts1);
        viewHolder.cutprice.setTypeface(fonts1);
        viewHolder.discount.setTypeface(fonts1);
        viewHolder.ratingtext.setTypeface(fonts1);


        viewHolder.cutprice.setPaintFlags(viewHolder.cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        Picasso.with(activity)

                .load(Constants.PRODUCT_IMAGE_PATH + product.getProductImage())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.image);

        if (product.getDiscount().equalsIgnoreCase("") || product.getDiscount() == null) {

            oldPrice = Float.valueOf(product.getPrice());
            newPrice = Float.valueOf(product.getPrice());


        } else if (Float.valueOf(product.getDiscount()) > 0) {

            oldPrice = Float.valueOf(product.getPrice());

            discountPrice = oldPrice * Float.valueOf(product.getDiscount()) / 100;

            newPrice = oldPrice - discountPrice;


        } else {
            oldPrice = Float.valueOf(product.getPrice());
            newPrice = Float.valueOf(product.getPrice());

        }


        viewHolder.title.setText(product.getProductName());
        viewHolder.price.setText(SmartPayApplication.CURRENCY_SYMBOL + newPrice);
        viewHolder.cutprice.setText(SmartPayApplication.CURRENCY_SYMBOL + oldPrice);
        viewHolder.discount.setText(product.getDiscount());

        viewHolder.ratingtext.setText("(" + product.getNo_of_rating() + ")");


        if (product.getRating() != null) {

            viewHolder.ratingbar.setRating(Float.valueOf(product.getRating()));

        } else {

            viewHolder.ratingbar.setRating(0);
        }

        LayerDrawable stars = (LayerDrawable) viewHolder.ratingbar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);

        viewHolder.ratingbar.setClickable(false);


        viewHolder.mSmallBang = SmallBang.attach2Window(activity);


        viewHolder.fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewHolder.fav2.setVisibility(View.VISIBLE);
                viewHolder.fav1.setVisibility(View.GONE);
                like(v);

            }

            public void like(View view) {
                viewHolder.fav2.setImageResource(R.drawable.f4);
                viewHolder.mSmallBang.bang(view);
                viewHolder.mSmallBang.setmListener(new SmallBangListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }


                });
            }

        });

        viewHolder.fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewHolder.fav2.setVisibility(View.GONE);
                viewHolder.fav1.setVisibility(View.VISIBLE);


            }
        });


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(activity, ProductDetailActivity.class);
                i2.putExtra("product_id", product.getId());
                activity.startActivity(i2);

            }
        });


    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
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
