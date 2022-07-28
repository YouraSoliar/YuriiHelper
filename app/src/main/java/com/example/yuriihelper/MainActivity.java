package com.example.yuriihelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {
    
    private TextView textViewCar;
    private TextView textViewMoney;
    private TextView textViewStatistic;
    private TextView textViewNotification;

    private TextView textViewDollar;
    private TextView textViewOldDollar;
    private TextView textViewEuro;
    private TextView textViewOldEuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main menu");
        
        initView();
        initAction();
    }

    private void initAction() {
        double dollar = 39.0;
        double oldDollar = 38.0;
        double euro = 43.0;
        double oldEuro = 44.0;

        textViewDollar.setText(String.valueOf(dollar));
        textViewOldDollar.setText(String.valueOf(oldDollar));
        textViewEuro.setText(String.valueOf(euro));
        textViewOldEuro.setText(String.valueOf(oldEuro));

        if (dollar > oldDollar) {
            textViewDollar.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            textViewDollar.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        if (euro > oldEuro) {
            textViewEuro.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            textViewEuro.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
    }

    private void initView() {
        textViewCar = findViewById(R.id.text_view_car);
        textViewMoney = findViewById(R.id.text_view_money);
        textViewStatistic = findViewById(R.id.text_view_statistic);
        textViewNotification = findViewById(R.id.text_view_notification);

        textViewDollar = findViewById(R.id.textViewDollar);
        textViewOldDollar = findViewById(R.id.textViewOldDollar);
        textViewEuro = findViewById(R.id.textViewEuro);
        textViewOldEuro = findViewById(R.id.textViewOldEuro);
    }


    public void onClickTextView(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.text_view_car:
                intent = new Intent(getApplicationContext(), CarActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_money:
                intent = new Intent(getApplicationContext(), MoneyActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_statistic:
                intent = new Intent(getApplicationContext(), StatisticActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_notification:
                intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}