package id.putraprima.mygoldtracker.screen.profile;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.putraprima.mygoldtracker.models.Profile;
import id.putraprima.mygoldtracker.repository.RepositoryProfile;

public class ProfileViewModel extends ViewModel {

    private RepositoryProfile repositoryProfile;
    private LiveData<Profile> profileLiveData;
    private MutableLiveData<Profile> profileMutableLiveData = new MutableLiveData<>();

    public ProfileViewModel(Application application){
        super();
        repositoryProfile = new RepositoryProfile(application);
        profileLiveData = repositoryProfile.getProfileLiveData();
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }

    public void onBtnUpdateClicked(String nama,String email){
        Profile profile = profileLiveData.getValue();
        assert profile != null;
        profile.setNama(nama);
        profile.setEmail(email);
        repositoryProfile.update(profile);
        profileMutableLiveData.setValue(profile);
    }

    public void onProfileUpdated(){
        profileMutableLiveData.setValue(null);
    }

    public LiveData<Profile> profileLiveData(){
        return profileMutableLiveData;
    }
}
