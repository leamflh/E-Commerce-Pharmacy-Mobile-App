package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


    }

    public void onnews1(View view) {
        Uri uri=Uri.parse("http://www.dailystar.com.lb/News/Lebanon-News/2021/Jan-31/516874-lebanon-surpasses-300000-covid-19-cases.ashx");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onnews2(View view) {
        Uri uri=Uri.parse("https://www.npr.org/sections/goatsandsoda/2021/01/29/959617806/coronavirus-faq-im-using-uv-light-to-disinfect-stuff-is-that-a-good-idea");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onnews3(View view) {
        Uri uri=Uri.parse("https://www.ctvnews.ca/health/coronavirus/coronavirus-vaccine-tracker-how-many-people-in-canada-have-received-shots-1.5247509");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void onnews4(View view) {
        Uri uri=Uri.parse("https://www.newscientist.com/article/2237475-covid-19-news-germany-wont-approve-oxford-vaccine-for-people-over-65/");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}