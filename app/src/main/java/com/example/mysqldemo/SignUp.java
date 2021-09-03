package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class SignUp extends AppCompatActivity {

    EditText etName,etEmail,etPhone,etPassword,etAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName=findViewById(R.id.name);
        etEmail=findViewById(R.id.email);
        etPhone=findViewById(R.id.phone);
        etAddress=findViewById(R.id.address);
        etPassword=findViewById(R.id.password);

    }


    public void OnSignUp(View view) {
       String name_client=etName.getText().toString();
       String email_client=etEmail.getText().toString();
       String phone_client=etPhone.getText().toString();
       String pass_client=etPassword.getText().toString();
        String address_client=etAddress.getText().toString();

       
       String type="signUp";
        BackgroundWorker newBackgroundWorker= new BackgroundWorker(this);
        newBackgroundWorker.execute(type,name_client,pass_client,email_client,phone_client,address_client);




    }
}