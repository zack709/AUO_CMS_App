package com.auo.shelf.cmsapp.ui.quick.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.config.RegionType;
import com.auo.shelf.cmsapp.json.Region;

public class LayoutRegion {

    private FrameLayout mLayout;
    private Context mContext;

    public LayoutRegion(Context context, Region region){
        mContext = context;
        mLayout = new FrameLayout(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(region.width, region.height);
        layoutParams.setMargins(region.left, region.top,0, 0);
        mLayout.setLayoutParams(layoutParams);
        mLayout.setZ(region.depth);
        RegionType regionType = new RegionType();
        mLayout.setBackgroundResource(regionType.getTypeResource(region.type));
    }

    public FrameLayout getLayout(){
        return mLayout;
    }
}
