package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

EditText usernameEt,passwordEt;
    TextView signUp;
    SharedPreferences preferences ;
    String client;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt= findViewById(R.id.etUserName);
        passwordEt=findViewById(R.id.etPassword);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        signUp=findViewById(R.id.signUpText);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

    }

    public void OnLogin(View view) {
        String userName= usernameEt.getText().toString();
        String password= passwordEt.getText().toString();
        String type="login";
        BackgroundWorker newBackgroundWorker= new BackgroundWorker(this);
        newBackgroundWorker.execute(type,userName,password);
        ////SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Client",userName);

        editor.commit();


            BackgroundWorker worker = new BackgroundWorker(this);

            date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            worker.execute("newOrder", userName, date);


    }
    public void openActivity(){
        Intent intent= new Intent(this,SignUp.class);
        startActivity(intent);
    }


}