package com.example.yuriihelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        initView();
    }

    private void initView() {
        getSupportActionBar().setTitle("Statistic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button2));
    }

    public void openLink(View view) {
        Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfyXHTmiNAn5dg14Ella_ncSQiG81LXuLaMLo3XGTcIXUUwXA/viewform?usp=sf_link");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}