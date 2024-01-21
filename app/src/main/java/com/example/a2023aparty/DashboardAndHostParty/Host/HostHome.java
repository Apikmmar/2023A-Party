package com.example.a2023aparty.DashboardAndHostParty.Host;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2023aparty.PartyAndLocationInfo.Host.picLocationInfo;
import com.example.a2023aparty.R;
import com.example.a2023aparty.RequestAndFeedback.Host.HostFeedback;
import com.example.a2023aparty.RequestAndFeedback.Host.HostViewRequest;

public class HostHome extends AppCompatActivity implements SensorEventListener {

    private Button buttonMyProfile, buttonPartyInfo, buttonPartyFeedback, buttonListGuest, buttonPartyRequest, buttonLogout;
    private TextView lightTextView;
    private SensorManager sensorManager;
    private Sensor lightSensor;

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

        lightTextView = findViewById(R.id.lightTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            lightTextView.setText("No light sensor found");
        }

        buttonMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, HostManageInfo.class);
                startActivity(a);
            }
        });

        buttonPartyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, picLocationInfo.class);
                startActivity(a);
            }
        });

        buttonPartyFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, HostFeedback.class);
                startActivity(a);
            }
        });

        buttonListGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, HostManageInfo.class);
                startActivity(a);
            }
        });

        buttonPartyRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostHome.this, HostViewRequest.class);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightValue = event.values[0];

            lightTextView.setText("Light Intensity: " + lightValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}