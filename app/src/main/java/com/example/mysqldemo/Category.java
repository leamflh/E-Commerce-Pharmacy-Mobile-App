package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Category extends AppCompatActivity {

    ListView listView;

    ArrayList<CategoryInfo> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        listView = findViewById(R.id.listView);
        getJSON("http://10.0.2.2/android/getTypeProduct.php");

    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    String result = "";
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        result += json;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    public void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] titles = new String[jsonArray.length()];
        String[] images = new String[jsonArray.length()];
        String[] picture = new String[jsonArray.length()];
        String url = "http://10.0.2.2/android/image/";

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            titles[i] = obj.getString("category");
            images[i] = obj.getString("image");
            String imgFile = url + images[i];

            picture[i] = imgFile;
        }

        rowItems = new ArrayList();

        for (int i = 0; i < titles.length; i++) {
            CategoryInfo item = new CategoryInfo(titles[i], picture[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.listView);
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id)
            {
                String category =((TextView)view.findViewById(R.id.title)).getText().toString();

                Intent intent= new Intent(getApplicationContext(),Catalog.class);
                intent.putExtra("Category", category);
                startActivity(intent);
            }
        });
    }


    public void goToCart(View view){
        Intent intent= new Intent(this,Cart.class);
        startActivity(intent);

    }


}







