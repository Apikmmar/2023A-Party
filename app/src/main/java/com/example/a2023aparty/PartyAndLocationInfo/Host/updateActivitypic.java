package com.example.a2023aparty.PartyAndLocationInfo.Host;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2023aparty.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateActivitypic extends AppCompatActivity {
    EditText editTextEventsUpdate, editTextTimeUpdate, editTextDateUpdate, editTextLocationsUpdate;
    Button buttonUpdate, buttonDelete;
    String id, events, time, date, locations;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activitypic);

        editTextEventsUpdate = findViewById(R.id.editTextEventsUpdate);
        editTextTimeUpdate = findViewById(R.id.editTextTimeUpdate);
        editTextDateUpdate = findViewById(R.id.editTextDateUpdate);
        editTextLocationsUpdate = findViewById(R.id.editTextLocationsUpdate);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        getIntentData();

        myRef = FirebaseDatabase.getInstance().getReference().child("detailsinfo");


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change these lines to set values as strings
                myRef.child(id).child("events").setValue(editTextEventsUpdate.getText().toString());
                myRef.child(id).child("time").setValue(editTextTimeUpdate.getText().toString());
                myRef.child(id).child("date").setValue(editTextDateUpdate.getText().toString());
                myRef.child(id).child("locations").setValue(editTextLocationsUpdate.getText().toString());

            }
        });

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("events") && getIntent().hasExtra("time")
                && getIntent().hasExtra("date") && getIntent().hasExtra("locations")) {
            id = getIntent().getStringExtra("id");
            events = getIntent().getStringExtra("events");
            time = getIntent().getStringExtra("time");
            date = getIntent().getStringExtra("date");
            locations = getIntent().getStringExtra("locations");

            editTextEventsUpdate.setText(events);
            editTextTimeUpdate.setText(time);
            editTextDateUpdate.setText(date);
            editTextLocationsUpdate.setText(locations);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + events + " ?");
        builder.setMessage("Are you sure want to delete" + events + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myRef.child(id).removeValue();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle No button click if needed
            }
        });
        builder.create().show();
    }
}
