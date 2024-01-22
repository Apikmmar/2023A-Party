package com.example.a2023aparty.PartyDetailsAndRegistration.Host;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.DashboardAndHostParty.Host.HostHome;
import com.example.a2023aparty.PartyDetailsAndRegistration.RegistrationAdapter;
import com.example.a2023aparty.R;
import com.example.a2023aparty.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GuestLists extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> List;

    DatabaseReference myRef;

    RegistrationAdapter adapter;

    Button ScanGuestQR, BackGuestList, DeleteButton;

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     * @deprecated This method has been deprecated in favor of using the
     * {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.
     * The OnBackPressedDispatcher controls how back button events are dispatched
     * to one or more {@link OnBackPressedCallback} objects.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GuestLists.this, HostHome.class));

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_lists);

        BackGuestList=findViewById(R.id.BackGuestList);
        BackGuestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestLists.this,HostHome.class));
            }
        });


        ScanGuestQR=findViewById(R.id.ScanGuestQR);
        ScanGuestQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestLists.this, ScanGuest.class));
            }
        });



        recyclerView=findViewById(R.id.recyclerView);
        myRef= FirebaseDatabase.getInstance().getReference("users");
        List = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RegistrationAdapter(this,List);

        recyclerView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    User user = dataSnapshot.getValue(User.class);
                    List.add(user);
                }

                adapter.notifyDataSetChanged();
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









    }
}