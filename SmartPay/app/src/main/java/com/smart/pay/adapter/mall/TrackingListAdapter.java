package com.smart.pay.adapter.mall;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.github.vipulasri.timelineview.TimelineView;
import com.smart.pay.R;
import com.smart.pay.models.output.TrackDeliveryOutputModel;
import com.smart.pay.views.MyTextView;

import java.util.List;

/**
 * Created by Sandeep Londhe on 15-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class TrackingListAdapter extends RecyclerView.Adapter<TrackingListAdapter.TimeLineViewHolder> {

    List<TrackDeliveryOutputModel.Trackinfo> trackinfoList;
    Activity activity;


    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        public MyTextView trackAddress;
        public MyTextView trackTime;
        public TimelineView timelineView;


        public TimeLineViewHolder(View itemView) {
            super(itemView);

            trackAddress = (MyTextView) itemView.findViewById(R.id.text_timeline_title);
            trackTime = (MyTextView) itemView.findViewById(R.id.text_timeline_date);
            timelineView = (TimelineView) itemView.findViewById(R.id.timeline);


        }

    }

    public TrackingListAdapter(List<TrackDeliveryOutputModel.Trackinfo> trackinfoList, Activity activity) {
        this.trackinfoList = trackinfoList;
        this.activity = activity;
    }


    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracking_timeline_item, parent, false);

        return new TimeLineViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        final TrackDeliveryOutputModel.Trackinfo trackinfo = trackinfoList.get(position);

        holder.timelineView.initLine(position);

        holder.trackTime.setText(trackinfo.getDate());

        holder.trackAddress.setText(trackinfo.getStatusDescription());

        holder.timelineView.setMarker(activity.getResources().getDrawable(R.drawable.ic_marker));


        holder.timelineView.setVisibility(View.VISIBLE);


    }

    @Override
    public int getItemCount() {
        return trackinfoList.size();
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
