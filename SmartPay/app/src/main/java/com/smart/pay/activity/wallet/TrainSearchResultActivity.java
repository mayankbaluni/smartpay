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
import com.smart.pay.adapter.travel.TrainListAdapter;
import com.smart.pay.models.output.TrainSearchResult;

import java.util.ArrayList;

public class TrainSearchResultActivity extends AppCompatActivity {

    RecyclerView trainSearchList;

    ArrayList<TrainSearchResult> trainSearchResultArrayList;

    TrainListAdapter trainListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.train_search_result_activity);
        setupActionBar();

        trainSearchList = (RecyclerView) findViewById(R.id.trainSearchList);


        trainSearchResultArrayList = new ArrayList<TrainSearchResult>();


        TrainSearchResult trainSearchResult1 = new TrainSearchResult();

        TrainSearchResult.CoachType coachType1 = new TrainSearchResult.CoachType();

        coachType1.setCoachClass("2A- AC 2 Tier");
        coachType1.setSeatPrice("2000");
        coachType1.setStrAvailability("1");
        coachType1.setStrLastUpdatedTime("1 hrs ago");
        coachType1.setStrIsTatkal("0");


        TrainSearchResult.CoachType coachType2 = new TrainSearchResult.CoachType();

        coachType2.setCoachClass("3A- AC 3 Tier");
        coachType2.setSeatPrice("1503");
        coachType2.setStrAvailability("2");
        coachType2.setStrLastUpdatedTime("2 hrs ago");
        coachType2.setStrIsTatkal("0");

        TrainSearchResult.CoachType coachType3 = new TrainSearchResult.CoachType();

        coachType3.setCoachClass("SL- Sleeper Class");
        coachType3.setSeatPrice("750");
        coachType3.setStrAvailability("1");
        coachType3.setStrLastUpdatedTime("1 hrs ago");
        coachType3.setStrIsTatkal("0");

        ArrayList<TrainSearchResult.CoachType> coachTypeArrayList1 = new ArrayList<TrainSearchResult.CoachType>();

        coachTypeArrayList1.add(coachType1);
        coachTypeArrayList1.add(coachType2);
        coachTypeArrayList1.add(coachType3);


        trainSearchResult1.setTrainArrivalStation("New Delhi");
        trainSearchResult1.setTrainArrivalTime("06:00");
        trainSearchResult1.setTrainDepartureStation("Mumbai");
        trainSearchResult1.setTrainDepartureTime("10:00");
        trainSearchResult1.setTrainName("Nainital Express(8822)");
        trainSearchResult1.setTrainTotalTravelTime("28 h 20 m");
        trainSearchResult1.setCoachTypeArrayList(coachTypeArrayList1);

        trainSearchResultArrayList.add(trainSearchResult1);


        //For Search2

        TrainSearchResult trainSearchResult2 = new TrainSearchResult();

        TrainSearchResult.CoachType coachType4 = new TrainSearchResult.CoachType();

        coachType4.setCoachClass("2A- AC 2 Tier");
        coachType4.setSeatPrice("2000");
        coachType4.setStrAvailability("1");
        coachType4.setStrLastUpdatedTime("1 hrs ago");
        coachType4.setStrIsTatkal("0");


        TrainSearchResult.CoachType coachType5 = new TrainSearchResult.CoachType();

        coachType5.setCoachClass("3A- AC 3 Tier");
        coachType5.setSeatPrice("1503");
        coachType5.setStrAvailability("2");
        coachType5.setStrLastUpdatedTime("2 hrs ago");
        coachType5.setStrIsTatkal("0");

        TrainSearchResult.CoachType coachType6 = new TrainSearchResult.CoachType();

        coachType6.setCoachClass("SL- Sleeper Class");
        coachType6.setSeatPrice("550");
        coachType6.setStrAvailability("1");
        coachType6.setStrLastUpdatedTime("1 hrs ago");
        coachType6.setStrIsTatkal("0");

        ArrayList<TrainSearchResult.CoachType> coachTypeArrayList2 = new ArrayList<TrainSearchResult.CoachType>();

        coachTypeArrayList2.add(coachType4);
        coachTypeArrayList2.add(coachType5);
        coachTypeArrayList2.add(coachType6);


        trainSearchResult2.setTrainArrivalStation("New Delhi");
        trainSearchResult2.setTrainArrivalTime("06:00");
        trainSearchResult2.setTrainDepartureStation("Mumbai");
        trainSearchResult2.setTrainDepartureTime("10:00");
        trainSearchResult2.setTrainName("Rajdhani Express(4051)");
        trainSearchResult2.setTrainTotalTravelTime("18 h 20 m");
        trainSearchResult2.setCoachTypeArrayList(coachTypeArrayList2);

        trainSearchResultArrayList.add(trainSearchResult2);

        //For Search 3


        TrainSearchResult trainSearchResult3 = new TrainSearchResult();

        TrainSearchResult.CoachType coachType7 = new TrainSearchResult.CoachType();

        coachType7.setCoachClass("2A- AC 2 Tier");
        coachType7.setSeatPrice("2000");
        coachType7.setStrAvailability("1");
        coachType7.setStrLastUpdatedTime("1 hrs ago");
        coachType7.setStrIsTatkal("0");


        TrainSearchResult.CoachType coachType8 = new TrainSearchResult.CoachType();

        coachType8.setCoachClass("3A- AC 3 Tier");
        coachType8.setSeatPrice("1503");
        coachType8.setStrAvailability("2");
        coachType8.setStrLastUpdatedTime("2 hrs ago");
        coachType8.setStrIsTatkal("0");

        TrainSearchResult.CoachType coachType9 = new TrainSearchResult.CoachType();

        coachType9.setCoachClass("SL- Sleeper Class");
        coachType9.setSeatPrice("550");
        coachType9.setStrAvailability("1");
        coachType9.setStrLastUpdatedTime("3 hrs ago");
        coachType9.setStrIsTatkal("0");

        ArrayList<TrainSearchResult.CoachType> coachTypeArrayList3 = new ArrayList<TrainSearchResult.CoachType>();

        coachTypeArrayList3.add(coachType7);
        coachTypeArrayList3.add(coachType8);
        coachTypeArrayList3.add(coachType9);


        trainSearchResult3.setTrainArrivalStation("New Delhi");
        trainSearchResult3.setTrainArrivalTime("06:00");
        trainSearchResult3.setTrainDepartureStation("Mumbai");
        trainSearchResult3.setTrainDepartureTime("10:00");
        trainSearchResult3.setTrainName("Dharawi Express(4051)");
        trainSearchResult3.setTrainTotalTravelTime("18 h 20 m");
        trainSearchResult3.setCoachTypeArrayList(coachTypeArrayList3);

        trainSearchResultArrayList.add(trainSearchResult3);


        //For Search 4

        TrainSearchResult trainSearchResult4 = new TrainSearchResult();

        TrainSearchResult.CoachType coachType10 = new TrainSearchResult.CoachType();

        coachType10.setCoachClass("2A- AC 2 Tier");
        coachType10.setSeatPrice("2000");
        coachType10.setStrAvailability("1");
        coachType10.setStrLastUpdatedTime("1 hrs ago");
        coachType10.setStrIsTatkal("0");


        TrainSearchResult.CoachType coachType11 = new TrainSearchResult.CoachType();

        coachType11.setCoachClass("3A- AC 3 Tier");
        coachType11.setSeatPrice("1503");
        coachType11.setStrAvailability("2");
        coachType11.setStrLastUpdatedTime("2 hrs ago");
        coachType11.setStrIsTatkal("0");

        TrainSearchResult.CoachType coachType12 = new TrainSearchResult.CoachType();

        coachType12.setCoachClass("SL- Sleeper Class");
        coachType12.setSeatPrice("550");
        coachType12.setStrAvailability("1");
        coachType12.setStrLastUpdatedTime("3 hrs ago");
        coachType12.setStrIsTatkal("0");

        ArrayList<TrainSearchResult.CoachType> coachTypeArrayList4 = new ArrayList<TrainSearchResult.CoachType>();

        coachTypeArrayList4.add(coachType10);
        coachTypeArrayList4.add(coachType11);
        coachTypeArrayList4.add(coachType12);


        trainSearchResult4.setTrainArrivalStation("New Delhi");
        trainSearchResult4.setTrainArrivalTime("06:00");
        trainSearchResult4.setTrainDepartureStation("Mumbai");
        trainSearchResult4.setTrainDepartureTime("10:00");
        trainSearchResult4.setTrainName("Kamayani Ex(4052)");
        trainSearchResult4.setTrainTotalTravelTime("18 h 20 m");
        trainSearchResult4.setCoachTypeArrayList(coachTypeArrayList4);

        trainSearchResultArrayList.add(trainSearchResult4);

        //Search Result 5


        TrainSearchResult trainSearchResult5 = new TrainSearchResult();

        TrainSearchResult.CoachType coachType13 = new TrainSearchResult.CoachType();

        coachType13.setCoachClass("2A- AC 2 Tier");
        coachType13.setSeatPrice("2000");
        coachType13.setStrAvailability("1");
        coachType13.setStrLastUpdatedTime("1 hrs ago");
        coachType13.setStrIsTatkal("0");


        TrainSearchResult.CoachType coachType14 = new TrainSearchResult.CoachType();

        coachType14.setCoachClass("3A- AC 3 Tier");
        coachType14.setSeatPrice("1503");
        coachType14.setStrAvailability("2");
        coachType14.setStrLastUpdatedTime("2 hrs ago");
        coachType14.setStrIsTatkal("0");

        TrainSearchResult.CoachType coachType15 = new TrainSearchResult.CoachType();

        coachType15.setCoachClass("SL- Sleeper Class");
        coachType15.setSeatPrice("550");
        coachType15.setStrAvailability("1");
        coachType15.setStrLastUpdatedTime("2 hrs ago");
        coachType15.setStrIsTatkal("0");

        ArrayList<TrainSearchResult.CoachType> coachTypeArrayList5 = new ArrayList<TrainSearchResult.CoachType>();

        coachTypeArrayList5.add(coachType13);
        coachTypeArrayList5.add(coachType14);
        coachTypeArrayList5.add(coachType15);


        trainSearchResult5.setTrainArrivalStation("New Delhi");
        trainSearchResult5.setTrainArrivalTime("06:00");
        trainSearchResult5.setTrainDepartureStation("Mumbai");
        trainSearchResult5.setTrainDepartureTime("10:00");
        trainSearchResult5.setTrainName("Chetak Express(4041)");
        trainSearchResult5.setTrainTotalTravelTime("20 h 20 m");
        trainSearchResult5.setCoachTypeArrayList(coachTypeArrayList5);

        trainSearchResultArrayList.add(trainSearchResult5);

        trainListAdapter = new TrainListAdapter(trainSearchResultArrayList, TrainSearchResultActivity.this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(TrainSearchResultActivity.this, RecyclerView.VERTICAL, false);

        trainSearchList.setLayoutManager(layoutManager);

        trainSearchList.setItemAnimator(new DefaultItemAnimator());
        trainSearchList.setAdapter(trainListAdapter);

        trainListAdapter.notifyDataSetChanged();


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
