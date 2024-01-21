package com.example.a2023aparty.PartyAndLocationInfo.Host;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2023aparty.R;

public class myAdapter extends RecyclerView.Adapter <myAdapter.MyViewHolder>{
    String data1[];
    int images[];
    Context context;
    public myAdapter(Context ct,String events2[], int img[]){
        context=ct;
        data1=events2;

        images=img;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);

        holder.myImage.setImageResource(images[position]);
        holder.picLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, secondActivitypic.class);
                intent.putExtra("data1",data1[position]);

                intent.putExtra("myImage",images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return images.length;
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;
        ImageView myImage;

        public View picLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1=itemView.findViewById(R.id.events);

            myImage=itemView.findViewById(R.id.myImageView);
            picLayout=itemView;
        }
    }
}