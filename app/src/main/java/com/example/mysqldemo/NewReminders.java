package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class NewReminders extends AppCompatActivity {
    EditText edname;
    NumberPicker np;
    RadioGroup rdgp1,rdgp2;
    RadioButton rdonce,rdtwice,rdthree,rdoncemoring,rdoncenoon,rdoncenight;
    Button btndone;
    int nb_times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminders);

        edname = (EditText) findViewById(R.id.edname);

        np = (NumberPicker) findViewById(R.id.number_picker1);

        rdgp1 = (RadioGroup) findViewById(R.id.RadioGroup1);
        rdgp2 = (RadioGroup) findViewById(R.id.RadioGroup2);
        rdonce = (RadioButton) findViewById(R.id.radiobutton1);
        rdtwice = (RadioButton) findViewById(R.id.radiobutton2);
        rdthree = (RadioButton) findViewById(R.id.radiobutton3);
        btndone = (Button) findViewById(R.id.btndone);

        rdoncemoring = (RadioButton) findViewById(R.id.radiobutton21);
        rdoncenoon = (RadioButton) findViewById(R.id.radiobutton22);
        rdoncenight = (RadioButton) findViewById(R.id.radiobutton23);
        np.setMaxValue(30);
        np.setMinValue(1);

        rdonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb_times = 1;
                rdgp2.setVisibility(View.VISIBLE);
                rdoncemoring.setVisibility(View.VISIBLE);
                rdoncenoon.setVisibility(View.VISIBLE);
                rdoncenight.setVisibility(View.VISIBLE);
            }
        });

        rdtwice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdgp2.getVisibility() == View.VISIBLE) {
                    rdgp2.setVisibility(View.GONE);
                    rdoncemoring.setVisibility(View.GONE);
                    rdoncenoon.setVisibility(View.GONE);
                    rdoncenight.setVisibility(View.GONE);
                }
            }
        });

        rdthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdgp2.getVisibility() == View.VISIBLE) {
                    rdgp2.setVisibility(View.GONE);
                    rdoncemoring.setVisibility(View.GONE);
                    rdoncenoon.setVisibility(View.GONE);
                    rdoncenight.setVisibility(View.GONE);
                }
            }
        });


        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String medname = edname.getText().toString();
                String numdays = String.valueOf(np.getValue());

                if (TextUtils.isEmpty(medname)) {
                    edname.setError("Please enter Medecine Name");
                    edname.requestFocus();
                    return;
                }

                if (rdgp1.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please choose an option!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (rdoncemoring.getVisibility() == View.VISIBLE && rdgp2.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please choose an option!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (rdtwice.isChecked()) {
                    nb_times = 2;
                    sendNotification(8,np.getValue());
                    sendNotification(20,np.getValue());
                }
                if (rdthree.isChecked()){
                    nb_times = 3;
                    sendNotification(8,np.getValue());
                    sendNotification(14,np.getValue());
                    sendNotification(20,np.getValue());
                }

                String numtimes = String.valueOf(nb_times);
                String method = "addReminder";
                BackgroundWorkerReminder backgroundWorker = new BackgroundWorkerReminder(NewReminders.this);
                backgroundWorker.execute(method, medname, numdays, numtimes);

                Toast.makeText(getApplicationContext(), "Reminder Set!", Toast.LENGTH_SHORT).show();

                if(rdoncemoring.isChecked()){
                    sendNotification(10,np.getValue());
                }
                if (rdoncenoon.isChecked()) {
                    sendNotification(14,np.getValue());
                }
                if (rdoncenight.isChecked()) {
                    sendNotification(20,np.getValue());
                }
                finish();
                Intent intent = new Intent(NewReminders.this, DisplayReminders.class);
                startActivity(intent);
            }
        });    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("noti","my notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel");

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void sendNotification(int hour,int nbdays){
        createNotificationChannel();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,56);
        Calendar calendar2 = Calendar.getInstance();//.getTimeInMillis()+(nbdays*86400000);
        calendar2.setTimeInMillis(nbdays*86400000);
        if(calendar.getTimeInMillis()<calendar2.getTimeInMillis()){
            Intent i = new Intent(NewReminders.this, Notification_receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, i, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        }
    }
    public void onBackPressed() {
        Intent intent = new Intent(this,DisplayReminders.class);
        startActivity(intent);
    }
}