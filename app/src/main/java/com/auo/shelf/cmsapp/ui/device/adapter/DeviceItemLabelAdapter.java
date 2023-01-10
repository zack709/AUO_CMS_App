package com.auo.shelf.cmsapp.ui.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceBean;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;

import java.util.ArrayList;

public class DeviceItemLabelAdapter extends RecyclerView.Adapter<DeviceItemLabelAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<DeviceLabelBean> mList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_device_item_label, viewGroup, false);
        return new DeviceItemLabelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeviceLabelBean bean = mList.get(position);
        holder.itemLayout.setCardBackgroundColor(mContext.getColor(bean.backgroundColor));
        holder.labelName.setText(bean.name);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItem(DeviceLabelBean bean){
        mList.add(bean);
    }

    public void setList(ArrayList<DeviceLabelBean> list){
        mList.clear();
        mList.addAll(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView itemLayout;
        public TextView labelName;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemLayout = view.findViewById(R.id.item_device_label_layout);
            labelName = view.findViewById(R.id.item_device_label_name);
        }
    }
}
