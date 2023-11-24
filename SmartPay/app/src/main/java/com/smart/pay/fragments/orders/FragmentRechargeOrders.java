package com.smart.pay.fragments.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.adapter.orders.RechargeOrderListAdapter;
import com.smart.pay.models.output.RechargeOrderListOutput;

import java.util.ArrayList;

public class FragmentRechargeOrders extends Fragment {

    ArrayList<RechargeOrderListOutput> rechargeOrderListOutputArrayList;
    RechargeOrderListAdapter rechargeOrderListAdapter;


    View mView;

    RecyclerView rechargeOrdersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.recharge_orders_fragment, container, false);

        rechargeOrdersList = (RecyclerView) mView.findViewById(R.id.rechargeOrdersList);

        rechargeOrderListOutputArrayList = new ArrayList<RechargeOrderListOutput>();

        RechargeOrderListOutput rechargeOrderListOutput1 = new RechargeOrderListOutput();
        rechargeOrderListOutput1.setOrderId("11229989");
        rechargeOrderListOutput1.setOrderDate("6:20 PM 18 Sep 2019");
        rechargeOrderListOutput1.setOrderPrice("500");
        rechargeOrderListOutput1.setOrderStatus("1");
        rechargeOrderListOutput1.setOrderImage(R.mipmap.ic_order_list1);


        RechargeOrderListOutput rechargeOrderListOutput2 = new RechargeOrderListOutput();
        rechargeOrderListOutput2.setOrderId("11229990");
        rechargeOrderListOutput2.setOrderDate("4:20 AM 19 Sep 2019");
        rechargeOrderListOutput2.setOrderPrice("100");
        rechargeOrderListOutput2.setOrderStatus("1");
        rechargeOrderListOutput2.setOrderImage(R.mipmap.ic_order_list2);


        RechargeOrderListOutput rechargeOrderListOutput3 = new RechargeOrderListOutput();
        rechargeOrderListOutput3.setOrderId("11229991");
        rechargeOrderListOutput3.setOrderDate("4:00 PM 19 Sep 2019");
        rechargeOrderListOutput3.setOrderPrice("200");
        rechargeOrderListOutput3.setOrderStatus("0");
        rechargeOrderListOutput3.setOrderImage(R.mipmap.ic_order_list3);

        RechargeOrderListOutput rechargeOrderListOutput4 = new RechargeOrderListOutput();
        rechargeOrderListOutput4.setOrderId("11229992");
        rechargeOrderListOutput4.setOrderDate("4:20 AM 19 Sep 2019");
        rechargeOrderListOutput4.setOrderPrice("100");
        rechargeOrderListOutput4.setOrderStatus("1");
        rechargeOrderListOutput4.setOrderImage(R.mipmap.ic_order_list1);

        RechargeOrderListOutput rechargeOrderListOutput5 = new RechargeOrderListOutput();
        rechargeOrderListOutput5.setOrderId("11229995");
        rechargeOrderListOutput5.setOrderDate("8:20 AM 29 Oct 2019");
        rechargeOrderListOutput5.setOrderPrice("10");
        rechargeOrderListOutput5.setOrderStatus("0");
        rechargeOrderListOutput5.setOrderImage(R.mipmap.ic_order_list2);


        RechargeOrderListOutput rechargeOrderListOutput6 = new RechargeOrderListOutput();
        rechargeOrderListOutput6.setOrderId("11229999");
        rechargeOrderListOutput6.setOrderDate("8:20 AM 39 Oct 2019");
        rechargeOrderListOutput6.setOrderPrice("1000");
        rechargeOrderListOutput6.setOrderStatus("1000");
        rechargeOrderListOutput6.setOrderImage(R.mipmap.ic_order_list3);


        rechargeOrderListOutputArrayList.add(rechargeOrderListOutput1);
        rechargeOrderListOutputArrayList.add(rechargeOrderListOutput2);
        rechargeOrderListOutputArrayList.add(rechargeOrderListOutput3);
        rechargeOrderListOutputArrayList.add(rechargeOrderListOutput4);
        rechargeOrderListOutputArrayList.add(rechargeOrderListOutput5);
        rechargeOrderListOutputArrayList.add(rechargeOrderListOutput6);


        rechargeOrderListAdapter = new RechargeOrderListAdapter(rechargeOrderListOutputArrayList, (AppCompatActivity) getActivity());


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        rechargeOrdersList.setLayoutManager(layoutManager);

        rechargeOrdersList.setItemAnimator(new DefaultItemAnimator());
        rechargeOrdersList.setAdapter(rechargeOrderListAdapter);

        rechargeOrderListAdapter.notifyDataSetChanged();

        return mView;
    }
}
