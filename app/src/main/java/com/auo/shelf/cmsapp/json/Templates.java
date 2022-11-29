package com.auo.shelf.cmsapp.json;

import android.util.Log;

import com.auo.shelf.cmsapp.bean.Template;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Templates {
    private ArrayList<Template> templateList = new ArrayList<>();

    public boolean fromJson(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray templateArray = jsonObj.getJSONArray("templates");
            for (int i=0; i<templateArray.length(); i++){
                JSONObject templateObj = templateArray.getJSONObject(i);
                Template template = new Template();
                template.id = templateObj.getInt("id");
                template.name = templateObj.getString("name");
                template.description = templateObj.getString("description");
                template.width = templateObj.getInt("width");
                template.height = templateObj.getInt("height");

                JSONArray regionArray = templateObj.getJSONArray("regions");
                for (int j=0; j<regionArray.length(); j++) {
                    JSONObject regionObj = regionArray.getJSONObject(j);
                    Region region = new Region();
                    region.type = Integer.parseInt(regionObj.getString("type"));
                    region.width = regionObj.getInt("width");
                    region.height = regionObj.getInt("height");
                    region.top = regionObj.getInt("top");
                    region.left = regionObj.getInt("left");
                    region.depth = regionObj.optInt("depth", 1);
                    template.regions.add(region);
                }
                templateList.add(template);
            }
        } catch (JSONException e) {
            Log.d("Zack", e.getMessage());
            return false;
        }
        return true;
    }

    public void addTemplate(Template template){
        templateList.add(template);
    }

    public ArrayList<Template> getList(){
        return templateList;
    }
}
