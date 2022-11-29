package com.auo.shelf.cmsapp.json;

import android.util.Log;

import com.auo.shelf.cmsapp.bean.PlayerBean;
import com.auo.shelf.cmsapp.bean.ShelfBean;
import com.auo.shelf.cmsapp.bean.ShelfColumn;
import com.auo.shelf.cmsapp.bean.ShelfRow;
import com.auo.shelf.cmsapp.bean.Template;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShelfList {
    private ArrayList<ShelfBean> shelfList = new ArrayList<>();

    public boolean fromJson(String json){
        try {

            JSONArray shelfArray = new JSONArray(json);
            for (int i=0; i<shelfArray.length(); i++){
                ShelfBean shelfBean = new ShelfBean();
                JSONObject shelfObj = shelfArray.getJSONObject(i);
                shelfBean.pkey = shelfObj.getInt("pkey");
                shelfBean.name = shelfObj.getString("name");
                shelfBean.description = shelfObj.getString("description");
                shelfBean.rows = shelfObj.optInt("rows", 0);
                shelfBean.columns = shelfObj.optInt("columns", 0);
                int rows = shelfObj.optInt("rows", 0);
                for (int j=0; j<rows; j++){
                    ShelfRow shelfRow = new ShelfRow();
                    shelfRow.index = j;

                    int coulmns = shelfObj.optInt("columns", 0);
                    for (int k=0; k<coulmns; k++){
                        ShelfColumn shelfColumn = new ShelfColumn();
                        shelfColumn.index = k;
                        shelfColumn.rowIndex = j;
                        shelfRow.shelfColumn.add(shelfColumn);
                    }
                    shelfBean.shelfRow.add(shelfRow);
                }

                JSONArray playerArray = shelfObj.getJSONArray("players");
                for (int j=0; j<playerArray.length(); j++){
                    PlayerBean playerBean = new PlayerBean();
                    JSONObject playerObj = playerArray.getJSONObject(j);
                    playerBean.pkey = playerObj.getInt("pkey");
                    playerBean.shelfPkey = playerObj.getInt("shelf_pkey");
                    playerBean.devicePkey = playerObj.getInt("device_pkey");
                    playerBean.name = "Player " + j;
                    playerBean.row = playerObj.getInt("row");
                    playerBean.column = playerObj.getInt("column");
                    shelfBean.setPlayer(playerBean.row, playerBean.column - 1, playerBean);
                }
                shelfList.add(shelfBean);
            }

        } catch (JSONException e) {
            Log.d("Zack", e.getMessage());
            return false;
        }
        return true;
    }

    public void addShelf(ShelfBean bean){
        shelfList.add(bean);
    }

    public ArrayList<ShelfBean> getList(){
        return shelfList;
    }
}
