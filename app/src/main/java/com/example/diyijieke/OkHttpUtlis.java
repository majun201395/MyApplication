package com.example.diyijieke;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 马骏 on 2017/9/11.
 */

public class OkHttpUtlis {
    private static OkHttpUtlis utils;
    private  OkHttpClient client;


    private OkHttpUtlis(){
        client = new OkHttpClient.Builder().build();

    }
    public static synchronized OkHttpUtlis getInstance(){
        if (utils==null){
            utils = new OkHttpUtlis();
        }
        return utils;
    }
    public void sendGET(String str, Callback callback){
        Request build = new Request.Builder().url(str).build();
        Call call = client.newCall(build);
        call.enqueue(callback);
    }
}
