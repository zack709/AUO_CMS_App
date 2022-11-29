package com.auo.shelf.cmsapp.json;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiResponse {
    public boolean success;
    public int statusCode;
    public String message;
    public String payload;

    public boolean fromJson(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            this.success = jsonObj.getBoolean("success");
            this.statusCode = jsonObj.getInt("statusCode");
            this.message = jsonObj.getString("message");
            this.payload = jsonObj.getString("payload");
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}


