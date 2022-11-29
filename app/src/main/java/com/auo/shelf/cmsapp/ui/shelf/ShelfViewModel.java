package com.auo.shelf.cmsapp.ui.shelf;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auo.shelf.cmsapp.bean.ShelfBean;
import com.auo.shelf.cmsapp.json.ShelfList;

import java.util.ArrayList;

public class ShelfViewModel extends ViewModel {

//    private final MutableLiveData<String> mText;
//
//    public ShelfViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is gallery fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }

    private int selectedPkey = 0;
    private ShelfList shelfList;

    public void setSelectPkey(int pkey){
        selectedPkey = pkey;
    }

    public ShelfBean getSelectedShelf(){
        if (shelfList != null) {
            for (int i = 0; i < shelfList.getList().size(); i++) {
                ShelfBean bean = shelfList.getList().get(i);
                if (bean.pkey == selectedPkey) {
                    return bean;
                }
            }
        }
        return null;
    }

    public void setShelfList(ShelfList list){
        shelfList = list;
    }

    public ShelfList getShelfList(){
        return shelfList;
    }

}