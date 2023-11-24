package com.smart.pay.adapter.travel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.activity.wallet.AddTrainTicketInfo;
import com.smart.pay.activity.wallet.TrainTicketDetailActivity;
import com.smart.pay.models.output.TrainSearchResult;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class CoachViewAdapter extends RecyclerView.Adapter<CoachViewAdapter.MyViewHolder> {


    Context context;
    ArrayList<TrainSearchResult.CoachType> coachTypeArrayList;

    public CoachViewAdapter(Context context, ArrayList<TrainSearchResult.CoachType> coachTypeArrayList) {
        this.context = context;
        this.coachTypeArrayList = coachTypeArrayList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyTextView coachName;
        public MyTextView coachPrice;
        public MyTextView coachAvailability;
        public MyTextView updatedTime;

        public MyViewHolder(View view) {
            super(view);

            coachName = (MyTextView) view.findViewById(R.id.coachName);
            coachPrice = (MyTextView) view.findViewById(R.id.coachPrice);
            coachAvailability = (MyTextView) view.findViewById(R.id.coachAvailability);
            updatedTime = (MyTextView) view.findViewById(R.id.updatedTime);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.train_coach_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TrainSearchResult.CoachType coachType = coachTypeArrayList.get(position);
        Typeface typeFace_Rupee = Typeface.createFromAsset(context.getAssets(), "fonts/Rupee_Foradian.ttf");
        holder.coachPrice.setTypeface(typeFace_Rupee);

        holder.coachName.setText(coachType.getCoachClass());
        holder.coachPrice.setText("`" + coachType.getSeatPrice());
        holder.coachAvailability.setText("AVAILABLE");
        holder.updatedTime.setText(coachType.getStrLastUpdatedTime());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AddTrainTicketInfo.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return coachTypeArrayList.size();
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
