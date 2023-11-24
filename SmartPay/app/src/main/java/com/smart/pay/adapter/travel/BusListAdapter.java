package com.smart.pay.adapter.travel;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.activity.wallet.BookBusTicketActivity;
import com.smart.pay.models.output.BusSearchResult;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class BusListAdapter extends RecyclerView.Adapter<BusListAdapter.MyViewHolder> {

    ArrayList<BusSearchResult> busSearchResultArrayList;
    AppCompatActivity appCompatActivity;

    public BusListAdapter(ArrayList<BusSearchResult> busSearchResultArrayList, AppCompatActivity appCompatActivity) {
        this.busSearchResultArrayList = busSearchResultArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyTextView travelsName;
        public MyTextView busPrice;
        public MyTextView busType;
        public MyTextView busArrivalTime;
        public MyTextView busSeats;
        public MyTextView busTotalDuration;
        public MyTextView busReachTime;
        public MyTextView busRatings;
        public MyTextView busTotalRatings;

        public MyViewHolder(View view) {
            super(view);

            travelsName = (MyTextView) view.findViewById(R.id.travelsName);
            busPrice = (MyTextView) view.findViewById(R.id.busPrice);
            busType = (MyTextView) view.findViewById(R.id.busType);
            busArrivalTime = (MyTextView) view.findViewById(R.id.busArrivalTime);
            busSeats = (MyTextView) view.findViewById(R.id.busSeats);
            busTotalDuration = (MyTextView) view.findViewById(R.id.busTotalDuration);
            busReachTime = (MyTextView) view.findViewById(R.id.busReachTime);
            busRatings = (MyTextView) view.findViewById(R.id.busRatings);
            busTotalRatings = (MyTextView) view.findViewById(R.id.busTotalRatings);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bus_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BusSearchResult busSearchResult = busSearchResultArrayList.get(position);

        holder.travelsName.setText(busSearchResult.getTravelsName());

        Typeface typeFace_Rupee = Typeface.createFromAsset(appCompatActivity.getAssets(), "fonts/Rupee_Foradian.ttf");
        holder.busPrice.setTypeface(typeFace_Rupee);

        holder.busPrice.setText("`" + busSearchResult.getBusPrice());
        holder.busType.setText(busSearchResult.getBusType());
        holder.busArrivalTime.setText(busSearchResult.getBusArrivalTime());
        holder.busSeats.setText(busSearchResult.getBusSeats());
        holder.busTotalDuration.setText(busSearchResult.getBusTotalDuration());
        holder.busReachTime.setText(busSearchResult.getBusReachTime());
        holder.busRatings.setText(busSearchResult.getBusRatings());
        holder.busTotalRatings.setText(busSearchResult.getBusTotalRatings() + " Ratings");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(appCompatActivity, BookBusTicketActivity.class);
                appCompatActivity.startActivity(newIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return busSearchResultArrayList.size();
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
