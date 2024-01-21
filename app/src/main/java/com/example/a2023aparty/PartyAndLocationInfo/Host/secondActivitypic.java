package com.example.a2023aparty.PartyAndLocationInfo.Host;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2023aparty.R;

public class secondActivitypic extends AppCompatActivity {

    ImageView myImageView;
    TextView events;

    String data1;
    int myImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activitypic);

        myImageView = findViewById(R.id.myImageView);
        events = findViewById(R.id.events);


        if (getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") ) {

            data1 = getIntent().getStringExtra("data1");

            myImage = getIntent().getIntExtra("myImage", 1);
        } else {
            Toast.makeText(this, "There is no data", Toast.LENGTH_SHORT).show();
        }

        events.setText(data1);

        myImageView.setImageResource(myImage);



    }
}