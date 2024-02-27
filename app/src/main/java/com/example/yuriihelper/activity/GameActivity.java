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
import com.example.yuriihelper.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initView();
        initAction();
    }

    private void initAction() {
        textViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewPlay.getText().equals(getText(R.string.text_view_play))) {
                    textViewPlay.setText(R.string.text_view_next);
                }
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}