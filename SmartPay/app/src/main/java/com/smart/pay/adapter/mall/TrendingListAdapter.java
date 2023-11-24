package com.smart.pay.adapter.mall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;

import androidx.appcompat.app.AppCompatActivity;
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
import com.smart.pay.models.output.ProductsListModel;
import com.smart.pay.utils.SmallBang;
import com.smart.pay.utils.SmallBangListener;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Sandeep Londhe on 27-01-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class TrendingListAdapter extends RecyclerView.Adapter<TrendingListAdapter.SimpleViewHolder> {

    private Context context;
    private List<ProductsListModel.Product> elements;

    Typeface fonts1, fonts2;

    float oldPrice, newPrice, discountPrice;


    public TrendingListAdapter(Context context, List<ProductsListModel.Product> elements) {
        this.context = context;
        this.elements = elements;
        // Fill dummy list


    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        //      public final Button button;

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


        public SimpleViewHolder(View convertView) {
            super(convertView);


//


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
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context).inflate(R.layout.grid_productlist, parent, false);

        fonts1 = Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");


        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {
        // holder.button.setText(elements.get(position));

        final ProductsListModel.Product bean = (ProductsListModel.Product) getItem(position);

        Picasso.with(context)
                .load(Constants.PRODUCT_IMAGE_PATH + bean.getProductImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.image);


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


        holder.title.setText(bean.getProductName());
        holder.price.setText(SmartPayApplication.CURRENCY_SYMBOL + newPrice);
        holder.cutprice.setText(SmartPayApplication.CURRENCY_SYMBOL + oldPrice);
        holder.discount.setText(bean.getDiscount() + "%");
        holder.ratingtext.setText("(" + bean.getNoOfRating() + ")");

        if (bean.getRating() != null) {

            holder.ratingbar.setRating(Float.valueOf(bean.getRating()));

        } else {

            holder.ratingbar.setRating(0);
        }


        //        ***********ratingBar**********
        LayerDrawable stars = (LayerDrawable) holder.ratingbar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);


        holder.mSmallBang = SmallBang.attach2Window((AppCompatActivity) context);

        holder.title.setTypeface(fonts2);
        holder.price.setTypeface(fonts1);
        holder.cutprice.setTypeface(fonts1);
        holder.discount.setTypeface(fonts1);

        holder.ratingtext.setTypeface(fonts1);

        holder.cutprice.setPaintFlags(holder.cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        holder.fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.fav2.setVisibility(View.VISIBLE);
                holder.fav1.setVisibility(View.GONE);
                like(v);

            }

            public void like(View view) {
                holder.fav2.setImageResource(R.drawable.f4);
                holder.mSmallBang.bang(view);
                holder.mSmallBang.setmListener(new SmallBangListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }


                });
            }

        });


        holder.fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.fav2.setVisibility(View.GONE);
                holder.fav1.setVisibility(View.VISIBLE);


            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i2 = new Intent(context, ProductDetailActivity.class);
                i2.putExtra("product_id", bean.getId());
                context.startActivity(i2);
            }
        });

    }


    public Object getItem(int position) {
        return elements.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.elements.size();
    }

}
