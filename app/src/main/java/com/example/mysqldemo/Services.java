package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Services extends AppCompatActivity {
    GridView gridView;
    ArrayList<ServicesInfo> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getJSON("http://10.0.2.2/android/getServices.php");
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
        String[] names = new String[jsonArray.length()];
        String[] images = new String[jsonArray.length()];
        String[] picture = new String[jsonArray.length()];
        Integer[] prices = new Integer[jsonArray.length()];

        String url = "http://10.0.2.2/android/services/";

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            prices[i] = obj.getInt("price");
            names[i] = obj.getString("name");


            images[i] = obj.getString("image");
            String imgFile = url + images[i];

            picture[i] = imgFile;
        }

        rowItems = new ArrayList();

        for (int i = 0; i < names.length; i++) {
            ServicesInfo item = new ServicesInfo(names[i], picture[i], prices[i]);
            rowItems.add(item);

        }

        gridView = findViewById(R.id.gridView);
        AdapterServices adapter = new AdapterServices(this, rowItems);
        gridView.setAdapter(adapter);



    }
}