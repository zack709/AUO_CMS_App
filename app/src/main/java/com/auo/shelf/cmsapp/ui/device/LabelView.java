package com.auo.shelf.cmsapp.ui.device;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.utility.Utility;

public class LabelView extends LinearLayout {

    private LabelView mLabelView;
    private Context mContext;
    private DeviceLabelBean mBean;
    private CardView cardView;
    private TextView nameView;
    private ImageView ivClose;
    private OnCloseListener mOnCloseListener;
    private boolean isCloseable = false;

    public LabelView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LabelView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mLabelView = this;
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_label, null);
        cardView = view.findViewById(R.id.layout_label);
        nameView = view.findViewById(R.id.layout_label_name);
        ivClose = view.findViewById(R.id.layout_label_close);
        addView(view);

        setOnClickListener(onClickListener);
    }

    public void setDeviceLabelBean(DeviceLabelBean bean){
        mBean = bean;
        cardView.setCardBackgroundColor(mContext.getColor(mBean.backgroundColor));
        nameView.setText(mBean.name);
        mLabelView.setVisibility(View.VISIBLE);
    }

    public void setCloseable(boolean closeable){
        if (closeable) {
            ivClose.setVisibility(View.VISIBLE);
        }else{
            ivClose.setVisibility(View.GONE);
        }
        isCloseable = closeable;
    }

    public interface OnCloseListener{
        void onClose();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener){
        mOnCloseListener = onCloseListener;
        ivClose.setVisibility(View.VISIBLE);
        isCloseable = true;
    }

    public void removeOnCloseListener(){
        mOnCloseListener = null;
        ivClose.setVisibility(View.GONE);
        isCloseable = false;
    }

    private View.OnClickListener onClickListener = v -> {
        if (isCloseable && mOnCloseListener != null){
            mLabelView.setVisibility(View.GONE);
            mOnCloseListener.onClose();
        }
    };
}
