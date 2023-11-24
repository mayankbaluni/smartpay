package com.smart.pay.activity.wallet;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.adapter.recyclerview.HotelListItemAdapter;
import com.smart.pay.models.output.HotelListOutput;

import java.util.ArrayList;

public class HotelSearchResultActivity extends AppCompatActivity {

    RecyclerView hotelsList;

    ArrayList<HotelListOutput> hotelListOutputArrayList;
    HotelListItemAdapter hotelListItemAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hotel_search_result_activity);
        setupActionBar();

        hotelsList = (RecyclerView) findViewById(R.id.hotelsList);

        hotelListOutputArrayList = new ArrayList<HotelListOutput>();

        HotelListOutput hotelListOutput1 = new HotelListOutput();
        hotelListOutput1.setStrHotelAddress("Bandra, Mumbai");
        hotelListOutput1.setStrHotelDistance("10");
        hotelListOutput1.setStrHotelId("1");
        hotelListOutput1.setStrHotelImage(R.mipmap.hotel1);
        hotelListOutput1.setStrHotelName("Giner Mumbai Bandra");
        hotelListOutput1.setStrHotelPrice("4230");
        hotelListOutput1.setStrHotelType("Luxury, Family");


        HotelListOutput hotelListOutput2 = new HotelListOutput();
        hotelListOutput2.setStrHotelAddress("Powai");
        hotelListOutput2.setStrHotelDistance("20.19");
        hotelListOutput2.setStrHotelId("2");
        hotelListOutput2.setStrHotelImage(R.mipmap.hotel2);
        hotelListOutput2.setStrHotelName("Renaissance Mumbai");
        hotelListOutput2.setStrHotelPrice("9500");
        hotelListOutput2.setStrHotelType("Business, Luxury, Family");


        HotelListOutput hotelListOutput3 = new HotelListOutput();
        hotelListOutput3.setStrHotelAddress("Khar");
        hotelListOutput3.setStrHotelDistance("12.29");
        hotelListOutput3.setStrHotelId("3");
        hotelListOutput3.setStrHotelImage(R.mipmap.hotel3);
        hotelListOutput3.setStrHotelName("Grand Hyatt Mumbai");
        hotelListOutput3.setStrHotelPrice("13,300");
        hotelListOutput3.setStrHotelType("Business, Luxury");


        HotelListOutput hotelListOutput4 = new HotelListOutput();
        hotelListOutput4.setStrHotelAddress("Parel");
        hotelListOutput4.setStrHotelDistance("3.39");
        hotelListOutput4.setStrHotelId("4");
        hotelListOutput4.setStrHotelImage(R.mipmap.hotel4);
        hotelListOutput4.setStrHotelName("ITC Grand Central");
        hotelListOutput4.setStrHotelPrice("18,500");
        hotelListOutput4.setStrHotelType("Business, Luxury");


        HotelListOutput hotelListOutput5 = new HotelListOutput();
        hotelListOutput5.setStrHotelAddress("Thane west");
        hotelListOutput5.setStrHotelDistance("31.29");
        hotelListOutput5.setStrHotelId("5");
        hotelListOutput5.setStrHotelImage(R.mipmap.hotel5);
        hotelListOutput5.setStrHotelName("Giner Thane");
        hotelListOutput5.setStrHotelPrice("4329");
        hotelListOutput5.setStrHotelType("Family Friendly");

        HotelListOutput hotelListOutput6 = new HotelListOutput();
        hotelListOutput6.setStrHotelAddress("New Marine Lines");
        hotelListOutput6.setStrHotelDistance("3.49");
        hotelListOutput6.setStrHotelId("6");
        hotelListOutput6.setStrHotelImage(R.mipmap.hotel6);
        hotelListOutput6.setStrHotelName("West End Hotel");
        hotelListOutput6.setStrHotelPrice("5400");
        hotelListOutput6.setStrHotelType("Business, Luxury");


        hotelListOutputArrayList.add(hotelListOutput1);
        hotelListOutputArrayList.add(hotelListOutput2);
        hotelListOutputArrayList.add(hotelListOutput3);
        hotelListOutputArrayList.add(hotelListOutput4);
        hotelListOutputArrayList.add(hotelListOutput5);
        hotelListOutputArrayList.add(hotelListOutput6);


        hotelListItemAdapter = new HotelListItemAdapter(hotelListOutputArrayList, HotelSearchResultActivity.this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(HotelSearchResultActivity.this, RecyclerView.VERTICAL, false);

        hotelsList.setLayoutManager(layoutManager);

        hotelsList.setItemAnimator(new DefaultItemAnimator());
        hotelsList.setAdapter(hotelListItemAdapter);

        hotelListItemAdapter.notifyDataSetChanged();

    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("132 Hotels Found");

        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);

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
