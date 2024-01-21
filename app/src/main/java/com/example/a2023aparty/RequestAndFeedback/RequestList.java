package com.example.a2023aparty.RequestAndFeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2023aparty.GuestRequest;
import com.example.a2023aparty.R;
import com.example.a2023aparty.RequestAndFeedback.User.GuestAddRequest;
import com.example.a2023aparty.RequestAndFeedback.User.RegistrationSuccess;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestList extends AppCompatActivity {

    private Button addBtn,doneBtn;
    GuestRequestAdapter adapter;
    RecyclerView recyclerViewD;
    ArrayList<GuestRequest> userRequest;
    DatabaseReference myRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        recyclerViewD=findViewById(R.id.recyclerViewDisplay);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(RequestList.this));

        myRef= FirebaseDatabase.getInstance().getReference().child("GuestRequest");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userRequest=new ArrayList<GuestRequest>();
                for (DataSnapshot item:snapshot.getChildren())
                {
                    GuestRequest g=item.getValue(GuestRequest.class);
                    userRequest.add(g);
                }
                adapter=new GuestRequestAdapter(RequestList.this,userRequest);
                recyclerViewD.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RequestList.this,"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });

        addBtn=findViewById(R.id.back);
        doneBtn=findViewById(R.id.done);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(RequestList.this, GuestAddRequest.class);
                startActivity(add);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent done=new Intent(RequestList.this, RegistrationSuccess.class);
                startActivity(done);
            }
        });
    }
}