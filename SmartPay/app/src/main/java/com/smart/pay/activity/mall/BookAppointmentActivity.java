package com.smart.pay.activity.mall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.models.output.AppointmentBookingInputModel;
import com.smart.pay.models.output.AppointmentBookingOutputModel;
import com.smart.pay.utils.DataVaultManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_EMAIL;
import static com.smart.pay.utils.DataVaultManager.KEY_NAME;
import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 13-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class BookAppointmentActivity extends AppCompatActivity {

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;


    //Paypal Configuration Object
    private static PayPalConfiguration config;

    TextView confirmBooking, serviceFees;

    MainAPIInterface mainAPIInterface;

    private ProgressDialog dialog;

    TextView txtTime1, txtTime2, txtTime3, txtTime4, txtTime5, txtTime6, txtTime7, txtTime8,
            txtTime9, txtTime10, txtTime11, txtTime12, txtTime13;

    String strVisitTime, strVisitDate;

    String strServiceId, strSellerId, strPrice, strCustomerId, strCustomerName, strCustomerEmail,
            paypal_id, serviceName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_appointment_activity);
        setupActionBar();

        mainAPIInterface = ApiUtils.getAPIService();

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds

        simpleCalendarView.setFirstDayOfWeek(2); // set Monday as the first day of the week

        txtTime1 = (TextView) findViewById(R.id.txtTime1);
        txtTime2 = (TextView) findViewById(R.id.txtTime2);
        txtTime3 = (TextView) findViewById(R.id.txtTime3);
        txtTime4 = (TextView) findViewById(R.id.txtTime4);
        txtTime5 = (TextView) findViewById(R.id.txtTime5);
        txtTime6 = (TextView) findViewById(R.id.txtTime6);
        txtTime7 = (TextView) findViewById(R.id.txtTime7);
        txtTime8 = (TextView) findViewById(R.id.txtTime8);
        txtTime9 = (TextView) findViewById(R.id.txtTime9);
        txtTime10 = (TextView) findViewById(R.id.txtTime10);
        txtTime11 = (TextView) findViewById(R.id.txtTime11);
        txtTime12 = (TextView) findViewById(R.id.txtTime12);
        txtTime13 = (TextView) findViewById(R.id.txtTime13);

        serviceFees = (TextView) findViewById(R.id.serviceFees);
        confirmBooking = (TextView) findViewById(R.id.confirmBooking);


        Intent i = getIntent();

        strSellerId = i.getExtras().getString("sellerId");
        strServiceId = i.getExtras().getString("serviceId");
        strPrice = i.getExtras().getString("price");
        serviceName = i.getExtras().getString("serviceName");

        if (SmartPayApplication.PAYPAL_SANDBOX.equalsIgnoreCase("1")) {
            config = new PayPalConfiguration()
                    // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                    // or live (ENVIRONMENT_PRODUCTION)
                    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                    .clientId(SmartPayApplication.PAYPAL_CLIENT_ID);

        } else {
            config = new PayPalConfiguration()
                    // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                    // or live (ENVIRONMENT_PRODUCTION)
                    .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
                    .clientId(SmartPayApplication.PAYPAL_CLIENT_ID);

        }

        strVisitDate = String.valueOf(selectedDate);

        serviceFees.setText(SmartPayApplication.CURRENCY_SYMBOL + strPrice);


        txtTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime1.getText().toString();

                txtTime1.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline2);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);


            }
        });


        txtTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime2.getText().toString();

                txtTime2.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline2);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime3.getText().toString();

                txtTime3.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline2);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime4.getText().toString();

                txtTime4.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline2);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime5.getText().toString();

                txtTime5.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline2);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime6.getText().toString();

                txtTime6.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline2);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });

        txtTime7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime7.getText().toString();

                txtTime7.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline2);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime8.getText().toString();

                txtTime8.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline2);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime9.getText().toString();

                txtTime9.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline2);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime10.getText().toString();

                txtTime10.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline2);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime11.getText().toString();


                txtTime11.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline2);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });


        txtTime12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime12.getText().toString();

                txtTime12.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline2);
                txtTime13.setBackgroundResource(R.drawable.round_outline3);

            }
        });

        txtTime13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strVisitTime = txtTime13.getText().toString();

                txtTime13.setSelected(true);
                txtTime1.setBackgroundResource(R.drawable.round_outline3);
                txtTime2.setBackgroundResource(R.drawable.round_outline3);
                txtTime3.setBackgroundResource(R.drawable.round_outline3);
                txtTime4.setBackgroundResource(R.drawable.round_outline3);
                txtTime5.setBackgroundResource(R.drawable.round_outline3);
                txtTime6.setBackgroundResource(R.drawable.round_outline3);
                txtTime7.setBackgroundResource(R.drawable.round_outline3);
                txtTime8.setBackgroundResource(R.drawable.round_outline3);
                txtTime9.setBackgroundResource(R.drawable.round_outline3);
                txtTime10.setBackgroundResource(R.drawable.round_outline3);
                txtTime11.setBackgroundResource(R.drawable.round_outline3);
                txtTime12.setBackgroundResource(R.drawable.round_outline3);
                txtTime13.setBackgroundResource(R.drawable.round_outline2);

            }
        });


        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                paymentRequestForBooking();


            }
        });

    }


    private void paymentRequestForBooking() {

        Double strPrice1 = Double.valueOf(strPrice) / Double.valueOf(SmartPayApplication.CURRENCY_EXCHANGE_RATE);


        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(strPrice1)), "USD", getString(R.string.app_name),
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(BookAppointmentActivity.this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        Toast.makeText(BookAppointmentActivity.this, "Thanks For Making Payment", Toast.LENGTH_LONG).show();


                        JSONObject newJsonArray = new JSONObject(paymentDetails);

                        JSONObject newJsonObject2 = newJsonArray.getJSONObject("response");

                        paypal_id = newJsonObject2.getString("id");

                        System.out.print("PAYPAL ID==" + paypal_id);

                        confirmBookingRequest();

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }


    private void confirmBookingRequest() {


        dialog = new ProgressDialog(BookAppointmentActivity.this);

        dialog.setMessage("Booking your appointment.");
        dialog.show();


        strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);
        strCustomerName = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_NAME);
        strCustomerEmail = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_EMAIL);


        String xAccessToken = "mykey";

        AppointmentBookingInputModel appointmentBookingInputModel = new AppointmentBookingInputModel();
        appointmentBookingInputModel.setServiceId(strServiceId);
        appointmentBookingInputModel.setServiceType("1");
        appointmentBookingInputModel.setPrice(strPrice);
        appointmentBookingInputModel.setSellerId(strSellerId);
        appointmentBookingInputModel.setCustomerId(strCustomerId);
        appointmentBookingInputModel.setCustomerName(strCustomerName);
        appointmentBookingInputModel.setCustomerEmail(strCustomerEmail);
        appointmentBookingInputModel.setCustomerPhone("");
        appointmentBookingInputModel.setBookingDate(strVisitDate);
        appointmentBookingInputModel.setBookingTime(strVisitTime);
        appointmentBookingInputModel.setPaypal_payment_id(paypal_id);


        mainAPIInterface.bookAppointment(xAccessToken, appointmentBookingInputModel).enqueue(new Callback<AppointmentBookingOutputModel>() {
            @Override
            public void onResponse(Call<AppointmentBookingOutputModel> call, Response<AppointmentBookingOutputModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {


                        Toast.makeText(BookAppointmentActivity.this, "Your Payment Successful.", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(BookAppointmentActivity.this, ThankYouAppointmentActivity.class);
                        i.putExtra("serviceName", serviceName);
                        i.putExtra("appointmentDate", strVisitDate);
                        i.putExtra("appointmentTime", strVisitTime);
                        i.putExtra("fees", strPrice);

                        startActivity(i);

                        BookAppointmentActivity.this.finish();


                    } else {

                        Toast.makeText(BookAppointmentActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();

                    }


                }
            }

            @Override
            public void onFailure(Call<AppointmentBookingOutputModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
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

        toolbarTitle.setText("Book an Appointment");


        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Book an Appointment");

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
