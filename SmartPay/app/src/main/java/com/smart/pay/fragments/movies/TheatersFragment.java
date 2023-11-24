package com.smart.pay.fragments.movies;

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
import com.smart.pay.adapter.movie.TheaterListAdapter;
import com.smart.pay.models.output.TheaterListOutput;

import java.util.ArrayList;

public class TheatersFragment extends Fragment {

    View mView;
    RecyclerView theaterList;

    TheaterListAdapter theaterListAdapter;
    ArrayList<TheaterListOutput> theaterListOutputArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.theaters_list_fragment, container, false);

        theaterList = (RecyclerView) mView.findViewById(R.id.theaterList);

        theaterListOutputArrayList = new ArrayList<TheaterListOutput>();

        TheaterListOutput.ShowTime showTime1 = new TheaterListOutput.ShowTime();
        showTime1.setShowTime("9:00 AM");

        TheaterListOutput.ShowTime showTime2 = new TheaterListOutput.ShowTime();
        showTime2.setShowTime("12:00 PM");

        TheaterListOutput.ShowTime showTime3 = new TheaterListOutput.ShowTime();
        showTime3.setShowTime("2:30 PM");

        TheaterListOutput.ShowTime showTime4 = new TheaterListOutput.ShowTime();
        showTime4.setShowTime("4:30 AM");

        TheaterListOutput.ShowTime showTime5 = new TheaterListOutput.ShowTime();
        showTime5.setShowTime("6:00 PM");


        TheaterListOutput.ShowTime showTime6 = new TheaterListOutput.ShowTime();
        showTime6.setShowTime("8:00 PM");

        TheaterListOutput.ShowTime showTime7 = new TheaterListOutput.ShowTime();
        showTime7.setShowTime("10:30 PM");


        TheaterListOutput.ShowTime showTime8 = new TheaterListOutput.ShowTime();
        showTime8.setShowTime("11:45 PM");


        ArrayList<TheaterListOutput.ShowTime> showTimeArrayList1 = new ArrayList<TheaterListOutput.ShowTime>();

        showTimeArrayList1.add(showTime1);
        showTimeArrayList1.add(showTime2);
        showTimeArrayList1.add(showTime3);
        showTimeArrayList1.add(showTime4);
        showTimeArrayList1.add(showTime5);
        showTimeArrayList1.add(showTime6);
        showTimeArrayList1.add(showTime7);
        showTimeArrayList1.add(showTime8);


        TheaterListOutput theaterListOutput1 = new TheaterListOutput();

        theaterListOutput1.setTheaterName("Vivial Mall Thane(W)");
        theaterListOutput1.setTheaterDistance("5.9 km");
        theaterListOutput1.setShowTimeArrayList(showTimeArrayList1);

        theaterListOutputArrayList.add(theaterListOutput1);


        ArrayList<TheaterListOutput.ShowTime> showTimeArrayList2 = new ArrayList<TheaterListOutput.ShowTime>();

        showTimeArrayList2.add(showTime5);
        showTimeArrayList2.add(showTime6);
        showTimeArrayList2.add(showTime7);
        showTimeArrayList2.add(showTime8);


        TheaterListOutput theaterListOutput2 = new TheaterListOutput();

        theaterListOutput2.setTheaterName("INOX Korum Mall, Thane(W)");
        theaterListOutput2.setTheaterDistance("5.9 km");
        theaterListOutput2.setShowTimeArrayList(showTimeArrayList2);

        theaterListOutputArrayList.add(theaterListOutput2);

        //Third theater

        ArrayList<TheaterListOutput.ShowTime> showTimeArrayList3 = new ArrayList<TheaterListOutput.ShowTime>();

        showTimeArrayList3.add(showTime3);
        showTimeArrayList3.add(showTime4);
        showTimeArrayList3.add(showTime5);
        showTimeArrayList3.add(showTime6);
        showTimeArrayList3.add(showTime7);
        showTimeArrayList3.add(showTime8);


        TheaterListOutput theaterListOutput3 = new TheaterListOutput();

        theaterListOutput3.setTheaterName("CinemaStar High Street, Thane(W)");
        theaterListOutput3.setTheaterDistance("6.9 km");
        theaterListOutput3.setShowTimeArrayList(showTimeArrayList3);

        theaterListOutputArrayList.add(theaterListOutput3);

        //Fourth theater


        ArrayList<TheaterListOutput.ShowTime> showTimeArrayList4 = new ArrayList<TheaterListOutput.ShowTime>();

        showTimeArrayList4.add(showTime3);
        showTimeArrayList4.add(showTime4);
        showTimeArrayList4.add(showTime5);
        showTimeArrayList4.add(showTime8);


        TheaterListOutput theaterListOutput4 = new TheaterListOutput();

        theaterListOutput4.setTheaterName("Cinemax Wonder, Thane(W)");
        theaterListOutput4.setTheaterDistance("5.4 km");
        theaterListOutput4.setShowTimeArrayList(showTimeArrayList4);

        theaterListOutputArrayList.add(theaterListOutput4);


        //Fifth theater

        ArrayList<TheaterListOutput.ShowTime> showTimeArrayList5 = new ArrayList<TheaterListOutput.ShowTime>();

        showTimeArrayList5.add(showTime3);
        showTimeArrayList5.add(showTime4);
        showTimeArrayList5.add(showTime5);


        TheaterListOutput theaterListOutput5 = new TheaterListOutput();

        theaterListOutput5.setTheaterName("Cinexmax Eternity, Thane(W)");
        theaterListOutput5.setTheaterDistance("6.9 km");
        theaterListOutput5.setShowTimeArrayList(showTimeArrayList5);

        theaterListOutputArrayList.add(theaterListOutput5);


        //Sixt theater

        ArrayList<TheaterListOutput.ShowTime> showTimeArrayList6 = new ArrayList<TheaterListOutput.ShowTime>();

        showTimeArrayList6.add(showTime1);
        showTimeArrayList6.add(showTime2);
        showTimeArrayList6.add(showTime3);
        showTimeArrayList6.add(showTime6);
        showTimeArrayList6.add(showTime7);
        showTimeArrayList6.add(showTime8);


        TheaterListOutput theaterListOutput6 = new TheaterListOutput();

        theaterListOutput6.setTheaterName("Gold Cinema, Thane(W)");
        theaterListOutput6.setTheaterDistance("4.7 km");
        theaterListOutput6.setShowTimeArrayList(showTimeArrayList6);

        theaterListOutputArrayList.add(theaterListOutput6);


        theaterListAdapter = new TheaterListAdapter(theaterListOutputArrayList, (AppCompatActivity) getActivity());

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        theaterList.setLayoutManager(layoutManager);

        theaterList.setItemAnimator(new DefaultItemAnimator());
        theaterList.setAdapter(theaterListAdapter);

        theaterListAdapter.notifyDataSetChanged();

        return mView;

    }
}
