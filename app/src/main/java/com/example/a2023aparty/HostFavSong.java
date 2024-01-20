package com.example.a2023aparty;

import com.google.firebase.database.FirebaseDatabase;

public class HostFavSong {
    private String songID, songName, artistName;

    public HostFavSong(String songName, String artistName) {
        this.songID = FirebaseDatabase.getInstance().getReference().child("host_fav_song").push().getKey();;
        this.songName = songName;
        this.artistName = artistName;
    }

    public HostFavSong() {
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
