package com.auo.shelf.cmsapp.ui.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.NotificationBean;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<NotificationBean> mList = new ArrayList<>();

    public NotificationAdapter(ArrayList<NotificationBean> list){
        setList(list);
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_notification, viewGroup, false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        NotificationBean bean = mList.get(position);
        holder.ivIcon.setImageResource(bean.getIconRes());
        holder.tvName.setText(bean.title);
        holder.tvTime.setText(bean.subtitle);
        if (bean.isShowArrow()) {
            holder.ivArrow.setVisibility(View.VISIBLE);
        } else {
            holder.ivArrow.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<NotificationBean> list){
        mList.clear();
        mList.addAll(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout itemLayout;
        public ImageView ivIcon;
        public TextView tvName;
        public TextView tvTime;
        public ImageView ivArrow;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemLayout = view.findViewById(R.id.notification_item);
            ivIcon = view.findViewById(R.id.notification_item_icon);
            tvName = view.findViewById(R.id.notification_item_name);
            tvTime = view.findViewById(R.id.notification_item_time);
            ivArrow = view.findViewById(R.id.notification_item_arrow);
        }
    }
}

