package com.example.yuriihelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends BaseActivity {
    
    TextView textViewCar;
    TextView textViewMoney;
    TextView textViewStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main menu");
        
        initView();
    }

    private void initView() {
        textViewCar = findViewById(R.id.text_view_car);
        textViewMoney = findViewById(R.id.text_view_money);
        textViewStatistic = findViewById(R.id.text_view_statistic);
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
            default:
                break;
        }
    }
}