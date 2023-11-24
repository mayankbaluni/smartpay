package com.smart.pay.activity.wallet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.smart.pay.R;
import com.smart.pay.SmartPayApplication;
import com.smart.pay.fragments.bottom.BankFragment;
import com.smart.pay.fragments.bottom.HomeFragment;
import com.smart.pay.fragments.bottom.InboxFragment;
import com.smart.pay.fragments.bottom.MallFragment;
import com.smart.pay.fragments.bottom.ScanFragment;
import com.smart.pay.utils.DataVaultManager;
import com.volcaniccoder.bottomify.BottomifyNavigationView;
import com.volcaniccoder.bottomify.OnNavigationItemChangeListener;

import static com.smart.pay.utils.DataVaultManager.KEY_MOBILE;
import static com.smart.pay.utils.DataVaultManager.KEY_NAME;

public class HomeActivity extends AppCompatActivity {

    BottomifyNavigationView bottomify_nav;

    private AccountHeader headerResult = null;
    private Drawer result = null;
    ImageView menu_icon;

    private boolean mSlideState = false;

    String strUserName, strUserMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        menu_icon = (ImageView) findViewById(R.id.menu_icon);

        strUserName = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_NAME);
        strUserMobile = DataVaultManager.getInstance(SmartPayApplication.getInstance()).getVaultValue(KEY_MOBILE);


        Fragment fragment = new HomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.mainContainer, fragment);
        fragmentTransaction.commit();


        bottomify_nav = (BottomifyNavigationView) findViewById(R.id.bottomify_nav);

        bottomify_nav.setOnNavigationItemChangedListener(new OnNavigationItemChangeListener() {
            @Override
            public void onNavigationItemChanged(BottomifyNavigationView.NavigationItem navigationItem) {

                switch (navigationItem.getPosition()) {

                    case 0:
                        Fragment fragment = new HomeFragment();

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.replace(R.id.mainContainer, fragment);
                        fragmentTransaction.commit();


                        break;

                    case 1:

                        Fragment fragment2 = new MallFragment();

                        FragmentManager fragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.addToBackStack(null);
                        fragmentTransaction2.replace(R.id.mainContainer, fragment2);
                        fragmentTransaction2.commit();


                        break;

                    case 2:

                        Fragment fragment3 = new ScanFragment();

                        FragmentManager fragmentManager3 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                        fragmentTransaction3.addToBackStack(null);
                        fragmentTransaction3.replace(R.id.mainContainer, fragment3);
                        fragmentTransaction3.commit();


                        break;

                    case 3:

                        Fragment fragment4 = new BankFragment();

                        FragmentManager fragmentManager4 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.addToBackStack(null);
                        fragmentTransaction4.replace(R.id.mainContainer, fragment4);
                        fragmentTransaction4.commit();


                        break;

                    case 4:

                        Fragment fragment5 = new InboxFragment();

                        FragmentManager fragmentManager5 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction5 = fragmentManager5.beginTransaction();
                        fragmentTransaction5.addToBackStack(null);
                        fragmentTransaction5.replace(R.id.mainContainer, fragment5);
                        fragmentTransaction5.commit();


                        break;
                }
            }
        });

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSlideState) {
                    result.closeDrawer();
                } else {
                    result.openDrawer();
                }
            }
        });


        setupDrawer(savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();
        setupActionBar();

    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private void setupDrawer(Bundle savedInstanceState) {

        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //     View footerView = vi.inflate(R.layout.footer_layout, null);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.header)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(strUserName)
                                .withEmail(strUserMobile)
                                .withIcon(R.mipmap.ic_profile)
                )
                .withTextColor(getResources().getColor(R.color.colorPrimary))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {


                        Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(profileIntent);

                        return false;
                    }
                })
                .build();


        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withSelectedItem(-1)
                //     .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withSliderBackgroundColor(getResources().getColor(R.color.white))
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                //  .withFooter(footerView)
                .addDrawerItems(

                        new PrimaryDrawerItem().withName("Accept Payments").withIcon(R.drawable.ic_payments).withIdentifier(1).withTextColor(getResources().getColor(R.color.colorAccent)).withSelectable(true),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName("My Orders").withIcon(R.drawable.ic_orders).withIdentifier(2).withTextColor(getResources().getColor(R.color.colorAccent)),
                        new PrimaryDrawerItem().withName("My Passbook").withIcon(R.drawable.ic_passbook).withIdentifier(3).withTextColor(getResources().getColor(R.color.colorAccent)),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName("Payment Settings").withIcon(R.drawable.ic_settings).withIdentifier(4).withTextColor(getResources().getColor(R.color.colorAccent)),
                        new PrimaryDrawerItem().withName("Profile Settings").withIcon(R.drawable.ic_profile_settings).withIdentifier(5).withTextColor(getResources().getColor(R.color.colorAccent)),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName("Help & Support").withIcon(R.drawable.ic_help).withIdentifier(6).withTextColor(getResources().getColor(R.color.colorAccent)),

                        new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.ic_logout).withIdentifier(7).withTextColor(getResources().getColor(R.color.colorAccent))

                ) // add the items we want to use with our Drawer


                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem
                        view.setBackgroundColor(getResources().getColor(R.color.transparent));

                        if (drawerItem != null) {
                            Intent intent = null;

                            String title = getString(R.string.app_name);

                            if (drawerItem.getIdentifier() == 1) {

                                Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                                startActivity(profileIntent);


                            } else if (drawerItem.getIdentifier() == 2) {

                                Intent i = new Intent(HomeActivity.this, MyOrdersActivity.class);
                                startActivity(i);

                            } else if (drawerItem.getIdentifier() == 3) {

                                Intent i = new Intent(HomeActivity.this, MyPassbook.class);
                                startActivity(i);


                            } else if (drawerItem.getIdentifier() == 4) {
                                Intent i = new Intent(HomeActivity.this, PaymentSettings.class);
                                startActivity(i);


                            } else if (drawerItem.getIdentifier() == 5) {

                                Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                                startActivity(i);


                            } else if (drawerItem.getIdentifier() == 6) {


                            } else if (drawerItem.getIdentifier() == 7) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.MyAlertDialogStyle);
                                builder.setTitle(R.string.app_name);
                                builder.setMessage("Are you sure to want to Logout ?");
                                builder.setIcon(R.mipmap.ic_launcher);
                                builder.setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                logoutUserRequest();
                                            }
                                        });
                                builder.setNegativeButton("No",

                                        new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.cancel();
                                            }
                                        });

                                builder.show();

                            } else if (drawerItem.getIdentifier() == 8) {

                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

    }


    @Override
    public void onBackPressed() {

        try {


            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainContainer);

            if (currentFragment instanceof HomeFragment) {
                Log.d("message", "home fragment");

                finish();

            } else {
                Log.d("message", "popping backstack");

                getSupportFragmentManager().popBackStack();
                getSupportActionBar().show();

            }
        } catch (Exception e) {
        }

    }


    private void logoutUserRequest() {

        DataVaultManager.getInstance(HomeActivity.this).saveName("");
        DataVaultManager.getInstance(HomeActivity.this).saveUserEmail("");
        DataVaultManager.getInstance(HomeActivity.this).saveUserMobile("");
        DataVaultManager.getInstance(HomeActivity.this).saveUserPassword("");
        DataVaultManager.getInstance(HomeActivity.this).saveUserId("");
        DataVaultManager.getInstance(HomeActivity.this).saveUserLogo("");
        DataVaultManager.getInstance(HomeActivity.this).saveWalletId("");

        Intent i = new Intent(HomeActivity.this, SignInActivity.class);
        startActivity(i);
        finish();
    }
}
