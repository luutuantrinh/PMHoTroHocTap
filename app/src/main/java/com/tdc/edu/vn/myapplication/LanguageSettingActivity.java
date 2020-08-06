package com.tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageSettingActivity extends AppCompatActivity {

    private TextView lblTitle;
    private ListView listLanguage;
    private ArrayList<String> languages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);

        //Create language package name
        languages.add(getResources().getString(R.string.lbl_Vietnamese));
        //languages.add(getResources().getString(R.string.lbl_Hangul));
        languages.add(getResources().getString(R.string.lbl_English));

        //Get view
        lblTitle = (TextView) findViewById(R.id.lblTitleLanguageSetting);
        listLanguage = (ListView) findViewById(R.id.listLanguage);

        //
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);
        listLanguage.setAdapter(adapter);

        listLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    finish();
                    startActivity(getIntent());
                    settingLanguage("vi");
                    MainActivity.SYSTEM_LANGUAGE = "vi";
                } else {
                    finish();
                    startActivity(getIntent());
                    settingLanguage("en");
                    MainActivity.SYSTEM_LANGUAGE = "en";
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void settingLanguage(String language) {
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                config,
                getBaseContext().getResources().getDisplayMetrics()
        );
        Intent intent = new Intent(LanguageSettingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}