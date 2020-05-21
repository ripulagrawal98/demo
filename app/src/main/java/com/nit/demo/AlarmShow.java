package com.nit.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AlarmShow extends AppCompatActivity {

    private TextView mEmptyStateTextView;
    private FloatingActionButton fab;

    protected static RecyclerAdapter_Alarm  adapter;
    protected RecyclerView sRecyclerView;
    protected RecyclerView.LayoutManager sLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_show);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        fab = findViewById(R.id.fab);
        sRecyclerView = (RecyclerView)findViewById(R.id.recycler);
        sRecyclerView.setHasFixedSize(true);
        sLayoutManager = new LinearLayoutManager(AlarmShow.this);
        sRecyclerView.setLayoutManager(sLayoutManager);

        adapter = new RecyclerAdapter_Alarm(AlarmShow.this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlarmShow.this, AddRow.class);
                startActivity(intent);

            }
        });

    }
}
