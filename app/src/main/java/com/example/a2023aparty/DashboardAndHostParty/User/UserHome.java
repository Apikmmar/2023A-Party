package com.example.a2023aparty.DashboardAndHostParty.User;

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

import com.example.a2023aparty.DashboardAndHostParty.MainActivity;
import com.example.a2023aparty.R;
import com.example.a2023aparty.RequestAndFeedback.User.GuestFeedback;

public class UserHome extends AppCompatActivity implements SensorEventListener {

    private Button buttonBackHome, buttonRegParty, buttonLocInfo, buttonHostInfo, buttonPartyInfo, buttonFeedback;
    private TextView lightTextView;
    private SensorManager sensorManager;
    private Sensor lightSensor;

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

        lightTextView = findViewById(R.id.lightTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            lightTextView.setText("No light sensor found");
        }


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
                Intent a = new Intent(UserHome.this, GuestFeedback.class);
                startActivity(a);
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