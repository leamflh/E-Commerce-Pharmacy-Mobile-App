package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Cart extends AppCompatActivity {

    SharedPreferences preferences;
    String id;
    ArrayList<Product> rowItems;
    ListView listView;
    AdapterCart adapter;
    Integer sum=0;
    TextView textView;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        id = preferences.getString("idOrder", null);
        textView=findViewById(R.id.totalPrice);

        getJSON("http://10.0.2.2/android/getOrderline.php",id);

    }

    private void getJSON(final String urlWebService,String title) {

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

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                    String postData= URLEncoder.encode("idOrder","UTF-8")+"="+ URLEncoder.encode(title,"UTF-8");

                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

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
        Integer[] quantity = new Integer[jsonArray.length()];
        String url = "http://10.0.2.2/project/products/";

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            prices[i] = obj.getInt("price");
            names[i] = obj.getString("name");
            quantity[i] = obj.getInt("quantity");

            images[i] = obj.getString("image");
            String imgFile = url + images[i];

            picture[i] = imgFile;
            sum=obj.getInt("total");
        }

        rowItems = new ArrayList();

        for (int i = 0; i < names.length; i++) {
            Product item = new Product(names[i], picture[i], prices[i], quantity[i]);
            rowItems.add(item);
            Log.d("myTag", item.toString());
        }

        listView= findViewById(R.id.listProduct);
        adapter = new AdapterCart(this, rowItems);
        listView.setAdapter(adapter);
        textView.setText(sum.toString()+".000L.L");

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("total", sum.toString());
        editor.commit();

    }




    public void checkOut(View view) {

        Random random = new Random();
        int randomNumber = random.nextInt(1000000-65) + 65;
        Remove remove= new Remove(this);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String total = preferences.getString("total", null);
        if(total ==  null || total.length() == 0){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Message :");
            alertDialog.setMessage("No products picked");
            alertDialog.show();
        }
        else{

            BackgroundWorker worker = new BackgroundWorker(this);


            worker.execute("checkOut",id,date,total, String.valueOf(randomNumber));
            remove.execute();
        }










    }
}