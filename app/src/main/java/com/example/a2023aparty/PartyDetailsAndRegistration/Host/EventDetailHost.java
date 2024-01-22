package com.example.a2023aparty.PartyDetailsAndRegistration.Host;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.Host.HostHome;
import com.example.a2023aparty.R;

public class EventDetailHost extends AppCompatActivity {

    EditText UpdateAbout1, UpdateAbout2, UpdateTheme;

    Button UpdateEvent, BackEventHost;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail_host);

        UpdateAbout1=findViewById(R.id.UpdateAbout1);


        UpdateAbout2=findViewById(R.id.UpdateAbout2);
        UpdateTheme=findViewById(R.id.UpdateTheme);
        UpdateEvent=findViewById(R.id.UpdateEvent);

        BackEventHost=findViewById(R.id.BackEventHost);
        BackEventHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDetailHost.this, HostHome.class));
            }
        });


        UpdateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                TextView textView=findViewById(R.id.TextAbout1);
                textView.setText(UpdateAbout1.getText());

                TextView textView2=findViewById(R.id.TextAbout2);
                textView2.setText(UpdateAbout1.getText());

                TextView textView3=findViewById(R.id.TextTheme);
                textView3.setText(UpdateAbout1.getText());
            }
        });


    }
}