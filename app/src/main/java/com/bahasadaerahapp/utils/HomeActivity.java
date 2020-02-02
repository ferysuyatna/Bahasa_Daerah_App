package com.bahasadaerahapp.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bahasadaerahapp.R;
import com.bahasadaerahapp.kamus.DictionaryLanguageListActivity;
import com.bahasadaerahapp.kuis.QuizListLanguageActivity;
import com.bahasadaerahapp.kursus.CourseLanguageListActivity;
import com.bahasadaerahapp.kursus.KursusActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewName;
    private ImageView imageViewLogout;
    private LinearLayout llKamus, llKuis, llKursus, llTentang;
    private Boolean session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // getSupportActionBar().hide();

        textViewName = findViewById(R.id.tv_username);
        llKamus = findViewById(R.id.ll_kamus);
        llKuis = findViewById(R.id.ll_kuis);
        llKursus = findViewById(R.id.ll_kursus);
        llTentang = findViewById(R.id.ll_tentang);


//        String nameFromIntent = getIntent().getStringExtra("EMAIL");
//        String[] name = nameFromIntent.split("@");
//        textViewName.setText(name[0]);

        Intent intent = getIntent();
        if (intent.hasExtra("EMAIL")){
//            String nameFromIntent = getIntent().getStringExtra("EMAIL");
//            textViewName.setText("Welcome " + nameFromIntent);
            String nameFromIntent = getIntent().getStringExtra("EMAIL");
            String[] name = nameFromIntent.split("@");
            textViewName.setText(name[0]);
        }else{
            String email = PreferenceUtils.getEmail(this);
          //  textViewName.setText("Welcome " + email);
            String[] name = email.split("@");
            textViewName.setText(name[0]);

        }



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
                Intent intent = new Intent(HomeActivity.this, QuizListLanguageActivity.class);
                startActivity(intent);
            }
        });

        llKursus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CourseLanguageListActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.log_out:
                PreferenceUtils.savePassword(null, this);
                PreferenceUtils.saveEmail(null, this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
