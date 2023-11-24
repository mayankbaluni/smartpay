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
import com.smart.pay.adapter.travel.FlightListAdapter;
import com.smart.pay.models.output.FlightSearchResult;

import java.util.ArrayList;

public class FlightSearchResultActivity extends AppCompatActivity {


    RecyclerView flightsList;

    FlightListAdapter flightListAdapter;

    ArrayList<FlightSearchResult> flightSearchResultArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flight_search_result_activity);
        setupActionBar();

        flightsList = (RecyclerView) findViewById(R.id.flightsList);

        flightSearchResultArrayList = new ArrayList<FlightSearchResult>();

        FlightSearchResult flightSearchResult1 = new FlightSearchResult();

        flightSearchResult1.setAirlineName("SpiceJet SG- 9154");
        flightSearchResult1.setFlightArrivalStation("BOM");
        flightSearchResult1.setFlightArrivalTime("21:00");
        flightSearchResult1.setFlightDepartureStation("DEL");
        flightSearchResult1.setFlightDepartureTime("23:10");
        flightSearchResult1.setFlightTotalDuration("2h 5m");
        flightSearchResult1.setFlightPrice("2693");
        flightSearchResult1.setStrAirline("1");


        FlightSearchResult flightSearchResult2 = new FlightSearchResult();

        flightSearchResult2.setAirlineName("Vistara UK- 988");
        flightSearchResult2.setFlightArrivalStation("BOM");
        flightSearchResult2.setFlightArrivalTime("21:00");
        flightSearchResult2.setFlightDepartureStation("DEL");
        flightSearchResult2.setFlightDepartureTime("23:10");
        flightSearchResult2.setFlightTotalDuration("2h 5m");
        flightSearchResult2.setFlightPrice("2930");
        flightSearchResult2.setStrAirline("4");


        FlightSearchResult flightSearchResult3 = new FlightSearchResult();

        flightSearchResult3.setAirlineName("Vistara UK- 950");
        flightSearchResult3.setFlightArrivalStation("BOM");
        flightSearchResult3.setFlightArrivalTime("22:00");
        flightSearchResult3.setFlightDepartureStation("DEL");
        flightSearchResult3.setFlightDepartureTime("24:10");
        flightSearchResult3.setFlightTotalDuration("2h 5m");
        flightSearchResult3.setFlightPrice("2930");
        flightSearchResult3.setStrAirline("4");


        FlightSearchResult flightSearchResult4 = new FlightSearchResult();

        flightSearchResult4.setAirlineName("Air India AI-101");
        flightSearchResult4.setFlightArrivalStation("BOM");
        flightSearchResult4.setFlightArrivalTime("21:00");
        flightSearchResult4.setFlightDepartureStation("DEL");
        flightSearchResult4.setFlightDepartureTime("23:10");
        flightSearchResult4.setFlightTotalDuration("2h 10m");
        flightSearchResult4.setFlightPrice("3228");
        flightSearchResult4.setStrAirline("2");


        FlightSearchResult flightSearchResult5 = new FlightSearchResult();

        flightSearchResult5.setAirlineName("Air India AI-677");
        flightSearchResult5.setFlightArrivalStation("BOM");
        flightSearchResult5.setFlightArrivalTime("13:00");
        flightSearchResult5.setFlightDepartureStation("DEL");
        flightSearchResult5.setFlightDepartureTime("15:15");
        flightSearchResult5.setFlightTotalDuration("2h 5m");
        flightSearchResult5.setFlightPrice("3228");
        flightSearchResult5.setStrAirline("2");


        FlightSearchResult flightSearchResult6 = new FlightSearchResult();

        flightSearchResult6.setAirlineName("Indigo 6E-956");
        flightSearchResult6.setFlightArrivalStation("BOM");
        flightSearchResult6.setFlightArrivalTime("20:00");
        flightSearchResult6.setFlightDepartureStation("DEL");
        flightSearchResult6.setFlightDepartureTime("22:10");
        flightSearchResult6.setFlightTotalDuration("2h 5m");
        flightSearchResult6.setFlightPrice("3218");
        flightSearchResult6.setStrAirline("3");


        FlightSearchResult flightSearchResult7 = new FlightSearchResult();

        flightSearchResult7.setAirlineName("Indigo 6E-841");
        flightSearchResult7.setFlightArrivalStation("BOM");
        flightSearchResult7.setFlightArrivalTime("17:00");
        flightSearchResult7.setFlightDepartureStation("DEL");
        flightSearchResult7.setFlightDepartureTime("20:10");
        flightSearchResult7.setFlightTotalDuration("2h 5m");
        flightSearchResult7.setFlightPrice("3218");
        flightSearchResult7.setStrAirline("3");


        flightSearchResultArrayList.add(flightSearchResult1);
        flightSearchResultArrayList.add(flightSearchResult2);
        flightSearchResultArrayList.add(flightSearchResult3);
        flightSearchResultArrayList.add(flightSearchResult4);
        flightSearchResultArrayList.add(flightSearchResult5);
        flightSearchResultArrayList.add(flightSearchResult6);
        flightSearchResultArrayList.add(flightSearchResult7);


        flightListAdapter = new FlightListAdapter(flightSearchResultArrayList, FlightSearchResultActivity.this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(FlightSearchResultActivity.this, RecyclerView.VERTICAL, false);

        flightsList.setLayoutManager(layoutManager);

        flightsList.setItemAnimator(new DefaultItemAnimator());
        flightsList.setAdapter(flightListAdapter);

        flightListAdapter.notifyDataSetChanged();

    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Delhi --> Mumbai");

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
