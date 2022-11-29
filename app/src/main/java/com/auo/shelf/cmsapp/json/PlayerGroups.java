package com.auo.shelf.cmsapp.json;

import android.util.Log;

import com.auo.shelf.cmsapp.bean.Player;
import com.auo.shelf.cmsapp.bean.PlayerGroup;
import com.auo.shelf.cmsapp.bean.Template;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayerGroups {
    private ArrayList<PlayerGroup> playerGroupList = new ArrayList<>();

    public boolean fromJson(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray playerGroupArray = jsonObj.getJSONArray("groups");
            for (int i=0; i<playerGroupArray.length(); i++){
                JSONObject playerGroupObj = playerGroupArray.getJSONObject(i);
                PlayerGroup playerGroup = new PlayerGroup();
                playerGroup.id = playerGroupObj.getInt("id");
                playerGroup.name = playerGroupObj.getString("name");

                JSONArray playerArray = playerGroupObj.getJSONArray("players");
                for (int j=0; j<playerArray.length(); j++) {
                    JSONObject playerObj = playerArray.getJSONObject(j);
                    Player player = new Player();
                    player.id = playerObj.getInt("id");
                    player.name = playerObj.getString("name");

                    playerGroup.players.add(player);
                }
                playerGroupList.add(playerGroup);
            }


            PlayerGroup playerGroup = new PlayerGroup();
            playerGroup.id = 0;
            playerGroup.name = "Individuals";
            JSONArray playerArray = jsonObj.getJSONArray("individuals");
            for (int j=0; j<playerArray.length(); j++) {
                JSONObject playerObj = playerArray.getJSONObject(j);
                Player player = new Player();
                player.id = playerObj.getInt("id");
                player.name = playerObj.getString("name");

                playerGroup.players.add(player);
            }
            playerGroupList.add(playerGroup);

        } catch (JSONException e) {
            Log.d("Zack", e.getMessage());
            return false;
        }
        return true;
    }

    public void addPlayerGroup(PlayerGroup group){
        playerGroupList.add(group);
    }

    public ArrayList<PlayerGroup> getList(){
        return playerGroupList;
    }
}
