package com.example.a2023aparty.PartyAndLocationInfo.Host;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.R;
import com.example.a2023aparty.eventInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEvent extends AppCompatActivity {

    private EditText editTextevents, editTextTime, editTextLocations, editTextDate;
    private Button buttonAdd, buttonView;

    com.example.a2023aparty.eventInfo eventInfo;

    DatabaseReference myRef;
    int maxid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);
        editTextevents = findViewById(R.id.editTextEvents);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocations = findViewById(R.id.editTextLocations);
        editTextTime = findViewById(R.id.editTextTime);

        eventInfo = new eventInfo();
        myRef = FirebaseDatabase.getInstance().getReference().child("detailsInfo");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Use getChildrenCount() to get the number of children
                    maxid = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled if needed
            }
        });

        // AddEvent.java
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTexteventsText = editTextevents.getText().toString();
                String editTextTimeText = editTextTime.getText().toString();
                String editTextLocationsText = editTextLocations.getText().toString();
                String editTextDateText = editTextDate.getText().toString();

                if (TextUtils.isEmpty(editTexteventsText)) {
                    Toast.makeText(AddEvent.this, "Please add an event.", Toast.LENGTH_SHORT).show();
                } else {
                    // Use the existing ID for updating
                    int newId = maxid + 1;
                    eventInfo.setId(newId);
                    eventInfo.setEvent(editTexteventsText);
                    eventInfo.setTime(editTextTimeText);
                    eventInfo.setLocations(editTextLocationsText);
                    eventInfo.setDate(editTextDateText);
                    myRef.child(String.valueOf(newId)).setValue(eventInfo);
                    Toast.makeText(AddEvent.this, "Event Added!!", Toast.LENGTH_SHORT).show();
                    Intent list = new Intent(AddEvent.this, DisplayEvent.class);
                    startActivity(list);
                }
            }
        });


        buttonView = findViewById(R.id.buttonView);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEvent.this, DisplayEvent.class);
                startActivity(intent);
            }
        });
    }
}