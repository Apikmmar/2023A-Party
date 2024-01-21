package com.example.a2023aparty.RequestAndFeedback.Host;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.DashboardAndHostParty.Host.HostHome;
import com.example.a2023aparty.GuestRequest;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HostViewRequest extends AppCompatActivity {

    private Button addBtn,backBtn;
    HostRequestAdapter adapter;
    RecyclerView recyclerViewD;
    ArrayList<GuestRequest> userRequest;
    DatabaseReference myRef;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_view_request);

        recyclerViewD=findViewById(R.id.recyclerViewDisplay);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(HostViewRequest.this));

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
                adapter=new HostRequestAdapter(HostViewRequest.this,userRequest);
                recyclerViewD.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HostViewRequest.this,"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });

        addBtn=findViewById(R.id.add);
        backBtn=findViewById(R.id.back);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(HostViewRequest.this,HostAddRequest.class);
                startActivity(add);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(HostViewRequest.this, HostHome.class);
                startActivity(back);
            }
        });
    }
}