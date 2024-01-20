package com.example.a2023aparty.DashboardAndHostParty.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2023aparty.DashboardAndHostParty.MainActivity;
import com.example.a2023aparty.R;

public class UserHome extends AppCompatActivity {

    private Button buttonBackHome, buttonRegParty, buttonLocInfo, buttonHostInfo, buttonPartyInfo, buttonFeedback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        buttonBackHome = findViewById(R.id.backtohomepage);
        buttonRegParty = findViewById(R.id.attendParty);
        buttonLocInfo = findViewById(R.id.locationInfo);
        buttonHostInfo = findViewById(R.id.hostPartyInfo);
        buttonPartyInfo = findViewById(R.id.partyInfo);
        buttonFeedback = findViewById(R.id.feedbackReview);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.yellowdes));
        buttonRegParty.setBackgroundColor(getResources().getColor(R.color.yellowdes));
        buttonLocInfo.setBackgroundColor(getResources().getColor(R.color.yellowdes));
        buttonHostInfo.setBackgroundColor(getResources().getColor(R.color.yellowdes));
        buttonPartyInfo.setBackgroundColor(getResources().getColor(R.color.yellowdes));
        buttonFeedback.setBackgroundColor(getResources().getColor(R.color.yellowdes));

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UserHome.this, MainActivity.class);
                startActivity(a);
            }
        });

        buttonRegParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UserHome.this, MainActivity.class);
                startActivity(a);
            }
        });

        buttonLocInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UserHome.this, MainActivity.class);
                startActivity(a);
            }
        });

        buttonHostInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UserHome.this, HostPartyInfo.class);
                startActivity(a);
            }
        });

        buttonPartyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UserHome.this, MainActivity.class);
                startActivity(a);
            }
        });

        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UserHome.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}