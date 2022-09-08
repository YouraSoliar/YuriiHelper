package com.example.yuriihelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.R;

public class MainActivity extends AppCompat {

    private boolean toSettings = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAction();
    }

    private void initAction() {
    }

    private void initView() {
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.black));
        getSupportActionBar().setTitle(R.string.main_menu);
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
            case R.id.text_view_bank:
                intent = new Intent(getApplicationContext(), BankActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_percent:
                intent = new Intent(getApplicationContext(), PercentActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_team_score:
                intent = new Intent(getApplicationContext(), TeamScoreActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_weather:
                intent = new Intent(getApplicationContext(), WeatherActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_random:
                intent = new Intent(getApplicationContext(), RandomActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.support_settings) {
            toSettings = true;
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (toSettings) {
            toSettings = false;
            recreate();
        }
    }
}