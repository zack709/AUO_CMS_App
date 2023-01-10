package com.auo.shelf.cmsapp.ui.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.ui.common.AUOEditText;
import com.auo.shelf.cmsapp.ui.device.DeviceLabelEditFragment;

import java.util.ArrayList;

public class DeviceLabelAdapter extends RecyclerView.Adapter<DeviceLabelAdapter.ViewHolder>{

    private Context mContext;
    private DeviceLabelEditFragment mFragment;
    private ArrayList<DeviceLabelBean> mList = new ArrayList<>();

    public DeviceLabelAdapter(DeviceLabelEditFragment fragment){
        mFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_device_label, viewGroup, false);
        return new DeviceLabelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeviceLabelBean bean = mList.get(position);
        holder.name.setText(bean.name);

        holder.btnEdit.setTag(position);
        holder.btnEdit.setOnClickListener(editClick);

        holder.btnDelete.setTag(position);
        holder.btnDelete.setOnClickListener(deleteClick);

        if (bean.onEdit){
            holder.name.setVisibility(View.GONE);
            holder.etName.setText(bean.name);
            holder.etName.setVisibility(View.VISIBLE);
            holder.etName.requestFocus();

            holder.btnEdit.setImageResource(R.drawable.icon_edit_label_gray);
            holder.btnDelete.setImageResource(R.drawable.icon_delete_label_gray);
        }else{
            holder.etName.setVisibility(View.GONE);
            holder.name.setText(bean.name);
            holder.name.setVisibility(View.VISIBLE);

            holder.btnEdit.setImageResource(R.drawable.icon_edit_label_white);
            holder.btnDelete.setImageResource(R.drawable.icon_delete_label_white);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<DeviceLabelBean> list){
        mList.clear();
        mList.addAll(list);
    }

    public ArrayList<DeviceLabelBean> getList(){
        return mList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public AUOEditText etName;
        public ImageView btnEdit;
        public ImageView btnDelete;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.item_device_label_name);
            etName = view.findViewById(R.id.item_device_label_name_edittext);
            btnEdit = view.findViewById(R.id.item_device_label_edit);
            btnDelete = view.findViewById(R.id.item_device_label_delete);
        }
    }

    private View.OnClickListener editClick = v -> {
        int pos = (int) v.getTag();
        mList.get(pos).onEdit = true;
        notifyItemChanged(pos);
        mFragment.syncList(mList);
    };

    private View.OnClickListener deleteClick = v ->{
        int pos = (int) v.getTag();
        mList.remove(pos);
        notifyItemRemoved(pos);
        mFragment.syncList(mList);
    };
}
