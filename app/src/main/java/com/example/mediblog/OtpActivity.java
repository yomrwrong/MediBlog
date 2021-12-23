package com.example.mediblog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.chaos.view.PinView;

public class OtpActivity extends AppCompatActivity {

    public static final String KEY_NUMBER = "com.example.mediblog.phone.number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);

        String mobileNo = getIntent().getStringExtra(KEY_NUMBER);

        TextView textViewError = findViewById(R.id.text_view_error);
        TextView textViewMessage = findViewById(R.id.text_view_verify_your_number_desc);

        textViewMessage.setText("Mobile No provided ( " + mobileNo + " ), Default OTP Password is 1234");

        PinView pinView = findViewById(R.id.pinView);

        findViewById(R.id.text_view_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewError.setVisibility(View.GONE);
                if (TextUtils.isEmpty(pinView.getText())) {
                    textViewError.setVisibility(View.VISIBLE);
                    textViewError.setText("Enter Valid OTP");
                } else {
                    String code = pinView.getText().toString();
                    if (code.equalsIgnoreCase("1234")) {
                        startActivity(new Intent(OtpActivity.this, HomeActivity.class));
                        finishAffinity();
                    } else {
                        textViewError.setVisibility(View.VISIBLE);
                        textViewError.setText("Enter Valid OTP");
                    }
                }
            }
        });

    }
}