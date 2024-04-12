package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Cart extends AppCompatActivity {
     String iqty;
     int globalCnt;
     Button addmore,ordernow;
     RecyclerView cartview;
    Cartadaptor cartAdapter;
    RecyclerView.LayoutManager layoutManager;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
         globalCnt=((Globalclass)getApplication()).c;
        cartview=findViewById(R.id.cartview);
        addmore=findViewById(R.id.addmore);
        ordernow=findViewById(R.id.cartorder);
        layoutManager = new LinearLayoutManager(Cart.this, LinearLayoutManager.VERTICAL, false);
        cartview.setLayoutManager(layoutManager);


        iqty= ((Globalclass)getApplication()).pqty[globalCnt];
        carthandle();


    addmore.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Cart.this,Dashboard.class);
            startActivity(i);
        }
    });

    ordernow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Cart.this,Order.class);
            startActivity(i);
        }
    });
//
    }
    public void carthandle()
    {
        cartAdapter = new Cartadaptor(Cart.this,
                ((Globalclass)getApplication()).pimg,
                ((Globalclass)getApplication()).pid,
                ((Globalclass)getApplication()).pname,
                ((Globalclass)getApplication()).prate,
                ((Globalclass)getApplication()).punit,
                ((Globalclass)getApplication()).pcategory,
                ((Globalclass)getApplication()).pqty,
                ((Globalclass)getApplication()).pamt,
                ((Globalclass)getApplication()).c);

        cartview.setAdapter(cartAdapter);
        cartview.setHasFixedSize(true);

    }
}