package com.smart.pay.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Sandeep Londhe on 02-10-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */


@Database(entities = {Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoProduct daoProduct();

}

