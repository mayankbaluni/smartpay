package com.smart.pay.utils;

import android.content.Context;

import com.sybase.persistence.DataVaultException;
import com.sybase.persistence.PrivateDataVault;


/**
 * @author
 */

public class DataVaultManager {

    private static String DVPassWord = "smartpay";
    private static String APP_VAULTNAME = "SMARTPAY";
    public static String COMMON_VAULTNAME = "com.smart.pay";

    public static final String KEY_NAME = "name";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_ACCESSTOKEN = "accessToken";
    public static final String KEY_USER_LOGO = "logo";
    public static final String KEY_WALLET_ID = "wallet_id";


    private static PrivateDataVault vault;
    private static DataVaultManager dataVaultManager;

    private DataVaultManager() {
    }

    public static DataVaultManager getInstance(Context context) {

        if (dataVaultManager == null) {
            dataVaultManager = new DataVaultManager();
        }

        PrivateDataVault.init(context);
        if (!PrivateDataVault.vaultExists(APP_VAULTNAME)) {
            vault = PrivateDataVault.createVault(APP_VAULTNAME, DVPassWord, "salt");
        } else {
            vault = PrivateDataVault.getVault(APP_VAULTNAME);
            checkVaultStatus();
        }
        return dataVaultManager;
    }

    public void saveName(String name) {
        checkVaultStatus();
        vault.setString(KEY_NAME, name);
        vault.lock();
    }

    public void saveUserMobile(String mobile) {
        checkVaultStatus();
        vault.setString(KEY_MOBILE, mobile);
        vault.lock();
    }

    public void saveUserEmail(String email) {
        checkVaultStatus();
        vault.setString(KEY_EMAIL, email);
        vault.lock();
    }

    public void saveUserPassword(String password) {
        checkVaultStatus();
        vault.setString(KEY_PASSWORD, password);
        vault.lock();
    }

    public void saveUserId(String userId) {
        checkVaultStatus();
        vault.setString(KEY_USER_ID, userId);
        vault.lock();
    }


    public void saveWalletId(String wallet_id) {
        checkVaultStatus();
        vault.setString(KEY_WALLET_ID, wallet_id);
        vault.lock();
    }

    public void saveUserAccessToken(String accessToken) {
        checkVaultStatus();
        vault.setString(KEY_ACCESSTOKEN, accessToken);
        vault.lock();
    }

    public void saveUserLogo(String userLogo) {
        checkVaultStatus();
        vault.setString(KEY_USER_LOGO, userLogo);
        vault.lock();
    }


    public String getVaultValue(String vaultName) {
        checkVaultStatus();

        String credential = "";
        try {
            credential = vault.getString(vaultName);
            vault.lock();

        } catch (DataVaultException e) {
            e.printStackTrace();
            return null;
        }
        return credential;
    }

    public void deleteVault() {
        try {
            if (PrivateDataVault.vaultExists(APP_VAULTNAME)) {
                PrivateDataVault.deleteVault(APP_VAULTNAME);
            }
        } catch (DataVaultException e1) {
            e1.printStackTrace();
        }
    }

    private static void checkVaultStatus() {
        if (vault.isLocked()) {
            vault.unlock(DVPassWord, "salt");
        }
    }
}