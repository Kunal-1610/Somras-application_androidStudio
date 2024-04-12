package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.Map;

public class Orderhistory extends AppCompatActivity {
    ListView lv;
    ArrayAdapter arrayAdapter;
    Globalclass ob = new Globalclass();
    String oid[],ototal[],oaddr[],odate[],msg[];

    String url = ob.ip + "history.php";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        lv=findViewById(R.id.orderHistory);

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
                                        msg=new String[result.length()];
                                        oid=new String[result.length()];
                                        oaddr=new String[result.length()];
                                        odate=new String[result.length()];
                                        ototal=new String[result.length()];
                                        for (int i = 0; i < result.length(); i++) {
                                            JSONObject jo = result.getJSONObject(i);

                                            oid[i] = jo.getString("o_id");
                                            oaddr[i]= jo.getString("o_add");
                                            ototal[i] = jo.getString("o_grandtotal");
                                            odate[i] = jo.getString("o_date");
                                            msg[i]="\nOrder: "+oid[i]+" \nAddress: "+oaddr[i]+" \nDate: "+odate[i]+" \nTotal: "+ototal[i];
                                        }
//                                        Toast.makeText(Orderhistory.this, ""+msg, Toast.LENGTH_LONG).show();
                                        arrayAdapter=new ArrayAdapter(Orderhistory.this, android.R.layout.simple_list_item_1, msg);
                                        lv.setAdapter(arrayAdapter);
                                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                                Toast.makeText(Orderhistory.this, ""+oid[position], Toast.LENGTH_SHORT).show();
                                                Intent i=new Intent(Orderhistory.this,History_view.class);
                                                i.putExtra("oid", oid[position]);
                                                startActivity(i);
                                            }
                                        });


                                    } catch (JSONException e) {
                                        Toast.makeText(Orderhistory.this, e.toString(), Toast.LENGTH_SHORT).show();
                                        Log.d("error1",e.toString());
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Orderhistory.this, "" + error, Toast.LENGTH_LONG).show();
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("id", ((Globalclass)getApplication()).cid);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }


        }


