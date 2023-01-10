package com.auo.shelf.cmsapp.bean;

import com.auo.shelf.cmsapp.R;

public class NotificationBean {

    public static final int TYPE_PROGRAM = 1;
    public static final int TYPE_DEVICE = 2;

    private static final int RES_PROGRAM = R.mipmap.icon_cast;
    private static final int RES_DEVICE = R.mipmap.icon_computer;

    private int iconRes;
    public String title;
    public String subtitle;
    private boolean showArrow = true;

    public NotificationBean(int type){
        switch (type){
            case TYPE_PROGRAM:
                iconRes = RES_PROGRAM;
                showArrow = true;
                break;
            case TYPE_DEVICE:
                iconRes = RES_DEVICE;
                showArrow = false;
                break;
        }
    }

    public int getIconRes(){
        return iconRes;
    }

    public boolean isShowArrow(){
        return showArrow;
    }
}
