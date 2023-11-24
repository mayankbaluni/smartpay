package com.smart.pay.adapter.wallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.models.output.InboxOutputModel;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class InboxListAdapter extends RecyclerView.Adapter<InboxListAdapter.MyViewHolder> {

    public ArrayList<InboxOutputModel> inboxOutputModelArrayList;
    AppCompatActivity appCompatActivity;

    public InboxListAdapter(ArrayList<InboxOutputModel> inboxOutputModelArrayList, AppCompatActivity appCompatActivity) {
        this.inboxOutputModelArrayList = inboxOutputModelArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyTextView inbox_title;
        public ImageView inbox_image;
        public TextView txt_article_desc;
        public TextView message_time;


        public MyViewHolder(View view) {
            super(view);

            inbox_title = (MyTextView) view.findViewById(R.id.inbox_title);
            inbox_image = (ImageView) view.findViewById(R.id.inbox_image);
            txt_article_desc = (TextView) view.findViewById(R.id.txt_article_desc);
            message_time = (TextView) view.findViewById(R.id.message_time);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inbox_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        InboxOutputModel inboxOutputModel = inboxOutputModelArrayList.get(position);

        holder.inbox_title.setText(inboxOutputModel.getTitle());
        holder.message_time.setText(inboxOutputModel.getAddedDate());
        holder.txt_article_desc.setText(inboxOutputModel.getDescription());

        holder.inbox_image.setImageResource(inboxOutputModel.getImage());


    }

    @Override
    public int getItemCount() {
        return inboxOutputModelArrayList.size();
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
