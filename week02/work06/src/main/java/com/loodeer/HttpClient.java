package com.loodeer;

import okhttp3.Request;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

/**
 * @author luzuheng
 * @since 2021-07-04 18:51
 */
public class HttpClient {

    public static void main(String[] args) throws IOException, ParseException {
        String url = "http://127.0.0.1:8801";
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
        response.close();
    }

}
