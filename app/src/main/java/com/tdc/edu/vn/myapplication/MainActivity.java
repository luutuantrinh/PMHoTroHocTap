package com.tdc.edu.vn.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.tdc.edu.vn.myapplication.maytinhcoban.MayTinhCo;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    CardView cal_card;
    CardView conver_card;
    CardView chemis_card;
    CardView math_card;
    CardView qr_card;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        cal_card = findViewById(R.id.cal_card);
        cal_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MayTinhCo.class);
                startActivity(intent);

            }
        });

        chemis_card = findViewById(R.id.chemis_card);
        chemis_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, FirebaseSearch_activity.class);
                startActivity(intent1);

            }
        });


        /*---------------------Hooks------------------------*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);

        /*---------------------Toolbar------------------------*/
        setSupportActionBar(toolbar);

        /*-----------Navigation Drawer Menu-------------------*/


        //hide or show item
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setCheckedItem(R.id.nav_home);

    }

    /*------ To avoid closing the application  on Back pressed ------*/
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_cal:
                Intent intent = new Intent(MainActivity.this, MayTinhCo.class);
                startActivity(intent);
                break;
            case R.id.nav_chemistry:
                Intent intent1 = new Intent(MainActivity.this, FirebaseSearch_activity.class);
                startActivity(intent1);
                break;
            case R.id.nav_login:
                menu.findItem(R.id.nav_logout).setVisible(true);
                menu.findItem(R.id.nav_profile).setVisible(true);
                menu.findItem(R.id.nav_login).setVisible(false);
                break;
            case R.id.nav_logout:
                menu.findItem(R.id.nav_logout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_login).setVisible(true);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
