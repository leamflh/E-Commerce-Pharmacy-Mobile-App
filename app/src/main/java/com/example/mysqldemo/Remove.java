package com.example.mysqldemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Remove extends AsyncTask<String,Void,String> {
    Context context;

    SharedPreferences sharedpreferences;



        Remove(Context ctx){
        context=ctx;
        }


    @Override
    protected String doInBackground(String... strings) {
         sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.remove("idOrder");
        editor.remove("total");

        editor.apply();

        Intent go= new Intent(context,PurchaseDone.class);
        context.startActivity(go);








        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
