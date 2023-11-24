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
import com.smart.pay.adapter.travel.BusListAdapter;
import com.smart.pay.models.output.BusSearchResult;

import java.util.ArrayList;

public class BusSearchResultActivity extends AppCompatActivity {

    RecyclerView busesList;
    ArrayList<BusSearchResult> busSearchResultArrayList;
    BusListAdapter busListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bus_search_result_activity);
        setupActionBar();

        busesList = (RecyclerView) findViewById(R.id.busesList);

        busSearchResultArrayList = new ArrayList<BusSearchResult>();

        BusSearchResult busSearchResult1 = new BusSearchResult();

        busSearchResult1.setTravelsName("PVS Travels (Mumbai)");
        busSearchResult1.setBusArrivalTime("1:55 PM");
        busSearchResult1.setBusType("AC Sleeper 2+1 Single Axle");
        busSearchResult1.setBusPrice("840");
        busSearchResult1.setBusSeats("20 Seats");
        busSearchResult1.setBusTotalDuration("12h 5m");
        busSearchResult1.setBusReachTime("2:00 AM");
        busSearchResult1.setBusRatings("5.0");
        busSearchResult1.setBusTotalRatings("10");


        BusSearchResult busSearchResult2 = new BusSearchResult();

        busSearchResult2.setTravelsName("Geeta Bus Travels");
        busSearchResult2.setBusArrivalTime("3:00 PM");
        busSearchResult2.setBusType("Non AC Sleeper 2+1 Single Axle");
        busSearchResult2.setBusPrice("650");
        busSearchResult2.setBusSeats("15 Seats");
        busSearchResult2.setBusTotalDuration("13h 10m");
        busSearchResult2.setBusReachTime("4:45 AM");
        busSearchResult2.setBusRatings("3.8");
        busSearchResult2.setBusTotalRatings("9");

        BusSearchResult busSearchResult3 = new BusSearchResult();

        busSearchResult3.setTravelsName("Shiv Baba Travel");
        busSearchResult3.setBusArrivalTime("6:30 PM");
        busSearchResult3.setBusType("AC Sleeper 2+1");
        busSearchResult3.setBusPrice("500");
        busSearchResult3.setBusSeats("12 Seats");
        busSearchResult3.setBusTotalDuration("10h 5m");
        busSearchResult3.setBusReachTime("5:00 AM");
        busSearchResult3.setBusRatings("3.5");
        busSearchResult3.setBusTotalRatings("12");


        BusSearchResult busSearchResult4 = new BusSearchResult();

        busSearchResult4.setTravelsName("VRL Travel");
        busSearchResult4.setBusArrivalTime("5:15 PM");
        busSearchResult4.setBusType("AC Sleeper");
        busSearchResult4.setBusPrice("950");
        busSearchResult4.setBusSeats("20 Seats");
        busSearchResult4.setBusTotalDuration("10h 5m");
        busSearchResult4.setBusReachTime("4:00 AM");
        busSearchResult4.setBusRatings("3.3");
        busSearchResult4.setBusTotalRatings("15");

        BusSearchResult busSearchResult5 = new BusSearchResult();

        busSearchResult5.setTravelsName("Dolhin Travel House");
        busSearchResult5.setBusArrivalTime("7:35 PM");
        busSearchResult5.setBusType("AC Sleeper 2+1 Dolhin");
        busSearchResult5.setBusPrice("590");
        busSearchResult5.setBusSeats("18 Seats");
        busSearchResult5.setBusTotalDuration("11h 30m");
        busSearchResult5.setBusReachTime("7:00 AM");
        busSearchResult5.setBusRatings("3.7");
        busSearchResult5.setBusTotalRatings("3");


        BusSearchResult busSearchResult6 = new BusSearchResult();

        busSearchResult6.setTravelsName("SRS Travel");
        busSearchResult6.setBusArrivalTime("4:30 PM");
        busSearchResult6.setBusType("Non AC Sleeper Non Video");
        busSearchResult6.setBusPrice("750");
        busSearchResult6.setBusSeats("15 Seats");
        busSearchResult6.setBusTotalDuration("12h 0m");
        busSearchResult6.setBusReachTime("4:30 AM");
        busSearchResult6.setBusRatings("5.0");
        busSearchResult6.setBusTotalRatings("5");


        BusSearchResult busSearchResult7 = new BusSearchResult();

        busSearchResult7.setTravelsName("Jagdamba Shubham Travels");
        busSearchResult7.setBusArrivalTime("7:35 PM");
        busSearchResult7.setBusType("AC Sleeper 2+1 Bharat Benz");
        busSearchResult7.setBusPrice("550");
        busSearchResult7.setBusSeats("20 Seats");
        busSearchResult7.setBusTotalDuration("10h 5m");
        busSearchResult7.setBusReachTime("6:10 AM");
        busSearchResult7.setBusRatings("3.8");
        busSearchResult7.setBusTotalRatings("30");


        busSearchResultArrayList.add(busSearchResult1);
        busSearchResultArrayList.add(busSearchResult2);
        busSearchResultArrayList.add(busSearchResult3);
        busSearchResultArrayList.add(busSearchResult4);
        busSearchResultArrayList.add(busSearchResult5);
        busSearchResultArrayList.add(busSearchResult6);
        busSearchResultArrayList.add(busSearchResult7);


        busListAdapter = new BusListAdapter(busSearchResultArrayList, BusSearchResultActivity.this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(BusSearchResultActivity.this, RecyclerView.VERTICAL, false);

        busesList.setLayoutManager(layoutManager);

        busesList.setItemAnimator(new DefaultItemAnimator());
        busesList.setAdapter(busListAdapter);

        busListAdapter.notifyDataSetChanged();


    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Mumbai --> Pune");

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
