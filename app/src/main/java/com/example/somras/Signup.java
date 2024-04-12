package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText et_username;
    EditText et_address;
    EditText et_city;
    EditText et_email;
    EditText et_mobile;
    EditText et_password;
    EditText et_confirmpassword;
    Globalclass ob=new Globalclass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_username = findViewById(R.id.et_username);
        et_address = findViewById(R.id.et_address);
        et_city = findViewById(R.id.et_city);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mob);
        et_password = findViewById(R.id.et_pwd);
        et_confirmpassword = findViewById(R.id.et_conpwd);
    }

    public void registration(View view) {
        String username = et_username.getText().toString();
        String address = et_address.getText().toString();
        String city = et_city.getText().toString();
        String email = et_email.getText().toString();
        String mobile = et_mobile.getText().toString();
        String password = et_password.getText().toString();
//        Toast.makeText(Registration.this, username + address + city + email + mobile + password, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = ob.ip+"signup.php";//"http://192.168.122.243/bca6/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(Registration.this,response, Toast.LENGTH_SHORT).show();
                        if (response.equals("Success")) {
                            Toast.makeText(Signup.this, "Successfull added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Signup.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Signup.this, "That didn't work!" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("addr", address);
                params.put("city", city);
                params.put("pass", password);
                params.put("email", email);
                params.put("phone", mobile);

                return params;

            }
        };
        queue.add(stringRequest);
    }



}
