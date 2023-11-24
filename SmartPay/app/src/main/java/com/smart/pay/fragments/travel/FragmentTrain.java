package com.smart.pay.fragments.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.smart.pay.R;
import com.smart.pay.activity.wallet.TrainSearchResultActivity;
import com.smart.pay.views.MyTextView;

public class FragmentTrain extends Fragment {

    View mView;

    MyTextView btnSearchTrains;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.train_fragment, container, false);

        btnSearchTrains = (MyTextView) mView.findViewById(R.id.btnSearchTrains);

        btnSearchTrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TrainSearchResultActivity.class);
                startActivity(intent);

            }
        });
        return mView;
    }
}
