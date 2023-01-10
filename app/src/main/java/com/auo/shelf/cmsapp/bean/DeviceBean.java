package com.auo.shelf.cmsapp.bean;

import android.content.Context;

import com.auo.shelf.cmsapp.R;

import java.util.ArrayList;

public class DeviceBean {
    public Context mContext;
    public String name;
    public String resolution;
    public ArrayList<DeviceLabelBean> labelList = new ArrayList<>();

    public DeviceBean(Context context, String name, String resolution){
        mContext = context;
        this.name = name;
        this.resolution = resolution;
        loadLabelList();
    }

    private void loadLabelList(){
        int mainLabel = (int) ((Math.random() * 2) + 1);
        labelList.add(new DeviceLabelBean(mContext, mainLabel));

        int updateLabel = (int) (Math.random() * 2);
        if (updateLabel == 1){
            labelList.add(new DeviceLabelBean(mContext, DeviceLabelBean.TYPE_UPDATABLE));
        }

        String[] extLabels = {"Hsinchu", "ATC/L3B", "Staff Canteen Shop", "Starbucks", "Lobby"};
        int labelNum = (int) (Math.random() * extLabels.length);
        for (int i=0; i<labelNum; i++){
            labelList.add(new DeviceLabelBean(extLabels[i]));
        }
    }
}
