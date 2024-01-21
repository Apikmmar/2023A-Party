package com.example.a2023aparty.PartyDetailsAndRegistration.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.User.UserHome;
import com.example.a2023aparty.R;

public class EventDetail extends AppCompatActivity {

    Button BackEvent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        BackEvent=findViewById(R.id.BackEvent);


        BackEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetail.this, UserHome.class));
            }
        });



    }
}