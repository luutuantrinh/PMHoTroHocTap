package com.tdc.edu.vn.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityDangNhap extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText edtUser;
    EditText edtPassword;
    Button btnDangNhap;
    Button btnDangKy;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtUser = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        btnDangNhap = (Button) findViewById(R.id.DangNhap);
        btnDangKy = (Button) findViewById(R.id.DangKy);
        mAuth = FirebaseAuth.getInstance();
        setTitle("");

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ActivityDangNhap.this,ActivityDangKy.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    private void DangNhap(){
        final String email = edtUser.getText().toString();
        final String password = edtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivityDangNhap.this,"Đâng Nhập Thành Công!",Toast.LENGTH_SHORT).show();
                            intent = new Intent(ActivityDangNhap.this,ListviewMenuChuyenDoi.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ActivityDangNhap.this,"Đâng Nhập Thất Bại !!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
