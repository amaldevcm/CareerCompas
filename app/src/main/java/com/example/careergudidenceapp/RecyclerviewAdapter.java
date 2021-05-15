package com.example.careergudidenceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerViewHolder> {
    List<ListItems> list;
    Context context;

    public RecyclerviewAdapter(List<ListItems> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_listitem,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(holder.pic);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).getType() == "job"){
                    Intent intent = new Intent(context,JobActivity.class);
                    intent.putExtra("Course",list.get(position).getCourse());
                    intent.putExtra("Job",list.get(position).getTitle());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

                else if(list.get(position).getType() == "book"){
                    Intent intent = new Intent(context,BookActivity.class);
                    intent.putExtra("Course",list.get(position).getCourse());
                    intent.putExtra("Book",list.get(position).getTitle());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

                else{
                    Intent intent = new Intent(context,SubjectActivity.class);
                    intent.putExtra("Course",list.get(position).getCourse());
                    intent.putExtra("subject",holder.title.getText());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView pic;
        private TextView title;
        private CardView card;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            pic = itemView.findViewById(R.id.iv_pic);
            title = itemView.findViewById(R.id.tv_title);
            card = itemView.findViewById(R.id.card);
        }
    }
}
