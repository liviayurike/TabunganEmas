package com.example.tabunganemas.screens.profile;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tabunganemas.models.Profile;
import com.example.tabunganemas.repository.RepositoryProfile;

public class ProfileViewModel extends ViewModel {

    private RepositoryProfile repositoryProfile;
    private LiveData<Profile> profileLiveData;

    public ProfileViewModel(Application application){
        super();
        repositoryProfile = new RepositoryProfile(application);
        profileLiveData = repositoryProfile.getProfileLiveData();
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }
}
