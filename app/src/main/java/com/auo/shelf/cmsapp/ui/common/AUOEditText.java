package com.auo.shelf.cmsapp.ui.common;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.auo.shelf.cmsapp.R;

public class AUOEditText extends androidx.appcompat.widget.AppCompatEditText {

    private Context mContext;
    private AUOFocusImageView mDoneView;
    private String mHint;

    public AUOEditText(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AUOEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AUOEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (focused){
            setBackgroundResource(R.drawable.edit_text_focused);
            setHint("");
        }else{
            setBackgroundResource(R.drawable.edit_text_normal);
            setHint(mHint);
        }

        if (mDoneView != null) {
            setDoneViewFocused(focused);
        }
    }

    private void init(Context context){
        mContext = context;
        if (getHint() != null) {
            mHint = getHint().toString();
        }else{
            mHint = "";
        }
    }

    public void setFocusedDoneView(AUOFocusImageView doneView){
        mDoneView = doneView;
    }

    private void setDoneViewFocused(boolean focused){
         mDoneView.setFocused(focused);
    }
}
