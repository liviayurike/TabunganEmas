package com.example.tabunganemas.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabunganemas.models.Profile;

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

    @Query("SELECT * from profile limit 1")
    LiveData<Profile> getProfile() {
        return null;
    }
}
