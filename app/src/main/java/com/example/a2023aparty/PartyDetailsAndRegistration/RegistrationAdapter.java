package com.example.a2023aparty.PartyDetailsAndRegistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.R;
import com.example.a2023aparty.User;

import java.util.ArrayList;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> List;

    public RegistrationAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        List = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userlistholder,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = List.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.GuestName);
            email = itemView.findViewById(R.id.GuestEmail);
            phone = itemView.findViewById(R.id.GuestTelephone);
        }
    }
}
