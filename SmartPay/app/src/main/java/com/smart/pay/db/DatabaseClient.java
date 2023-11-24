package com.smart.pay.db;

/**
 * Created by Sandeep Londhe on 19-09-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.smart.pay.R;


public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;
    private NotificationDatabase notificationDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, mCtx.getString(R.string.app_name))
                .addMigrations(MIGRATION_LATEST)
                .fallbackToDestructiveMigration()
                .build();


        notificationDatabase = Room.databaseBuilder(mCtx, NotificationDatabase.class, "notifications").build();


    }



    private static final Migration MIGRATION_LATEST = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase db) {
            Log.i("Tag" , "Migration Started");
            //Migration logic
            Log.i("Tag" , "Migration Ended");
        }
    };

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public NotificationDatabase getNotificationDatabase() {
        return notificationDatabase;
    }

}
