package com.auo.shelf.cmsapp.ui.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DashboardProgramBean;
import com.auo.shelf.cmsapp.utility.Utility;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {

    private ArrayList<DashboardProgramBean> mList = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_dashboard_program, viewGroup, false);
        return new ProgramAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardProgramBean bean = mList.get(position);
        int radius = Utility.convertPixelsToDp(mContext, 100);
        Transformation transformation = new RoundedCornersTransformation(radius, 0);
        Picasso.get().load(bean.imageUri).transform(transformation).into(holder.image);
        holder.tvName.setText(bean.name);
        holder.tvRunTime.setText(bean.runTime);
        holder.tvViews.setText(bean.views);
        holder.tvAmongDevice.setText(bean.amongDevice);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<DashboardProgramBean> list){
        mList.clear();
        mList.addAll(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView tvName;
        public TextView tvRunTime;
        public TextView tvViews;
        public TextView tvAmongDevice;

        public ViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.item_dashboard_program_image);
            tvName = view.findViewById(R.id.item_dashboard_program_name);
            tvRunTime = view.findViewById(R.id.item_dashboard_program_runtime);
            tvViews = view.findViewById(R.id.item_dashboard_program_views);
            tvAmongDevice = view.findViewById(R.id.item_dashboard_program_among_device);
        }
    }
}
