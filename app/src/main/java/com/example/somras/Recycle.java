package com.example.somras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Recycle extends AppCompatActivity {

    RecyclerView recyclerView;
    Bestviewadaptor recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    String icode[] , iname[],irate[], iunit[] ,iimage[], icategory[];
    int imgid[];
    Globalclass ob = new Globalclass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView = findViewById(R.id.recyclerView);
      layoutManager = new GridLayoutManager(Recycle.this,2);
   recyclerView.setLayoutManager(layoutManager);

    //recyclerViewAdapter = new RecyclerViewAdapter(DispImg.this,arr);
//        recyclerView.setAdapter(recyclerViewAdapter);
//       recyclerView.setHasFixedSize(true);

        new FetchDataTask().execute();

    }//oncrea

    private class  FetchDataTask extends AsyncTask<Void,Void,String> {


        @Override
        protected String doInBackground(Void... voids)
        {
            try {
                String ipurl = ob.ip+"read.php";

                URL url = new URL(ipurl);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return stringBuilder.toString();
            }catch (IOException e){
                Toast.makeText(Recycle.this, "error 1"+e,Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result)
        {
            try {
                JSONArray jsonArray = new JSONArray(result);
                String arr[] = new String[jsonArray.length()];
                icode = new String[jsonArray.length()];
                iname = new String[jsonArray.length()];
                irate = new String[jsonArray.length()];
                iunit = new String[jsonArray.length()];
                iimage = new String[jsonArray.length()];
                icategory = new String[jsonArray.length()];
                imgid=new int[jsonArray.length()];
               //Toast.makeText(Recycle.this,""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                for (int i=0 ; i < jsonArray.length();i++){
                    JSONObject jsonObaject = jsonArray.getJSONObject(i);
                    String code = jsonObaject.getString("p_id");
                    String name = jsonObaject.getString("p_name");
                    String rate = jsonObaject.getString("p_rate");
                    String  unit = jsonObaject.getString("p_unit");
                    String image = jsonObaject.getString("p_img");
                    String category = jsonObaject.getString("p_category");

                    icode[i]=code;
                    iname[i]=name;
                    irate[i]=rate;
                    iunit[i]=unit;
                    iimage[i]=image;
                    icategory[i]=category;
                    int sid=getResources().getIdentifier(image,"drawable",getPackageName());
                    imgid[i]=sid;
                    arr[i]=code+"-"+name+"-"+rate+"-"+unit+ "-"+"-"+image+ "-"+"-"+category+ "-"+imgid[i];
                }
                recyclerViewAdapter=new Bestviewadaptor(Recycle.this, icode,imgid,iname,irate,iunit,icategory);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setHasFixedSize(true);
                Log.d("AdapterData", "ItemCount: " + recyclerViewAdapter.getItemCount());

            }catch (JSONException e ){
                Toast.makeText(Recycle.this, "error 2"+e,Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}   //class over