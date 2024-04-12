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

public class History_view extends AppCompatActivity {
    ListView lv;
    ArrayAdapter arrayAdapter;
    Globalclass ob = new Globalclass();
    String ordid;
    String pName[],pQty[],pAmt[],pCat[],msg[];


    String url = ob.ip + "historyview.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        Intent i=getIntent();
       ordid= i.getStringExtra("oid");
//        Toast.makeText(History_view.this, ""+id, Toast.LENGTH_SHORT).show();
        lv=findViewById(R.id.listHistory);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//                                Toast.makeText(History_view.this, ""+response, Toast.LENGTH_SHORT).show();
                        if (response.trim().length() != 0) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray("data1");//php key name
//
                                pName=new String[result.length()];
                                pAmt=new String[result.length()];
                                pQty=new String[result.length()];
                                pCat=new String[result.length()];
                                msg=new String[result.length()];

                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject jo = result.getJSONObject(i);

                                    pName[i] = jo.getString("p_name");
                                    pQty[i]= jo.getString("o_qty");
                                    pAmt[i] = jo.getString("o_amt");
                                    pCat[i] = jo.getString("p_category");
                                    msg[i]="\nName: "+pName[i]+" \nQuantity: "+pQty[i]+" \nAmount: "+pAmt[i]+" \nCategory: "+pCat[i];
                                }
//                                        Toast.makeText(Orderhistory.this, ""+msg, Toast.LENGTH_LONG).show();
                                arrayAdapter=new ArrayAdapter(History_view.this, android.R.layout.simple_list_item_1, msg);
                                lv.setAdapter(arrayAdapter);

                            } catch (JSONException e) {
                                Toast.makeText(History_view.this, e.toString(), Toast.LENGTH_LONG).show();
                                Log.d("error1",e.toString());
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(History_view.this, "" + error, Toast.LENGTH_LONG).show();
            }

        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id",ordid);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}
