package com.auo.shelf.cmsapp.ui.quick.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.ui.layout.LayoutContentFragment;

import java.util.ArrayList;

public class LayoutElementAdapter extends RecyclerView.Adapter<LayoutElementAdapter.ViewHolder> {

    LayoutContentFragment layoutContentFragment;
    ArrayList<String> itemList;

    public LayoutElementAdapter(LayoutContentFragment fragment, ArrayList<String> list){
        itemList = list;
        layoutContentFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout_content_element, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(itemList.get(position));
        holder.itemEdit.setTag(position);
//        holder.itemEdit.setOnClickListener(v -> {
//            NavHostFragment.findNavController(layoutContentFragment).navigate(R.id.action_quick_layout_content_to_edit);
//        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<String> list){
        itemList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView itemName;
        public final Button itemEdit;

        public ViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.fragment_quick_layout_content_item_name);
            itemEdit = view.findViewById(R.id.fragment_quick_layout_content_item_edit);
        }

    }
}
