package com.example.a2023aparty.DashboardAndHostParty.Host;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2023aparty.DashboardAndHostParty.MainActivity;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostLogin extends AppCompatActivity {

    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    private EditText username, password;
    private Button buttonBackHome, buttonLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_login);

        username = findViewById(R.id.inputusername);
        password = findViewById(R.id.inputpassword);

        buttonBackHome = findViewById(R.id.backtohomepage);
        buttonLogin = findViewById(R.id.login);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonLogin.setBackgroundColor(getResources().getColor(R.color.blue));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();

                DatabaseReference checkHostData = DATABASE.getReference("host_info");

                checkHostData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(inputUsername.isEmpty() || inputPassword.isEmpty()) {
                            Toast.makeText(HostLogin.this, "Please Insert The Login Detail", Toast.LENGTH_SHORT).show();
                        } else {
                            String read_user_name = snapshot.child("username").getValue(String.class);
                            String read_user_password = snapshot.child("password").getValue(String.class);

                            if (inputUsername.equals(read_user_name) && inputPassword.equals(read_user_password)) {
                                Intent a = new Intent(HostLogin.this, HostHome.class);
                                startActivity(a);

                            } else {
                                Toast.makeText(HostLogin.this, "Login Information Invalid!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostLogin.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}