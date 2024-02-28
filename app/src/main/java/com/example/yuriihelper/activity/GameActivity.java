package com.example.yuriihelper.activity;

import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.yuriihelper.AppCompat;
import com.example.yuriihelper.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    private boolean isHe = false;
    private boolean isAnal = false;
    private HashMap<String, List<String>> warmUpHe;
    private HashMap<String, List<String>> warmUpShe;
    private HashMap<String, List<String>> hotHe;
    private HashMap<String, List<String>> hotShe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initView();
        initAction();
        initMaps();

        imageAnimation(false, true);
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
                    isHe = 1 == Math.round(Math.random());
                    textViewPlay.setText(R.string.text_view_next);
                    imageAnimation(true, false);
                } else {
                    imageAnimation(false, false);
                }

                String[] values;
                if (radioButtonShe.isChecked()) {
                    textViewHint.setText(R.string.text_view_hint_for_her);
                    if (switchMode.isChecked()) {
                        values = getRandomStringValue(hotShe);
                    } else {
                        values = getRandomStringValue(warmUpShe);
                    }
                    textViewAction.setText(values[0]);
                    textViewPlace.setText(values[1]);
                }

                if (radioButtonHe.isChecked()) {
                    textViewHint.setText(R.string.text_view_hint_for_him);
                    if (switchMode.isChecked()) {
                        values = getRandomStringValue(getHotHe());
                    } else {
                        values = getRandomStringValue(warmUpHe);
                    }
                    textViewAction.setText(values[0]);
                    textViewPlace.setText(values[1]);
                }

                if (radioButtonBoth.isChecked()) {
                    if (isHe) {
                        isHe = false;
                        textViewHint.setText(R.string.text_view_hint_for_him);
                        if (switchMode.isChecked()) {
                            values = getRandomStringValue(getHotHe());
                        } else {
                            values = getRandomStringValue(warmUpHe);
                        }
                    } else {
                        isHe = true;
                        textViewHint.setText(R.string.text_view_hint_for_her);
                        if (switchMode.isChecked()) {
                            values = getRandomStringValue(hotShe);
                        } else {
                            values = getRandomStringValue(warmUpShe);
                        }
                    }

                    textViewAction.setText(values[0]);
                    textViewPlace.setText(values[1]);
                }
            }
        });
    }

    private void imageAnimation(boolean isStart, boolean isOpen) {
        textViewAction.setVisibility(View.GONE);
        textViewPlace.setVisibility(View.GONE);
        textViewHint.setVisibility(View.GONE);

        ObjectAnimator animatorEnlargeX = ObjectAnimator.ofFloat(imageViewHeart, "scaleX", 1f, 3.555f);
        ObjectAnimator animatorEnlargeY = ObjectAnimator.ofFloat(imageViewHeart, "scaleY", 1f, 3.555f);
        animatorEnlargeX.setDuration(500);
        animatorEnlargeY.setDuration(500);// Resize in 0.5 seconds

        ObjectAnimator animatorShrinkX = ObjectAnimator.ofFloat(imageViewHeart, "scaleX", 3.555f, 1f);
        ObjectAnimator animatorShrinkY = ObjectAnimator.ofFloat(imageViewHeart, "scaleY", 3.555f, 1f);
        animatorShrinkX.setDuration(500);
        animatorShrinkY.setDuration(500);
        animatorShrinkX.setStartDelay(500);
        animatorShrinkY.setStartDelay(500);// Resize in 0.5 seconds

        AlphaAnimation animationVisibility = new AlphaAnimation(0.0f, 1.0f);
        animationVisibility.setDuration(1000); // 1 second
        animationVisibility.setFillAfter(true);

        if (isOpen) {
            animatorEnlargeX.start();
            animatorEnlargeY.start();
        }

        if (isStart && !isOpen) {
            textViewAction.startAnimation(animationVisibility);
            textViewAction.setVisibility(View.VISIBLE);
            textViewPlace.startAnimation(animationVisibility);
            textViewPlace.setVisibility(View.VISIBLE);
            textViewHint.startAnimation(animationVisibility);
            textViewHint.setVisibility(View.VISIBLE);
            animatorShrinkX.start();
            animatorShrinkY.start();
        }

        if(!isStart && !isOpen) {
            animatorEnlargeX.start();
            animatorEnlargeY.start();
            textViewAction.startAnimation(animationVisibility);
            textViewAction.setVisibility(View.VISIBLE);
            textViewPlace.startAnimation(animationVisibility);
            textViewPlace.setVisibility(View.VISIBLE);
            textViewHint.startAnimation(animationVisibility);
            textViewHint.setVisibility(View.VISIBLE);
            animatorShrinkX.start();
            animatorShrinkY.start();
        }
    }

    private HashMap<String, List<String>> getHotHe () {
        if (isAnal != checkBoxAnal.isChecked()) {
            isAnal = checkBoxAnal.isChecked();
            hotHe.clear();
            hotHe.put(this.getString(R.string.place_vagina), Arrays.asList(this.getString(R.string.action_lick), this.getString(R.string.action_sniff), this.getString(R.string.action_kiss), this.getString(R.string.action_massage), this.getString(R.string.action_tease), this.getString(R.string.action_jerk_off)));
            hotHe.put(this.getString(R.string.place_clitoris), Arrays.asList(this.getString(R.string.action_tease), this.getString(R.string.action_kiss), this.getString(R.string.action_lick), this.getString(R.string.action_suck)));
            hotHe.put(this.getString(R.string.place_breast), Arrays.asList(this.getString(R.string.action_kiss), this.getString(R.string.action_massage), this.getString(R.string.action_lick), this.getString(R.string.action_squeeze)));
            hotHe.put(this.getString(R.string.place_nipples), Arrays.asList(this.getString(R.string.action_suck), this.getString(R.string.action_kiss), this.getString(R.string.action_bite), this.getString(R.string.action_lick)));
            if (isAnal) {
                hotHe.put(this.getString(R.string.place_anal), Arrays.asList(this.getString(R.string.action_lick), this.getString(R.string.action_massage), this.getString(R.string.action_tease), this.getString(R.string.action_finger)));
            }
        }

        return hotHe;
    }

    public void initMaps() {
        warmUpHe = new HashMap<>();
        warmUpHe.put(this.getString(R.string.place_ear), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss), this.getString(R.string.action_bite)));
        warmUpHe.put(this.getString(R.string.place_lips), Arrays.asList(this.getString(R.string.action_kiss), this.getString(R.string.action_bite)));
        warmUpHe.put(this.getString(R.string.place_neck), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss), this.getString(R.string.action_bite), this.getString(R.string.action_sniff)));
        warmUpHe.put(this.getString(R.string.place_arms), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss)));
        warmUpHe.put(this.getString(R.string.place_back), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss)));
        warmUpHe.put(this.getString(R.string.place_stomach), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss), this.getString(R.string.action_bite)));
        warmUpHe.put(this.getString(R.string.place_ass), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss), this.getString(R.string.action_bite), this.getString(R.string.action_squeeze), this.getString(R.string.action_slap)));
        warmUpHe.put(this.getString(R.string.place_legs), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss)));

        warmUpShe = new HashMap<>();
        warmUpShe.put(this.getString(R.string.place_ear), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss), this.getString(R.string.action_bite)));
        warmUpShe.put(this.getString(R.string.place_lips), Arrays.asList(this.getString(R.string.action_kiss), this.getString(R.string.action_bite)));
        warmUpShe.put(this.getString(R.string.place_neck), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss), this.getString(R.string.action_bite), this.getString(R.string.action_sniff)));
        warmUpShe.put(this.getString(R.string.place_arms), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss)));
        warmUpShe.put(this.getString(R.string.place_back), Arrays.asList(this.getString(R.string.action_massage)));
        warmUpShe.put(this.getString(R.string.place_stomach), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_bite)));
        warmUpShe.put(this.getString(R.string.place_breast), Arrays.asList(this.getString(R.string.action_massage), this.getString(R.string.action_kiss)));

        hotShe = new HashMap<>();
        hotShe.put(this.getString(R.string.place_dick), Arrays.asList(this.getString(R.string.action_jerk_off), this.getString(R.string.action_kiss), this.getString(R.string.action_lick), this.getString(R.string.action_slap), this.getString(R.string.action_tease), this.getString(R.string.action_massage), this.getString(R.string.action_squeeze), this.getString(R.string.action_sniff), this.getString(R.string.action_suck), this.getString(R.string.action_bite)));
        hotShe.put(this.getString(R.string.place_eggs), Arrays.asList(this.getString(R.string.action_kiss), this.getString(R.string.action_lick), this.getString(R.string.action_squeeze), this.getString(R.string.action_massage)));
        hotShe.put(this.getString(R.string.place_penis_head), Arrays.asList(this.getString(R.string.action_lick), this.getString(R.string.action_kiss), this.getString(R.string.action_suck), this.getString(R.string.action_tease)));

        hotHe = new HashMap<>();
        hotHe.put(this.getString(R.string.place_vagina), Arrays.asList(this.getString(R.string.action_lick), this.getString(R.string.action_sniff), this.getString(R.string.action_kiss), this.getString(R.string.action_massage), this.getString(R.string.action_tease), this.getString(R.string.action_jerk_off)));
        hotHe.put(this.getString(R.string.place_clitoris), Arrays.asList(this.getString(R.string.action_tease), this.getString(R.string.action_kiss), this.getString(R.string.action_lick), this.getString(R.string.action_suck), this.getString(R.string.action_finger)));
        hotHe.put(this.getString(R.string.place_breast), Arrays.asList(this.getString(R.string.action_kiss), this.getString(R.string.action_massage), this.getString(R.string.action_lick), this.getString(R.string.action_squeeze)));
        hotHe.put(this.getString(R.string.place_nipples), Arrays.asList(this.getString(R.string.action_suck), this.getString(R.string.action_kiss), this.getString(R.string.action_bite), this.getString(R.string.action_lick)));
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