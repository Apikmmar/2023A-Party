package com.example.a2023aparty.DashboardAndHostParty.User;

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

public class ViewHostFavSong extends AppCompatActivity {

    private Button buttonBackHome;
    private RecyclerView recycleView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_host_fav_song);

        buttonBackHome = findViewById(R.id.backtohomepage);

        buttonBackHome.setBackgroundColor(getResources().getColor(R.color.yellowdes));

        recycleView = findViewById(R.id.displayAllSong);
        recycleView.setLayoutManager(new LinearLayoutManager(ViewHostFavSong.this));

        DatabaseReference listAllSong = FirebaseDatabase.getInstance().getReference().child("host_fav_song");

        listAllSong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<HostFavSong> favSongs = new ArrayList<HostFavSong>();

                for (DataSnapshot song : snapshot.getChildren()) {
                    HostFavSong songs = song.getValue(HostFavSong.class);
                    favSongs.add(songs);
                }

                AllHostSong allSong = new AllHostSong(ViewHostFavSong.this, favSongs);
                recycleView.setAdapter(allSong);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewHostFavSong.this, "Failed to retrieve data.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ViewHostFavSong.this, HostPartyInfo.class);
                startActivity(a);
            }
        });
    }
}