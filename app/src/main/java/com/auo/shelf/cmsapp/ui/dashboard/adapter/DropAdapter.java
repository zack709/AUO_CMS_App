package com.auo.shelf.cmsapp.ui.dashboard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.auo.shelf.cmsapp.R;

import java.util.ArrayList;

public class DropAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String> mList = new ArrayList<>();
    private int selectedIndex = 0;

    public DropAdapter(@NonNull Context context, ArrayList<String> list) {
        super(context, 0, list);
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = inflateView(position, view);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.textName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = inflateView(position, view);
        ViewHolder holder = (ViewHolder) view.getTag();
        if (position == selectedIndex){
            holder.itemLayout.setBackgroundResource(R.drawable.dashboard_spinner_selected_layout);
        }else{
            holder.itemLayout.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.textName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        return view;
    }

    private View inflateView(int position, @Nullable View view){
        ViewHolder holder = new ViewHolder();
        if (mList != null){
            String name = mList.get(position);
            if (view == null) {
                view = inflater.inflate(R.layout.spinner_dropdown, null, false);
                holder.textName = view.findViewById(R.id.spinner_dropdown_item_name);
                holder.itemLayout = view.findViewById(R.id.spinner_dropdown_item_layout);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.textName.setText(name);
        }
        return view;
    }

    public void setList(ArrayList<String> list){
        mList.clear();
        mList.addAll(list);
    }

    public void setSelectedIndex(int index){
        selectedIndex = index;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        ConstraintLayout itemLayout;
        TextView textName;
    }
}
