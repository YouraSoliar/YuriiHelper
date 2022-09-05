package com.example.yuriihelper.activitiy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.LocaleHelper;
import com.example.yuriihelper.R;


public class SettingsActivity extends AppCompat {

    private TextView textViewLanguage;
    private LocaleHelper localeHelper;

    private Resources resources;
    private String[] languagesArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        localeHelper = new LocaleHelper(this);

        initView();
        initAction();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.support_settings);
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.gray));

        this.textViewLanguage = findViewById(R.id.textViewLanguage);

    }

    private void initAction() {
        resources = getResources();
        languagesArray = resources.getStringArray(R.array.spinner_language);

        textViewLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });


        String lang = localeHelper.getLang();
        if (lang.equals("en")) {
            textViewLanguage.setText(languagesArray[0]);
        } else if (lang.equals("uk")) {
            textViewLanguage.setText(languagesArray[1]);
        } else if (lang.equals("ru")) {
            textViewLanguage.setText(languagesArray[2]);
        }
    }

    private void showChangeLanguageDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
        mBuilder.setTitle(R.string.dialog_language);
        mBuilder.setSingleChoiceItems(R.array.spinner_language, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        localeHelper.updateResource("en");
                        recreate();
                        break;
                    case 1:
                        localeHelper.updateResource("uk");
                        recreate();
                        break;
                    case 2:
                        localeHelper.updateResource("ru");
                        recreate();
                        break;
                    default:
                        break;
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}