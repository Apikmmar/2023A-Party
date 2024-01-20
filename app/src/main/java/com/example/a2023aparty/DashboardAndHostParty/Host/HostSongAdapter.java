package com.example.a2023aparty.DashboardAndHostParty.Host;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.HostFavSong;
import com.example.a2023aparty.R;

import java.util.List;

public class HostSongAdapter extends RecyclerView.Adapter<HostSongAdapter.ViewHolder> {

    private Context context;
    private List<HostFavSong> songs;

    public HostSongAdapter(Context context, List<HostFavSong> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HostFavSong song = songs.get(position);
        holder.viewSongName.setText(song.getSongName());
        holder.viewArtistName.setText(song.getArtistName());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewSongName, viewArtistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewSongName = itemView.findViewById(R.id.songname);
            viewArtistName = itemView.findViewById(R.id.artistname);
        }
    }
}
