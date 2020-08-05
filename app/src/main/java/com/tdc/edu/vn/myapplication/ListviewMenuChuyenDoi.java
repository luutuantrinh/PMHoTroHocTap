package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tdc.edu.vn.myapplication.database.Database;
import com.tdc.edu.vn.myapplication.modals.ConvertType;
import com.tdc.edu.vn.myapplication.modals.ConvertUnit;
import com.tdc.edu.vn.myapplication.myadapter.ProductAdapter;

import java.util.ArrayList;

public class ListviewMenuChuyenDoi extends AppCompatActivity {

    Intent intent;
    private ListView listProduct;

    private Database DAO;
    private ArrayList<ConvertType> convertTitle = new ArrayList<>();
    public static String CONVERT_KEY = "CONVERT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_menu_chuyen_doi);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_storage_black_24dp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Chuyển Đổi Đơn Vị");*/
        setTitle("");

        DAO = new Database(this);
        DAO.getAllType(convertTitle);

        if(convertTitle.isEmpty()){
            DAO.initType();
            DAO.getAllType(convertTitle);
        }


        if(!convertTitle.isEmpty()){
            ProductAdapter adapter = new ProductAdapter(this, R.layout.product, convertTitle);

            listProduct = (ListView) findViewById(R.id.listProduct);
            listProduct.setAdapter(adapter);
        }


        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConvertType convertType = convertTitle.get(position);
                String msg = "You selected [ " + convertType.getTypeName() + " ] convert !";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                Bundle mBundle = new Bundle();
                mBundle.putString(CONVERT_KEY, convertType.getTypeName());
                intent = new Intent(ListviewMenuChuyenDoi.this, ChuyenDoiLuuLuong.class);
                intent.putExtras(mBundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                /*
                if(mainTitle[position] == "Tiền tệ"){
                    intent = new Intent(ListviewMenuChuyenDoi.this,ChuyenDoiDonVi1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else if(mainTitle[position]=="Lưu lượng"){
                    intent = new Intent(ListviewMenuChuyenDoi.this,ChuyenDoiLuuLuong.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }*/
            }
        });
    }
}
