package com.example.a2023aparty.RequestAndFeedback.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.DashboardAndHostParty.MainActivity;
import com.example.a2023aparty.DashboardAndHostParty.User.UserHome;
import com.example.a2023aparty.Feedback;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GuestFeedback extends AppCompatActivity {

    private Button home,submit;
    private SeekBar bar1,bar2,bar3;
    private EditText name,comment;
    private ImageButton snap;
    private ImageView photo;
    private TextView tv1,tv2,tv3;
    int maxid;
    double expRating,foodRating,musicRating,avgRating;
    Feedback feedback;
    DatabaseReference myRef;
    static final int REQUEST_IMAGE_CAPTURE=1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_feedback);

        home=findViewById(R.id.button1);
        submit=findViewById(R.id.button2);
        bar1=findViewById(R.id.seekBar);
        bar2=findViewById(R.id.seekBar2);
        bar3=findViewById(R.id.seekBar3);
        tv1=findViewById(R.id.textView10);
        tv2=findViewById(R.id.textView11);
        tv3=findViewById(R.id.textView12);
        comment=findViewById(R.id.editTextText);
        name=findViewById(R.id.editTextText2);
        snap=findViewById(R.id.imageButton);
        photo=findViewById(R.id.imageView4);

        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(GuestFeedback.this,MainActivity.class);
                startActivity(home);
            }
        });

        bar1.setMax(10);
        bar2.setMax(10);
        bar3.setMax(10);

        bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv1.setText(Integer.toString(progress));
                expRating = Double.parseDouble(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv2.setText(Integer.toString(progress));
                foodRating = Double.parseDouble(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv3.setText(Integer.toString(progress));
                musicRating = Double.parseDouble(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        feedback=new Feedback();
        myRef= FirebaseDatabase.getInstance().getReference().child("Feedback");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxid=(int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Experience = tv1.getText().toString();
                String Food = tv2.getText().toString();
                String Music = tv3.getText().toString();
                String Comment = comment.getText().toString();

                if (TextUtils.isEmpty(Name)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(GuestFeedback.this, "Please insert your name.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    feedback.setId(maxid + 1);
                    feedback.setName(Name);
                    feedback.setExp(Integer.parseInt(Experience));
                    feedback.setFood(Integer.parseInt(Food));
                    feedback.setMusic(Integer.parseInt(Music));
                    //feedback.setAvgRating(avgRating);
                    feedback.setComment(Comment);

                    myRef.child(String.valueOf(maxid + 1)).setValue(feedback);
                    Toast.makeText(GuestFeedback.this, "Feedback sent!", Toast.LENGTH_SHORT).show();
                    Intent list = new Intent(GuestFeedback.this, UserHome.class);
                    startActivity(list);
                }
            }
        });
    }

    private void takePicture()
    {
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        //displayPhoto();
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
            //Uri uri = data.getData();
            //uploadImage(uri);
        }
    }
}