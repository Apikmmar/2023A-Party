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

import com.example.a2023aparty.HostFavSong;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostAddFavSong extends AppCompatActivity {

    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    private EditText inputSong, inputArtist;
    private Button buttonBackHome, buttonNewSong, buttonReset;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_add_fav_song);

        inputSong = findViewById(R.id.inputnewsong);
        inputArtist = findViewById(R.id.inputartist);

        buttonBackHome = findViewById(R.id.backtohomepage);
        buttonNewSong = findViewById(R.id.addnewsong);
        buttonReset = findViewById(R.id.resetdata);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonNewSong.setBackgroundColor(getResources().getColor(R.color.blue));
        buttonReset.setBackgroundColor(getResources().getColor(R.color.red));

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostAddFavSong.this, HostEditFavSong.class);
                startActivity(a);
            }
        });

        buttonNewSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostAddFavSong();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputSong.setText("");
                inputArtist.setText("");
            }
        });
    }

    private void hostAddFavSong() {
        DatabaseReference createHostSong = DATABASE.getReference("host_fav_song");

        String song_name = inputSong.getText().toString().trim();
        String artist_name = inputArtist.getText().toString().trim();

        if (song_name.isEmpty()) {
            Toast.makeText(HostAddFavSong.this, "No Song Added", Toast.LENGTH_SHORT).show();
        } else {
            createHostSong.orderByChild("song_name").equalTo(song_name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HostFavSong hostFavSong = new HostFavSong(song_name, artist_name);
                    DatabaseReference inputFavSong = createHostSong.push();

                    inputFavSong.setValue(hostFavSong);

                    Toast.makeText(HostAddFavSong.this, "New Song Added!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}