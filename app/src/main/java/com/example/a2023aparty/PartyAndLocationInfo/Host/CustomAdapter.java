package com.example.a2023aparty.PartyAndLocationInfo.Host;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.R;
import com.example.a2023aparty.eventInfo;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private final Context context;
    private final ArrayList<eventInfo> eventInfoList;

    public CustomAdapter(Context context, ArrayList<eventInfo> p) {
        this.context = context;
        this.eventInfoList = new ArrayList<>();

        for (eventInfo eventInfo : p) {
            eventInfo event = new eventInfo();
            event.setId(eventInfo.getId());  // Include the ID
            event.setEvent(eventInfo.getEvent());
            event.setTime(eventInfo.getTime());
            event.setLocations(eventInfo.getLocations());
            event.setDate(eventInfo.getDate());

            eventInfoList.add(event);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewD = inflater.inflate(R.layout.my_display, parent, false);
        return new MyViewHolder(viewD);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {
        final eventInfo eventInfo = this.eventInfoList.get(position);
        holder.id.setText(String.valueOf(eventInfo.getId()));
        holder.events.setText(eventInfo.getEvent());
        holder.time.setText(String.valueOf(eventInfo.getTime()));
        holder.locations.setText(String.valueOf(eventInfo.getLocations()));
        holder.date.setText(String.valueOf(eventInfo.getDate()));
        holder.mainDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, updateActivitypic.class);
                intent.putExtra("id",String.valueOf(eventInfo.getId()));
                intent.putExtra("events",String.valueOf(eventInfo.getEvent()));
                intent.putExtra("time",String.valueOf(eventInfo.getTime()));
                intent.putExtra("date",String.valueOf(eventInfo.getDate()));
                intent.putExtra("locations",String.valueOf(eventInfo.getLocations()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, events, time, date, locations;
        public View mainDisplay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            events = itemView.findViewById(R.id.events);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            locations = itemView.findViewById(R.id.locations);

            mainDisplay = itemView;
        }
    }
}
