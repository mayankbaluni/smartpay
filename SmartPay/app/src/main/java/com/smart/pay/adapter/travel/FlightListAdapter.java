package com.smart.pay.adapter.travel;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.models.output.FlightSearchResult;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.MyViewHolder> {

    ArrayList<FlightSearchResult> flightSearchResultArrayList;
    AppCompatActivity appCompatActivity;


    public FlightListAdapter(ArrayList<FlightSearchResult> flightSearchResultArrayList, AppCompatActivity appCompatActivity) {

        this.flightSearchResultArrayList = flightSearchResultArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyTextView airlineName;
        public MyTextView flightArrivalTime;
        public MyTextView flightArrivalStation;
        public MyTextView flightTotalDuration;
        public MyTextView flightDepartureTime;
        public MyTextView flightDepartureStation;
        public MyTextView flightPrice;

        public ImageView imgAirline;

        public MyViewHolder(View view) {
            super(view);

            airlineName = (MyTextView) view.findViewById(R.id.airlineName);
            flightArrivalTime = (MyTextView) view.findViewById(R.id.flightArrivalTime);
            flightArrivalStation = (MyTextView) view.findViewById(R.id.flightArrivalStation);
            flightTotalDuration = (MyTextView) view.findViewById(R.id.flightTotalDuration);
            flightDepartureTime = (MyTextView) view.findViewById(R.id.flightDepartureTime);
            flightDepartureStation = (MyTextView) view.findViewById(R.id.flightDepartureStation);
            flightPrice = (MyTextView) view.findViewById(R.id.flightPrice);

            imgAirline = (ImageView) view.findViewById(R.id.imgAirline);

        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FlightSearchResult flightSearchResult = flightSearchResultArrayList.get(position);

        Typeface typeFace_Rupee = Typeface.createFromAsset(appCompatActivity.getAssets(), "fonts/Rupee_Foradian.ttf");
        holder.flightPrice.setTypeface(typeFace_Rupee);

        holder.airlineName.setText(flightSearchResult.getAirlineName());
        holder.flightPrice.setText("`" + flightSearchResult.getFlightPrice());
        holder.flightArrivalTime.setText(flightSearchResult.getFlightArrivalTime());
        holder.flightArrivalStation.setText(flightSearchResult.getFlightArrivalStation());
        holder.flightTotalDuration.setText(flightSearchResult.getFlightTotalDuration());
        holder.flightDepartureTime.setText(flightSearchResult.getFlightDepartureTime());
        holder.flightDepartureStation.setText(flightSearchResult.getFlightDepartureStation());

        if (flightSearchResult.getStrAirline().equalsIgnoreCase("1")) {

            holder.imgAirline.setImageResource(R.drawable.ic_spicejet);
        } else if (flightSearchResult.getStrAirline().equalsIgnoreCase("2")) {
            holder.imgAirline.setImageResource(R.drawable.ic_airindia);
        } else if (flightSearchResult.getStrAirline().equalsIgnoreCase("3")) {
            holder.imgAirline.setImageResource(R.drawable.ic_indigo);

        } else if (flightSearchResult.getStrAirline().equalsIgnoreCase("4")) {
            holder.imgAirline.setImageResource(R.drawable.ic_vistara);

        }

    }


    @Override
    public int getItemCount() {
        return flightSearchResultArrayList.size();
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
