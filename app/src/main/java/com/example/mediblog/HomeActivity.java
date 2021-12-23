package com.example.mediblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_list);

        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        ArrayList<Articles> articleList = new ArrayList<>();
        ArticleAdapter adapter = new ArticleAdapter(articleList);
        recyclerView.setAdapter(adapter);

        viewModel.getAllArticles().observe(this, articles -> {
            articleList.clear();
            articleList.addAll(articles);
            adapter.notifyDataSetChanged();
        });

        adapter.setListener(item -> {
            //MOVE TO OTHER ACTIVITY TO VIEW INFO
            Intent intent = new Intent(HomeActivity.this, InfoActivity.class);
            intent.putExtra(InfoActivity.KEY_ITEM, item);
            startActivity(intent);
        });
    }
}