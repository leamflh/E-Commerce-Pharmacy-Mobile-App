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

public class Catalog extends AppCompatActivity {
    GridView gridView;
    ArrayList<Product> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        Intent intent = getIntent();

        String title = intent.getStringExtra("Category");
        getJSON("http://10.0.2.2/android/getProducts.php",title);

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

                    String postData= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(title,"UTF-8");

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
            }

            rowItems = new ArrayList();

            for (int i = 0; i < names.length; i++) {
                Product item = new Product(names[i], picture[i], prices[i], quantity[i]);
                rowItems.add(item);
                Log.d("myTag", item.toString());
            }

            gridView = findViewById(R.id.gridView);
            AdapterCatalog adapter = new AdapterCatalog(this, rowItems);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(json.equals("No items")){
                        Intent intent = new Intent(getApplicationContext(), productInfo.class);
                        startActivity(intent);
                    }
                    Product product = (Product) gridView.getAdapter().getItem(position);
                    Intent intent = new Intent(getApplicationContext(), productInfo.class);
                    intent.putExtra("Name", product.getName());
                    intent.putExtra("Image", product.getImage());
                    intent.putExtra("Price", product.getPrice());
                    intent.putExtra("Quantity", product.getQuantity());
                    startActivity(intent);
                }
            });

        }
    public void goToCart(View view){
        Intent intent= new Intent(this,Cart.class);
        startActivity(intent);

    }
    }

