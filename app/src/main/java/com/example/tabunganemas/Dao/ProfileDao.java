package com.example.tabunganemas.Dao;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabunganemas.models.Profile;

@Dao
public class ProfileDao {
    @Insert
    void insert(Profile profile) {

    }

    @Update
    void update(Profile profile) {

    }

    @Query("DELETE FROM profile")
    void deleteAll() {

    }

    @Query("SELECT * from profile order by profileId desc limit 1")
    LiveData<Profile> getProfile() {
        return null;
    }

    @Query("SELECT count(*) as total from profile")
    LiveData<Integer> countProfile();
}
