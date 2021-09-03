package com.example.mysqldemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;



    BackgroundWorker(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://10.0.2.2/android/logIn.php";
        String signUp_url= "http://10.0.2.2/android/signUp.php";
        String addOrder_url="http://10.0.2.2/android/newOrder.php";
        String addProduct_url="http://10.0.2.2/android/addProduct.php";
        String removeProduct_url="http://10.0.2.2/android/removeProduct.php";
        String checkOut_url="http://10.0.2.2/android/newBill.php";
        String deleteOrder_url="http://10.0.2.2/android/deleteOrder.php";



        switch(type){
            case "login":
                try {
                String user_name=params[1];
                String password=params[2];

                URL url= new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                String postData= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");


                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();







                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;


            case "signUp":
            try {
                String userName=params[1];
                String password=params[2];
                String email=params[3];
                String phone=params[4];
                String address=params[5];

                URL url= new URL(signUp_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                String postData= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8");


                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

            case "newOrder":
                try {
                    String userName=params[1];
                    String date=params[2];


                    URL url= new URL(addOrder_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                    String postData= URLEncoder.encode("client","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
                            +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");


                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                    String result="";
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        result+=line;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("idOrder", result);

                    editor.commit();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "addProduct":
                try {
                    String quantity=params[1];
                    String nameProduct=params[2];
                    String idOrder=params[3];


                    URL url= new URL(addProduct_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                    String postData= URLEncoder.encode("quantity","UTF-8")+"="+URLEncoder.encode(quantity,"UTF-8")+"&"
                            +URLEncoder.encode("nameProduct","UTF-8")+"="+URLEncoder.encode(nameProduct,"UTF-8")+"&"
                            +URLEncoder.encode("idOrder","UTF-8")+"="+URLEncoder.encode(idOrder,"UTF-8");

                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                    String result="";
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        result+=line;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();



                    return result;



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;



            case "remove":
                try {
                    String idOrder=params[1];
                    String name=params[2];


                    URL url= new URL(removeProduct_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                    String postData= URLEncoder.encode("idOrder","UTF-8")+"="+URLEncoder.encode(idOrder,"UTF-8")+"&"
                            +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");


                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                    String result="";
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        result+=line;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;



            case "checkOut":
                try {
                    String idOrder=params[1];
                    String date=params[2];
                    String total=params[3];
                    String idBill=params[4];

                    URL url= new URL(checkOut_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                    String postData= URLEncoder.encode("idOrder","UTF-8")+"="+URLEncoder.encode(idOrder,"UTF-8")+"&"
                            +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                            +URLEncoder.encode("total","UTF-8")+"="+URLEncoder.encode(total,"UTF-8")+"&"
                            +URLEncoder.encode("idBill","UTF-8")+"="+URLEncoder.encode(idBill,"UTF-8");


                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                    String result="";
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        result+=line;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();





                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "deleteOrder":
                try {
                    String idOrder=params[1];


                    URL url= new URL(deleteOrder_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

                    String postData= URLEncoder.encode("idOrder","UTF-8")+"="+URLEncoder.encode(idOrder,"UTF-8");


                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                    String result="";
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        result+=line;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();



                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;



        }



        return null;
    }


    @Override
    protected void onPreExecute() {

        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Message");



    }

    @Override
    protected void onPostExecute(String result) {
    alertDialog.setMessage(result);
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();


    switch(result){
        case "Login success" :
            Intent myIntent = new Intent(context, Menu.class);
            context.startActivity(myIntent);
            break;
        case "Done": Intent myIntent2 = new Intent(context, MainActivity.class);
            context.startActivity(myIntent2);
            break;


    }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
