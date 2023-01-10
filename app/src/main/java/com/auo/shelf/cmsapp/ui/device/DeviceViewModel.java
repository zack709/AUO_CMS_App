package com.auo.shelf.cmsapp.ui.device;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.ui.device.adapter.DeviceSearchAdapter;

import java.util.ArrayList;

public class DeviceViewModel extends ViewModel {

    private ArrayList<DeviceLabelBean> mLabelList = new ArrayList<>();
    private DeviceSearchAdapter mLabelAdapter;

    public DeviceViewModel() {

    }

    public void createLabelAdapter(Context context){
        mLabelAdapter = new DeviceSearchAdapter(context, R.layout.item_device_item_label);
    }

    public void setLabelList(ArrayList<DeviceLabelBean> list){
        mLabelList.clear();
        mLabelList.addAll(list);
        mLabelAdapter.clear();
        mLabelAdapter.addAll(list);
    }

    public void addLabelListItem(DeviceLabelBean bean){
        mLabelList.add(bean);
        mLabelAdapter.addAll(bean);
    }

    public ArrayList<DeviceLabelBean> getLabelList(){
        return mLabelList;
    }

    public DeviceSearchAdapter getLabelAdapter(){
        return mLabelAdapter;
    }
}
