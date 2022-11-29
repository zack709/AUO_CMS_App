package com.auo.shelf.cmsapp.ui.quick;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auo.shelf.cmsapp.bean.PlayerSelectBean;
import com.auo.shelf.cmsapp.bean.Template;

import java.util.ArrayList;

public class QuickPublishViewModel extends ViewModel {

//    private final MutableLiveData<QuickPublishSetting> mSetting;

    private QuickPublishSetting mSetting;

    public QuickPublishViewModel() {
//        mSetting = new MutableLiveData<>();
        mSetting = new QuickPublishSetting();
    }

    public void setQuickPublihSetting(QuickPublishSetting setting){
//        mSetting.setValue(setting);
        mSetting = setting;
    }

    public QuickPublishSetting getQuickPublishSetting(){
        return mSetting;
    }

//    public LiveData<QuickPublishSetting> getQuickPublihSetting(){
//        return mSetting;
//    }

}