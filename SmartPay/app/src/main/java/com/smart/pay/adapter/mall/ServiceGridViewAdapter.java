package com.smart.pay.adapter.mall;

/**
 * Created by Sandeep Londhe on 21-11-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.activity.mall.NewServiceListActivity;
import com.smart.pay.models.output.ServiceListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ServiceGridViewAdapter extends BaseAdapter {

    private Context mContext;

    ArrayList<ServiceListModel.Service> serviceListModels;

    public ServiceGridViewAdapter(Context context, ArrayList<ServiceListModel.Service> serviceListModels) {
        this.mContext = context;
        this.serviceListModels = serviceListModels;

    }

    @Override
    public int getCount() {
        return serviceListModels.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceListModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.gridview_layout_item, null);

            viewHolder = new ViewHolder();

            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.service_gridview_text);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.service_gridview_image);

            final ServiceListModel.Service bean = (ServiceListModel.Service) getItem(position);


            viewHolder.textViewTitle.setText(bean.getCatName());


            viewHolder.textViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(mContext, NewServiceListActivity.class);
                    intent.putExtra("cat_id", bean.getCatId());
                    intent.putExtra("cat_name", bean.getCatName());

                    mContext.startActivity(intent);

                    System.out.println("cat_id==" + bean.getCatId() + "\ncat_name==" + bean.getCatName());

                }
            });

            Picasso.with(mContext)
                    .load(bean.getCatImage())
                    .placeholder(R.drawable.placeholder)
                    .into(viewHolder.imageView);


            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }


    class ViewHolder {

        TextView textViewTitle;
        ImageView imageView;
    }
}