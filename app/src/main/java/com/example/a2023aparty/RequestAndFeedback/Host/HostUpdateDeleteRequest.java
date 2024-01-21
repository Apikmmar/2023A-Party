package com.example.a2023aparty.RequestAndFeedback.Host;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2023aparty.GuestRequest;
import com.example.a2023aparty.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HostUpdateDeleteRequest extends AppCompatActivity {

    private EditText nameUpdate,requestUpdate,detailsUpdate;
    private Button updateBtn,deleteBtn,backBtn;
    String id,name,request,details;
    DatabaseReference myRef;
    GuestRequest guestRequest;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_update_delete_request);

        nameUpdate=findViewById(R.id.nameUpdate);
        requestUpdate=findViewById(R.id.requestUpdate);
        detailsUpdate=findViewById(R.id.detailsUpdate);
        updateBtn=findViewById(R.id.update);
        deleteBtn=findViewById(R.id.delete);
        backBtn=findViewById(R.id.back);

        myRef= FirebaseDatabase.getInstance().getReference().child("GuestRequest");

        getIntentData();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(id).child("name").setValue(nameUpdate.getText().toString());
                myRef.child(id).child("request").setValue(requestUpdate.getText().toString());
                myRef.child(id).child("details").setValue(detailsUpdate.getText().toString());
                Toast.makeText(HostUpdateDeleteRequest.this,"Request updated",Toast.LENGTH_SHORT).show();
                Intent back=new Intent(HostUpdateDeleteRequest.this,HostViewRequest.class);
                startActivity(back);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(HostUpdateDeleteRequest.this,HostViewRequest.class);
                startActivity(back);
            }
        });
    }

    void getIntentData()
    {
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("name")&&getIntent().hasExtra("request")&&getIntent().hasExtra("details"))
        {
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            request=getIntent().getStringExtra("request");
            details=getIntent().getStringExtra("details");

            nameUpdate.setText(name);
            requestUpdate.setText(request);
            detailsUpdate.setText(details);
        }
        else
        {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete request?");
        builder.setMessage("Are you sure you want to delete this request?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myRef.child(id).removeValue();
                Toast.makeText(HostUpdateDeleteRequest.this,"Request deleted",Toast.LENGTH_SHORT).show();
                Intent back=new Intent(HostUpdateDeleteRequest.this,HostViewRequest.class);
                startActivity(back);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}