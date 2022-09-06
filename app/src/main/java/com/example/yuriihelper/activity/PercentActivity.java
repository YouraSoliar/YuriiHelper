package com.example.yuriihelper.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.R;

public class PercentActivity extends AppCompat {

    private TextView textViewResult;
    private EditText editTextNumber;
    private EditText editTextPercent;
    private TextView textViewCalculate;
    private TextView textViewPercentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);

        initView();
        initAction();
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.button_percent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button7_2));

        this.textViewResult = findViewById(R.id.textViewResult);
        this.editTextNumber = findViewById(R.id.editTextNumber);
        this.editTextPercent = findViewById(R.id.editTextPercent);
        this.textViewCalculate = findViewById(R.id.textViewCalculate);
        this.textViewPercentOperation = findViewById(R.id.textViewPercentOperation);
    }

    private void initAction() {



        textViewPercentOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPercentDialog();
            }
        });

        textViewCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNumeric(editTextNumber.getText().toString()) && isNumeric(editTextPercent.getText().toString())) {
                    int number = Integer.parseInt(String.valueOf(editTextNumber.getText()));
                    int percent = Integer.parseInt(String.valueOf(editTextPercent.getText()));
                    float result = 0;
                    if (textViewPercentOperation.getText().toString().equals("%")) {
                        result = (float) ((percent * number) / 100);
                        textViewResult.setText(String.valueOf(result));
                    } else {
                        result = (float) ((percent * 100) / number);
                        textViewResult.setText(String.valueOf(result) + " %");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_incorrect_values, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private void showPercentDialog() {
        Resources resources = getResources();
        String[] percentArray = resources.getStringArray(R.array.spinner_percent);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(PercentActivity.this);
        mBuilder.setTitle(R.string.dialog_percent);
        mBuilder.setSingleChoiceItems(R.array.spinner_percent, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    textViewPercentOperation.setText(percentArray[0]);
                } else {
                    textViewPercentOperation.setText(percentArray[1]);
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