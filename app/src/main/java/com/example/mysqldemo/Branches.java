package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Branches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    public void onmapclick(View view) {
        Uri uri = Uri.parse("https://www.google.com/maps/d/viewer?hl=en&hl=en&mid=1M6x3S9IA7WSbgmTT15s68XgrfpRbFqsQ&ll=33.90748510342368%2C35.589869000000014&z=13");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onphone1(View view) {
        Uri uri=Uri.parse("tel:04544923");
        Intent intent = new Intent (Intent.ACTION_DIAL,uri);
        startActivity(intent);

    }

    public void onphone2(View view) {
        Uri uri=Uri.parse("tel:01680248");
        Intent intent = new Intent (Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }
}