package com.auo.shelf.cmsapp.ui.dashboard.spinner;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.utility.Utility;

public class DashboardSpinner extends AppCompatSpinner {

    private boolean isPopup = false;

    public DashboardSpinner(@NonNull Context context) {
        super(context);
        init();
    }

    public DashboardSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DashboardSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean performClick() {
        isPopup = true;
        setBackgroundResource(R.drawable.dashboard_spinner_popup);

        return super.performClick();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (isPopup && hasWindowFocus){
            isPopup = false;
            setBackgroundResource(R.drawable.dashboard_spinner);
        }
    }

    private void init(){
        this.setDropDownVerticalOffset(Utility.convertDpToPixel(getContext(), 40));
    }
}
