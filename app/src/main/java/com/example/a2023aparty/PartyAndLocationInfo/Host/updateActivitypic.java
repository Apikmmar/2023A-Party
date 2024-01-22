package com.example.a2023aparty.PartyAndLocationInfo.Host;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateActivitypic extends AppCompatActivity {
    private EditText editTextEventsUpdate, editTextTimeUpdate, editTextDateUpdate, editTextLocationsUpdate;
    private Button buttonUpdate, buttonDelete;
    String id, events, time, date, locations;


    DatabaseReference myRef;
    @SuppressLint("MissingInflatedId")
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

        myRef = FirebaseDatabase.getInstance().getReference().child("detailsInfo");


        // updateActivitypic.java
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure id is not null and other editText fields are not empty
                if (id != null && !TextUtils.isEmpty(editTextEventsUpdate.getText()) &&
                        !TextUtils.isEmpty(editTextTimeUpdate.getText()) &&
                        !TextUtils.isEmpty(editTextDateUpdate.getText()) &&
                        !TextUtils.isEmpty(editTextLocationsUpdate.getText())) {

                    // Update the "events" field in the database using the existing ID
                    myRef.child(id).child("events").setValue(editTextEventsUpdate.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Handle success
                                    Toast.makeText(updateActivitypic.this, "Event updated", Toast.LENGTH_SHORT).show();

                                    // Continue with updating other fields if needed
                                    myRef.child(id).child("time").setValue(editTextTimeUpdate.getText().toString());
                                    myRef.child(id).child("date").setValue(editTextDateUpdate.getText().toString());
                                    myRef.child(id).child("locations").setValue(editTextLocationsUpdate.getText().toString());

                                    Intent back = new Intent(updateActivitypic.this, DisplayEvent.class);
                                    startActivity(back);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure
                                    Toast.makeText(updateActivitypic.this, "Failed to update event: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    // Handle the case where id is null or editText fields are empty
                    Toast.makeText(updateActivitypic.this, "Invalid update request", Toast.LENGTH_SHORT).show();
                }
            }
        });




        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
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
        }
        else
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + events + " ?");
        builder.setMessage("Are you sure want to delete " + events + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myRef.child(id).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Handle success
                                Toast.makeText(updateActivitypic.this, "Request deleted", Toast.LENGTH_SHORT).show();
                                Intent back = new Intent(updateActivitypic.this, DisplayEvent.class);
                                startActivity(back);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failure
                                Toast.makeText(updateActivitypic.this, "Deletion failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
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
