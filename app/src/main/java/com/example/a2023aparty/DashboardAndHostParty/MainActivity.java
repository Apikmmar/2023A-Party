package com.example.a2023aparty.DashboardAndHostParty;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.Host.HostLogin;
import com.example.a2023aparty.DashboardAndHostParty.User.UserHome;
import com.example.a2023aparty.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button buttonUserHome;
    private TextView lightTextView;
    private SensorManager sensorManager;
    private Sensor lightSensor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUserHome = findViewById(R.id.gotouserhome);
        buttonUserHome.setBackgroundColor(getResources().getColor(R.color.yellowdes));

        lightTextView = findViewById(R.id.lightTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            lightTextView.setText("No light sensor found");
        }

        buttonUserHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, UserHome.class);
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

    public void onImageViewHostPage(View view) {
        Intent a = new Intent(MainActivity.this, HostLogin.class);
        startActivity(a);
    }
}
