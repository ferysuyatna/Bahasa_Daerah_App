package com.bahasadaerahapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bahasadaerahapp.kamus.KamusActivity;
import com.bahasadaerahapp.kuis.KuisActivity;
import com.bahasadaerahapp.kursus.KursusActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewName;
    private ImageView ivKamus, ivKuis, ivKursus, ivTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewName = findViewById(R.id.tv_username);
        ivKamus = findViewById(R.id.iv_kamus);
        ivKuis = findViewById(R.id.iv_kuis);
        ivKursus = findViewById(R.id.iv_kursus);
        ivTentang = findViewById(R.id.iv_tentang);

        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);

        ivKamus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KamusActivity.class);
                startActivity(intent);
            }
        });

        ivKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KuisActivity.class);
                startActivity(intent);
            }
        });

        ivKursus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KursusActivity.class);
                startActivity(intent);
            }
        });

        ivTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TentangActivity.class);
                startActivity(intent);
            }
        });


    }
}
