package com.auo.shelf.cmsapp.json;

import android.util.Log;

import com.auo.shelf.cmsapp.bean.DashboardBean;

import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard {

    public boolean fromPlaybackSummaryJson(String json, DashboardBean dashboardBean){
        try {
            JSONObject jsonObj = new JSONObject(json);
            dashboardBean.playbackDelivered = jsonObj.optInt("delivered", 0);
            dashboardBean.playbackWaitingDelivery = jsonObj.optInt("waitingDelivery", 0);
            dashboardBean.playbackCurrentPlaying = jsonObj.optInt("currentPlaying", 0);
            dashboardBean.playbackPlayedSeconds = jsonObj.optInt("playedSeconds", 0);
            dashboardBean.playbackMediaStorage = jsonObj.optInt("mediaStorage", 0);
            return true;
        } catch (JSONException e) {
            Log.d("Zack", e.getMessage());
        }
        return false;
    }

    public boolean fromActivityStatistics(String json, DashboardBean dashboardBean) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            dashboardBean.playerActiveCount = jsonObj.optInt("activeCount", 0);
            dashboardBean.playerInactiveCount = jsonObj.optInt("inactiveCount", 0);
            return true;
        } catch (JSONException e) {
            Log.d("Zack", e.getMessage());
        }
        return false;
    }

    public boolean fromMediaStatistics(String json, DashboardBean dashboardBean) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            dashboardBean.mediaImageCount = jsonObj.optInt("imageCount", 0);
            dashboardBean.mediaVideoCount = jsonObj.optInt("videoCount", 0);
            return true;
        } catch (JSONException e) {
            Log.d("Zack", e.getMessage());
        }
        return false;
    }
}
