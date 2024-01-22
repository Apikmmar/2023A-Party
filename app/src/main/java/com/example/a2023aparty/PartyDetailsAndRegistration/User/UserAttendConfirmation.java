package com.example.a2023aparty.PartyDetailsAndRegistration.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.R;

public class UserAttendConfirmation extends AppCompatActivity {

    Button BackAttend, AttendConfButton;

    EditText AttendName, AttendTel, AttendTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_attend_confirmation);

        BackAttend=findViewById(R.id.BackAttend);
        AttendConfButton=findViewById(R.id.AttendConfButton);

        AttendName=findViewById(R.id.AttendName);
        AttendTel=findViewById(R.id.AttendTel);
        AttendTime=findViewById(R.id.AttendTime);

        BackAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAttendConfirmation.this, PartyRegistration.class));
            }
        });

        AttendConfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = AttendName.getText().toString();
                String Telephone = AttendTel.getText().toString();
                String Time = AttendTime.getText().toString();


                if (Name.isEmpty())
                {
                    showError(AttendName,"Enter Your Name");
                }
                else if (Telephone.isEmpty() || Telephone.length()<10 || Telephone.length()>11)
                {
                    showError(AttendTel,"Not a Phone Number");
                }
                else {
                    try {
                        int userInput = Integer.parseInt(Telephone);
                        // Handle the valid input (userInput) here

                        Intent intent = new Intent(UserAttendConfirmation.this, ScanQr.class);
                        intent.putExtra("keyname", Name);
                        intent.putExtra("keytele", Telephone);
                        intent.putExtra("keytime", Time);
                        startActivity(intent);

                    } catch (NumberFormatException e) {
                        // Handle the case where the input is not a valid integer
                        showError(AttendTel,"Only Enter Numbers");
                    }


                }

            }

            private void showError(EditText input, String s) {
                input.setError(s);
                input.requestFocus();
            }
        });


    }
}