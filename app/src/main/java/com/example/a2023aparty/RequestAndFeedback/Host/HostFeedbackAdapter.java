package com.example.a2023aparty.RequestAndFeedback.Host;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.Feedback;
import com.example.a2023aparty.R;

import java.util.ArrayList;

public class HostFeedbackAdapter extends RecyclerView.Adapter<HostFeedbackAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Feedback> feedback;

    public HostFeedbackAdapter(Context context, ArrayList<Feedback> f) {
        this.context = context;
        this.feedback = f;
    }

    @NonNull
    @Override
    public HostFeedbackAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View viewD=inflater.inflate(R.layout.host_feedback,parent,false);
        return new MyViewHolder(viewD);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Feedback feedback;
        feedback = this.feedback.get(position);
        holder.id_txt.setText(String.valueOf(feedback.getId()));
        holder.name_txt.setText(String.valueOf(feedback.getName()));
        holder.exp_txt.setText(String.valueOf(feedback.getExp()));
        holder.food_txt.setText(String.valueOf(feedback.getFood()));
        holder.music_txt.setText(String.valueOf(feedback.getMusic()));

        holder.DisplayFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,HostViewFeedback.class);
                intent.putExtra("id",String.valueOf(feedback.getId()));
                intent.putExtra("id",String.valueOf(feedback.getId()));
                intent.putExtra("name",String.valueOf(feedback.getName()));
                intent.putExtra("exp",String.valueOf(feedback.getExp()));
                intent.putExtra("food",String.valueOf(feedback.getFood()));
                intent.putExtra("music",String.valueOf(feedback.getMusic()));
                intent.putExtra("avgRating",String.valueOf(feedback.getAvgRating()));
                intent.putExtra("comment",String.valueOf(feedback.getComment()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedback.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id_txt,name_txt,exp_txt,food_txt,music_txt;
        public View DisplayFeedback;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt=itemView.findViewById(R.id.id);
            name_txt=itemView.findViewById(R.id.name);
            exp_txt=itemView.findViewById(R.id.exp);
            food_txt=itemView.findViewById(R.id.food);
            music_txt=itemView.findViewById(R.id.music);
            //comment_txt=itemView.findViewById(R.id.comment);

            DisplayFeedback=itemView;
        }
    }
}
