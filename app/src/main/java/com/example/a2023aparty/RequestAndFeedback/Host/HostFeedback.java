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
import com.example.a2023aparty.Feedback;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HostFeedback extends AppCompatActivity {

    private Button backBtn;
    HostFeedbackAdapter adapter;
    RecyclerView recyclerViewD;
    ArrayList<Feedback> userFeedback;
    DatabaseReference myRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_feedback);

        backBtn=findViewById(R.id.back);
        recyclerViewD=findViewById(R.id.recyclerViewDisplay);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(HostFeedback.this));

        myRef= FirebaseDatabase.getInstance().getReference().child("Feedback");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userFeedback=new ArrayList<Feedback>();
                for (DataSnapshot item:snapshot.getChildren())
                {
                    Feedback f=item.getValue(Feedback.class);
                    userFeedback.add(f);
                }
                adapter=new HostFeedbackAdapter(HostFeedback.this,userFeedback);
                recyclerViewD.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HostFeedback.this,"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(HostFeedback.this, HostHome.class);
                startActivity(back);
            }
        });
    }
}