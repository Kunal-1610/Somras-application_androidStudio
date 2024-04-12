package com.example.somras;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText unm, pass;
    MaterialButton btn;

    Globalclass ob = new Globalclass();

    String url = ob.ip + "login.php";//"http://192.168.1.10/bca6/login.php";
    String id, name, addr, city, email, mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unm = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        btn = findViewById(R.id.loginbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = unm.getText().toString();
                String pwd = pass.getText().toString();


                if (!name.equals("") && !pass.equals("")) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
//                                    if (response.equals("Error")) {
//                                        Toast.makeText(Login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
//
//                                    } else {
//                                        search();
//                                    }
////                                    Toast.makeText(Login.this, , Toast.LENGTH_SHORT).show();
                                    if (response.trim().length() != 0) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            JSONArray result = jsonObject.getJSONArray("data1");//php key name
//


                                            for (int i = 0; i < result.length(); i++) {
                                                JSONObject jo = result.getJSONObject(0);
                                                id = jo.getString("c_id");
                                                name = jo.getString("c_name");
                                                addr = jo.getString("c_addr");
                                                city = jo.getString("c_city");
                                                email = jo.getString("c_email");
                                                mob = jo.getString("c_mob");
                                            }

                                            if (id.length() != 0) {
//                                                    Toast.makeText(Login.this, id+" "+name+" "+addr+" "+city+" "+mob+" "+email+" ", Toast.LENGTH_LONG).show();
//                                                ob.cid = id;
//                                                ob.cname = name;
//                                                ob.caddr = addr;
//                                                ob.ccity = city;
//                                                ob.cemail = email;
//                                                ob.cmob = mob;
                                                ((Globalclass) getApplication()).cname = name;
                                                ((Globalclass) getApplication()).cid = id;
                                                ((Globalclass) getApplication()).cemail = email;
                                                ((Globalclass) getApplication()).cmob = mob;
                                                ((Globalclass) getApplication()).ccity = city;
                                                ((Globalclass) getApplication()).caddr = addr;
//                                                    Toast.makeText(Login.this, ob.caddr, Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(Login.this, Menu.class);
                                                startActivity(i);



                                            } else {
                                                Toast.makeText(Login.this, "Not found", Toast.LENGTH_SHORT).show();

                                            }
                                            // }

                                        } catch (JSONException e) {
                                            Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Login.this, "" + error, Toast.LENGTH_LONG).show();
                        }

                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("username", name);
                            data.put("password", pwd);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(Login.this, "empty field", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }
}