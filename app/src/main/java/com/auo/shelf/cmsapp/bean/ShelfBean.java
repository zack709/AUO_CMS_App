package com.auo.shelf.cmsapp.bean;

import android.util.Log;

import com.auo.shelf.cmsapp.ui.shelf.adapter.ShelfAdapter;

import java.util.ArrayList;

public class ShelfBean {
    public int pkey;
    public String name;
    public String description;
    public int rows;
    public int columns;
    public ArrayList<ShelfRow> shelfRow = new ArrayList<>();

    public PlayerBean getPlayer(int row, int column){
        return shelfRow.get(row).shelfColumn.get(column).playerBean;
    }

    public void setPlayer(int row, int column, PlayerBean bean){
        shelfRow.get(row).shelfColumn.get(column).playerBean = bean;
    }
}
