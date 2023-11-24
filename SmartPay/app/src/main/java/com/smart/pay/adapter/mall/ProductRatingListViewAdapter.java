package com.smart.pay.adapter.mall;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.pay.R;
import com.smart.pay.models.output.ProductReviewListOutputModel;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


/**
 * Created by Sandeep Londhe on 07-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ProductRatingListViewAdapter extends RecyclerView.Adapter<ProductRatingListViewAdapter.MyViewHolder> {

    private ArrayList<ProductReviewListOutputModel.Reviews> productReviewList;
    Activity activity;

    public ProductRatingListViewAdapter(ArrayList<ProductReviewListOutputModel.Reviews> productReviewList, Activity activity) {
        this.productReviewList = productReviewList;
        this.activity = activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyTextView title;
        public MyTextView rateno;
        public MyTextView date;
        public MyTextView username;
        public MyTextView comments;


        public MyViewHolder(View view) {
            super(view);

            title = (MyTextView) view.findViewById(R.id.title);
            rateno = (MyTextView) view.findViewById(R.id.rateno);
            date = (MyTextView) view.findViewById(R.id.date);
            username = (MyTextView) view.findViewById(R.id.username);
            comments = (MyTextView) view.findViewById(R.id.comments);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_review_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ProductReviewListOutputModel.Reviews review = productReviewList.get(position);

        holder.title.setText(review.getTitle());
        holder.comments.setText(review.getComments());
        holder.rateno.setText(review.getRating() + "/5");
        holder.date.setText(review.getDateAdded());
        holder.username.setText(review.getCustomerName());

    }

    @Override
    public int getItemCount() {
        return productReviewList.size();
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
