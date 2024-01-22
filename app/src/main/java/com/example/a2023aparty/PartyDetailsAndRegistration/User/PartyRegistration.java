package com.example.a2023aparty.PartyDetailsAndRegistration.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.User.UserHome;
import com.example.a2023aparty.R;
import com.example.a2023aparty.RequestAndFeedback.User.PartyRequest;
import com.example.a2023aparty.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PartyRegistration extends AppCompatActivity {

    private EditText Name, Email, Phone;
    private Button BackRegisterButton, RegisterButton, AttendButton;

    // Firebase
    private DatabaseReference myRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_registration);

        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);

        BackRegisterButton=findViewById(R.id.BackRegisterButton);
        BackRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PartyRegistration.this, UserHome.class));
            }
        });


        AttendButton=findViewById(R.id.AttendButton);
        AttendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PartyRegistration.this, UserAttendConfirmation.class));
            }
        });


        RegisterButton = findViewById(R.id.RegisterButton);

        // Initialize Firebase
        myRef = FirebaseDatabase.getInstance().getReference();

        // Set click listener for the registration button
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String userName = Name.getText().toString().trim();
                String userEmail = Email.getText().toString().trim();
                String userPhone = Phone.getText().toString().trim();

                // Validate the inputs
                if (userName.isEmpty()) {
                    showError(Name,"Please Enter your name");
                }
                else if (userEmail.isEmpty() || !userEmail.contains("@"))
                {
                    showError(Email,"Email must contain '@'");
                }
                else if (userPhone.isEmpty() || userPhone.length()<10 || userPhone.length()>11)
                {
                    showError(Phone,"Enter a Phone Number");
                }

                else {
                    try {
                        int userInput = Integer.parseInt(userPhone);
                        // Handle the valid input (userInput) here

                        // Call a method to save user details to Firebase
                        saveUserDetails(userName, userEmail, userPhone);
                        startActivity(new Intent(PartyRegistration.this, PartyRequest.class));

                    } catch (NumberFormatException e) {
                        // Handle the case where the input is not a valid integer
                        showError(Phone,"Only Enter Numbers");
                    }


                }
            }
        });
    }


    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


    // Method to save user details to Firebase Realtime Database
    private void saveUserDetails(String name, String email, String phone) {
        // Create a unique key for the user
        String userId = myRef.child("users").push().getKey();

        // Create a User object with the provided details
        User user = new User(name, email, phone);

        // Save the user details to the database
        myRef.child("users").child(userId).setValue(user);

        // Display success message
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
    }
}

