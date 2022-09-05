package com.example.yuriihelper.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.yuriihelper.R;

public class MainActivity extends AppCompatActivity {

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
            default:
                break;
        }
    }

}