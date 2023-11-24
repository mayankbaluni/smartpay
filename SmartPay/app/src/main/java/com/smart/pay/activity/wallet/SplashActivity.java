package com.smart.pay.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.pay.R;
import com.smart.pay.utils.DataVaultManager;

import org.apache.commons.lang3.StringUtils;


public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static final int SPLASH_TIMEOUT = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        setupUI();
    }


    private void setupUI() {
        String userName = DataVaultManager.getInstance(this).getVaultValue(DataVaultManager.KEY_MOBILE);
        if (!StringUtils.isEmpty(userName)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIMEOUT);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIMEOUT);
        }

    }


}
