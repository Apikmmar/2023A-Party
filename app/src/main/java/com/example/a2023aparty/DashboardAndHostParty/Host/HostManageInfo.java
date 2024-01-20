package com.example.a2023aparty.DashboardAndHostParty.Host;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.HostData;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostManageInfo extends AppCompatActivity {

    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    private EditText stageName, fullName, address, age, hp, username, password;
    private Button buttonBackHome, buttonGoToFavSong, buttonSubmitUpdate, buttonResetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_manage_info);

        stageName = findViewById(R.id.editstagename);
        fullName = findViewById(R.id.editfullname);
        address = findViewById(R.id.editaddress);
        age = findViewById(R.id.editage);
        hp = findViewById(R.id.edithp);
        username = findViewById(R.id.editusername);
        password = findViewById(R.id.editpass);

        buttonBackHome = findViewById(R.id.backtohomepage);
        buttonGoToFavSong = findViewById(R.id.gotofavsong);
        buttonSubmitUpdate = findViewById(R.id.updatedata);
        buttonResetData = findViewById(R.id.resetdata);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonGoToFavSong.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonSubmitUpdate.setBackgroundColor(getResources().getColor(R.color.blue));
        buttonResetData.setBackgroundColor(getResources().getColor(R.color.red));

        hostReadData();

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostManageInfo.this, HostHome.class);
                startActivity(a);
            }
        });

        buttonGoToFavSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostManageInfo.this, HostEditFavSong.class);
                startActivity(a);
            }
        });

        buttonSubmitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostUpdateData();
                Toast.makeText(HostManageInfo.this, "Host Info Updated!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostReadData();
                Toast.makeText(HostManageInfo.this, " Data Reset To Default!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hostReadData() {
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
                    String read_user_name = snapshot.child("username").getValue(String.class);
                    String read_user_password = snapshot.child("password").getValue(String.class);

                    stageName.setText(read_stage_name);
                    fullName.setText(read_full_name);
                    address.setText(read_address_name);
                    age.setText(String.valueOf(read_current_age));
                    hp.setText(String.valueOf(read_current_hp));
                    username.setText(read_user_name);
                    password.setText(read_user_password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void hostUpdateData() {
        String stage_name = stageName.getText().toString().trim();
        String full_name = fullName.getText().toString().trim();
        String address_name = address.getText().toString().trim();
        String current_age = age.getText().toString().trim();
        String current_hp = hp.getText().toString().trim();
        String user_name = username.getText().toString().trim();
        String user_password = password.getText().toString().trim();
        String user_role = "host";

        if (stage_name.isEmpty() || full_name.isEmpty() || address_name.isEmpty() ||
            current_age.isEmpty() || current_hp.isEmpty() || user_name.isEmpty() ||
            user_password.isEmpty()) {

            Toast.makeText(HostManageInfo.this, "One Column is Empty!", Toast.LENGTH_SHORT).show();
        } else {
            int ageVal = Integer.parseInt(current_age);
            int hpVal = Integer.parseInt(current_hp);

            HostData hostData = new HostData(stage_name, full_name, address_name, user_name, user_password, user_role, ageVal, hpVal);
            DatabaseReference updateHostInfo = DATABASE.getReference("host_info");

            updateHostInfo.setValue(hostData);
        }
    }

    public void onImageViewHostImage(View view) {
        String instagramUrl = "http://instagram.com/_u/apik_aa";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(instagramUrl));

        startActivity(intent);
    }
}