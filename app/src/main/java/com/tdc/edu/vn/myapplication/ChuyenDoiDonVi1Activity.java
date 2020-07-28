package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tdc.edu.vn.myapplication.R;

import java.security.Key;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChuyenDoiDonVi1Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_KetQua;
    private EditText edt_ManHinh;
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
    private Button btn_Cong;
    private Button btn_Tru;
    private Button btn_Nhan;
    private Button btn_Chia;
    private Button btn_Chuyen;
    private Button btn_KQ;
    private Button btn_Cham;
    private Button btn_All;
    private Button btn_Clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_doi_don_vi1);
        edt_ManHinh = (EditText) findViewById(R.id.edt_ManHinh);
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
        btn_Cong = (Button) findViewById(R.id.btn_Cong);
        btn_Tru = (Button) findViewById(R.id.btn_Tru);
        btn_Nhan = (Button) findViewById(R.id.btn_Nhan);
        btn_Chia = (Button) findViewById(R.id.btn_Chia);
        btn_Chuyen = (Button) findViewById(R.id.btn_Chuyen);
        btn_KQ = (Button) findViewById(R.id.btn_KQ);
        btn_Cham = (Button) findViewById(R.id.btn_Cham);
        btn_All = (Button) findViewById(R.id.btnAll);
        btn_Clear = (Button) findViewById(R.id.btn_Clear);
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
        btn_Cong.setOnClickListener(this);
        btn_Tru.setOnClickListener(this);
        btn_Nhan.setOnClickListener(this);
        btn_Chia.setOnClickListener(this);
        btn_Chuyen.setOnClickListener(this);
        btn_KQ.setOnClickListener(this);
        btn_Cham.setOnClickListener(this);
        btn_All.setOnClickListener(this);
        btn_Clear.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                edt_ManHinh.append("1");
                break;
            case R.id.btn0:
                edt_ManHinh.append("0");
                break;
            case R.id.btn2:
                //TO DO
                edt_ManHinh.append("2");
                break;
            case R.id.btn3:
                edt_ManHinh.append("3");
                //TO DO
                break;
            case R.id.btn4:
                //TO DO
                edt_ManHinh.append("4");
                break;
            case R.id.btn5:
                //TO DO
                edt_ManHinh.append("5");
                break;
            case R.id.btn6:
                //TO DO
                edt_ManHinh.append("6");
                break;
            case R.id.btn7:
                //TO DO
                edt_ManHinh.append("7");
                break;
            case R.id.btn8:
                //TO DO
                edt_ManHinh.append("8");
                break;
            case R.id.btn9:
                //TO DO
                edt_ManHinh.append("9");
                break;
            case R.id.btn_Cong:
                //TO DO
                edt_ManHinh.append("+");
                break;
            case R.id.btn_Tru:
                //TO DO
                edt_ManHinh.append("-");
                break;
            case R.id.btn_Nhan:
                //TO DO
                edt_ManHinh.append("*");
                break;
            case R.id.btn_Chia:
                //TO DO
                edt_ManHinh.append("/");
                break;
            case R.id.btn_Cham:
                //TO DO
                edt_ManHinh.append(".");
                break;
            case R.id.btnAll:
                tv_KetQua.setText("");
                edt_ManHinh.setText("");
                break;
            case R.id.btn_Clear:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(edt_ManHinh, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btn_KQ:
                DecimalFormat df = new DecimalFormat("###.#######");
                double result = Double.parseDouble(edt_ManHinh.getText().toString()) * 23000;
                tv_KetQua.setText(df.format(result) + "");
                break;
            default:
                break;
        }

    }
}
