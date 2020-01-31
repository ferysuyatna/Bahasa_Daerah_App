package com.bahasadaerahapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        LinearLayout emailFery, emailAji, emailDinu;
        emailFery = findViewById(R.id.fery);
        emailAji = findViewById(R.id.aji);
        emailDinu = findViewById(R.id.dinu);

        clicked(emailFery, getResources().getString(R.string.email_of_fery));
        clicked(emailAji, getResources().getString(R.string.email_of_hadiyan));
        clicked(emailDinu, getResources().getString(R.string.email_of_dinu));
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void clicked(LinearLayout peopleClicked, final String email){
        peopleClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(new String[]{email}, getResources().getString(R.string.app_name));
            }
        });
    }
}
