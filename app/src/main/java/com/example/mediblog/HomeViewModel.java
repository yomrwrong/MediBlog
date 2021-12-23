package com.example.mediblog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    HomeRepository repository;

    public HomeViewModel() {
        repository = new HomeRepository();
    }

    public LiveData<ArrayList<Articles>> getAllArticles(){
        return repository.getAllArticles();
    }
}
