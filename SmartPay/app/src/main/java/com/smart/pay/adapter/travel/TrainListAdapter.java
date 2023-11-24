package com.smart.pay.adapter.travel;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.widget.HorizontalGridView;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.models.output.TrainSearchResult;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.MyViewHolder> {

    ArrayList<TrainSearchResult> trainSearchResultArrayList;
    AppCompatActivity appCompatActivity;

    public TrainListAdapter(ArrayList<TrainSearchResult> trainSearchResultArrayList, AppCompatActivity appCompatActivity) {
        this.trainSearchResultArrayList = trainSearchResultArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTrainName;
        public MyTextView arrivalTime;
        public MyTextView arrivalStation;
        public MyTextView departureTime;
        public MyTextView departureStation;
        public MyTextView totalDuration;
        public HorizontalGridView coachView;


        public MyViewHolder(View view) {
            super(view);

            txtTrainName = (TextView) view.findViewById(R.id.txtTrainName);
            arrivalTime = (MyTextView) view.findViewById(R.id.arrivalTime);
            arrivalStation = (MyTextView) view.findViewById(R.id.arrivalStation);
            departureTime = (MyTextView) view.findViewById(R.id.departureTime);
            departureStation = (MyTextView) view.findViewById(R.id.departureStation);
            totalDuration = (MyTextView) view.findViewById(R.id.totalDuration);

            coachView = (HorizontalGridView) view.findViewById(R.id.coachView);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.train_search_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TrainSearchResult trainSearchResult = trainSearchResultArrayList.get(position);


        holder.txtTrainName.setText(trainSearchResult.getTrainName());
        holder.arrivalStation.setText(trainSearchResult.getTrainArrivalStation());
        holder.arrivalTime.setText(trainSearchResult.getTrainArrivalTime());
        holder.departureStation.setText(trainSearchResult.getTrainDepartureStation());
        holder.departureTime.setText(trainSearchResult.getTrainDepartureTime());
        holder.totalDuration.setText(trainSearchResult.getTrainTotalTravelTime());

        CoachViewAdapter coachViewAdapter = new CoachViewAdapter(appCompatActivity, trainSearchResult.getCoachTypeArrayList());

        holder.coachView.setAdapter(coachViewAdapter);
        coachViewAdapter.notifyDataSetChanged();



    }

    @Override
    public int getItemCount() {
        return trainSearchResultArrayList.size();
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
