package com.example.blogapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon,new FragmentHome()).commit();




        btm = findViewById(R.id.bottombarmenu);
        btm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case(R.id.bottombarmenu_home):
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon,new FragmentHome()).commit();
                        break;
                    case(R.id.bottombarmenu_profile):
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon,new FragmentProfile()).commit();
                        break;
                }
                return true;
            }
        });
    }
}