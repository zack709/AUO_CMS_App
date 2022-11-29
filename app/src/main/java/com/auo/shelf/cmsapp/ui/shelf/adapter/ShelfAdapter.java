package com.auo.shelf.cmsapp.ui.shelf.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.bean.ShelfBean;

import java.util.ArrayList;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.ui.quick.adapter.PlayerSelectAdapter;
import com.auo.shelf.cmsapp.ui.shelf.ShelfManager;

public class ShelfAdapter extends RecyclerView.Adapter<ShelfAdapter.ViewHolder>{

    private ArrayList<ShelfBean> mList = new ArrayList<>();
    private ShelfManager mShelfManager;

    public ShelfAdapter(ShelfManager shelfManager){
        mShelfManager = shelfManager;
    }

    @NonNull
    @Override
    public ShelfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_shelf_manager, viewGroup, false);
        return new ShelfAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShelfBean shelfBean = mList.get(position);

        holder.itemName.setText(shelfBean.name);
        holder.itemRows.setText("" + shelfBean.rows);
        holder.itemColumns.setText("" + shelfBean.columns);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            mShelfManager.gotoPlayerManager(mList.get(pos).pkey);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<ShelfBean> list){
        mList.clear();
        mList.addAll(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ConstraintLayout itemView;
        public final TextView itemName;
        public final TextView itemRows;
        public final TextView itemColumns;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemView = view.findViewById(R.id.fragment_shelf_manager_shelf_list_item_view);
            itemName = view.findViewById(R.id.fragment_shelf_manager_shelf_list_item_name);
            itemRows = view.findViewById(R.id.fragment_shelf_manager_shelf_list_item_rows);
            itemColumns = view.findViewById(R.id.fragment_shelf_manager_shelf_list_item_columns);
        }
    }
}
