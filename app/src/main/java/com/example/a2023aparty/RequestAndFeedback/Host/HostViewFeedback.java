package com.example.a2023aparty.RequestAndFeedback.Host;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.Feedback;
import com.example.a2023aparty.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HostViewFeedback extends AppCompatActivity {

    TextView nameView,expView,foodView,musicView,avgRatingView,commentView;
    Button backBtn;
    String id,Name,Exp,Food,Music,AvgRating,Comment;
    DatabaseReference myRef;
    Feedback feedback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_view_feedback);

        nameView=findViewById(R.id.name);
        expView=findViewById(R.id.exp);
        foodView=findViewById(R.id.food);
        musicView=findViewById(R.id.music);
        commentView=findViewById(R.id.comment);
        backBtn=findViewById(R.id.button1);

        myRef= FirebaseDatabase.getInstance().getReference().child("Feedback");

        getIntentData();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(HostViewFeedback.this,HostFeedback.class);
                startActivity(back);
            }
        });
    }

    void getIntentData()
    {
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("name")&&getIntent().hasExtra("exp")&&getIntent().hasExtra("food")&&getIntent().hasExtra("music")&&getIntent().hasExtra("avgRating")&&getIntent().hasExtra("comment"))
        {
            id=getIntent().getStringExtra("id");
            Name=getIntent().getStringExtra("name");
            Exp=getIntent().getStringExtra("exp");
            Food=getIntent().getStringExtra("food");
            Music=getIntent().getStringExtra("music");
            Comment=getIntent().getStringExtra("comment");

            nameView.setText(Name);
            expView.setText(Exp);
            foodView.setText(Food);
            musicView.setText(Music);
            commentView.setText(Comment);
        }
        else
        {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
}