package com.example.yuriihelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity {

    TextView textViewGoogleForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getSupportActionBar().setTitle("Statistic");

        textViewGoogleForm = findViewById(R.id.text_view_google_form);
    }

    public void openLink(View view) {
        Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfyXHTmiNAn5dg14Ella_ncSQiG81LXuLaMLo3XGTcIXUUwXA/viewform?usp=sf_link");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}