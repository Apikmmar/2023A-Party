package com.example.a2023aparty.DashboardAndHostParty.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.HostFavSong;
import com.example.a2023aparty.R;

import java.util.List;

public class AllHostSong extends RecyclerView.Adapter<AllHostSong.ViewHolder> {
    private Context context;
    private List<HostFavSong> songs;

    public AllHostSong(Context context, List<HostFavSong> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public AllHostSong.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_fav_song, parent, false);
        return new AllHostSong.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllHostSong.ViewHolder holder, int position) {
        HostFavSong song = songs.get(position);
        holder.songName.setText(song.getSongName());
        holder.artistName.setText(song.getArtistName());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView songName;
        private TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.songname);
            artistName = itemView.findViewById(R.id.artistname);
        }
    }
}

