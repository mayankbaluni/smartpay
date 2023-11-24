package com.smart.pay.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smart.pay.R;
import com.smart.pay.views.MyTextView;

public class AddTrainTicketInfo extends AppCompatActivity {

    MyTextView proceedToBook;

    Spinner stationNameSpinner;

    String[] stationNames = new String[]{"NDLS-New Delhi, 05:15", "FDB-Faridabad. 05:43", "KSV-Kosi Kalan, 06:40",
            "MTJ-Mathura Jn, 07:40", "RKM-Raja Ki Mandi, 08:50", "AGC-Agra Cantt, 08:50", "DHO-Dhoulpur, 09:30",
            "MRA-Morena, 09:55", "GWL-Gwalior, 10:40", "DBA-Dabra, 11:17"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_train_ticket_info);
        setupActionBar();

        proceedToBook = (MyTextView) findViewById(R.id.proceedToBook);

        stationNameSpinner = (Spinner) findViewById(R.id.stationNameSpinner);

        ArrayAdapter<String> gameKindArray = new ArrayAdapter<String>(AddTrainTicketInfo.this, android.R.layout.simple_spinner_item, stationNames);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stationNameSpinner.setAdapter(gameKindArray);

        proceedToBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(AddTrainTicketInfo.this, TrainTicketDetailActivity.class);
                startActivity(newIntent);

            }
        });
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("12332 - Punjab Mail");

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
