package com.example.yuriihelper;

import android.os.Bundle;

public class MoneyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        getSupportActionBar().setTitle("Money");
    }
}