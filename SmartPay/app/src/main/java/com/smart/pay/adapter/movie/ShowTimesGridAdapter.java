package com.smart.pay.adapter.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.pay.R;
import com.smart.pay.activity.wallet.MovieTicketSeatViewActivity;
import com.smart.pay.models.output.TheaterListOutput;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class ShowTimesGridAdapter extends BaseAdapter {

    ArrayList<TheaterListOutput.ShowTime> showTimeArrayList;
    AppCompatActivity appCompatActivity;

    public ShowTimesGridAdapter(ArrayList<TheaterListOutput.ShowTime> showTimeArrayList, AppCompatActivity appCompatActivity) {
        this.showTimeArrayList = showTimeArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public int getCount() {
        return showTimeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return showTimeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) appCompatActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.show_time_grid_item, null);

            viewHolder = new ViewHolder();

            viewHolder.showTime = (MyTextView) convertView.findViewById(R.id.showTime);

            convertView.setTag(viewHolder);


        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        final TheaterListOutput.ShowTime showTime = showTimeArrayList.get(position);

        viewHolder.showTime.setText(showTime.getShowTime());

        viewHolder.showTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(appCompatActivity, MovieTicketSeatViewActivity.class);
                appCompatActivity.startActivity(newIntent);

            }
        });

        return convertView;

    }

    private class ViewHolder {

        MyTextView showTime;
    }
}
