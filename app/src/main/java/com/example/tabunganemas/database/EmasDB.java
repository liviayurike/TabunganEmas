package com.example.tabunganemas.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;

import com.example.tabunganemas.Dao.ProfileDao;
import com.example.tabunganemas.models.Profile;

@Database(entities = {Profile.class},version = 1,exportSchema = false)

public class EmasDB extends RoomDatabase {
    public abstract ProfileDao profileDao();
    private static EmasDB INSTANCE;
    private static Context ctx;

    public static EmasDB getDatabase(Context context){
        if(INSTANCE == null){
            ctx = context;
            synchronized (EmasDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),EmasDB.class,"emas_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(dbCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
        public void onCreate(@NotNull SupportSQLiteDatabase db) {

            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    getDatabase(ctx).profileDao().insert(new Profile("Putra Prima A","putraprima@gmail.com",""));
                }
            });
        }
    };
}
