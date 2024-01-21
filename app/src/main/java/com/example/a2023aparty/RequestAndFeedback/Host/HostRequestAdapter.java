package com.example.a2023aparty.RequestAndFeedback.Host;

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

import java.util.ArrayList;

public class HostRequestAdapter extends RecyclerView.Adapter<HostRequestAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<GuestRequest> guestRequest;

    public HostRequestAdapter(Context context, ArrayList<GuestRequest> g) {
        this.context = context;
        this.guestRequest = g;
    }

    @NonNull
    @Override
    public HostRequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View viewD=inflater.inflate(R.layout.host_request,parent,false);
        return new MyViewHolder(viewD);
    }

    @Override
    public void onBindViewHolder(@NonNull HostRequestAdapter.MyViewHolder holder, final int position) {
        final GuestRequest guestRequest=this.guestRequest.get(position);
        holder.id_txt.setText(String.valueOf(guestRequest.getId()));
        holder.request_txt.setText(String.valueOf(guestRequest.getRequest()));
        holder.name_txt.setText(guestRequest.getName());

        holder.DisplayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,HostUpdateDeleteRequest.class);
                intent.putExtra("id",String.valueOf(guestRequest.getId()));
                intent.putExtra("name",String.valueOf(guestRequest.getName()));
                intent.putExtra("request",String.valueOf(guestRequest.getRequest()));
                intent.putExtra("details",String.valueOf(guestRequest.getDetails()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return guestRequest.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id_txt,request_txt,name_txt;
        public View DisplayRequest;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt=itemView.findViewById(R.id.id);
            request_txt=itemView.findViewById(R.id.request);
            name_txt=itemView.findViewById(R.id.name);

            DisplayRequest=itemView;
        }
    }
}
