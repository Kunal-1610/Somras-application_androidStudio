package com.example.somras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE=1;

    String sms="Thanks for placing order with SOMRAS your ";
    TextView totqty ,orderid ,cid,cname ,cemail,cphoneno,totalamt;
    EditText address;
    int totamt = 0;

    String caddr,oid ;//ordered by
    String phone;

    Globalclass ob = new Globalclass();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        address=findViewById(R.id.changeAddress);
        caddr=((Globalclass)getApplication()).caddr;//ordered by
        address.setHint("Delivery address: "+caddr);

        int k;
        totamt = 0;

        for (k = 0; k < ((Globalclass) this.getApplication()).c; k++) {
            totamt += Integer.parseInt(((Globalclass) this.getApplication()).pamt[k]);
//            totamt += amt;
        }
        phone=((Globalclass)getApplication()).cmob;
        totqty = findViewById(R.id.txt_totqty);
        orderid = findViewById(R.id.txt_orderno);
        cid = findViewById(R.id.txt_c_id);
        cname =findViewById(R.id.txt_c_name);
        cemail = findViewById(R.id.txt_c_email);
        cphoneno = findViewById(R.id.txt_c_mob);
        totalamt = findViewById(R.id.txt_tot_amt);
        cid.setText("Customer Id : "+((Globalclass) getApplication()).cid);
        cname.setText("Name : "+((Globalclass) getApplication()).cname);
        cphoneno.setText("Mobil NO : "+((Globalclass) getApplication()).cmob);
        cemail.setText("Email : "+((Globalclass) getApplication()).cemail);
        totqty.setText("Total No Of Product : "+((Globalclass) getApplication()).c);


    }

    public void order1(View view)
    {

        String cid = ((Globalclass)getApplication()).cid;//ordered by


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = ob.ip + "ordersave.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //     Toast.makeText(getBaseContext(),"Run ok"+response+queue.toString(),Toast.LENGTH_LONG).show();
                        String x=response.substring(0,7);
                        oid=response.substring(7);


                        if (x.equals("Success"))
                        {
                            sendSMS();
//                            Toast.makeText(Order.this, oid, Toast.LENGTH_SHORT).show();
                            ((Globalclass)getApplication()).c=0;
                            Toast.makeText(Order.this, "Successfull added "+response, Toast.LENGTH_LONG).show();
                            Intent i=new Intent(Order.this,Dashboard.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Order.this, "error"+response, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Order.this, "That didn't work!" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {


            protected Map<String, String> getParams()
            {
                try {
                    Map<String, String> paramV = new HashMap<>();


                    //*****************************

                    JSONArray jino = new JSONArray(((Globalclass)getApplication()).pid);
                    JSONArray jrate = new JSONArray(((Globalclass)getApplication()).prate);
                    JSONArray jqty = new JSONArray(((Globalclass)getApplication()).pqty);
//                    JSONArray  = new JSONArray(((Globalclass)getApplication()).pqty);
                    JSONArray jamt = new JSONArray(((Globalclass)getApplication()).pamt);

                    paramV.put("id", cid);
                    if(address.getText().toString().length()==0)
                    {
                    paramV.put("addr", String.valueOf(caddr));
                    }
                    else{
                        paramV.put("addr",address.getText().toString());
                    }
                    paramV.put("totamt", String.valueOf(totamt));
                    //loop
                    paramV.put("jino", jino.toString());
                    paramV.put("jrate", jrate.toString());
                    paramV.put("jqty", jqty.toString());
                    paramV.put("jamt", jamt.toString());

                    address.setHint("Delivery address : "+caddr.toString());


                    return paramV;
                } catch (JSONException e)
                {
                    Log.e("jsonerror", e.getMessage());
                    return  null;
                }
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 15000; // 15 seconds
        RetryPolicy policy = new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        );
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
//
    }//orrder1 puru save order

    private void sendSMS() {
//        Toast.makeText(Order.this, "ok massege", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+totamt, Toast.LENGTH_SHORT).show();
        sms+=" \n order no is "+oid+" \n total amt is "+totamt+" you can complete your payment by cash only";
        String phoneNumber = phone;
//        String message = sms;
        if (checkPermission()) {
            //sendSMS();
        } else
        {
            requestPermission();
        }

        if (phoneNumber.isEmpty() || sms.isEmpty())
        {
            return;
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, sms, null, null);

            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS"+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

    private boolean checkPermission()
    {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMS();
            } else {
                Toast.makeText(this, "Permission denied. Unable to send SMS.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

