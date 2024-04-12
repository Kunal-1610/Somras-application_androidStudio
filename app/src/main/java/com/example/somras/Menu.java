package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        welcome=findViewById(R.id.welcometxt);
        welcome.setText("Welcome  "+((Globalclass) this.getApplication()).cname);
    }

    public void ordernow(View view) {
        Intent i=new Intent(Menu.this, Dashboard.class);
        startActivity(i);
    }
    public void checkHistory(View view) {
        Intent i=new Intent(Menu.this, Orderhistory.class);
        startActivity(i);
    }
    public void logout(View view) {
        ((Globalclass) getApplication()).c = 0;
        Intent i=new Intent(Menu.this, Intro.class);
        startActivity(i);
    }

}