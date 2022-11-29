package com.auo.shelf.cmsapp.utility;

import android.util.Log;

import androidx.annotation.NonNull;

import com.auo.shelf.cmsapp.json.ApiResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpConnector {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiResponse> respAdapter = moshi.adapter(ApiResponse.class);

    private String requestUrl;
    private HttpCallback mHttpCallback;
    private final OkHttpClient client = new OkHttpClient();

    public HttpConnector(String url, HttpCallback httpCallback){
        requestUrl = url;
        mHttpCallback = httpCallback;
    }

    public void setRequestUrl(String url){
        requestUrl = url;
    }

    public void post(String json){
        RequestBody requestBody = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(requestUrl)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String resp = response.body().string();
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<ApiResponse> jsonAdapter = moshi.adapter(ApiResponse.class);
                mHttpCallback.onSuccess(jsonAdapter.fromJson(resp));
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHttpCallback.onFailure("連線至伺服器失敗");
            }
        });
    }

    public interface HttpCallback {
        void onFailure(String errorMessage);
        void onSuccess(ApiResponse apiResponse);
    }
}
