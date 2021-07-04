package com.loodeer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author luzuheng
 * @since 2021-07-04 18:42
 */
public class OkHttp {

    public static void main(String[] args) throws IOException {
        String url = "http://127.0.0.1:8801";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
