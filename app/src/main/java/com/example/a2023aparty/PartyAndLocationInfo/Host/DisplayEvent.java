package com.example.a2023aparty.PartyAndLocationInfo.Host;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.R;
import com.example.a2023aparty.eventInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayEvent extends AppCompatActivity {

    CustomAdapter customAdapter;
    RecyclerView recyclerViewD;
    ArrayList<eventInfo> userData;  // Change the type to ArrayList<eventInfo>
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);

        recyclerViewD = findViewById(R.id.recyclerViewDisplay);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(DisplayEvent.this));

        myRef = FirebaseDatabase.getInstance().getReference().child("detailsInfo");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData = new ArrayList<>();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    eventInfo p = item.getValue(eventInfo.class);
                    userData.add(p);
                }
                customAdapter = new CustomAdapter(DisplayEvent.this, userData);
                recyclerViewD.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DisplayEvent.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}