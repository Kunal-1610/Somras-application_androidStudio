package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Viewcategory extends AppCompatActivity {
TextView textView;
    RecyclerView  categoryView;
    RecyclerView.LayoutManager layoutManager;
    Globalclass ob = new Globalclass();
    int pimg[];
    String pid[], pname[], prate[], punit[], pcategory[],cat;
    ViewcategoryAdapt viewcategoryAdapt;


    String url = ob.ip + "Viewcategory.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcategory);
        categoryView=findViewById(R.id.ViewCategory);
        layoutManager = new GridLayoutManager(this,2);
        categoryView.setLayoutManager(layoutManager);
        textView=findViewById(R.id.viewcatname);
        Intent i=getIntent();
         cat=i.getStringExtra("category");
//        Toast.makeText(this, ""+cat, Toast.LENGTH_SHORT).show();
        textView.setText(cat.toUpperCase()+" for You");


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//                                Toast.makeText(Orderhistory.this, ""+response, Toast.LENGTH_SHORT).show();
                        if (response.trim().length() != 0) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray("data1");//php key name
//
                                pid = new String[result.length()];
                                pname = new String[result.length()];
                                prate = new String[result.length()];
                                pimg = new int[result.length()];
                                pcategory = new String[result.length()];
                                punit = new String[result.length()];
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject jo = result.getJSONObject(i);

                                    String code = jo.getString("p_id");
                                    String name = jo.getString("p_name");
                                    String rate = jo.getString("p_rate");
                                    String unit = jo.getString("p_unit");
                                    String image = jo.getString("p_img");
                                    String category = jo.getString("p_category");

                                    pid[i] = code;
                                    pname[i] = name;
                                    prate[i] = rate;
                                    punit[i] = unit;
                                    pcategory[i] = category;
                                    int sid = getResources().getIdentifier(image, "drawable", getPackageName());
                                    pimg[i] = sid;


//                                    msg[i]="\nOrder: "+oid[i]+" \nAddress: "+oaddr[i]+" \nDate: "+odate[i]+" \nTotal: "+ototal[i];
                                }
                                viewcategoryAdapt=new ViewcategoryAdapt(Viewcategory.this, pid,pimg,pname,prate,punit,pcategory);
                                categoryView.setAdapter(viewcategoryAdapt);
//                                categoryView.setHasFixedSize(true);
//                                        Toast.makeText(Orderhistory.this, ""+msg, Toast.LENGTH_LONG).show();


                            } catch (JSONException e) {
//                                Toast.makeText(Viewcategory.this, e.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("error1",e.toString());
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                  Toast.makeText(Viewcategory.this, "" + error, Toast.LENGTH_LONG).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("cat", cat.toLowerCase());
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }


}




