package com.smart.pay.fragments.bottom;

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
import com.smart.pay.adapter.wallet.InboxListAdapter;
import com.smart.pay.models.output.InboxOutputModel;

import java.util.ArrayList;

public class InboxFragment extends Fragment {

    View mView;
    RecyclerView inboxListView;

    InboxListAdapter inboxListAdapter;
    ArrayList<InboxOutputModel> inboxOutputModelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.inbox_fragment, container, false);

        inboxListView = (RecyclerView) mView.findViewById(R.id.inboxListView);


        InboxOutputModel inboxOutputModel1 = new InboxOutputModel();
        inboxOutputModel1.setTitle("News 1");
        inboxOutputModel1.setAddedDate("13th June");
        inboxOutputModel1.setDescription("This is demo text");
        inboxOutputModel1.setImage(R.drawable.banner1);

        InboxOutputModel inboxOutputModel2 = new InboxOutputModel();
        inboxOutputModel2.setTitle("News 2");
        inboxOutputModel2.setAddedDate("15th July");
        inboxOutputModel2.setDescription("This is demo text");
        inboxOutputModel2.setImage(R.drawable.banner2);


        InboxOutputModel inboxOutputModel3 = new InboxOutputModel();
        inboxOutputModel3.setTitle("News 3");
        inboxOutputModel3.setAddedDate("19th Sep");
        inboxOutputModel3.setDescription("This is demo text");
        inboxOutputModel3.setImage(R.drawable.banner3);


        InboxOutputModel inboxOutputModel4 = new InboxOutputModel();
        inboxOutputModel4.setTitle("News 4");
        inboxOutputModel4.setAddedDate("1th Oct");
        inboxOutputModel4.setDescription("This is demo text");
        inboxOutputModel4.setImage(R.drawable.banner4);


        inboxOutputModelArrayList.add(inboxOutputModel1);
        inboxOutputModelArrayList.add(inboxOutputModel2);
        inboxOutputModelArrayList.add(inboxOutputModel3);
        inboxOutputModelArrayList.add(inboxOutputModel4);

        inboxListAdapter = new InboxListAdapter(inboxOutputModelArrayList, (AppCompatActivity) getActivity());

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        inboxListView.setLayoutManager(layoutManager);

        inboxListView.setItemAnimator(new DefaultItemAnimator());
        inboxListView.setAdapter(inboxListAdapter);

        inboxListAdapter.notifyDataSetChanged();


        return mView;
    }

}
