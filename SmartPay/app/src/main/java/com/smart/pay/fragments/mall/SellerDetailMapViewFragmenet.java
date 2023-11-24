package com.smart.pay.fragments.mall;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sandeep Londhe on 13-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
@SuppressLint("ValidFragment")
public class SellerDetailMapViewFragmenet extends Fragment {

//    private SupportMapFragment mSupportMapFragment;
//
//    double latitude;
//    double longitude;
//
//    String sellerId;
//
//    View mView;
//
//    MainAPIInterface mainAPIInterface;
//
//
//    LatLng newPosition;
//
//    MyTextView seller_name, seller_address;
//
//    Button btnCallSeller, btnEmailSeller;
//    String strPhone, strEmail;
//
//    @SuppressLint("ValidFragment")
//    public SellerDetailMapViewFragmenet(String sellerId) {
//        this.sellerId = sellerId;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        mView = inflater.inflate(R.layout.seller_detail_map_view, container, false);
//
//
//        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
//
//        mainAPIInterface = ApiUtils.getAPIService();
//
//        seller_address = (MyTextView) mView.findViewById(R.id.seller_address);
//        seller_name = (MyTextView) mView.findViewById(R.id.seller_name);
//        btnEmailSeller = (Button) mView.findViewById(R.id.btnEmailSeller);
//        btnCallSeller = (Button) mView.findViewById(R.id.btnCallSeller);
//
//
//        getSellerDetails();
//
//        if (mSupportMapFragment == null) {
//
//            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            mSupportMapFragment = SupportMapFragment.newInstance();
//            fragmentTransaction.replace(R.id.googlemap, mSupportMapFragment).commit();
//        }
//
//
//        return mView;
//    }
//
//
//    public void getSellerDetails() {
//
//
//        String xAccessToken = "mykey";
//
//        MultipartBody.Part sellerId_body = MultipartBody.Part.createFormData("seller_id", sellerId);
//
//        mainAPIInterface.getSellerPublicDetails(xAccessToken, sellerId_body).enqueue(new Callback<SellerPublicDetailsOutputModel>() {
//            @Override
//            public void onResponse(Call<SellerPublicDetailsOutputModel> call, Response<SellerPublicDetailsOutputModel> response) {
//
//                if (response.isSuccessful()) {
//
//
//                    latitude = Double.valueOf(response.body().getProfile().get(0).getLatitude());
//                    latitude = Double.valueOf(response.body().getProfile().get(0).getLatitude());
//                    seller_name.setText(response.body().getProfile().get(0).getSellerName());
//                    seller_address.setText(response.body().getProfile().get(0).getSellerAddress());
//
//                    strPhone = response.body().getProfile().get(0).getContactNo();
//                    strEmail = response.body().getProfile().get(0).getContactEmail();
//
//
//                    newPosition = new LatLng(latitude, longitude);
//
//                    if (mSupportMapFragment != null) {
//                        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
//                            @Override
//                            public void onMapReady(GoogleMap googleMap) {
//                                if (googleMap != null) {
//
//
//                                    googleMap.getUiSettings().setAllGesturesEnabled(true);
//                                    googleMap.getUiSettings().setMapToolbarEnabled(true);
//                                    googleMap.getUiSettings().setZoomControlsEnabled(true);
//
//                                    //       -> marker_latlng // MAKE THIS WHATEVER YOU WANT
//
//                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(newPosition).zoom(5.0f).build();
//                                    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//                                    googleMap.moveCamera(cameraUpdate);
//
//
//                                }
//
//                            }
//                        });
//                    }
//
//
//                    btnCallSeller.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//
//                            Intent intent = new Intent(Intent.ACTION_CALL);
//
//                            intent.setData(Uri.parse("tel:" + strPhone));
//                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                                // TODO: Consider calling
//                                //    ActivityCompat#requestPermissions
//                                // here to request the missing permissions, and then overriding
//                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                //                                          int[] grantResults)
//                                // to handle the case where the user grants the permission. See the documentation
//                                // for ActivityCompat#requestPermissions for more details.
//                                return;
//                            }
//                            getActivity().startActivity(intent);
//
//                        }
//                    });
//
//
//                    btnEmailSeller.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//
//                            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                            emailIntent.setType("text/plain");
//                            emailIntent.putExtra(Intent.EXTRA_EMAIL, strEmail);
//                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer Inquiry");
//
//                            emailIntent.setType("message/rfc822");
//
//                            try {
//                                getActivity().startActivity(Intent.createChooser(emailIntent,
//                                        "Send email using..."));
//                            } catch (android.content.ActivityNotFoundException ex) {
//                                Toast.makeText(getActivity(),
//                                        "No email clients installed.",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        }
//                    });
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SellerPublicDetailsOutputModel> call, Throwable t) {
//
//                Log.i("isUserExistError", t.getMessage().toString());
//            }
//        });
//
//    }
//

}
