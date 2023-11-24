package com.smart.pay.activity.mall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.views.MyTextView;

import java.util.Date;


/**
 * Created by Sandeep Londhe on 08-02-2019.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ThankYouAppointmentActivity extends AppCompatActivity {

    MyTextView thanksServiceName;
    MyTextView appointmentDate;
    MyTextView appointmentTime;
    MyTextView totalFees;
    MyTextView txtYouAppointmentsSection;

    String serviceName, strAppointmentDate, strAppointmentTime, fees;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thankyou_appointment);
        setupActionBar();

        thanksServiceName = (MyTextView) findViewById(R.id.thanksServiceName);
        appointmentDate = (MyTextView) findViewById(R.id.appointmentDate);
        appointmentTime = (MyTextView) findViewById(R.id.appointmentTime);
        totalFees = (MyTextView) findViewById(R.id.totalFees);
        txtYouAppointmentsSection = (MyTextView) findViewById(R.id.txtYouAppointmentsSection);


        Intent value = getIntent();
        serviceName = value.getExtras().getString("serviceName");
        strAppointmentDate = value.getExtras().getString("appointmentDate");
        strAppointmentTime = value.getExtras().getString("appointmentTime");
        fees = value.getExtras().getString("fees");

        thanksServiceName.setText(serviceName);
        appointmentTime.setText("Time:- " + strAppointmentTime);
        totalFees.setText(SmartPayApplication.CURRENCY_SYMBOL + fees);

        // timestamp to Date
        long timestamp = Long.valueOf(strAppointmentDate); //Example -> in ms
        Date day = new Date(timestamp);

        appointmentDate.setText("Day:- " + day);

        txtYouAppointmentsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ThankYouAppointmentActivity.this, YourAppointmentsActivity.class);
                startActivity(i);
            }
        });


    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);

        ImageView appLogo = toolbar.findViewById(R.id.appLogo);
        appLogo.setVisibility(View.GONE);

        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Thank you");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Thank you");

        toolbar.setTitleTextColor(Color.WHITE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // action bar menu behaviour
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
