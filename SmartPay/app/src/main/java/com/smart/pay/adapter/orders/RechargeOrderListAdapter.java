package com.smart.pay.adapter.orders;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.adapter.travel.BusListAdapter;
import com.smart.pay.models.output.RechargeOrderListOutput;
import com.smart.pay.views.MyTextView;
import com.smart.pay.views.MyTextViewBold;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RechargeOrderListAdapter extends RecyclerView.Adapter<RechargeOrderListAdapter.MyViewHolder> {


    public ArrayList<RechargeOrderListOutput> rechargeOrderListOutputArrayList;
    public AppCompatActivity appCompatActivity;

    public RechargeOrderListAdapter(ArrayList<RechargeOrderListOutput> rechargeOrderListOutputArrayList, AppCompatActivity appCompatActivity) {
        this.rechargeOrderListOutputArrayList = rechargeOrderListOutputArrayList;
        this.appCompatActivity = appCompatActivity;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyTextView orderDate, orderIsRepeat;

        public MyTextViewBold orderId, orderStatus, orderPrice;

        public CircleImageView orderImage;

        public MyViewHolder(View view) {
            super(view);
            orderId = (MyTextViewBold) view.findViewById(R.id.orderId);
            orderDate = (MyTextView) view.findViewById(R.id.orderDate);
            orderPrice = (MyTextViewBold) view.findViewById(R.id.orderPrice);
            orderStatus = (MyTextViewBold) view.findViewById(R.id.orderStatus);
            orderIsRepeat = (MyTextView) view.findViewById(R.id.orderIsRepeat);
            orderImage = (CircleImageView) view.findViewById(R.id.orderImage);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recharge_order_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RechargeOrderListOutput rechargeOrderListOutput = rechargeOrderListOutputArrayList.get(position);

        holder.orderId.setText("Order No " + rechargeOrderListOutput.getOrderId());
        holder.orderDate.setText(rechargeOrderListOutput.getOrderDate());
        Typeface typeFace_Rupee = Typeface.createFromAsset(appCompatActivity.getAssets(), "fonts/Rupee_Foradian.ttf");
        holder.orderPrice.setTypeface(typeFace_Rupee);

        holder.orderPrice.setText("`" + rechargeOrderListOutput.getOrderPrice());

        if (rechargeOrderListOutput.getOrderStatus().equalsIgnoreCase("1")) {
            holder.orderStatus.setText("Your order is successful");

        } else {
            holder.orderStatus.setText("Your order is failed");
        }

        holder.orderImage.setImageResource(rechargeOrderListOutput.getOrderImage());


    }

    @Override
    public int getItemCount() {
        return rechargeOrderListOutputArrayList.size();
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
