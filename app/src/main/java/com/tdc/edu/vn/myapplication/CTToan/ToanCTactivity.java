package com.tdc.edu.vn.myapplication.CTToan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.tdc.edu.vn.myapplication.R;
import com.tdc.edu.vn.myapplication.card_model.card_view_model;

import java.util.Vector;


public class ToanCTactivity extends AppCompatActivity {
    private Vector<card_view_model> data = new Vector<card_view_model>();
    RecyclerView recyclerView;
    private int position = 0;
    private boolean deretion = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_toan);
        //Initiation of data
        data.add(new card_view_model("page 1", R.drawable.cttth));
        data.add(new card_view_model("page 2", R.drawable.ctt2));
        data.add(new card_view_model("page 3", R.drawable.trang2));
        data.add(new card_view_model("page 4", R.drawable.trang3));
        data.add(new card_view_model("page 5", R.drawable.trang4));
        data.add(new card_view_model("page 6", R.drawable.trang5));
        data.add(new card_view_model("page 7", R.drawable.trang6));
        data.add(new card_view_model("page 8", R.drawable.trang7));
        data.add(new card_view_model("page 9", R.drawable.trang8));
        data.add(new card_view_model("page 10", R.drawable.trang9));
        data.add(new card_view_model("page 11", R.drawable.trang10));
        data.add(new card_view_model("page 12", R.drawable.trang11));
        data.add(new card_view_model("page 13", R.drawable.trang12));
        data.add(new card_view_model("page 14", R.drawable.trang13));
        data.add(new card_view_model("page 15", R.drawable.trang14));
        data.add(new card_view_model("page 16", R.drawable.trang15));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }

}
