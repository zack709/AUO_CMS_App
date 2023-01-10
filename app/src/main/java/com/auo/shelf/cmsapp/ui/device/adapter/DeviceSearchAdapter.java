package com.auo.shelf.cmsapp.ui.device.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.ui.device.DeviceViewModel;


public class DeviceSearchAdapter extends ArrayAdapter<DeviceLabelBean> {

    private Context mContext;
    private LayoutInflater inflater;

    public DeviceSearchAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        DeviceLabelBean bean = getItem(position);
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_device_search_label, null);
            holder.itemLayout = view.findViewById(R.id.item_device_label_layout);
            holder.labelName = view.findViewById(R.id.item_device_label_name);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.itemLayout.setCardBackgroundColor(mContext.getColor(bean.backgroundColor));
        holder.labelName.setText(bean.name);
        return view;
    }

    public static class ViewHolder {
        public CardView itemLayout;
        public TextView labelName;
    }
}
