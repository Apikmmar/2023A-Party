package com.example.a2023aparty.DashboardAndHostParty.Host;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2023aparty.R;

public class HostHome extends AppCompatActivity {

    private Button buttonMyProfile, buttonPartyInfo, buttonPartyFeedback, buttonListGuest, buttonPartyRequest, buttonLogout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_home);


        buttonMyProfile = findViewById(R.id.managehostprofile);
        buttonPartyInfo = findViewById(R.id.managepartyinfo);
        buttonPartyFeedback = findViewById(R.id.managepartyfeedback);
        buttonListGuest = findViewById(R.id.managelistofguest);
        buttonPartyRequest = findViewById(R.id.managepartyrequest);
        buttonLogout = findViewById(R.id.backtologin);


        buttonMyProfile.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonPartyInfo.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonPartyFeedback.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonListGuest.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonPartyRequest.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonLogout.setBackgroundColor(getResources().getColor(R.color.lightblue));

        buttonMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, HostManageInfo.class);
                startActivity(a);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, HostLogin.class);
                startActivity(a);
                Toast.makeText(HostHome.this, "You Successfully Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}