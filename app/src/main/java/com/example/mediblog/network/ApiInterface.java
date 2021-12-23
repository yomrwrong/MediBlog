package com.example.mediblog.network;

import com.example.mediblog.Articles;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v3/articles")
    Call<ArrayList<Articles>> getAllArticles();
}
