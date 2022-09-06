package com.example.yuriihelper.activity;

import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.R;

public class StatisticActivity extends AppCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        initView();
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.button_statistic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button2_2));
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