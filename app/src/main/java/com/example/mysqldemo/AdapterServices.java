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


public class AdapterServices extends BaseAdapter {
    Context context;
    List<ServicesInfo> rowItems;

    public AdapterServices(Context context, List<ServicesInfo> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txt1,txt2;



    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.service_card, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.txt1 = (TextView) convertView.findViewById(R.id.name);
            holder.txt2 = (TextView) convertView.findViewById(R.id.price);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ServicesInfo rowItem = (ServicesInfo) getItem(position);


        holder.txt1.setText(rowItem.getName());
        holder.txt2.setText("Price:"+rowItem.getPrice().toString()+".000L.L");

        Picasso.get().load(rowItem.getImage()).into(holder.imageView);

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
