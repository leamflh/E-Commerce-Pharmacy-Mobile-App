package com.example.mysqldemo;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.BaseAdapter;



import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;


public class AdapterCart extends BaseAdapter {
    Context context;
    List<Product> rowItems;

    public AdapterCart(Context context, List<Product> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txt1,txt2,txt3;
        Button btn;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_product, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.imageProductCart);
            holder.txt1 = (TextView) convertView.findViewById(R.id.productName);
            holder.txt2 = (TextView) convertView.findViewById(R.id.priceProduct);
            holder.txt3 = (TextView) convertView.findViewById(R.id.quantityProduct);
            holder.btn= (Button) convertView.findViewById(R.id.remove);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product rowItem = (Product) getItem(position);
        holder.txt1.setText(rowItem.getName());
        holder.txt2.setText(rowItem.getPrice().toString()+".000L.L");
        holder.txt3.setText("/"+rowItem.getQuantity().toString()+" Pcs");
        Picasso.get().load(rowItem.getImage()).into(holder.imageView);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                 String id = preferences.getString("idOrder", null);
                BackgroundWorker worker=new BackgroundWorker(context);

                worker.execute("remove",id,rowItem.getName());

                Intent refresh = new Intent(context, Cart.class);
                refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                ((Activity)context).startActivityForResult(refresh, 0);
                ((Activity)context). overridePendingTransition(0,0);
                ((Activity)context).finish();





            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }


}