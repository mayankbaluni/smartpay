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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.models.output.CategoryListModel;

import java.util.List;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private List<CategoryListModel.Cat> deliveryResponseList;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCatName;

        public MyViewHolder(View view) {
            super(view);
            txtCatName = (TextView) view.findViewById(R.id.txtCatName);

        }
    }


    public CategoryListAdapter(List<CategoryListModel.Cat> deliveryResponseList, Activity activity) {
        this.deliveryResponseList = deliveryResponseList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoryListModel.Cat deliveryResponse = deliveryResponseList.get(position);
        holder.txtCatName.setText(deliveryResponse.getServiceName());


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

