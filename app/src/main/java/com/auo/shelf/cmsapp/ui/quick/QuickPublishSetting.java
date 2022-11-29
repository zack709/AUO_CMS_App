package com.auo.shelf.cmsapp.ui.quick;

import com.auo.shelf.cmsapp.bean.PlayerSelectBean;
import com.auo.shelf.cmsapp.bean.Template;

import java.util.ArrayList;

public class QuickPublishSetting {
    private Template template;
    private ArrayList<PlayerSelectBean> playerList;
    private String startTime, endTime;

    public void setTemplate(Template template){
        this.template = template;
    }

    public Template getTemplate(){
        return template;
    }

    public void setPlayerList(ArrayList<PlayerSelectBean> list){
        playerList = new ArrayList<>();
        playerList.addAll(list);
    }

    public ArrayList<PlayerSelectBean> getPlayerList() {
        return playerList;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
