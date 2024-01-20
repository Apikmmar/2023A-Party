package com.example.a2023aparty.DashboardAndHostParty.Host;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2023aparty.HostFavSong;
import com.example.a2023aparty.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HostEditFavSong extends AppCompatActivity {

    private Button buttonBackHome, buttonAddSong;
    private RecyclerView recycleView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_edit_fav_song);

        buttonBackHome = findViewById(R.id.backtohomepage);
        buttonAddSong = findViewById(R.id.gotoaddsong);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.lightblue));
        buttonAddSong.setBackgroundColor(getResources().getColor(R.color.blue));

        recycleView = findViewById(R.id.displayAllSong);
        recycleView.setLayoutManager(new LinearLayoutManager(HostEditFavSong.this));

        DatabaseReference listAllSong = FirebaseDatabase.getInstance().getReference().child("host_fav_song");

        listAllSong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<HostFavSong> favSongs = new ArrayList<HostFavSong>();

                for (DataSnapshot song: snapshot.getChildren()) {
                    HostFavSong songs = song.getValue(HostFavSong.class);
                    favSongs.add(songs);
                }

                HostSongAdapter songAdapter = new HostSongAdapter(HostEditFavSong.this, favSongs);
                recycleView.setAdapter(songAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HostEditFavSong.this, "Failed to retrieve data.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostEditFavSong.this, HostManageInfo.class);
                startActivity(a);
            }
        });

        buttonAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HostEditFavSong.this, HostAddFavSong.class);
                startActivity(a);
            }
        });
    }
}