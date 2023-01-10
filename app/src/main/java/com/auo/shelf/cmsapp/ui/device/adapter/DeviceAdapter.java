package com.auo.shelf.cmsapp.ui.device.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceBean;
import com.auo.shelf.cmsapp.ui.device.DeviceEditDialog;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<DeviceBean> mList = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_device, viewGroup, false);
        return new DeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeviceBean bean = mList.get(position);
        holder.name.setText(bean.name);
        holder.resolution.setText(bean.resolution);

        DeviceItemLabelAdapter adapter = new DeviceItemLabelAdapter();
        adapter.setList(bean.labelList);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        holder.labelList.setLayoutManager(layoutManager);
        holder.labelList.setAdapter(adapter);


        holder.editBtn.setTag(position);
        holder.editBtn.setOnClickListener(editClick);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<DeviceBean> list){
        mList.clear();
        mList.addAll(list);
    }

    public ArrayList<DeviceBean> getList(){
        return mList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView resolution;
        public RecyclerView labelList;
        public MaterialButton editBtn;
        public MaterialButton deleteBtn;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.item_device_name);
            resolution = view.findViewById(R.id.item_device_resolution);
            labelList = view.findViewById(R.id.fragment_device_item_label_list);
            editBtn = view.findViewById(R.id.item_device_edit_button);
            deleteBtn = view.findViewById(R.id.item_device_delete_button);
        }
    }

    private View.OnClickListener editClick = v -> {
        int pos = (int) v.getTag();
        DeviceBean bean = mList.get(pos);
        DeviceEditDialog dialog = new DeviceEditDialog(mContext, bean);
        dialog.show();
    };
}
