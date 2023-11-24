package com.smart.pay.adapter.mall;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.smart.pay.R;
import com.smart.pay.models.output.YourAppointmentOutputModel;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;


/**
 * Created by Sandeep Londhe on 03-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class YourAppointmentListAdapter extends RecyclerView.Adapter<YourAppointmentListAdapter.MyViewHolder> {

    public ArrayList<YourAppointmentOutputModel.Booking> bookingArrayList;
    public Activity activity;


    public YourAppointmentListAdapter(ArrayList<YourAppointmentOutputModel.Booking> bookingArrayList, Activity activity) {
        this.bookingArrayList = bookingArrayList;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyTextView service_name, booking_id, booking_date, booking_time, booking_fees;
        public Button btnCall, btnEmail;


        public MyViewHolder(View containerView) {
            super(containerView);

            service_name = (MyTextView) containerView.findViewById(R.id.service_name);
            booking_id = (MyTextView) containerView.findViewById(R.id.booking_id);
            booking_date = (MyTextView) containerView.findViewById(R.id.booking_date);
            booking_time = (MyTextView) containerView.findViewById(R.id.booking_time);
            booking_fees = (MyTextView) containerView.findViewById(R.id.booking_fees);

            btnCall = (Button) containerView.findViewById(R.id.btnCall);
            btnEmail = (Button) containerView.findViewById(R.id.btnEmail);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_appointment_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final YourAppointmentOutputModel.Booking booking = bookingArrayList.get(position);

        holder.booking_id.setText(booking.getBookingId());
        holder.service_name.setText(booking.getServiceName());
        holder.booking_time.setText(booking.getBookingTime());
        holder.booking_date.setText(booking.getBookedDate());
        holder.booking_fees.setText(booking.getPrice());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + booking.getContactNo()));
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                activity.startActivity(intent);

            }
        });


        holder.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, booking.getContactEmail());
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My3DWorld Customer Inquiry");

                emailIntent.setType("message/rfc822");

                try {
                    activity.startActivity(Intent.createChooser(emailIntent,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(activity,
                            "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return bookingArrayList.size();
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
