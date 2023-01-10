package com.auo.shelf.cmsapp.ui.common;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.utility.Utility;

public class DropDownEditText extends AppCompatAutoCompleteTextView implements View.OnTouchListener {

    private Context mContext;
    private String mHint = "";
    private Drawable mRightIcon;
    private Drawable mFocusedIcon;
    private boolean mDropdown = false;
    private OnLabelSelectedListener labelSelectedListener;

    public DropDownEditText(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DropDownEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DropDownEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused){
            if (mFocusedIcon != null) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, mFocusedIcon, null);
            }
            setBackgroundResource(R.drawable.edit_text_focused);
            setHint("");
            if (mDropdown) showDropDown();
            Utility.showKeyboard(mContext);
        }else{
            if (mRightIcon != null) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, mRightIcon, null);
            }
            setBackgroundResource(R.drawable.edit_text_normal);
            if (getText().toString().length() == 0){
                setHint(mHint);
            }
            if (mDropdown) dismissDropDown();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (getRight() - (getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() + getPaddingEnd()))) {
                setText("");
                this.clearFocus();
                Utility.hideKeyboard(mContext, this);
                return true;
            }else{
                showDropDown();
            }
        }
        return false;
    }

    @Override
    protected void replaceText(CharSequence text) {
        Utility.hideKeyboard(mContext, this);
        clearFocus();
    }

    private void init(Context context){
        mContext = context;
        if (getHint().toString() != null){
            mHint = getHint().toString();
        }
        setOnTouchListener(this);
        setOnKeyListener(keyListener);
        setDropDownBackgroundResource(R.drawable.autocomplete_dropdown);
        setOnItemClickListener(onItemClickListener);

        setDropDownVerticalOffset(Utility.convertDpToPixel(mContext, 1));

        postDelayed(() -> {
            if (mRightIcon == null) {
                Drawable[] compoundDrawables = getCompoundDrawablesRelative();
                mRightIcon = compoundDrawables[2];
            }
        }, 10);
    }

    public void setRightIconRes(int res){
        mRightIcon = mContext.getDrawable(res);
        setCompoundDrawablesWithIntrinsicBounds(null, null, mRightIcon, null);
    }

    public void setFocusedRightIconRes(int res){
        mFocusedIcon = mContext.getDrawable(res);
    }

    public void isDropDown(boolean dropdown){
        mDropdown = dropdown;
    }

    public View.OnKeyListener keyListener = (view, keyCode, event) -> {

        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            return true;
        }
        return false;
    };

    public interface OnLabelSelectedListener{
        void onLabelSelected(DeviceLabelBean deviceLabelBean);
    }

    public void setOnLabelSelectedListener(OnLabelSelectedListener labelSelectedListener){
        this.labelSelectedListener = labelSelectedListener;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            DeviceLabelBean bean = (DeviceLabelBean) getAdapter().getItem(pos);
            if (labelSelectedListener != null){
                labelSelectedListener.onLabelSelected(bean);
            }
        }
    };
}
