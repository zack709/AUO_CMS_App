package com.auo.shelf.cmsapp.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
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

    public static float convertDpToPixel(Context context, float dp){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(Context context, float px){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static Drawable getResizeDrawable(Context context, int resId, float dpWidth, float dpHeight){
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resId);
        int width = Math.round(Utility.convertDpToPixel(context, dpWidth));
        int height = Math.round(Utility.convertDpToPixel(context, dpHeight));
        bm = Bitmap.createScaledBitmap(bm, width, height, false);
        return new BitmapDrawable(context.getResources(), bm);
    }
}
