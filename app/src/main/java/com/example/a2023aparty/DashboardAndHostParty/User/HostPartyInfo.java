package com.example.a2023aparty.DashboardAndHostParty.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostPartyInfo extends AppCompatActivity {

    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    private TextView stageNameText, fullNameText, ageText, addressText, hpText, roleText;
    private Button buttonBackHome, buttonFavSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_party_info);

        stageNameText = findViewById(R.id.viewstagename);
        fullNameText = findViewById(R.id.viewfullname);
        ageText = findViewById(R.id.viewage);
        addressText = findViewById(R.id.viewaddress);
        hpText = findViewById(R.id.viewhp);
        roleText = findViewById(R.id.viewrole);

        buttonBackHome = findViewById(R.id.backtohomepage);
        buttonFavSong = findViewById(R.id.gotofavsong);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.yellowdes));
        buttonFavSong.setBackgroundColor(getResources().getColor(R.color.yellowdes));

        readDataHost();

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostPartyInfo.this, UserHome.class);
                startActivity(a);
            }
        });

        buttonFavSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostPartyInfo.this, ViewHostFavSong.class);
                startActivity(a);
            }
        });
    }

    private void readDataHost() {
        DatabaseReference readHostInfo = DATABASE.getReference("host_info");

        readHostInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String read_stage_name = snapshot.child("stageName").getValue(String.class);
                    String read_full_name = snapshot.child("fullName").getValue(String.class);
                    String read_address_name = snapshot.child("address").getValue(String.class);
                    int read_current_age = snapshot.child("age").getValue(int.class);
                    int read_current_hp = snapshot.child("hp").getValue(int.class);
                    String read_user_role = snapshot.child("role").getValue(String.class);

                    stageNameText.setText(read_stage_name.toUpperCase());
                    fullNameText.setText(read_full_name.toUpperCase());
                    addressText.setText(read_address_name.toUpperCase());
                    ageText.setText(String.valueOf(read_current_age));
                    hpText.setText(String.valueOf(read_current_hp));
                    roleText.setText(read_user_role.toUpperCase());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onImageViewHostImage(View view) {
        Toast.makeText(this, "ImageView Clicked!", Toast.LENGTH_SHORT).show();
    }
}