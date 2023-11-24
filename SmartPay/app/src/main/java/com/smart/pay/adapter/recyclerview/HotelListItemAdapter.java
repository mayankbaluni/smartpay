package com.smart.pay.adapter.recyclerview;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.adapter.travel.BusListAdapter;
import com.smart.pay.models.output.HotelListOutput;
import com.smart.pay.views.MyTextView;
import com.smart.pay.views.MyTextViewBold;

import java.util.ArrayList;

public class HotelListItemAdapter extends RecyclerView.Adapter<HotelListItemAdapter.MyViewHolder> {

    ArrayList<HotelListOutput> hotelListOutputArrayList;
    AppCompatActivity appCompatActivity;

    public HotelListItemAdapter(ArrayList<HotelListOutput> hotelListOutputArrayList, AppCompatActivity appCompatActivity) {
        this.hotelListOutputArrayList = hotelListOutputArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView hotelImage;
        public MyTextViewBold hotelName, hotelPrice;
        public MyTextView hotelAddress, hotelDistance, hotelType;
        public RatingBar ratingbar;

        public MyViewHolder(View view) {
            super(view);

            hotelImage = (ImageView) view.findViewById(R.id.hotelImage);
            hotelName = (MyTextViewBold) view.findViewById(R.id.hotelName);
            hotelPrice = (MyTextViewBold) view.findViewById(R.id.hotelPrice);
            hotelAddress = (MyTextView) view.findViewById(R.id.hotelAddress);
            hotelDistance = (MyTextView) view.findViewById(R.id.hotelDistance);
            hotelType = (MyTextView) view.findViewById(R.id.hotelType);
            ratingbar = (RatingBar) view.findViewById(R.id.ratingbar);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HotelListOutput hotelListOutput = hotelListOutputArrayList.get(position);

        holder.hotelName.setText(hotelListOutput.getStrHotelName());

        Typeface typeFace_Rupee = Typeface.createFromAsset(appCompatActivity.getAssets(), "fonts/Rupee_Foradian.ttf");
        holder.hotelPrice.setTypeface(typeFace_Rupee);

        holder.hotelPrice.setText("`" + hotelListOutput.getStrHotelPrice());

        holder.hotelImage.setImageResource(hotelListOutput.getStrHotelImage());
        holder.hotelDistance.setText(hotelListOutput.getStrHotelDistance() + "km from center");
        holder.hotelAddress.setText(hotelListOutput.getStrHotelAddress());
        holder.hotelType.setText(hotelListOutput.getStrHotelType());


    }

    @Override
    public int getItemCount() {
        return hotelListOutputArrayList.size();
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
