package com.example.tabunganemas.repository;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.example.tabunganemas.Dao.ProfileDao;
import com.example.tabunganemas.database.EmasDB;
import com.example.tabunganemas.models.Profile;

public class RepositoryProfile {
    private ProfileDao profileDao;
    private LiveData<Profile> profileLiveData;

    public RepositoryProfile(Application application){
        EmasDB db = EmasDb.getDatabase(application);
        profileDao = db.profileDao();
        profileLiveData = profileDao.getProfile();
    }

    LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }

    public void insert(Profile profile){
        new insertAsyncTask(profileDao).execute(profile);
    }


    private class insertAsyncTask extends AsyncTask<Profile,Void,Void> {

        private ProfileDao profileDao;

        public insertAsyncTask(ProfileDao profileDao) {
            profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insert(profiles[0]);
            return null;
        }
    }

    public void deleteAll(){
        new deleteAllAsyncTask(profileDao).execute();
    }

    private class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{

        private ProfileDao profileDao;

        public deleteAllAsyncTask(ProfileDao dao) {
            profileDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            profileDao.deleteAll();
            return null;
        }
    }

    public void update(Profile profile){
        new updateAsyncTask(profileDao).execute(profile);
    }

    private class updateAsyncTask extends AsyncTask<Profile,Void,Void>{
        private ProfileDao profileDao;

        public updateAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.update(profiles[0]);
            return null;
        }
    }
}
