package com.example.a2023aparty.PartyDetailsAndRegistration.User;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.R;
import com.example.a2023aparty.RequestAndFeedback.User.PartyRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PartyRegistration extends AppCompatActivity {

    TextView textHaveAccount;
    Button RegisterButton;

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    private EditText Username,Email,Password,ConfirmPassword;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_registration);


        Username=findViewById(R.id.Username);
        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        ConfirmPassword=findViewById(R.id.ConfirmPassword);


        mAuth=FirebaseAuth.getInstance();
        mLoadingBar= new ProgressDialog(PartyRegistration.this);

        RegisterButton=findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });



    }

    private void checkCredentials() {
        String username=Username.getText().toString();
        String email=Email.getText().toString();
        String password=Password.getText().toString();
        String confirmpassword=ConfirmPassword.getText().toString();

        if (username.isEmpty() || username.length()<5)
        {
            showError(Username,"Username is not Valid");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(Email,"Email is not Valid");
        }
        else if (password.isEmpty() || password.length()<8)
        {
            showError(Password,"Password is not Valid");
        }
        else if (confirmpassword.isEmpty() || !confirmpassword.equals(password))
        {
            showError(ConfirmPassword,"Password does not match");
        }
        else
        {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Registration on the way");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        Toast.makeText(PartyRegistration.this, "Register Successful", Toast.LENGTH_SHORT).show();

                        mLoadingBar.dismiss();
                        Intent intent = new Intent(PartyRegistration.this, PartyRequest.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(PartyRegistration.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}