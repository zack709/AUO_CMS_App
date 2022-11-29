package com.auo.shelf.cmsapp.ui.shelf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.PlayerAdapterBean;
import com.auo.shelf.cmsapp.bean.ShelfBean;
import com.auo.shelf.cmsapp.bean.ShelfColumn;
import com.auo.shelf.cmsapp.bean.ShelfRow;
import com.auo.shelf.cmsapp.ui.shelf.PlayerManager;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder>{

    private ArrayList<PlayerAdapterBean> mList = new ArrayList<>();
    private PlayerManager mPlayerManager;

    public PlayerAdapter(PlayerManager playerManager){
        mPlayerManager = playerManager;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_player_manager, viewGroup, false);
        return new PlayerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayerAdapterBean bean = mList.get(position);
        if (bean.row != null){
            holder.rowView.setVisibility(View.VISIBLE);
            holder.playerView.setVisibility(View.GONE);
            String rowText = mPlayerManager.getString(R.string.fragment_shelf_player_row_text, (bean.row.index + 1));
            holder.rowView.setText(rowText);
        }
        if (bean.column != null){
            holder.rowView.setVisibility(View.GONE);
            holder.playerView.setVisibility(View.VISIBLE);
            String columnIndex = mPlayerManager.getString(R.string.fragment_shelf_player_row_text, (bean.column.index + 1));
            holder.playerColumn.setText(columnIndex);
            if (bean.column.playerBean != null) {
                holder.qrCode.setVisibility(View.GONE);
                holder.playerName.setText(bean.column.playerBean.name);
            }else{
                holder.qrCode.setVisibility(View.VISIBLE);
                holder.qrCode.setOnClickListener(view -> {
                    mPlayerManager.gotoBinding();
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ShelfBean shelfBean){
        for (int i=0; i<shelfBean.rows; i++){
            PlayerAdapterBean rowBean = new PlayerAdapterBean();
            rowBean.row = shelfBean.shelfRow.get(i);
            rowBean.row.index = i;
            mList.add(rowBean);
            for (int j=0; j<shelfBean.shelfRow.get(i).shelfColumn.size(); j++){
                PlayerAdapterBean columnBean = new PlayerAdapterBean();
                columnBean.column = shelfBean.shelfRow.get(i).shelfColumn.get(j);
                mList.add(columnBean);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rowView;
        public ConstraintLayout playerView;
        public TextView playerColumn;
        public TextView playerName;
        public ImageView qrCode;

        public ViewHolder(@NonNull View view) {
            super(view);
            rowView = view.findViewById(R.id.item_shelf_manager_shelf_row);
            playerView = view.findViewById(R.id.item_shelf_manager_player_row);
            playerColumn = view.findViewById(R.id.item_shelf_manager_player_column);
            playerName = view.findViewById(R.id.item_shelf_manager_player_name);
            qrCode = view.findViewById(R.id.item_shelf_manager_player_binding);
        }
    }
}
