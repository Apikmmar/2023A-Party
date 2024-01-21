package com.example.a2023aparty.RequestAndFeedback.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.User.UserHome;
import com.example.a2023aparty.R;

public class RegistrationSuccess extends AppCompatActivity {

    private Button home;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_success);

        home=findViewById(R.id.button2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(RegistrationSuccess.this, UserHome.class);
                startActivity(home);
            }
        });
    }
}