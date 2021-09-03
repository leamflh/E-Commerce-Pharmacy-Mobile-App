package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PurchaseDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_done);
    }

    public void goHome(View view) {
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String  client = sharedpreferences.getString("Client", null);
        String test=sharedpreferences.getString("idOrder",null);
        String  date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        BackgroundWorker worker=new BackgroundWorker(this);

        if(test ==  null || test.length() == 0) worker.execute("newOrder", client, date);
        this.finishAffinity();


        Intent intent= new Intent(this,Menu.class);
        startActivity(intent);
    }
}