package com.tdc.edu.vn.myapplication;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseSearch_activity extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
    private List<ItemPTPU> listData;
    private RecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebasesearch_layout);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("data");
        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        listData = new ArrayList<>();
        adapter = new RecyAdapter(listData);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        //Lấy dữ liệu từ firebase về
        mUserDatabase.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.exists()) {
                                                        int leng = (int) snapshot.getChildrenCount();
                                                        for (int i = 0; i < leng; i++) {
                                                            String ptpu = snapshot.child(String.valueOf(i)).child("ptpu").getValue(String.class);
                                                            listData.add(new ItemPTPU(ptpu));
                                                            //add vào list
                                                        }

                                                    }
                                                    adapter.notifyDataSetChanged();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            }
        );

        //
        mResultList.setAdapter(adapter);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchField.getText().toString().trim();

                adapter.getFilter().filter(searchText);
//                firebaseUserSearch(searchText);

            }
        });

        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textSearch = charSequence.toString().trim();
                adapter.getFilter().filter(textSearch);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    adapter.getFilter().filter(editable.toString());

                }

            }
        });
    }

}
