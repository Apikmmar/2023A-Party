package com.example.a2023aparty.RequestAndFeedback.Host;

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

import com.example.a2023aparty.GuestRequest;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostAddRequest extends AppCompatActivity {

    private Button back,submit;
    private EditText name,request,details;
    int maxid;
    GuestRequest guestRequest;
    DatabaseReference myRef;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_add_request);

        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        name=findViewById(R.id.name);
        request = findViewById(R.id.request);
        details = findViewById(R.id.details);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(HostAddRequest.this, HostViewRequest.class);
                startActivity(back);
            }
        });

        guestRequest=new GuestRequest();
        myRef= FirebaseDatabase.getInstance().getReference().child("GuestRequest");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxid=(int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Request = request.getText().toString();
                String Details = details.getText().toString();

                if (TextUtils.isEmpty(Request))
                {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(HostAddRequest.this, "Please add a request.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // else call the method to add
                    // data to our database.
                    guestRequest.setId(maxid + 1);
                    guestRequest.setName(Name);
                    guestRequest.setRequest(Request);
                    guestRequest.setDetails(Details);
                    myRef.child(String.valueOf(maxid + 1)).setValue(guestRequest);
                    Toast.makeText(HostAddRequest.this, "Request sent!", Toast.LENGTH_SHORT).show();
                    Intent list = new Intent(HostAddRequest.this, HostViewRequest.class);
                    startActivity(list);
                }
            }
        });
    }
}