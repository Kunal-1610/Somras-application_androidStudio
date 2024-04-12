package com.example.somras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Cartadaptor extends RecyclerView.Adapter<Cartadaptor.MyViewHolder> {
    int aar [];
    int cnt;
    String []icode;

    String []iname;
    String []iunit;
    String []icategory;
    String []irate;
    String []iqty;
    String[] total;
    Context context;

    public Cartadaptor(Context context, int[] aar,String[] icode, String[] iname, String[] irate, String[] iunit, String[] icategory, String[] iqty, String[] itotal,int cnt) {
        this.aar = aar;
        this.icode = icode;
        this.iname = iname;
        this.iunit = iunit;
        this.icategory = icategory;
        this.irate = irate;
        this.iqty=iqty;
        this.total=itotal;
        this.context=context;
        this.cnt=cnt;
//       Toast.makeText(context,"adaptor"+total[0], Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_detail,parent,false);
        return new Cartadaptor.MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(aar[position]);
        holder.name.setText(iname[position] );
        holder.price.setText("Price: "+irate[position]);
        holder.qty.setText("Qty: "+iqty[position]);
        holder.category.setText(icategory[position]);
        holder.total.setText("Total: "+total[position]);

    }

    @Override
    public int getItemCount() {return cnt;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,price,qty,total,category;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.iimg);
            name=itemView.findViewById(R.id.iname);
            price=itemView.findViewById(R.id.iprice);
            qty=itemView.findViewById(R.id.qty);
            category=itemView.findViewById(R.id.icategory);
            total=itemView.findViewById(R.id.itotal);


        }
    }
}
