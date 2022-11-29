package com.auo.shelf.cmsapp.ui.quick.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.FrameLayout;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.Template;

public class LayoutTemplate {

    private FrameLayout rootLayout;
    private Context mContext;
    private Template mTemplate;

    public LayoutTemplate(Context context, Template template){
        mContext = context;
        mTemplate = template;

        rootLayout = new FrameLayout(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mTemplate.width, mTemplate.height);
        rootLayout.setLayoutParams(layoutParams);
        rootLayout.setBackgroundResource(R.drawable.border_layout);

    }

    public void addRegion(LayoutRegion region){
        if (rootLayout != null){
            FrameLayout layout = region.getLayout();
            if (layout != null) {
                rootLayout.addView(layout);
            }else{
                Log.d("Zack", "Region is null");
            }
        }else{
            Log.d("Zack", "Template is null");
        }
    }

    public FrameLayout getLayout(){
        return rootLayout;
    }

    public Bitmap getBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(
                mTemplate.width, mTemplate.width, Bitmap.Config.ARGB_4444
        );
        Canvas canvas = new Canvas(bitmap);
        rootLayout.draw(canvas);
        return bitmap;
    }
}
