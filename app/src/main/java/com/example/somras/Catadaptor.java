package com.example.somras;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Catadaptor extends RecyclerView.Adapter<Catadaptor.MyViewHolder> {

    ArrayList<Integer> cat= new ArrayList<>();
    String name[];



    Context context;
    public Catadaptor(ArrayList<Integer> cat, String[] catname) {
        this.cat = cat;
        this.name=catname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_cat,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.textView.setText(name[position]);
        Glide.with(context)
                .load(cat.get(position))
                .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return cat.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_view);
            textView = itemView.findViewById(R.id.tvcat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Viewcategory.class);
                    i.putExtra("category",name[getAdapterPosition()]);
                    context.startActivity(i);
                }
            });
        }
    }
}