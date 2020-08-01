package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tdc.edu.vn.myapplication.myadapter.ProductAdapter;

public class ListviewMenuChuyenDoi extends AppCompatActivity {

    Intent intent;
    private ListView listProduct;
    String[] mainTitle = {
            "Tiền tệ","Chiều dài","Thời gian",
            "Lưu lượng"
    };
    String[] sub_title = {
            "Đô la Mỹ, Việt Nam ","Mile, Meter, Foot",
            "Hourd, Minute, Second","Gigabytes, Megabytes, Kilobytes"
    };
    Integer[] imgid = {
            R.drawable.ic_monney,R.drawable.ic_ruler,
            R.drawable.ic_clock,R.drawable.ic_can
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_menu_chuyen_doi);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_storage_black_24dp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Chuyển Đổi Đơn Vị");*/
        setTitle("Chuyển Đổi Đơn Vị");

        ProductAdapter adapter = new ProductAdapter(this,mainTitle,sub_title,imgid);
        listProduct = (ListView) findViewById(R.id.listProduct);
        listProduct.setAdapter(adapter);

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "Bạn đã chọn chuyển đổi [" + mainTitle[position]+ "]";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                if(mainTitle[position] == "Tiền tệ"){
                    intent = new Intent(ListviewMenuChuyenDoi.this,ChuyenDoiDonVi1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else if(mainTitle[position]=="Lưu lượng"){
                    intent = new Intent(ListviewMenuChuyenDoi.this,ChuyenDoiLuuLuong.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });
    }
}
