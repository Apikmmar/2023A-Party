package com.example.a2023aparty.PartyAndLocationInfo.Host;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2023aparty.R;

public class picLocationInfo extends AppCompatActivity {

    String events2[];
    RecyclerView recyclerView;
    int images[] = {R.drawable.annual_dinner, R.drawable.carnival, R.drawable.treasure_hunt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_location_info);

        events2 = getResources().getStringArray(R.array.events);


        recyclerView = findViewById(R.id.recyclerView);
        myAdapter myAdapter = new myAdapter(this, events2,  images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Locate the NewEventbutton
        Button newEventButton = findViewById(R.id.NewEventbutton);

        // Set an OnClickListener for the NewEventbutton
        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the AddEvent activity
                Intent intent = new Intent(picLocationInfo.this, AddEvent.class);

                // Start the AddEvent activity
                startActivity(intent);
            }
        });
    }
}