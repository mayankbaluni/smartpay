package com.smart.pay.adapter.wallet;


import android.app.Activity;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.models.output.GetWalletTransactionsOutput;
import com.smart.pay.views.MyTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sandeep Londhe on 18-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class WalletTransactionAdapter extends RecyclerView.Adapter<WalletTransactionAdapter.MyViewHolder> {


    ArrayList<GetWalletTransactionsOutput.Transaction> transactionArrayList;
    Activity activity;

    public WalletTransactionAdapter(ArrayList<GetWalletTransactionsOutput.Transaction> transactionArrayList, Activity activity) {

        this.transactionArrayList = transactionArrayList;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyTextView creditedAmount;
        public MyTextView creditedDate;
        public MyTextView trans_status;
        public MyTextView walletHeading;

        public MyViewHolder(View view) {
            super(view);

            creditedAmount = (MyTextView) view.findViewById(R.id.creditedAmount);
            creditedDate = (MyTextView) view.findViewById(R.id.creditedDate);
            trans_status = (MyTextView) view.findViewById(R.id.trans_status);
            walletHeading = (MyTextView) view.findViewById(R.id.walletHeading);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallet_transaction_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetWalletTransactionsOutput.Transaction transaction = transactionArrayList.get(position);

        double value = Double.valueOf(transaction.getAmount());

        String strAmount = String.format("%.2f", value);


        holder.creditedAmount.setText("+" + SmartPayApplication.CURRENCY_SYMBOL + strAmount);

        if (transaction.getType().equalsIgnoreCase("1")) {
            holder.trans_status.setText("Money added to wallet");

        } else if (transaction.getType().equalsIgnoreCase("2")) {
            holder.trans_status.setText("Money debited from wallet");
            // holder.trans_status.setTextColor(activity.getColor(R.color.colorRedDark));
            holder.walletHeading.setText("Wallet Debited");
            // holder.walletHeading.setTextColor(activity.getColor(R.color.colorRedDark));

            holder.creditedAmount.setTextColor(activity.getColor(R.color.colorRedDark));

        }

        try {
            String selectedDate = getDate(transaction.getAddedDate());
            String selectedMonth = getMonth(transaction.getAddedDate());
            holder.creditedDate.setText(selectedDate + " " + selectedMonth);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    private static String getMonth(String date) throws ParseException {
        Date d = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
        return monthName;
    }


    private static String getDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("dd").format(cal.getTime());
        return monthName;
    }


    @Override
    public int getItemCount() {
        return transactionArrayList.size();
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
