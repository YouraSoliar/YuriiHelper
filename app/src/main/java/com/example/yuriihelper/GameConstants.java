package com.example.yuriihelper;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameConstants {

    private Resources res;
    private Context context;
    private HashMap<String, List<String>> warmUpHe;
    private HashMap<String, List<String>> warmUpShe;
    private HashMap<String, List<String>> hotHe;
    private HashMap<String, List<String>> hotShe;
    private boolean anal = false;

    public GameConstants(Context context) {
        this.context = context;
        res = context.getResources();

        initMaps();
    }

    private void initMaps() {
        warmUpHe = new HashMap<>();
        warmUpHe.put(res.getString(R.string.place_ear), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpHe.put(res.getString(R.string.place_lips), Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpHe.put(res.getString(R.string.place_neck), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_sniff)));
        warmUpHe.put(res.getString(R.string.place_arms), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));
        warmUpHe.put(res.getString(R.string.place_back), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));
        warmUpHe.put(res.getString(R.string.place_stomach), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpHe.put(res.getString(R.string.place_ass), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_squeeze), res.getString(R.string.action_slap)));
        warmUpHe.put(res.getString(R.string.place_legs), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));

        warmUpShe = new HashMap<>();
        warmUpShe.put(res.getString(R.string.place_ear), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpShe.put(res.getString(R.string.place_lips), Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpShe.put(res.getString(R.string.place_neck), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_sniff)));
        warmUpShe.put(res.getString(R.string.place_arms), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));
        warmUpShe.put(res.getString(R.string.place_back), Arrays.asList(res.getString(R.string.action_massage)));
        warmUpShe.put(res.getString(R.string.place_stomach), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_bite)));
        warmUpShe.put(res.getString(R.string.place_breast), Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));

        hotShe = new HashMap<>();
        hotShe.put(res.getString(R.string.place_dick), Arrays.asList(res.getString(R.string.action_jerk_off), res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_slap), res.getString(R.string.action_tease), res.getString(R.string.action_massage), res.getString(R.string.action_squeeze), res.getString(R.string.action_sniff), res.getString(R.string.action_suck), res.getString(R.string.action_bite)));
        hotShe.put(res.getString(R.string.place_eggs), Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_squeeze), res.getString(R.string.action_massage)));
        hotShe.put(res.getString(R.string.place_penis_head), Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_kiss), res.getString(R.string.action_suck), res.getString(R.string.action_tease)));

        hotHe = new HashMap<>();
        hotHe.put(res.getString(R.string.place_vagina), Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_sniff), res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_tease), res.getString(R.string.action_jerk_off)));
        hotHe.put(res.getString(R.string.place_clitoris), Arrays.asList(res.getString(R.string.action_tease), res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_suck)));
        hotHe.put(res.getString(R.string.place_breast), Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_lick), res.getString(R.string.action_squeeze)));
        hotHe.put(res.getString(R.string.place_nipples), Arrays.asList(res.getString(R.string.action_suck), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_lick)));

    }

    public HashMap<String, List<String>> getWarmUpHe() {
        return warmUpHe;
    }

    public HashMap<String, List<String>> getWarmUpShe() {
        return warmUpShe;
    }

    public HashMap<String, List<String>> getHotHe(boolean isAnal) {
        if (isAnal != anal) {
            anal = isAnal;
            hotHe.clear();
            hotHe.put(res.getString(R.string.place_vagina), Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_sniff), res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_tease), res.getString(R.string.action_jerk_off)));
            hotHe.put(res.getString(R.string.place_clitoris), Arrays.asList(res.getString(R.string.action_tease), res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_suck)));
            hotHe.put(res.getString(R.string.place_breast), Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_lick), res.getString(R.string.action_squeeze)));
            hotHe.put(res.getString(R.string.place_nipples), Arrays.asList(res.getString(R.string.action_suck), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_lick)));
            if (isAnal) {
                hotHe.put(res.getString(R.string.place_anal), Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_massage), res.getString(R.string.action_tease)));
            }
        }

        return hotHe;
    }

    public HashMap<String, List<String>> getHotShe() {
        return hotShe;
    }
}
