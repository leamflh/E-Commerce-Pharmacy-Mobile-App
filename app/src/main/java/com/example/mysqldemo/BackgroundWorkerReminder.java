package com.example.mysqldemo;

import android.content.Context;
import android.os.AsyncTask;

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

public class BackgroundWorkerReminder extends AsyncTask<String,Void,String> {
    Context context;
    BackgroundWorkerReminder(Context ctx) { context=ctx;}

    @Override
    protected java.lang.String doInBackground(java.lang.String... params) {
        java.lang.String method = params[0];
        java.lang.String reminder_url = "http://10.0.2.2//android/addReminder.php";
        if(method.equals("addReminder")){
            try {
                java.lang.String medecine_name = params[1];
                java.lang.String nb_days = params[2];
                java.lang.String nb_times = params[3];
                URL url = new URL(reminder_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                java.lang.String post_data= URLEncoder.encode("medname","UTF-8")+"="+ URLEncoder.encode(medecine_name,"UTF-8")+"&"+
                        URLEncoder.encode("nb_days","UTF-8")+"="+ URLEncoder.encode(nb_days,"UTF-8")+"&"+
                        URLEncoder.encode("nb_times","UTF-8")+"="+ URLEncoder.encode(nb_times,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                java.lang.String result="";
                java.lang.String line="";
                while((line=bufferedReader.readLine())!=null){
                    result += line;
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
        }
        return null;
    }
}
