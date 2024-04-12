package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Viewproduct extends AppCompatActivity {
    ImageView iv;
    TextView name,price,dispcount;
    int img;
    int count=1;

    String icode,iname,iunit,icategory,irate;
     static int iqty;

    Button addtocart,increment,decrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproduct);
        iv=findViewById(R.id.pimg);
        name=findViewById(R.id.pname);
        price=findViewById(R.id.pprice);
        increment=findViewById(R.id.increment_btn);
        decrement=findViewById(R.id.decrement_btn);
        dispcount=findViewById(R.id.dispcount);

        dispcount.setText("1");


        Intent i=getIntent();
         img =i.getIntExtra("img",0);
        iv.setImageResource(img);
        name.setText(i.getStringExtra("name"));
        price.setText(i.getStringExtra("rate"));
        icode=i.getStringExtra("code");
        iname=i.getStringExtra("name");
        irate=i.getStringExtra("rate");
        iunit=i.getStringExtra("unit");
        icategory=i.getStringExtra("category");


        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=10)
                {
                    count=10;
                }
                else
                    count ++;
                dispcount.setText(""+count);
                iqty= Integer.parseInt(String.valueOf(dispcount.getText()));
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<=1)
                    count =1;
                else
                    count--;
                dispcount.setText(""+count);
                iqty= Integer.parseInt(String.valueOf(dispcount.getText()));
               // Toast.makeText(Viewproduct.this,"qty="+iqty,Toast.LENGTH_LONG).show();
            }
        });

        addtocart=findViewById(R.id.addtocart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String f=dispcount.getText().toString();
//                Toast.makeText(Viewproduct.this,"qty="+f,Toast.LENGTH_LONG).show();
                int globalCnt=((Globalclass)getApplication()).c;
                ((Globalclass)getApplication()).pimg[globalCnt]=img;
               ((Globalclass)getApplication()).pid[globalCnt]=icode;
               ((Globalclass)getApplication()).pname[globalCnt]=iname;
               ((Globalclass)getApplication()).prate[globalCnt]=irate;
               ((Globalclass)getApplication()).punit[globalCnt]=iunit;
               ((Globalclass)getApplication()).pqty[globalCnt]= dispcount.getText().toString();
               ((Globalclass)getApplication()).pcategory[globalCnt]=icategory;
               ((Globalclass)getApplication()).pamt[globalCnt]=String.valueOf((Integer.parseInt(dispcount.getText().toString())*Integer.parseInt(irate)));
              globalCnt++;
                ((Globalclass)getApplication()).c=globalCnt;

                Intent i = new Intent(Viewproduct.this, Cart.class);
                startActivity(i);
//

            }
        });
    }
}