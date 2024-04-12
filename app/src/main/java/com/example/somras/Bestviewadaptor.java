package com.example.somras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.somras.R;

public class Bestviewadaptor extends RecyclerView.Adapter <Bestviewadaptor.myViewHolder>
{
    int aar [];
    String []icode;
    String []iname;
    String []iunit;
    String []icategory;
    String []irate;
    Context context;
    public Bestviewadaptor(Context context , String[] icode, int[] aar, String[] iname, String[] irate, String[] iunit, String[] icategory)
    {
        this.aar = aar;
        this.icode = icode;
        this.iname = iname;
        this.irate = irate;
        this.iunit = iunit;
        this.icategory = icategory;

        this.context =  context;
//        Toast.makeText(context, icode[1], Toast.LENGTH_SHORT).show();

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_bestdeal,parent,false);

        myViewHolder myViewHolder = new myViewHolder(view);
        return (myViewHolder);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position)
    {
        holder.imageView.setImageResource(aar[position]);
        holder.textView1.setText(iname[position] );
        holder.textView2.append(irate[position]);


    }

    @Override
    public int getItemCount() {
        return aar.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView1,textView2,textView3;

        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.bestdealImg);
            textView1 = itemView.findViewById(R.id.bestdealtitle);
            textView2 = itemView.findViewById(R.id.bestdealprice);
//            textView3 = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, Viewproduct.class);
                    i.putExtra("img",aar[getAdapterPosition()]);
                    i.putExtra("code",icode[getAdapterPosition()]);
                    i.putExtra("name",iname[getAdapterPosition()]);
                    i.putExtra("rate",irate[getAdapterPosition()]);
                    i.putExtra("unit",iunit[getAdapterPosition()]);
                    i.putExtra("category",icategory[getAdapterPosition()]);

                    context.startActivity(i);
//                    ((Activity) context).finish();
                }
            });

        }
    }

}
