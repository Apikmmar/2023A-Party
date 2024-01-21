package com.example.a2023aparty.PartyAndLocationInfo.Host;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2023aparty.R;
import com.example.a2023aparty.eventInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEvent extends AppCompatActivity {

    EditText editTextevents, editTextTime, editTextLocations, editTextDate;
    Button buttonAdd,buttonView;

    DatabaseReference myRef;
    int maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        buttonAdd=(Button)findViewById(R.id.buttonAdd);
        buttonView=(Button)findViewById(R.id.buttonView);
        editTextevents=(EditText)findViewById(R.id.editTextEvents);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editTextLocations=(EditText)findViewById(R.id.editTextLocations);
        editTextTime=(EditText)findViewById(R.id.editTextTime);

        eventInfo eventInfo = new eventInfo();
        myRef= FirebaseDatabase.getInstance().getReference().child("detailsInfo");
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


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventInfo.setId(maxid+1);
                eventInfo.setEvent(String.valueOf(editTextevents.getText().toString()));
                eventInfo.setTime(String.valueOf(editTextTime.getText().toString()));
                eventInfo.setLocations(String.valueOf(editTextLocations.getText().toString()));
                eventInfo.setDate(String.valueOf(editTextDate.getText().toString()));
                myRef.push().setValue(eventInfo);
                Toast.makeText(AddEvent.this, "data inserted successfully", Toast.LENGTH_SHORT).show();


            }
        });

        buttonView=(Button)findViewById(R.id.buttonView);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddEvent.this, DisplayEvent.class);
                startActivity(intent);
            }
        });
    }
}