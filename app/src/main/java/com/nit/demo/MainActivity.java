package com.nit.demo;

// firebase database doc help
//https://www.learnhowtoprogram.com/android/data-persistence/firebase-reading-data-and-event-listeners

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class MainActivity<mdata> extends AppCompatActivity {


    private ArrayList<User> mdata;

    protected static int size;
    protected static int scrollPosition;

    protected static RecyclerAdapter adapter;
    protected static RecyclerView sRecyclerView;
    protected static RecyclerView.LayoutManager sLayoutManager;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdata = new ArrayList<>();

//        // code to request permissions for foreground service
//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.FOREGROUND_SERVICE}, PERMISSION_GRANTED);

        dataref = FirebaseDatabase.getInstance().getReference("User");
        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()){
                    String name = looping.child("name").getValue(String.class);

                    String time = looping.child("time").getValue(String.class);
                    User userref = new User(name,time);

                    System.out.println(userref.getTime());
                    mdata.add(userref);
}
                sRecyclerView = (RecyclerView)findViewById(R.id.recycler1);
                sRecyclerView.setHasFixedSize(true);
                // use a linear layout manager
                sLayoutManager = new LinearLayoutManager(MainActivity.this);
                sRecyclerView.setLayoutManager(sLayoutManager);


                adapter = new RecyclerAdapter(MainActivity.this,mdata);
                sRecyclerView.setAdapter(adapter);
                try {
                    scheduleTime();
                }

                catch (Exception e)
                {
                    System.out.println("There is an exception");
                    Toast.makeText(getApplicationContext(),"There is an exception",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void scheduleTime() throws Exception {

        ArrayList<Integer> AlarmList = new ArrayList<Integer>();
        int i =0;
        size = mdata.size();

        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(calendar.MINUTE);

        // this variable store total number of minutes of current time from the starting of a new day i.e. "00:00"
        int total_currentminute = (currentHour)*60 + currentMinute;

        //his loop will make  a new array to store all the minute values in an array

        for(i = 0;i<size;i++)
        {
            User user = mdata.get(i);
            String nextTime = user.getTime().substring(0,2) + ":" + user.getTime().substring(3);

            int Hour = Integer.parseInt(nextTime.substring(0,2));
            int Minute = Integer.parseInt(nextTime.substring(3));
            System.out.println("Print the time "+nextTime);
            System.out.println("Value of Minute" + Minute);

            int total_minute = (Hour*60 )+ Minute;

            AlarmList.add(total_minute);

         }



        // Now sort this array in ascending order i.e. which alarm to be scheduled before
        Collections.sort(AlarmList);

        int hour = AlarmList.get(0)/60;
        int minute = AlarmList.get(0) %60;


        Calendar cal = Calendar.getInstance();
        AlarmManager alarms = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<>();

//        System.out.println("IS this Schedule time calling");
        for(i = 0;i<mdata.size();i++)
        {
            System.out.println("\n Value of totoal minutes of currenthour "+total_currentminute+"\n value of i is "+ i );
            System.out.println("\n"+ i + " element of ALarmList "+ AlarmList.get(i));
            if(total_currentminute <= AlarmList.get(i)){

                System.out.println("Part2 **"+ i + " element of ALarmList "+ AlarmList.get(i));
                 hour = AlarmList.get(i)/60;
                 minute = AlarmList.get(i) %60;
                System.out.println("Check the value of hour :"+hour+" Minute: "+minute);

                Intent activate = new Intent(MainActivity.this, Alarm.class);
//                System.out.println("Intent Initialization");

                PendingIntent alarmIntent = PendingIntent.getBroadcast(this, i, activate, 0);
//                System.out.println("Pending Intent");

                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 00);
                System.out.println("Calendar parameters set");

                alarms.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY, alarmIntent);
                Toast.makeText(getApplicationContext(),"Alarm Set",Toast.LENGTH_SHORT).show();
                System.out.println("Alarm is set");
                intentArray.add(alarmIntent);

//                if(i == AlarmList.size()-1)
//                {
//                    cal.add(Calendar.DATE,1);
//                    i = 0;
//                }


            }
//
                if(i == AlarmList.size()-1)
                {
                    cal.add(Calendar.DATE,1);
                    System.out.println("Date of nextDay "+cal.get(Calendar.DATE));
                    System.out.println("Hour of next day "+cal.get(Calendar.HOUR_OF_DAY));
                    System.out.println("Minute of next day "+cal.get(Calendar.MINUTE));

//                    Toast.makeText(getApplicationContext(), (CharSequence) cal,Toast.LENGTH_SHORT).show();
//                    i = 0;
                }




        }


//        System.out.println("IS this Schedule time calling");
//        for(i = 0;i<mdata.size();i++)
//        {
//            User user = mdata.get(i);
//            System.out.println("Is this loop calling");
//            System.out.println("Check the value in mData "+user.getTime());
//            String nextTime = user.getTime();
//            System.out.println("Length of String "+nextTime.length());
//            System.out.println("Part1 value of hour :"+nextTime.substring(0, 2)+" Minute: "+nextTime.substring(3));
//            System.out.println("Type of Variable: "+ nextTime.substring(0,2).getClass().getSimpleName());
//
//            int hour = Integer.parseInt(nextTime.substring(0, 2));
//            int minute = Integer.parseInt(nextTime.substring(3));
//            System.out.println("Check the value of hour :"+hour+" Minute: "+minute);
//
//            Intent activate = new Intent(MainActivity.this, Alarm.class);
//            System.out.println("Intent Initialization");
//            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, i, activate, 0);
//            System.out.println("Pending Intent");
//            cal.set(Calendar.HOUR_OF_DAY, hour);
//            cal.set(Calendar.MINUTE, minute);
//            cal.set(Calendar.SECOND, 00);
//            System.out.println("Calendar parameters set");
//            alarms.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
//            Toast.makeText(getApplicationContext(),"Alarm Set",Toast.LENGTH_SHORT).show();
//            System.out.println("Alarm is set");
//            intentArray.add(alarmIntent);
//        }


    }
}

