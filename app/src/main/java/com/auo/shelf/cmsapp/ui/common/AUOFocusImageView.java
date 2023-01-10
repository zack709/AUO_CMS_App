package com.auo.shelf.cmsapp.ui.common;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.auo.shelf.cmsapp.R;

public class AUOFocusImageView extends AppCompatImageView {

    private Context mContext;
    private int noFocusRes, focusedRes;
    private boolean isFocused = false;

    public AUOFocusImageView(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public AUOFocusImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public AUOFocusImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setImageRes(int noFocusRes, int focusedRes){
        this.noFocusRes = noFocusRes;
        this.focusedRes = focusedRes;
    }

    public void setFocused(boolean focused){
        isFocused = focused;
        if (focused){
            setImageResource(focusedRes);
            setBackgroundResource(R.drawable.circle_view_blue_gray_400);
        }else{
            setImageResource(noFocusRes);
            setBackgroundColor(0x00000000);
        }
    }

    public boolean getFocused(){
        return isFocused;
    }
}
