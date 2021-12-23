package com.example.mediblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediblog.databinding.ActivitySignUpBinding;
import com.example.mediblog.db.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SIgnUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private UserViewModel viewModel;
    private List<User> allUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(UserViewModel.class);

        viewModel.getAllUsers().observe(this, users -> {
            allUser = new ArrayList<>();
            allUser.addAll(users);
        });

        binding.textViewContinue.setOnClickListener(view -> {

            binding.textViewFirstNameError.setVisibility(View.GONE);
            binding.textViewLastNameError.setVisibility(View.GONE);
            binding.textViewPhoneNoError.setVisibility(View.GONE);
            binding.textViewEmailError.setVisibility(View.GONE);

            if (TextUtils.isEmpty(binding.editTextFirstName.getText().toString())) {
                //Error
                binding.textViewFirstNameError.setText("Required");
                binding.textViewFirstNameError.setVisibility(View.VISIBLE);
            } else {

                if (TextUtils.isEmpty(binding.editTextLastName.getText().toString())) {
                    //Error
                    binding.textViewLastNameError.setText("Required");
                    binding.textViewLastNameError.setVisibility(View.VISIBLE);
                } else {

                    if (TextUtils.isEmpty(binding.editTextPhoneNo.getText().toString())) {
                        //Error
                        binding.textViewPhoneNoError.setText("Required");
                        binding.textViewPhoneNoError.setVisibility(View.VISIBLE);
                    } else {

                        if (binding.editTextPhoneNo.getText().length() == 10) {

                            if (TextUtils.isEmpty(binding.editTextEmail.getText().toString())) {
                                //REGISTER USER
                                registerUser("NA");
                            } else {
                                //CHECK EMAIL FORMAT
                                if (isEmailValid(binding.editTextEmail.getText().toString())) {
                                    //REGISTER USER
                                    registerUser(binding.editTextEmail.getText().toString());
                                } else {
                                    //ERROR
                                    binding.textViewEmailError.setText("Invalid");
                                    binding.textViewEmailError.setVisibility(View.VISIBLE);
                                }
                            }

                        } else {
                            binding.textViewPhoneNoError.setText("Invalid");
                            binding.textViewPhoneNoError.setVisibility(View.VISIBLE);
                        }
                    }

                }

            }
        });

    }

    private void registerUser(String email) {

        boolean isPresent = false;

        for (int i = 0; i < allUser.size(); i++) {

            if (binding.editTextPhoneNo.getText().toString().equalsIgnoreCase(allUser.get(i).getPhoneNumber())) {
                isPresent = true;
            }

        }

        if (isPresent) {
            binding.textViewPhoneNoError.setText("Mobile no already registered");
            binding.textViewPhoneNoError.setVisibility(View.VISIBLE);
        } else {
            User user = new User(binding.editTextFirstName.getText().toString(), binding.editTextLastName.getText().toString(), binding.editTextPhoneNo.getText().toString(), email);
            viewModel.registerUser(user);
            Intent intent = new Intent(SIgnUpActivity.this, OtpActivity.class);
            intent.putExtra(OtpActivity.KEY_NUMBER, binding.editTextPhoneNo.getText().toString());
            startActivity(intent);
        }

    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}