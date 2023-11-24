package com.smart.pay.fragments.mall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.activity.mall.CartActivity;
import com.smart.pay.activity.mall.ThankYouActivity;
import com.smart.pay.api.ApiUtils;
import com.smart.pay.api.MainAPIInterface;
import com.smart.pay.db.DatabaseClient;
import com.smart.pay.db.Product;
import com.smart.pay.models.output.GetOnlineCartOutput;
import com.smart.pay.models.output.ProductOrderNewModel;
import com.smart.pay.models.output.ProductOrderNewOutputModel;
import com.smart.pay.utils.DataVaultManager;
import com.smart.pay.views.MyTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smart.pay.utils.DataVaultManager.KEY_EMAIL;
import static com.smart.pay.utils.DataVaultManager.KEY_USER_ID;


/**
 * Created by Sandeep Londhe on 07-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

public class PaymentFragment extends Fragment {

    View mView;

    MyTextView placeOrderFinal;

    String newProductNames;

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;

    //Paypal Configuration Object
    private static PayPalConfiguration config;

    LinearLayout paypalLayout, codLayout;


    ImageView imgPaypalselected, imgWalletselected;

    int isPaymentSelected;

    String strPaymentType;

    MainAPIInterface mainAPIInterface;

    public ArrayList<String> paybleList;


    TextView finalAmount;

    TextView finalAddress;

    double price = 0;
    double old_price = 0;
    double discount = 0;


    private ProgressDialog dialog;

    String strSellerId;


    String paypal_id;

    TextView walletBalance;

    TextView txtCurrrencySymbol;

    ArrayList<GetOnlineCartOutput.CartProduct> cartProductArrayList;

    ArrayList<ProductOrderNewModel.Product> productArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.payment_fragment, container, false);

        mainAPIInterface = ApiUtils.getAPIService();

        placeOrderFinal = (MyTextView) mView.findViewById(R.id.placeOrderFinal);

        productArrayList = new ArrayList<ProductOrderNewModel.Product>();

        paypalLayout = (LinearLayout) mView.findViewById(R.id.paypalLayout);
        codLayout = (LinearLayout) mView.findViewById(R.id.codLayout);

        txtCurrrencySymbol = (TextView) mView.findViewById(R.id.txtCurrrencySymbol);

        CartActivity.strProductName = new ArrayList<String>();

        txtCurrrencySymbol.setText(SmartPayApplication.CURRENCY_CODE + " " + SmartPayApplication.CURRENCY_SYMBOL);

        imgPaypalselected = (ImageView) mView.findViewById(R.id.imgPaypalselected);
        imgWalletselected = (ImageView) mView.findViewById(R.id.imgWalletselected);

        walletBalance = (TextView) mView.findViewById(R.id.walletBalance);

        finalAmount = (TextView) mView.findViewById(R.id.finalAmount);
        finalAddress = (TextView) mView.findViewById(R.id.finalAddress);

        paybleList = CartActivity.strTotalPayble;


        if (SmartPayApplication.PAYPAL_SANDBOX.equalsIgnoreCase("1")) {

            System.out.println(SmartPayApplication.CURRENCY_CODE);


            config = new PayPalConfiguration()
                    // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                    // or live (ENVIRONMENT_PRODUCTION)
                    .rememberUser(true)
                    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                    .clientId(SmartPayApplication.PAYPAL_CLIENT_ID);

        } else {


            System.out.println(SmartPayApplication.CURRENCY_CODE);

            config = new PayPalConfiguration()
                    // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
                    // or live (ENVIRONMENT_PRODUCTION)
                    .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
                    .rememberUser(true)
                    .clientId(SmartPayApplication.PAYPAL_CLIENT_ID);

        }


        finalAddress.setText(CartActivity.strCustomerAddress + " " + CartActivity.strCustomerCity + "\n" +
                CartActivity.strCustomerZipcode + " " + CartActivity.strCustomerCountry);


        paypalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartActivity.strPaymentType = "1";

                isPaymentSelected = 1;

                paypalLayout.isSelected();
                imgPaypalselected.setVisibility(View.VISIBLE);
                imgWalletselected.setVisibility(View.INVISIBLE);

            }
        });


        codLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isPaymentSelected = 2;

                codLayout.isSelected();
                imgPaypalselected.setVisibility(View.INVISIBLE);
                imgWalletselected.setVisibility(View.VISIBLE);

            }
        });


        placeOrderFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPaymentSelected == 1) {

                    paypalPaymentRequest();

                } else if (isPaymentSelected == 2) {

                    placeCashOnDeleveryRequest("2");


                } else {
                    Toast.makeText(getActivity(), "Please select payment method.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllCartProducts();

    }

    public void paypalPaymentRequest() {

        price = price / Double.valueOf(SmartPayApplication.CURRENCY_EXCHANGE_RATE);

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(price)), "USD", "Pay to " + getActivity().getString(R.string.app_name),
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(getActivity(), PaymentActivity.class);

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

                        Toast.makeText(getActivity(), "Thanks For Making Payment", Toast.LENGTH_LONG).show();

                        JSONObject newJsonArray = new JSONObject(paymentDetails);

                        JSONObject newJsonObject2 = newJsonArray.getJSONObject("response");

                        paypal_id = newJsonObject2.getString("id");

                        System.out.print("PAYPAL ID==" + paypal_id);


                        placeCashOnDeleveryRequest("1");


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

//    private void placeOrderRequest() {
//
//
//        String xAccessToken = "mykey";
//
//        dialog = new ProgressDialog(getActivity());
//
//        dialog.setMessage("Placing your order.");
//        dialog.show();
//
//
//        String newProductIds = new Gson().toJson(CartActivity.strProductId);
//        newProductNames = new Gson().toJson(CartActivity.strProductName);
//
//
//        BookOrderInputModel bookOrderInputModel = new BookOrderInputModel();
//        bookOrderInputModel.setProductId(CartActivity.strProductId);
//        bookOrderInputModel.setProductName(newProductNames);
//        bookOrderInputModel.setCategoryId(CartActivity.strCategoryId);
//        bookOrderInputModel.setSubcatId(CartActivity.strSubcatId);
//        bookOrderInputModel.setCustomerId(DataVaultManager.getInstance(LykaaApplication.getInstance()).getVaultValue(KEY_USER_ID));
//        bookOrderInputModel.setCustomerFirstname(CartActivity.strCustomerFirstname);
//        bookOrderInputModel.setCustomerLastname(CartActivity.strCustomerLastname);
//        bookOrderInputModel.setCustomerZipcode(CartActivity.strCustomerZipcode);
//        bookOrderInputModel.setState(CartActivity.strCustomerState);
//        bookOrderInputModel.setCustomerCountry(CartActivity.strCustomerCountry);
//        bookOrderInputModel.setCustomerCity(CartActivity.strCustomerCity);
//        bookOrderInputModel.setCustomerAddress(CartActivity.strCustomerAddress);
//        bookOrderInputModel.setCustomerPhoneNumber(CartActivity.strPhoneNo);
//        bookOrderInputModel.setCustomerEmailAddress(CartActivity.strEmailAddress);
//        bookOrderInputModel.setTotalAmount(String.valueOf(price));
//        bookOrderInputModel.setPaypalPaymentId(paypal_id);
//
//
//        mainAPIInterface.bookOrder(xAccessToken, bookOrderInputModel).enqueue(new Callback<BookOrderModel>() {
//            @Override
//            public void onResponse(Call<BookOrderModel> call, Response<BookOrderModel> response) {
//
//
//                if (response.isSuccessful()) {
//
//                    dialog.dismiss();
//
//                    if (response.body().getSuccess().equalsIgnoreCase("1")) {
//
//                        new DeleteCartItems().execute();
//
//                        Toast.makeText(getActivity(), "Your Payment Successful.", Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(getActivity(), ThankYouActivity.class);
//                        i.putStringArrayListExtra("newProductNames", CartActivity.strProductName);
//                        i.putExtra("amount", String.valueOf(price));
//                        i.putExtra("address", CartActivity.strCustomerAddress);
//                        startActivity(i);
//                        getActivity().finish();
//
//
//                    } else {
//                        Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_LONG).show();
//
//                    }
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BookOrderModel> call, Throwable t) {
//                dialog.dismiss();
//                Log.i("tag", t.getMessage().toString());
//            }
//        });
//
//
//    }

    private void placeCashOnDeleveryRequest(String strType) {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Placing your order.");
        dialog.show();

        ProductOrderNewModel productOrderNewModel = new ProductOrderNewModel();
        productOrderNewModel.setProducts(productArrayList);
        productOrderNewModel.setCustomerName(CartActivity.strCustomerFirstname);
        productOrderNewModel.setCustomerId(DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID));
        productOrderNewModel.setCustomerEmail(DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_EMAIL));
        productOrderNewModel.setCustomerPhone(CartActivity.strPhoneNo);
        productOrderNewModel.setAddress(CartActivity.strCustomerAddress);
        productOrderNewModel.setCity(CartActivity.strCustomerCity);
        productOrderNewModel.setState(CartActivity.strCustomerState);
        productOrderNewModel.setZipcode(CartActivity.strCustomerZipcode);
        productOrderNewModel.setCountry(CartActivity.strCustomerCountry);
        productOrderNewModel.setPaymentType(strType);
        productOrderNewModel.setSellerId(strSellerId);
        productOrderNewModel.setTotalAmount(String.valueOf(price));
        productOrderNewModel.setShippingCharge("0");
        productOrderNewModel.setTax("0");


        mainAPIInterface.newOrderBookingRequest(xAccessToken, productOrderNewModel).enqueue(new Callback<ProductOrderNewOutputModel>() {
            @Override
            public void onResponse(Call<ProductOrderNewOutputModel> call, Response<ProductOrderNewOutputModel> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        new DeleteCartItems().execute();

                        Toast.makeText(getActivity(), "Your Payment Successful.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getActivity(), ThankYouActivity.class);
                        i.putStringArrayListExtra("newProductNames", CartActivity.strProductName);
                        i.putExtra("amount", String.valueOf(price));
                        i.putExtra("address", CartActivity.strCustomerAddress);
                        startActivity(i);
                        getActivity().finish();


                    } else {
                        Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_LONG).show();

                    }


                }
            }

            @Override
            public void onFailure(Call<ProductOrderNewOutputModel> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }

    class DeleteCartItems extends AsyncTask<Void, Void, List<Product>> {

        @Override
        protected void onPreExecute() {

//            deliveryResponseList.clear();
            Log.e("onPreExecutive", "called");
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            DatabaseClient
                    .getInstance(getActivity())
                    .getAppDatabase()
                    .daoProduct()
                    .delete();


            return null;


        }

        @Override
        protected void onPostExecute(List<Product> productsList) {
            super.onPostExecute(productsList);

            //     Toast.makeText(getActivity(), "Product Removed", Toast.LENGTH_LONG).show();

        }
    }

    private void getAllCartProducts() {
        String xAccessToken = "mykey";

        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Calculating final amount.");
        dialog.show();

        String strCustomerId = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_USER_ID);

        MultipartBody.Part customer_id_body = MultipartBody.Part.createFormData("customer_id", strCustomerId);

        mainAPIInterface.getOnlineCartList(xAccessToken, customer_id_body).enqueue(new Callback<GetOnlineCartOutput>() {
            @Override
            public void onResponse(Call<GetOnlineCartOutput> call, Response<GetOnlineCartOutput> response) {


                if (response.isSuccessful()) {

                    dialog.dismiss();

                    cartProductArrayList = response.body().getCartProducts();


                    for (int i = 0; i < cartProductArrayList.size(); i++) {

                        price += Double.valueOf(cartProductArrayList.get(i).getPrice());
                        old_price += Double.valueOf(cartProductArrayList.get(i).getOld_price());

                        strSellerId = cartProductArrayList.get(i).getSellerId();
                        ProductOrderNewModel.Product product = new ProductOrderNewModel.Product();


                        product.setProductId(cartProductArrayList.get(i).getProductId());
                        product.setPrice(cartProductArrayList.get(i).getPrice());
                        product.setDiscount(cartProductArrayList.get(i).getDiscount());
                        product.setProductName(cartProductArrayList.get(i).getProductName());
                        product.setQuantity(cartProductArrayList.get(i).getQuantity());
                        product.setSellerId(cartProductArrayList.get(i).getSellerId());

                        productArrayList.add(product);


                        CartActivity.strProductName.add(cartProductArrayList.get(i).getProductName());

                    }

                    discount = old_price - price;

                    finalAmount.setText(String.valueOf(price));


                }
            }

            @Override
            public void onFailure(Call<GetOnlineCartOutput> call, Throwable t) {
                dialog.dismiss();
                Log.i("tag", t.getMessage().toString());
            }
        });


    }


}