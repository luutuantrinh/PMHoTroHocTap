package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class LayoutTestActivity extends AppCompatActivity {

    public static String SYSTEM_LANGUAGE = "vi";
    private Button btnConvert, btnLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_test);

        //Find view
        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnLanguage = (Button) findViewById(R.id.btnSettingsLanguage);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale(SYSTEM_LANGUAGE);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(
                        config,
                        getBaseContext().getResources().getDisplayMetrics()
                );

                Toast.makeText(LayoutTestActivity.this, LayoutTestActivity.SYSTEM_LANGUAGE, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LayoutTestActivity.this, ListviewMenuChuyenDoi.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                finish();
                startActivity(intent);
            }
        });

        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale(SYSTEM_LANGUAGE);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(
                        config,
                        getBaseContext().getResources().getDisplayMetrics()
                );

                Toast.makeText(LayoutTestActivity.this, LayoutTestActivity.SYSTEM_LANGUAGE, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LayoutTestActivity.this, LanguageSettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}