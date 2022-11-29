package com.auo.shelf.cmsapp.config;

import com.auo.shelf.cmsapp.R;

import java.util.HashMap;
import java.util.Map;

public class RegionType {
    public static final int TYPE_MEDIA = 1;
    public static final int TYPE_MARQUEE = 2;
    public static final int TYPE_HTML = 3;
    public static final int TYPE_STREAM = 4;
    public static final int TYPE_WEATHER = 5;
    public static final int TYPE_TIME = 6;
    public static final int TYPE_SOUND = 7;
    public static final int TYPE_RSS = 8;
    public static final int TYPE_EXCHANGE_RATE = 9;
    public static final int TYPE_YOUTUBE_LIVE = 10;
    public static final int TYPE_PROGRAM_SWITCH = 11;
    public static final int TYPE_THSRC_TIME = 12;
    public static final int TYPE_HLS_LIVE = 13;
    public static final int TYPE_CVS = 14;
    public static final int TYPE_SCHEDULE_QRCODE = 15;
    public static final int TYPE_PRICE_TAG = 16;

    private static HashMap<Integer, Integer> typeResouece = new HashMap<>();

    public RegionType(){
        typeResouece.put(TYPE_MEDIA, R.drawable.region_type_1);
        typeResouece.put(TYPE_MARQUEE, R.drawable.region_type_2);
        typeResouece.put(TYPE_HTML, R.drawable.region_type_3);
    }

    public int getTypeResource(int type){
        return typeResouece.get(type);
    }
}
