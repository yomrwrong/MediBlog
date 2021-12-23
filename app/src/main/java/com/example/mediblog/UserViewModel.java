package com.example.mediblog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mediblog.db.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> userList;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        userList = repository.getAllProfiles();
    }

    public void registerUser(User user){
        repository.insertProfile(user);
    }

    public LiveData<List<User>> getAllUsers(){
        return userList;
    }
}
