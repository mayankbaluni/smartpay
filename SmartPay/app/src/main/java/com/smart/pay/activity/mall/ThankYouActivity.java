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

import java.util.ArrayList;


/**
 * Created by Sandeep Londhe on 07-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ThankYouActivity extends AppCompatActivity {

    String amount, address;

    ArrayList<String> newProductNames;

    MyTextView txtYourOrderSection;
    MyTextView thanksProductName, deliveryAddress, totalAmount;

    String emptyString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thankyou);
        setupActionBar();

        Intent i = getIntent();


        newProductNames = i.getExtras().getStringArrayList("newProductNames");
        amount = i.getExtras().getString("amount");
        address = i.getExtras().getString("address");

        thanksProductName = (MyTextView) findViewById(R.id.thanksProductName);
        deliveryAddress = (MyTextView) findViewById(R.id.deliveryAddress);
        totalAmount = (MyTextView) findViewById(R.id.totalAmount);

        txtYourOrderSection = (MyTextView) findViewById(R.id.txtYourOrderSection);

        System.out.print("Array List Size==" + newProductNames.size());


        try {

            thanksProductName.setText("");

            for (int i2 = 0; i2 <= newProductNames.size(); i2++) {

                thanksProductName.append(newProductNames.get(i2) + "\n");

            }


        } catch (IndexOutOfBoundsException e) {

            System.out.print(e.getLocalizedMessage());
        }


        deliveryAddress.setText(address);
        totalAmount.setText(SmartPayApplication.CURRENCY_SYMBOL + amount);

        txtYourOrderSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent yourOrderIntent = new Intent(ThankYouActivity.this, YourOrderActivity.class);
                startActivity(yourOrderIntent);
                finish();

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

        toolbarTitle.setText("Your Cart");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Your Cart");

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
