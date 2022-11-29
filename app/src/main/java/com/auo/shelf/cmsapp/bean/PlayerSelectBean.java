package com.auo.shelf.cmsapp.bean;

public class PlayerSelectBean {
    public static final int TYPE_DEVICE = 1;
    public static final int TYPE_GROUP = 2;

    public int type = 1; // 1: Device, 2: Group
    public int id;
    public String name = "";
    public boolean isChecked = false;

    public PlayerSelectBean(int id, int type, String name){
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
