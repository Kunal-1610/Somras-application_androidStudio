package com.example.somras;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    RecyclerView catview, bestdealsview;
    RecyclerView.Adapter catadaptor;
    Bestviewadaptor bestdealadaptor;
    ArrayList<Integer> cat;
    String pid[], pname[], prate[], punit[], pcategory[];
    int pimg[];
    Globalclass ob = new Globalclass();

    RecyclerView.LayoutManager layoutManager;

    //int cat[]={R.drawable.beer,R.drawable.cat3,R.drawable.cat2,R.drawable.cat1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initRecyclerviewCat();
        initRecyclerviewBestdeal();
    }

    private void initRecyclerviewBestdeal() {
        bestdealsview = findViewById(R.id.bestview);
        layoutManager = new GridLayoutManager(this,2);
        bestdealsview.setLayoutManager(layoutManager);
        new FetchDataTask().execute();


//        bestdealsview.setAdapter(bestdealadaptor);
    }

    private void initRecyclerviewCat() {
        cat = new ArrayList<>();
        cat.add(R.drawable.beer);
        cat.add(R.drawable.item3);
        cat.add(R.drawable.item5);
        cat.add(R.drawable.cat3);
        String catname[]={"beer","whisky","wine","vodka"};

        catview = findViewById(R.id.catview);
        catadaptor = new Catadaptor(cat,catname);
        layoutManager = new LinearLayoutManager(Dashboard.this, LinearLayoutManager.HORIZONTAL, false);
        catview.setLayoutManager(layoutManager);
        catview.setAdapter(catadaptor);
    }


    class FetchDataTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {
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
                Toast.makeText(Dashboard.this, "error 1"+e,Toast.LENGTH_LONG).show();
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
                pid = new String[jsonArray.length()];
                pname = new String[jsonArray.length()];
                prate = new String[jsonArray.length()];
                punit = new String[jsonArray.length()];
                pimg = new int[jsonArray.length()];
                pcategory = new String[jsonArray.length()];
                //Toast.makeText(Recycle.this,""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                for (int i=0 ; i < jsonArray.length();i++){
                    JSONObject jsonObaject = jsonArray.getJSONObject(i);
                    String code = jsonObaject.getString("p_id");
                    String name = jsonObaject.getString("p_name");
                    String rate = jsonObaject.getString("p_rate");
                    String  unit = jsonObaject.getString("p_unit");
                    String image = jsonObaject.getString("p_img");
                    String category = jsonObaject.getString("p_category");

                    pid[i]=code;
                    pname[i]=name;
                    prate[i]=rate;
                    punit[i]=unit;
//                    pimg[i]=image;
                    pcategory[i]=category;
                    int sid=getResources().getIdentifier(image,"drawable",getPackageName());
                    pimg[i]=sid;
                    arr[i]=code+"-"+name+"-"+rate+"-"+unit+ "-"+"-"+image+ "-"+"-"+category+ "-"+pimg[i];
                }
                bestdealadaptor=new Bestviewadaptor(Dashboard.this, pid,pimg,pname,prate,punit,pcategory);
                bestdealsview.setAdapter(bestdealadaptor);
                bestdealsview.setHasFixedSize(true);
                Log.d("AdapterData", "ItemCount: " + bestdealadaptor.getItemCount());

            }catch (JSONException e ){
                Toast.makeText(Dashboard.this, "error 2"+e,Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    }


