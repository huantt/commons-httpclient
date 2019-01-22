package com.huantt.commons.httpclient.async;

import com.huantt.commons.httpclient.UrlBuilder;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author huantt on 10/23/18
 */
public class HttpClient {

    private static final int DEFAULT_TIME_OUT = 5; // Minutes

    private static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MINUTES)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.MINUTES)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.MINUTES)
                .build();
    }

    public static void setClient(OkHttpClient client) {
        HttpClient.client = client;
    }

    public static void get(String url, Map<String, Object> headers, Callback callback) throws IOException {
        HttpUrl httpUrl = new UrlBuilder(url).build();
        HttpClient.get(httpUrl, headers, callback);
    }

    public static void get(HttpUrl httpUrl, Map<String, Object> headers, Callback callback) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl)
                .get();
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, String.valueOf(value));
            });
        }

        Request request = requestBuilder.build();
        client.newCall(request).enqueue(callback);
    }

    public static void post(String url, Map<String, Object> headers, String json, Callback callback) throws IOException {
        HttpUrl httpUrl = new UrlBuilder(url).build();
        HttpClient.post(httpUrl, headers, json, callback);
    }

    public static void post(HttpUrl httpUrl, Map<String, Object> headers, String json, Callback callback) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl);
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, String.valueOf(value));
            });
        }

        Request request = requestBuilder.post(body).build();
        client.newCall(request).enqueue(callback);
    }

    public static void post(String url, Map<String, Object> headers, Map<String, Object> form, Callback callback) throws IOException {
        HttpUrl httpUrl = new UrlBuilder(url).build();
        HttpClient.post(httpUrl, headers, form, callback);
    }

    public static void post(HttpUrl httpUrl, Map<String, Object> headers, Map<String, Object> form, Callback callback) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl);
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, String.valueOf(value));
            });
        }

        FormBody.Builder formBodyBuidler = new FormBody.Builder();
        if (form != null) {
            form.forEach((key, value) -> {
                formBodyBuidler.add(key, String.valueOf(value));
            });
        }
        RequestBody body = formBodyBuidler.build();

        Request request = requestBuilder.post(body).build();
        client.newCall(request).enqueue(callback);
    }

    public static void put(String url, Map<String, Object> headers, Map<String, Object> form, Callback callback) throws IOException {
        HttpUrl httpUrl = new UrlBuilder(url).build();
        HttpClient.put(httpUrl, headers, form, callback);
    }

    public static void put(HttpUrl httpUrl, Map<String, Object> headers, Map<String, Object> form, Callback callback) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl);
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, String.valueOf(value));
            });
        }

        FormBody.Builder formBodyBuidler = new FormBody.Builder();
        if (form != null) {
            form.forEach((key, value) -> {
                formBodyBuidler.add(key, String.valueOf(value));
            });
        }
        RequestBody body = formBodyBuidler.build();

        Request request = requestBuilder.put(body).build();
        client.newCall(request).enqueue(callback);
    }

    public static void put(String url, Map<String, Object> headers, String json, Callback callback) throws IOException {
        HttpUrl httpUrl = new UrlBuilder(url).build();
        HttpClient.put(httpUrl, headers, json, callback);
    }

    public static void put(HttpUrl httpUrl, Map<String, Object> headers, String json, Callback callback) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl);
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, String.valueOf(value));
            });
        }

        Request request = requestBuilder.put(body).build();
        client.newCall(request).enqueue(callback);
    }

    public static void delete(String url, Map<String, Object> headers, Callback callback) throws IOException {
        HttpUrl httpUrl = new UrlBuilder(url).build();
        HttpClient.delete(httpUrl, headers, callback);
    }

    public static void delete(HttpUrl httpUrl, Map<String, Object> headers, Callback callback) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrl)
                .delete();
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, String.valueOf(value));
            });
        }

        Request request = requestBuilder.build();
        client.newCall(request).enqueue(callback);
    }
}
