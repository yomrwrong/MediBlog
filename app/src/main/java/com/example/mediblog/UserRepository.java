package com.example.mediblog;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mediblog.db.ProfileDao;
import com.example.mediblog.db.ProfileDb;
import com.example.mediblog.db.User;

import java.util.List;

public class UserRepository {

    private ProfileDao profileDao;

    private LiveData<List<User>> userList;

    public UserRepository(Application application) {
        ProfileDb db = ProfileDb.getInstance(application);
        profileDao = db.profileDao();
        userList = profileDao.getAllUsers();
    }

    public LiveData<List<User>> getAllProfiles(){
        return userList;
    }

    public void insertProfile(User user){
        new InsertAsyncTask(profileDao).execute(user);
    }

    public static class InsertAsyncTask extends AsyncTask<User,Void,Void> {
        private ProfileDao profileDao;

        public InsertAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            profileDao.insertNewUser(users[0]);
            return null;
        }
    }

}
