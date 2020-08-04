package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tdc.edu.vn.myapplication.database.Database;
import com.tdc.edu.vn.myapplication.modals.ConvertUnit;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    private Database DAO;
    private ArrayList<ConvertUnit> convertList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        DAO = new Database(this);
        DAO.getAllConvert(convertList);
        if(convertList.isEmpty()){
            DAO.init();
            DAO.getAllConvert(convertList);
        }

        Log.d("TEST", "LENGHTH = " + convertList.size());
    }
}