package com.nit.demo;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class linked extends AppCompatActivity {

    private FABProgressCircle fabProgressCircle;
    private DatabaseReference mDatabaseReference;
    private User user;
    private boolean taskRunning;

    private TextView timeClock;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked);

        timeClock = findViewById(R.id.time);

        user = getIntent().getParcelableExtra("datafeed");


        timeClock.setCompoundDrawablesWithIntrinsicBounds(R.drawable.time_icon,0,0,0);




        timeClock.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);


                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(linked.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        time = ((hourOfDay<10)?"0"+hourOfDay:hourOfDay)+ ":"+((minute<10)?"0"+minute:minute);

                        timeClock.setText(((hourOfDay<10)?"0"+hourOfDay:hourOfDay)+":"+((minute<10)?"0"+minute:minute));
                        mcurrentTime.set(mcurrentTime.HOUR_OF_DAY,hourOfDay);


                    }

                },hour,minute,true);



                mTimePicker.setTitle("Select Time");

                mTimePicker.show();

            }


        });
//
        fabProgressCircle = findViewById(R.id.fabProgressCircle);
        fabProgressCircle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (!taskRunning && timeClock.getText().toString().length()>0) {
                    fabProgressCircle.beginFinalAnimation();
                    runMockInteractor();
                }
                else
                {
                    Toast.makeText(linked.this,"Invalid Input",Toast.LENGTH_SHORT).show();

                }
            }

        });




    }
    private void runMockInteractor()
    {
        taskRunning = true;

    mDatabaseReference = FirebaseDatabase.getInstance().getReference("User");
            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot looping : dataSnapshot.getChildren()){
                    User userref = looping.getValue(User.class);
                    if(userref.getName().equals(user.getName())){
//                        userref.setTime(time);
                        looping.child("time").getRef().setValue(time);
                        startActivity(new Intent(linked.this,MainActivity.class));
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
