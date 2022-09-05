package com.example.yuriihelper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppCompat extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper localeHelper = new LocaleHelper(this);
        localeHelper.updateResource(localeHelper.getLang());
    }
}
