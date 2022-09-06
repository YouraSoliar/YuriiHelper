package com.example.yuriihelper.activity;

import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.R;

public class TeamScoreActivity extends AppCompat {

    private int team1 = 0;
    private int team2 = 0;
    private TextView textViewTeam1;
    private TextView textViewTeam2;
    private TextView textViewReset;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_score);

        initView();

        sharedPreferences = getSharedPreferences("Points", MODE_PRIVATE);
        team1 = sharedPreferences.getInt("team1", 0);
        team2 = sharedPreferences.getInt("team2", 0);

        textViewTeam1.setText(String.valueOf(team1));
        textViewTeam2.setText(String.valueOf(team2));

        initAction();
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.button_team_score);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button5_2));

        this.textViewTeam1 = findViewById(R.id.textViewTeam1);
        this.textViewTeam2 = findViewById(R.id.textViewTeam2);
        this.textViewReset = findViewById(R.id.textViewReset);
    }

    private void initAction() {
        textViewTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoint(textViewTeam1);
            }
        });

        textViewTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoint(textViewTeam2);
            }
        });

        textViewReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team1 = 0;
                team2 = 0;
                textViewTeam1.setText("" + team1);
                textViewTeam2.setText("" + team2);
            }
        });
    }

    public void addPoint(TextView textView) {
        if (textView == textViewTeam1) {
            ++team1;
            textView.setText("" + team1);
        } else {
            ++team2;
            textView.setText("" + team2);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("team1", team1);
        editor.putInt("team2", team2);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}