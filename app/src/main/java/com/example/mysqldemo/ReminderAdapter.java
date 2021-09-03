package com.example.mysqldemo;

import android.content.Context;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;


public class ReminderAdapter extends BaseAdapter {
    Context context;
    List<Reminder> rowItems;

    public ReminderAdapter(Context context, List<Reminder> items) {
        this.context = context;
        this.rowItems = items;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReminderHolder reminderHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.display_row_reminder, parent, false);
            reminderHolder = new ReminderHolder();
            reminderHolder.txname = (TextView) row.findViewById(R.id.textv1);
            reminderHolder.txtimes = (TextView) row.findViewById(R.id.textv2);
            reminderHolder.sw = (Switch) row.findViewById(R.id.sw);
            row.setTag(reminderHolder);

        } else {
            reminderHolder = (ReminderHolder) row.getTag();
        }
        Reminder r = (Reminder) getItem(position);
        /*reminderHolder.sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                public int flag(){
                    if (reminderHolder.sw.)
                }
            }
        });*/
        reminderHolder.txname.setText(r.getMedname());
        if(r.getNbtimes()==1){
            reminderHolder.txtimes.setText("Once, Daily.");
        }
        else{
            if(r.getNbtimes()==2){
                reminderHolder.txtimes.setText("Twice, Daily.");
            }else{
                reminderHolder.txtimes.setText(Integer.toString(r.getNbtimes())+" times, Daily.");
            }
        }
        return row;
    }
    static class ReminderHolder {
        TextView txname, txtimes;
        Switch sw;
    }
}
