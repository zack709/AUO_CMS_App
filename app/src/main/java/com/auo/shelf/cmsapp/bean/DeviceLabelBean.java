package com.auo.shelf.cmsapp.bean;

import android.content.Context;

import com.auo.shelf.cmsapp.R;

public class DeviceLabelBean {

    public static final int TYPE_ONLINE = 1;
    public static final int TYPE_OFFLINE = 2;
    public static final int TYPE_UPDATABLE = 3;
    public static final int TYPE_CUSTOM = 4;

    public String name;
    public int type = TYPE_CUSTOM;
    public int backgroundColor = R.color.gray_600;
    public boolean onEdit = false;
    public boolean canDelete = true;

    public DeviceLabelBean(String name){
        this.name = name;
    }

    public DeviceLabelBean (Context context, int type){
        switch (type){
            case TYPE_ONLINE:
                this.name = context.getString(R.string.fragment_device_label_online);
                this.backgroundColor = R.color.green_500;
                break;
            case TYPE_OFFLINE:
                this.name = context.getString(R.string.fragment_device_label_offline);
                this.backgroundColor = R.color.blue_500;
                break;
            case TYPE_UPDATABLE:
                this.name = context.getString(R.string.fragment_device_label_updatable);
                this.backgroundColor = R.color.amber_500;
                break;
        }
        this.type = type;
        this.canDelete = false;
    }
}
