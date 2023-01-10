package com.auo.shelf.cmsapp.utility;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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

    public static int convertDpToPixel(Context context, float dp){
        return (int)(dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int convertPixelsToDp(Context context, float px){
        return (int)(px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static Drawable getResizeDrawable(Context context, int resId, float dpWidth, float dpHeight){
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resId);
        int width = Utility.convertDpToPixel(context, dpWidth);
        int height = Utility.convertDpToPixel(context, dpHeight);
        bm = Bitmap.createScaledBitmap(bm, width, height, false);
        return new BitmapDrawable(context.getResources(), bm);
    }

    public static void showKeyboard(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
