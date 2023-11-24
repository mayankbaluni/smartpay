package com.smart.pay.fragments.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.smart.pay.R;
import com.smart.pay.activity.wallet.FlightSearchResultActivity;
import com.smart.pay.views.MyTextView;

public class FragmentFlight extends Fragment {


    MyTextView btnSearchFlights;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.flight_fragment, container, false);

        btnSearchFlights = (MyTextView) mView.findViewById(R.id.btnSearchFlights);

        btnSearchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FlightSearchResultActivity.class);
                startActivity(intent);

            }
        });
        return mView;
    }


}
