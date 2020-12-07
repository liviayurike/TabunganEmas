package com.example.tabunganemas.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tabunganemas.Dao.ProfileDao;
import com.example.tabunganemas.models.Profile;

@Database(entities = {Profile.class},version = 1,exportSchema = false)
public abstract class GoldAppDb extends RoomDatabase {
    public abstract ProfileDao profileDao();
    private static GoldAppDb INSTANCE;

    public static GoldAppDb getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (GoldAppDb.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),GoldAppDb.class,"gold_app_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
