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
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.ProductDetailActivity;
import com.smart.pay.api.Constants;
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.utils.SmallBang;
import com.smart.pay.utils.SmallBangListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GridviewAdapter extends BaseAdapter {

    ArrayList<ProductsListModel.Product> bean;
    Typeface fonts1, fonts2;
    RatingBar ratingbar;
    AppCompatActivity main;

    float oldPrice, newPrice, discountPrice;

    public GridviewAdapter(AppCompatActivity activity, ArrayList<ProductsListModel.Product> bean) {
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

        fonts1 = Typeface.createFromAsset(main.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        fonts2 = Typeface.createFromAsset(main.getAssets(),
                "fonts/MavenPro-Regular.ttf");
//


        final ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) main.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grid_productlist, null);


            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.cutprice = (TextView) convertView.findViewById(R.id.cutprice);
            viewHolder.discount = (TextView) convertView.findViewById(R.id.discount);
            viewHolder.ratingtext = (TextView) convertView.findViewById(R.id.ratingtext);
            viewHolder.fav1 = (ImageView) convertView.findViewById(R.id.fav1);
            viewHolder.fav2 = (ImageView) convertView.findViewById(R.id.fav2);
            viewHolder.ratingbar = (RatingBar) convertView.findViewById(R.id.ratingbar);


            viewHolder.title.setTypeface(fonts2);

            viewHolder.price.setTypeface(fonts1);
            viewHolder.cutprice.setTypeface(fonts1);

            viewHolder.discount.setTypeface(fonts1);

            viewHolder.ratingtext.setTypeface(fonts1);


            convertView.setTag(viewHolder);

            viewHolder.cutprice.setPaintFlags(viewHolder.cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        final ProductsListModel.Product bean = (ProductsListModel.Product) getItem(position);

//        viewHolder.image.setImageResource(bean.getImage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //       Toast.makeText(main, "item clicked" + bean.getId(), Toast.LENGTH_SHORT).show();

                Intent i2 = new Intent(main, ProductDetailActivity.class);
                i2.putExtra("product_id", bean.getId());
                main.startActivity(i2);

            }
        });


        Picasso.with(main)
                .load(Constants.PRODUCT_IMAGE_PATH + bean.getProductImage())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.image);


        if (bean.getDiscount().equalsIgnoreCase("") || bean.getDiscount() == null) {

            oldPrice = Float.valueOf(bean.getPrice());
            newPrice = Float.valueOf(bean.getPrice());


        } else if (Float.valueOf(bean.getDiscount()) > 0) {

            oldPrice = Float.valueOf(bean.getPrice());

            discountPrice = oldPrice * Float.valueOf(bean.getDiscount()) / 100;

            newPrice = oldPrice - discountPrice;

        } else {

            oldPrice = Float.valueOf(bean.getPrice());
            newPrice = Float.valueOf(bean.getPrice());

        }


        viewHolder.title.setText(bean.getProductName());
        viewHolder.price.setText(SmartPayApplication.CURRENCY_SYMBOL + newPrice);
        viewHolder.cutprice.setText(SmartPayApplication.CURRENCY_SYMBOL + oldPrice);
        viewHolder.discount.setText(bean.getDiscount() + "%");
        viewHolder.ratingtext.setText("(" + bean.getNoOfRating() + ")");


        if (bean.getRating() != null) {

            viewHolder.ratingbar.setRating(Float.valueOf(bean.getRating()));

        } else {

            viewHolder.ratingbar.setRating(0);
        }

        LayerDrawable stars = (LayerDrawable) viewHolder.ratingbar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);

        viewHolder.ratingbar.setClickable(false);

        viewHolder.mSmallBang = SmallBang.attach2Window(main);


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

        return convertView;


    }

    private class ViewHolder {
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


    }
}







