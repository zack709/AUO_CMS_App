package com.auo.shelf.cmsapp.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

public class AUODialog extends AppCompatDialog implements View.OnClickListener{

    private Context mContext;

    public AUODialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AUODialog(@NonNull Context context, int theme) {
        super(context, theme);
        init(context);
    }

    protected AUODialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    public void init(Context context){
        mContext = context;
    }
}
