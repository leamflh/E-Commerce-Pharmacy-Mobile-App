package com.example.mysqldemo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;

import java.util.prefs.BackingStoreException;


public class Menu extends Activity {
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }

    public void goNews(View view) {




        Intent intent= new Intent(this,News.class);
        startActivity(intent);
    }

    public void goPill(View view) {




        Intent intent= new Intent(this,DisplayReminders.class);
        startActivity(intent);
    }

    public void GoCatalog(View view) {




        Intent intent= new Intent(this,Category.class);
        startActivity(intent);
    }
    public void GoBranches(View view) {




        Intent intent= new Intent(this,Branches.class);
        startActivity(intent);
    }

    public void goToCart(View view){

        Intent intent= new Intent(this,Cart.class);
        startActivity(intent);

    }

    public void logOut(View view){
        BackgroundWorker worker=new BackgroundWorker(this);
        Intent loginScreen=new Intent(this,MainActivity.class);
        alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Message");
        alertDialog.setMessage("Do you want to log out ?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String id = sharedpreferences.getString("idOrder", null);
                        editor = sharedpreferences.edit();
                        editor.clear();
                        editor.commit();
                        worker.execute("deleteOrder",id);
                        startActivity(loginScreen);


                    }
                } );
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#25CBDF"));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#25CBDF"));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);








    }

    public void goToServices(View view) {

        Intent intent= new Intent(this,Services.class);
        startActivity(intent);
    }
}