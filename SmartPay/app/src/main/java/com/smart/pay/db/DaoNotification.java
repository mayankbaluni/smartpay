package com.smart.pay.db;


import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoNotification {

    @Query("SELECT * FROM notification")
    List<Notification> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Notification notification);

    @Delete
    void delete(Notification notification);

    @Update
    void update(Notification notification);


    @Query("DELETE FROM Notification")
    void delete();

    @Query("SELECT COUNT(id) FROM notification")
    int getNumberOfRows();

}

