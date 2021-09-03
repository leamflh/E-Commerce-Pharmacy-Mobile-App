package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class productInfo extends AppCompatActivity {

    NumberPicker numberPicker;
    TextView nameProduct, quantityProduct, priceProduct;
    ImageView imageProduct;
    SharedPreferences preferences;
    String id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        name = getIntent().getExtras().getString("Name");
        String image = getIntent().getExtras().getString("Image");
        Integer price = getIntent().getExtras().getInt("Price");
        Integer quantity = getIntent().getExtras().getInt("Quantity");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        nameProduct = findViewById(R.id.textProduct);
        quantityProduct = findViewById(R.id.qtyProduct);
        priceProduct = findViewById(R.id.price);
        imageProduct = findViewById(R.id.imgProduct);
        numberPicker = findViewById(R.id.choice);


        nameProduct.setText(name);
        quantityProduct.setText(quantity.toString());
        priceProduct.setText(price.toString() + ".000 LL");
        Picasso.get().load(image).into(imageProduct);

        numberPicker.setMaxValue(quantity);
        numberPicker.setMinValue(1);
        numberPicker.setValue(2);


    }

    public void addToCart(View vew) {

        id = preferences.getString("idOrder", null);
        BackgroundWorker worker = new BackgroundWorker(this);

        AsyncTask<String, Void, String> var = worker.execute("addProduct", String.valueOf(numberPicker.getValue()), name, id);


    }
}
