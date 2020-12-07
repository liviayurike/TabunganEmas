package com.example.tabunganemas.screens.portofolio;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tabunganemas.models.Profile;
import com.example.tabunganemas.repository.RepositoryProfile;

public class PortofolioViewModel extends ViewModel {
    private RepositoryProfile repositoryProfile;
    private LiveData<Profile> profileLiveData;

    public PortfolioViewModel(Application application){
        super();
        repositoryProfile = new RepositoryProfile(application);
        profileLiveData = repositoryProfile.getProfileLiveData();
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }

    public void insert(Profile profile){
        repositoryProfile.insert(profile);
    }

    public void update(Profile profile){
        repositoryProfile.update(profile);
    }
}
