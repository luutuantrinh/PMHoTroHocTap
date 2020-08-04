package com.tdc.edu.vn.myapplication.maytinhcoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tdc.edu.vn.myapplication.R;

class MayTinhmy extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnxwx, btnxxx, btnxcanx, btnfact, btnmc, btnqua, btntoi, btnxoa1, btnxoahet, btnx1;
    Button btnngoac1, btnngoac2, btnmcong, btnmtru, btnmro;
    Button btnnhan, btnchia, btncong, btntru, btnans, btnbang, btncham, btntram;
    EditText edtnumberc, edtnumber;

    Double var1;
    Double var2;
    Double ans;
    // mac dinh cac  bieu thuc nay bang false khi nhan vao cac nut thi thanh tru
    Boolean addition = false, subtract = false, multiply = false, divide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_tinh_co);
        edtnumberc = findViewById(R.id.edtnumberc);
        edtnumber = findViewById(R.id.edtnumber);

        btnans = findViewById(R.id.btnans);
        btnbang = findViewById(R.id.btnbang);
        btncham = findViewById(R.id.btncham);
        btntram = findViewById(R.id.btntram);
        //so
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);

        btnx1 = findViewById(R.id.btnx1);

        btnxoa1 = findViewById(R.id.btnxoa1);
        btnxoahet = findViewById(R.id.btnxoahet);

        btnxcanx = findViewById(R.id.btnxcanx);
        btnfact = findViewById(R.id.btnlog10);
        btnmc = findViewById(R.id.btnmc);
        btnngoac1 = findViewById(R.id.btnngoac1);
        btnngoac2 = findViewById(R.id.btnngoac2);
        btnmro = findViewById(R.id.btnmro);
        btnmcong = findViewById(R.id.btnmcong);
        btnmcong = findViewById(R.id.btnmcong);
        btnmtru = findViewById(R.id.btnmtru);
        btnnhan = findViewById(R.id.btnnhan);
        btnchia = findViewById(R.id.btnchia);
        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);

        //button so 1 cho textview 1
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "1");
            }
        });
        //button so 2 cho textview 1
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "2");
            }
        });
        //button so 3 cho textview 1
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "3");
            }
        });
        //button so 4 cho textview 1
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "4");
            }
        });
        //button so 4 cho textview 1
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "5");
            }
        });
        //button so 6 cho textview 1
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "6");
            }
        });
        //button so 7 cho textview 1
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "7");
            }
        });
        //button so 8 cho textview 1
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "8");
            }
        });
        //button so 9 cho textview 1
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "9");
            }
        });
        //button so  cho textview 1
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "0");
            }
        });
        //nut cham
        btncham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + ".");
            }
        });
        //nut %
        btntram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumberc.setText(edtnumberc.getText() + "%");
            }
        });


        //chuc nang log 10
        btnfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorChange(btnfact);
                setVar1();
                ans = Math.log10(var1);
                edtnumber.setText((ans.toString()));
                btnbang.setEnabled(false);
                buttonFalse();
            }
        });
        //gan ham cong bang true thay doi mau va dac lai gia tri
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVar1();
                colorChange(btncong);
                buttonFalse();
                addition = true;
            }
        });

        //To subtract
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVar1();
                colorChange(btntru);
                buttonFalse();
                subtract = true;
            }
        });

        //To multiply
        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVar1();
                colorChange(btnnhan);
                buttonFalse();
                multiply = true;
            }
        });

        //To divide
        btnchia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVar1();
                colorChange(btnchia);
                buttonFalse();
                divide = true;
            }
        });
        //den can bat 2

        //math.cbrt la can 3
        btnxcanx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVar1();
                colorChange(btnxcanx);
                ans = Math.sqrt(var1);

                edtnumber.setText(ans.toString());
                btnbang.setEnabled(false);
                buttonFalse();
            }
        });
        //nut mu 2
        btnx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVar1();
                colorChange(btnx1);
                ans = var1 * var1;
                edtnumber.setText(ans.toString());
                btnbang.setEnabled(false);
                buttonFalse();
            }
        });
        //xoa het
        btnxoahet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allReset();
            }
        });
        //xoa 1 chu

        //m++

        //m--


        //mro

        //nut bang
        btnbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var2 = Double.parseDouble(edtnumberc.getText().toString());
                if (addition) {
                    ans = var1 + var2;
                } else if (subtract) {
                    ans = var1 - var2;
                } else if (multiply) {
                    ans = var1 * var2;
                } else if (divide) {
                    ans = var1 / var2;
                } else {
                    ans = ans + 0;
                }

                edtnumber.setText(ans.toString());
                btnbang.setEnabled(false);
            }
        });

    }

    //Để đặt giá trị val1
    public void setVar1() {
        var1 = Double.parseDouble(edtnumberc.getText().toString());
    }

    // để thiết lập lại tất cả các nút và xem văn bản
    public void allReset() {
        btnmro.setBackgroundColor(getResources().getColor(R.color.gray));
        btnmtru.setBackgroundColor(getResources().getColor(R.color.gray));
        btnngoac2.setBackgroundColor(getResources().getColor(R.color.gray));
        btnngoac1.setBackgroundColor(getResources().getColor(R.color.gray));
        btnxoahet.setBackgroundColor(getResources().getColor(R.color.gray));
        btnxoa1.setBackgroundColor(getResources().getColor(R.color.gray));
        btntoi.setBackgroundColor(getResources().getColor(R.color.gray));
        btnqua.setBackgroundColor(getResources().getColor(R.color.gray));
        btnmc.setBackgroundColor(getResources().getColor(R.color.gray));

        btnx1.setBackgroundColor(getResources().getColor(R.color.gray));
        btnxcanx.setBackgroundColor(getResources().getColor(R.color.gray));
        btnxwx.setBackgroundColor(getResources().getColor(R.color.gray));
        btnxxx.setBackgroundColor(getResources().getColor(R.color.gray));
        btnfact.setBackgroundColor(getResources().getColor(R.color.gray));
        btnnhan.setBackgroundColor(getResources().getColor(R.color.gray));
        btntru.setBackgroundColor(getResources().getColor(R.color.gray));
        btnchia.setBackgroundColor(getResources().getColor(R.color.gray));
        btnmcong.setBackgroundColor(getResources().getColor(R.color.gray));

        btnbang.setEnabled(true);
        btntru.setEnabled(true);
        btnnhan.setEnabled(true);
        btnchia.setEnabled(true);
        btnmcong.setEnabled(true);

        edtnumberc.setText("");
        edtnumber.setText("");

    }

    // Để tắt các nút
    public void buttonFalse() {

        btncong.setEnabled(false);
        btntru.setEnabled(false);
        btnnhan.setEnabled(false);
        btnchia.setEnabled(false);
        edtnumberc.setText("");
    }

    // để thay đổi màu nút
    public void colorChange(Button b) {
        b.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }
}