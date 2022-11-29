package com.auo.shelf.cmsapp.ui.quick.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.PlayerSelectBean;
import com.auo.shelf.cmsapp.ui.quick.PlayerSelectFragment;

import java.util.ArrayList;

public class PlayerSelectAdapter extends RecyclerView.Adapter<PlayerSelectAdapter.ViewHolder> {

    private ArrayList<PlayerSelectBean> itemList;
    private int selectedCount = 0;
    private PlayerSelectFragment mFragment;

    public PlayerSelectAdapter(PlayerSelectFragment fragment){
        mFragment = fragment;
    }

    @NonNull
    @Override
    public PlayerSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_quick_device_select, viewGroup, false);
        return new PlayerSelectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerSelectAdapter.ViewHolder holder, int position) {
        PlayerSelectBean bean = itemList.get(position);

        if (bean.type == PlayerSelectBean.TYPE_GROUP){
            holder.spaceLayout.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.parseColor("#336699"));
            holder.itemIcon.setImageResource(R.mipmap.ic_group_white);
            holder.itemName.setTextColor(Color.parseColor("#FFFFFF"));
            holder.itemName.setTypeface(null, Typeface.BOLD);
//            holder.itemCheckbox.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            holder.itemCheckbox.setVisibility(View.GONE);
        }else{
            holder.spaceLayout.setVisibility(View.VISIBLE);
            holder.itemIcon.setImageResource(R.mipmap.ic_device);
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.itemName.setTextColor(Color.parseColor("#000000"));
            holder.itemName.setTypeface(null, Typeface.NORMAL);
//            holder.itemCheckbox.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            holder.itemCheckbox.setVisibility(View.VISIBLE);
        }

        holder.itemName.setText(bean.name);
        holder.itemCheckbox.setChecked(bean.isChecked);

        holder.itemCheckbox.setTag(position);
        holder.itemCheckbox.setOnClickListener( view -> {
            int pos = (int) view.getTag();
            boolean isChecked = holder.itemCheckbox.isChecked();
            if (isChecked){
                selectedCount++;
            }else{
                selectedCount--;
            }

            mFragment.setNextButtonEnabled((selectedCount > 0));

            itemList.get(pos).isChecked = holder.itemCheckbox.isChecked();
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<PlayerSelectBean> list){
        itemList = list;
    }

    public ArrayList<PlayerSelectBean> getItemList(){
        return itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ConstraintLayout itemView;
        public final LinearLayout spaceLayout;
        public final ImageView itemIcon;
        public final TextView itemName;
        public final CheckBox itemCheckbox;

        public ViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.fragment_quick_device_select_item);
            spaceLayout = view.findViewById(R.id.fragment_quick_device_select_tab);
            itemIcon = view.findViewById(R.id.fragment_quick_device_select_icon);
            itemName = view.findViewById(R.id.fragment_quick_device_select_name);
            itemCheckbox = view.findViewById(R.id.fragment_quick_device_select_checkbox);
        }

    }
}
