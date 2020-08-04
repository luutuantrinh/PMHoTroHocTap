package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tdc.edu.vn.myapplication.database.Database;
import com.tdc.edu.vn.myapplication.modals.ConvertUnit;

import java.util.ArrayList;

public class ChuyenDoiLuuLuong extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_KetQua;
    private TextView tv_ManHinh;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btn_Chuyen;
    private Button btn_KQ;
    private Button btn_Cham;
    private Button btn_All;
    private Button btn_Clear;
    private Button btn_Delete;
    private Spinner sp1;
    private Spinner sp2;
    public ChuyenDoiLuuLuong() {
    }

    //Database and array of database data
    private Database DAO;
    private ArrayList<ConvertUnit> convertList = new ArrayList<>();

    //Spinner value
    private String valueLeft;
    private String valueRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get intent
        Bundle extras = getIntent().getExtras();
        String type = "";
        if(extras != null){
            type = extras.getString(ListviewMenuChuyenDoi.CONVERT_KEY);
        }

        //Get data from database
        DAO = new Database(this);
        DAO.getConvertByType(convertList, type);

        //Init if data is null
        if(convertList.isEmpty()){
            DAO.init();
            DAO.getConvertByType(convertList, type);
        }

        //Set the title of layout
        setContentView(R.layout.activity_chuyen_doi_luu_luong);
        String newTitle = type + " convert";
        setTitle(newTitle);

        //Get view in layout
        tv_ManHinh = (TextView) findViewById(R.id.tv_ManHinh);
        tv_KetQua = (TextView) findViewById(R.id.tv_KetQua);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btn_Chuyen = (Button) findViewById(R.id.btn_Chuyen);
        btn_KQ = (Button) findViewById(R.id.btn_KQ);
        btn_Cham = (Button) findViewById(R.id.btn_Cham);
        btn_All = (Button) findViewById(R.id.btnAll);
        btn_Delete = (Button) findViewById(R.id.btnDelete);
        btn_Clear = (Button) findViewById(R.id.btn_Clear);
        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);

        //Set spinner
        ArrayList<String> unitsName = new ArrayList<>();
        for(ConvertUnit item : convertList){
            unitsName.add(item.getUnitName());
        }

        //Add array to spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsName);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(spinnerAdapter);
        sp2.setAdapter(spinnerAdapter);

        //Get spinner's value
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueLeft = sp1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueRight = sp2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        //
        setEventClickViews();
    }

    public void setEventClickViews(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_Chuyen.setOnClickListener(this);
        btn_KQ.setOnClickListener(this);
        btn_Cham.setOnClickListener(this);
        btn_All.setOnClickListener(this);
        btn_Clear.setOnClickListener(this);
        btn_Delete.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                tv_ManHinh.append("1");
                break;
            case R.id.btn0:
                tv_ManHinh.append("0");
                break;
            case R.id.btn2:
                //TO DO
                tv_ManHinh.append("2");
                break;
            case R.id.btn3:
                tv_ManHinh.append("3");
                //TO DO
                break;
            case R.id.btn4:
                //TO DO
                tv_ManHinh.append("4");
                break;
            case R.id.btn5:
                //TO DO
                tv_ManHinh.append("5");
                break;
            case R.id.btn6:
                //TO DO
                tv_ManHinh.append("6");
                break;
            case R.id.btn7:
                //TO DO
                tv_ManHinh.append("7");
                break;
            case R.id.btn8:
                //TO DO
                tv_ManHinh.append("8");
                break;
            case R.id.btn9:
                //TO DO
                tv_ManHinh.append("9");
                break;
            case R.id.btn_Cham:
                //TO DO
                tv_ManHinh.append(".");
                break;
            case R.id.btnAll:
                tv_KetQua.setText("");
                tv_ManHinh.setText("");
                break;
            case R.id.btn_Clear:
                tv_ManHinh.setText("");
                tv_KetQua.setText("");
                break;
            case R.id.btnDelete:
                String input = tv_ManHinh.getText().toString();
                if(input.length() > 0){
                    input = input.substring(0, input.length()-1);
                }
                tv_ManHinh.setText(input);
                break;
            case R.id.btn_Chuyen:
                String temp = valueLeft;
                valueLeft = valueRight;
                valueRight = valueLeft;

                int temp2 = sp1.getSelectedItemPosition();
                sp1.setSelection(sp2.getSelectedItemPosition());
                sp2.setSelection(temp2);

                temp = tv_KetQua.getText().toString();
                tv_KetQua.setText(tv_ManHinh.getText().toString());
                tv_ManHinh.setText(temp);
                break;
            case R.id.btn_KQ:
                try{
                    calculating(tv_ManHinh, tv_KetQua,  valueLeft, valueRight);
                }catch (Exception e){
                    Toast.makeText(this, "Vui lòng nhập phép tính đúng!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    private void calculating(TextView input , TextView output, String inputUnit, String outputUnit){
        if(!input.getText().toString().isEmpty()){
            float rateInput = -1;
            float rateOutput = -1;
            for (ConvertUnit item : convertList){
                if(item.getUnitName().equals(inputUnit)){
                    rateInput = item.getRateToRootUnit();
                }
                if(item.getUnitName().equals(outputUnit)){
                    rateOutput = item.getRateToRootUnit();
                }
            }

            float result = (rateInput*1000 / rateOutput*1000) * Float.parseFloat(input.getText().toString());
            output.setText(result/1000000 + "");
        }
    }
}
