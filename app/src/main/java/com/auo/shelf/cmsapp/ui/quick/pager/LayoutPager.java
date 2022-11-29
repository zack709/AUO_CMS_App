package com.auo.shelf.cmsapp.ui.quick.pager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auo.shelf.cmsapp.databinding.PagerQuickLayoutBinding;
import com.auo.shelf.cmsapp.json.Region;
import com.auo.shelf.cmsapp.bean.Template;
import com.auo.shelf.cmsapp.ui.quick.layout.LayoutRegion;
import com.auo.shelf.cmsapp.ui.quick.layout.LayoutTemplate;

public class LayoutPager extends Fragment {

    private PagerQuickLayoutBinding binding;
    private Template template;
    private FrameLayout mLayout;

    public LayoutPager(){

    }

    public LayoutPager(Template template){
        this.template = template;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PagerQuickLayoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (template != null){
            if (binding.pagerQuickLayoutZoom.getChildCount() == 0) {
                LayoutTemplate layoutTemplate = inflateLayout(template);
                mLayout = layoutTemplate.getLayout();
                binding.pagerQuickLayoutZoom.addView(mLayout);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private LayoutTemplate inflateLayout(Template template){
        LayoutTemplate layoutTemplate = new LayoutTemplate(getContext(), template);

        for (int i=0; i<template.regions.size(); i++){
            Region region = template.regions.get(i);
            LayoutRegion layoutRegion = new LayoutRegion(getContext(), region);
            layoutTemplate.addRegion(layoutRegion);
        }

        return layoutTemplate;
    }
}
