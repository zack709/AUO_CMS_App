package com.auo.shelf.cmsapp.ui.quick.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.auo.shelf.cmsapp.bean.Template;
import com.auo.shelf.cmsapp.json.Templates;
import com.auo.shelf.cmsapp.ui.quick.LayoutSelectFragment;
import com.auo.shelf.cmsapp.ui.quick.pager.LayoutPager;

import java.util.List;

public class LayoutAdapter extends FragmentStateAdapter {

    private Templates templates;

    public LayoutAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (templates != null) {
            Template template = templates.getList().get(position);
            LayoutPager pager = new LayoutPager(template);
            return pager;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return templates.getList().size();
    }

    public void setTemplates(Templates templates){
        this.templates = templates;
    }


}

