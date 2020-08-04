package com.tdc.edu.vn.myapplication.CTToan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdc.edu.vn.myapplication.R;
import com.tdc.edu.vn.myapplication.card_model.card_view_model;

import java.util.Vector;

public class Lyactivity extends AppCompatActivity {
    private Vector<card_view_model> data = new Vector<card_view_model>();
    RecyclerView recyclerView;
    private int position = 0;
    private boolean deretion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_ly);
        //Initiation of data
        data.add(new card_view_model("page 1", R.drawable.vl2_thpt));
        data.add(new card_view_model("page 2", R.drawable.vl3_thpt));
        data.add(new card_view_model("page 3", R.drawable.vl4_thpt));
        data.add(new card_view_model("page 4", R.drawable.vl5_thpt));
        data.add(new card_view_model("page 5", R.drawable.vl6_thpt));
        data.add(new card_view_model("page 6", R.drawable.vl7_thpt));
        data.add(new card_view_model("page 6", R.drawable.vl8_thpt));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }

}
