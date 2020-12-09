package id.putraprima.mygoldtracker.screen.home;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.putraprima.mygoldtracker.models.Profile;
import id.putraprima.mygoldtracker.repository.RepositoryProfile;

import static id.putraprima.mygoldtracker.BR.profile;

public class HomeViewModel extends ViewModel {
    private RepositoryProfile repositoryProfile;
    private LiveData<Profile> profileLiveData;

    public HomeViewModel(Application application){
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