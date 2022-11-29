package com.auo.shelf.cmsapp.utility;

import android.content.Context;
import android.util.Log;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.json.ApiResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Utility {

    public static ApiResponse loadJson(Context context, int resource){
        // For Test
        InputStream is = context.getResources().openRawResource(resource);

        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            ApiResponse response = new ApiResponse();
            if (response.fromJson(new String(b))){
                return response;
            }else{
                Log.d("Zack", "Error Parse Response JSON");
            }

        } catch (IOException e) {
            Log.d("Zack", "Error Load JSON File");
        }
        return null;
    }
}
