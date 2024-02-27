package com.example.yuriihelper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.GameConstants;
import com.example.yuriihelper.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GameActivity extends AppCompat {

    private RadioButton radioButtonBoth;
    private RadioButton radioButtonHe;
    private RadioButton radioButtonShe;
    private TextView textViewPlay;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchMode;
    private TextView textViewAction;
    private TextView textViewPlace;
    private TextView textViewHint;
    private ImageView imageViewHeart;
    private CheckBox checkBoxAnal;
    private GameConstants gameConstants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameConstants = new GameConstants(getApplicationContext());

        initView();
        initAction();
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.button_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.bg_button7_1));

        this.radioButtonBoth = findViewById(R.id.radioButtonBoth);
        this.radioButtonHe = findViewById(R.id.radioButtonHe);
        this.radioButtonShe = findViewById(R.id.radioButtonShe);
        this.textViewPlay = findViewById(R.id.textViewPlay);
        this.switchMode = findViewById(R.id.switchMode);
        this.textViewAction = findViewById(R.id.textViewAction);
        this.textViewPlace = findViewById(R.id.textViewPlace);
        this.textViewHint = findViewById(R.id.textViewHint);
        this.imageViewHeart = findViewById(R.id.imageViewHeart);
        this.checkBoxAnal = findViewById(R.id.checkBoxAnal);
    }

    private void initAction() {
        textViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewPlay.getText().equals(getText(R.string.text_view_play))) {
                    textViewPlay.setText(R.string.text_view_next);
                }
                String[] values;
                if (radioButtonShe.isChecked()) {
                    if (switchMode.isChecked()) {
                        values = getRandomStringValue(gameConstants.getHotShe());
                    } else {
                        values = getRandomStringValue(gameConstants.getWarmUpShe());
                    }
                    textViewAction.setText(values[0]);
                    textViewPlace.setText(values[1]);
                }

                if (radioButtonHe.isChecked()) {
                    if (switchMode.isChecked()) {
                        values = getRandomStringValue(gameConstants.getHotHe(checkBoxAnal.isChecked()));
                    } else {
                        values = getRandomStringValue(gameConstants.getWarmUpHe());
                    }
                    textViewAction.setText(values[0]);
                    textViewPlace.setText(values[1]);
                }
            }
        });
    }

    private String[] getRandomStringValue(HashMap<String, List<String>> mapList) {

        Random random = new Random();

        List<String> keys = new ArrayList<>(mapList.keySet());
        String randomPlace = keys.get(random.nextInt(keys.size()));

        List<String> values = mapList.get(randomPlace);
        String randomAction = values.get(random.nextInt(values.size()));

        String[] randomResults = {randomAction, randomPlace};
        return randomResults;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}