package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductView extends AppCompatActivity {
ImageView imageView;
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        imageView=findViewById(R.id.productImg);
        textView=findViewById(R.id.productTitle);
        Intent i=getIntent();
        int img=i.getIntExtra("img",0);
        String msg=i.getStringExtra("name");
        imageView.setImageResource(img);
        textView.setText(msg);
    }
}