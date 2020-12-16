package com.tdc.edu.vn.myapplication.CTToan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdc.edu.vn.myapplication.R;
import com.tdc.edu.vn.myapplication.card_model.card_view_model;

import java.util.Vector;


public class Hoaactivity extends AppCompatActivity {
    private Vector<card_view_model> data = new Vector<card_view_model>();
    RecyclerView recyclerView;
    private int position = 0;
    private boolean deretion = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_hoa);
        //Initiation of data
        data.add(new card_view_model("page 1", R.drawable.hoa_10_1));
        data.add(new card_view_model("page 2", R.drawable.hoa_10_2));
        data.add(new card_view_model("page 3", R.drawable.hoa_10_3));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }

}
