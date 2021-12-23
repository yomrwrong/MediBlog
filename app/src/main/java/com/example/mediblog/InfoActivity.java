package com.example.mediblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mediblog.databinding.ActivityInfoBinding;

public class InfoActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "com.example.mediblog.item";

    ActivityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info);

        Articles item = (Articles) getIntent().getSerializableExtra(KEY_ITEM);
        binding.setArticleItem(item);

        binding.imageViewBack.setOnClickListener(view -> finish());
    }
}