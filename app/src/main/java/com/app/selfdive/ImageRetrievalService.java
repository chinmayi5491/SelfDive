package com.app.selfdive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Stephen on 4/9/2018.
 *
 * Service class for making API calls to the Getty API
 *
 * https://api.gettyimages.com/v3/search/images?phrase={keyword}
 *
 */

public class ImageRetrievalService {

    private OkHttpClient client = new OkHttpClient();
    private ImageResult imageResult;
    private Activity activity;

    public static final String TOKEN_IMAGE_RESULT_BUNDLE_KEY = "tokenImageResultBundleKey";
    public static final String TOKEN_IMAGE_RESULT_INTENT_KEY = "tokenImageResultIntentKey";

    public ImageRetrievalService(Activity activity) {
        this.activity = activity;
    }

    protected void getImages(String keyword) {

        final Request request = new Request.Builder()
                //.addHeader("Authorization", "BEARER " + "556wKMKXJzgVWUYwaSzfaeuhksJn5xpC7DTyNNRF6D9wT")
                .addHeader("Api-Key", "dd7tqnfeyvwy2bj5h2jmf3sg")
                .url("https://api.gettyimages.com/v3/search/images?phrase=" + keyword)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Http", "Call Failed!");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("Http", "Response: " + responseBody);
                parseImageResponse(responseBody);
            }
        });
    }

    protected void parseImageResponse(String json){
        Gson gson = new Gson();
        imageResult = gson.fromJson(json, ImageResult.class);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, ImageDisplay.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(TOKEN_IMAGE_RESULT_BUNDLE_KEY, imageResult);
                intent.putExtra(TOKEN_IMAGE_RESULT_INTENT_KEY, bundle);
                activity.startActivity(intent);
            }
        });

        Log.d("ImageResult", "Size of images is: " + imageResult.getImages().size());

    }

}
