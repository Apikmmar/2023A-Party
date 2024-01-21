package com.example.a2023aparty.PartyAndLocationInfo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a2023aparty.R;

public class detailsInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);

        Button picInfoButton = findViewById(R.id.picInfobutton);
        ImageView gpsLocationButton = findViewById(R.id.GpsLocationbutton);

        picInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        gpsLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Google Maps link in a browser
                String googleMapsLink = "https://www.google.com/maps/place/DEWAN+SERBAGUNA+UMPSA+PEKAN,"
                        + "Universiti+Malaysia+Pahang,+26600,+26600+Pekan,+Pahang/data=!4m2!3m1!1s0x31cf513fbbc08f3b:0x7a784530438d065a?utm_source=mstt_1&entry=gps&lucs=,47075915&g_ep=CAESCjExLjEwOS4xMDEYACDXggMqCSw0NzA3NTkxNUICTVk%3D";

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsLink));

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}
