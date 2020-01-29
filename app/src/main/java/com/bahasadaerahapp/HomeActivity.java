package com.bahasadaerahapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bahasadaerahapp.kamus.DictionaryLanguageListActivity;
import com.bahasadaerahapp.kamus.KamusActivity;
import com.bahasadaerahapp.kuis.KuisActivity;
import com.bahasadaerahapp.kursus.KursusActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewName;
    private LinearLayout llKamus, llKuis, llKursus, llTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        textViewName = findViewById(R.id.tv_username);
        llKamus = findViewById(R.id.ll_kamus);
        llKuis = findViewById(R.id.ll_kuis);
        llKursus = findViewById(R.id.ll_kursus);
        llTentang = findViewById(R.id.ll_tentang);

//        String nameFromIntent = getIntent().getStringExtra("EMAIL");
//        String[] name = nameFromIntent.split("@");
//        textViewName.setText(name[0]);

        llKamus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DictionaryLanguageListActivity.class);
                startActivity(intent);
            }
        });

        llKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KuisActivity.class);
                startActivity(intent);
            }
        });

        llKursus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KursusActivity.class);
                startActivity(intent);
            }
        });

        llTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TentangActivity.class);
                startActivity(intent);
            }
        });


    }
}
