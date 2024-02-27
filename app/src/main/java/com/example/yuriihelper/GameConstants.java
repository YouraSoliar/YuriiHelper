package com.example.yuriihelper;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class GameConstants {

    private Resources res;
    private Context context;
    private Map<String, ArrayList<String>> warmUpHe;
    private Map<String, ArrayList<String>> warmUpShe;
    private Map<String, ArrayList<String>> hotHe;
    private Map<String, ArrayList<String>> hotShe;
    private boolean anal = false;

    public GameConstants(Context context) {
        this.context = context;
        res = context.getResources();

        initMaps();
    }

    private void initMaps() {
        warmUpHe.put(res.getString(R.string.place_ear), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpHe.put(res.getString(R.string.place_lips), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpHe.put(res.getString(R.string.place_neck), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_sniff)));
        warmUpHe.put(res.getString(R.string.place_arms), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));
        warmUpHe.put(res.getString(R.string.place_back), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));
        warmUpHe.put(res.getString(R.string.place_stomach), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpHe.put(res.getString(R.string.place_ass), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_squeeze), res.getString(R.string.action_slap)));
        warmUpHe.put(res.getString(R.string.place_legs), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));

        warmUpShe.put(res.getString(R.string.place_ear), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpShe.put(res.getString(R.string.place_lips), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_bite)));
        warmUpShe.put(res.getString(R.string.place_neck), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_sniff)));
        warmUpShe.put(res.getString(R.string.place_arms), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));
        warmUpShe.put(res.getString(R.string.place_back), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage)));
        warmUpShe.put(res.getString(R.string.place_stomach), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_bite)));
        warmUpShe.put(res.getString(R.string.place_breast), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_massage), res.getString(R.string.action_kiss)));

        hotShe.put(res.getString(R.string.place_dick), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_jerk_off), res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_slap), res.getString(R.string.action_tease), res.getString(R.string.action_massage), res.getString(R.string.action_squeeze), res.getString(R.string.action_sniff), res.getString(R.string.action_suck), res.getString(R.string.action_bite)));
        hotShe.put(res.getString(R.string.place_eggs), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_squeeze), res.getString(R.string.action_massage)));
        hotShe.put(res.getString(R.string.place_penis_head), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_kiss), res.getString(R.string.action_suck), res.getString(R.string.action_tease)));

        hotHe.put(res.getString(R.string.place_vagina), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_sniff), res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_tease), res.getString(R.string.action_jerk_off)));
        hotHe.put(res.getString(R.string.place_clitoris), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_tease), res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_suck)));
        hotHe.put(res.getString(R.string.place_breast), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_lick), res.getString(R.string.action_squeeze)));
        hotHe.put(res.getString(R.string.place_nipples), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_suck), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_lick)));

    }

    public Map<String, ArrayList<String>> getWarmUpHe() {
        return warmUpHe;
    }

    public Map<String, ArrayList<String>> getWarmUpShe() {
        return warmUpShe;
    }

    public Map<String, ArrayList<String>> getHotHe(boolean isAnal) {
        if (isAnal != anal) {
            anal = isAnal;
            hotHe.clear();
            hotHe.put(res.getString(R.string.place_vagina), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_sniff), res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_tease), res.getString(R.string.action_jerk_off)));
            hotHe.put(res.getString(R.string.place_clitoris), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_tease), res.getString(R.string.action_kiss), res.getString(R.string.action_lick), res.getString(R.string.action_suck)));
            hotHe.put(res.getString(R.string.place_breast), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_kiss), res.getString(R.string.action_massage), res.getString(R.string.action_lick), res.getString(R.string.action_squeeze)));
            hotHe.put(res.getString(R.string.place_nipples), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_suck), res.getString(R.string.action_kiss), res.getString(R.string.action_bite), res.getString(R.string.action_lick)));
            if (isAnal) {
                hotHe.put(res.getString(R.string.place_anal), (ArrayList<String>) Arrays.asList(res.getString(R.string.action_lick), res.getString(R.string.action_massage), res.getString(R.string.action_tease)));
            }
        }

        return hotHe;
    }

    public Map<String, ArrayList<String>> getHotShe() {
        return hotShe;
    }
}
