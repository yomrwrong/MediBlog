package com.example.mediblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mediblog.databinding.ActivityLoginBinding;
import com.example.mediblog.db.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel viewModel;
    private List<User> allUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(UserViewModel.class);

        viewModel.getAllUsers().observe(this, users -> {
            allUser = new ArrayList<>();
            allUser.addAll(users);
        });


        binding.textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.textViewError.setVisibility(View.GONE);

                if (TextUtils.isEmpty(binding.editTextPhoneNo.getText().toString())) {
                    binding.textViewError.setText("Enter Valid Mobile No");
                    binding.textViewError.setVisibility(View.VISIBLE);
                } else if (binding.editTextPhoneNo.getText().length() != 10) {
                    binding.textViewError.setText("Enter Valid Mobile No");
                    binding.textViewError.setVisibility(View.VISIBLE);
                } else {

                    boolean isPresent = false;

                    for (int i = 0; i < allUser.size(); i++) {
                        if (binding.editTextPhoneNo.getText().toString().equalsIgnoreCase(allUser.get(i).getPhoneNumber())) {
                            isPresent = true;
                        }
                    }

                    if (isPresent) {
                        Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                        intent.putExtra(OtpActivity.KEY_NUMBER, binding.editTextPhoneNo.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Account Not Registered, Please register yourself with us", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, SIgnUpActivity.class));
                    }
                }
            }
        });


        binding.textViewNoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SIgnUpActivity.class));
            }
        });
    }
}