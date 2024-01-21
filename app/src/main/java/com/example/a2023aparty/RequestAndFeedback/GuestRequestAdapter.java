package com.example.a2023aparty.RequestAndFeedback;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.GuestRequest;
import com.example.a2023aparty.R;
import com.example.a2023aparty.RequestAndFeedback.User.UpdateDeleteRequest;

import java.util.ArrayList;

public class GuestRequestAdapter extends RecyclerView.Adapter<GuestRequestAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<GuestRequest> guestRequest;

    public GuestRequestAdapter(Context context, ArrayList<GuestRequest> g) {
        this.context = context;
        this.guestRequest = g;
    }

    @NonNull
    @Override
    public GuestRequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewD = inflater.inflate(R.layout.guest_request, parent, false);
        return new MyViewHolder(viewD);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestRequestAdapter.MyViewHolder holder, final int position) {
        final GuestRequest guestRequest = this.guestRequest.get(position);
        holder.id_txt.setText(String.valueOf(guestRequest.getId()));
        holder.request_txt.setText(String.valueOf(guestRequest.getRequest()));

        holder.DisplayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateDeleteRequest.class);
                intent.putExtra("id", String.valueOf(guestRequest.getId()));
                intent.putExtra("request", String.valueOf(guestRequest.getRequest()));
                intent.putExtra("details", String.valueOf(guestRequest.getDetails()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return guestRequest.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, request_txt, details_txt;
        public View DisplayRequest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id);
            request_txt = itemView.findViewById(R.id.req);

            DisplayRequest = itemView;
        }
    }
}
