package com.example.a2023aparty.PartyAndLocationInfo.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.User.UserHome;
import com.example.a2023aparty.R;

public class MultiLocationHome extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_location_home);

        Button detailsButton = findViewById(R.id.activityDetailsbutton);
        Button home = findViewById(R.id.button2);

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the detailsInfo activity
                Intent intent = new Intent(MultiLocationHome.this, detailsInfo.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the detailsInfo activity
                Intent intent = new Intent(MultiLocationHome.this, UserHome.class);
                startActivity(intent);
            }
        });
    }
}