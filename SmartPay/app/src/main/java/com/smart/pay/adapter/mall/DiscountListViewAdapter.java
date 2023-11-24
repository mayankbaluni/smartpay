package com.smart.pay.adapter.mall;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.models.output.DiscountClassModel;

import java.util.ArrayList;


public class DiscountListViewAdapter extends BaseAdapter {

    Context context;

    ArrayList<DiscountClassModel> bean1;

    TextView discounttext;
    Typeface fonts1;


    public DiscountListViewAdapter(Context context, ArrayList<DiscountClassModel> bean) {


        this.context = context;
        this.bean1 = bean;
    }


    @Override
    public int getCount() {
        return bean1.size();
    }

    @Override
    public Object getItem(int position) {
        return bean1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        fonts1 = Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

//        linear1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("1");
//
//            }
//        });

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.discountlist, null);

            viewHolder = new ViewHolder();


            viewHolder.discounttext = (TextView) convertView.findViewById(R.id.discounttext);

            viewHolder.discounttext.setTypeface(fonts1);

            convertView.setTag(viewHolder);


        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        DiscountClassModel bean = (DiscountClassModel) getItem(position);


        viewHolder.discounttext.setText(bean.getDiscounttext());


        return convertView;
    }

//    private void clickb(String s) { image.setColorFilter(context.getResources().getColor(R.color.colorTex), android.graphics.PorterDuff.Mode.MULTIPLY);
//        title.setTextColor(Color.parseColor("#8a929d"));
//
//
//        if (s.equalsIgnoreCase("1")) {
//
//            image.setColorFilter(context.getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
//            title.setTextColor(Color.parseColor("#ff5254"));
//        }
//
//
//    }


    private class ViewHolder {

        TextView discounttext;


    }
}



