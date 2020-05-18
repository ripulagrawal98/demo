package com.nit.demo;


//link to the source of this code   https://developer.android.com/guide/topics/ui/layout/recyclerview


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterViewHolder>{

    private ArrayList<User> mdataset;
    private Context mContext;

    public RecyclerAdapter(MainActivity context, ArrayList<User> myDataset){
        mdataset = myDataset;
        mContext = context;

    }

    @NonNull
    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_layout,parent, false);
        RecyclerAdapterViewHolder view = new RecyclerAdapterViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, final int position)
    {
        String title = mdataset.get(position).getName();
        holder.txtview.setText(title);

        holder.txtview.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,linked.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("datafeed",mdataset.get(position));
                i.putExtras(bundle);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdataset.size();
    }

    public class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imview ;
        TextView txtview;
        public RecyclerAdapterViewHolder(View itemView) {
            super(itemView);
            imview = (ImageView)itemView.findViewById(R.id.img1);
            txtview = (TextView)itemView.findViewById(R.id.text);
        }
    }
}
