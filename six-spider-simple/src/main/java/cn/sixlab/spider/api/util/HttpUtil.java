/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 21:51
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.api.util;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    
    private static OkHttpClient client = new OkHttpClient();
    public static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    
    /**
     * 请求URL，不修改Header的值，含有参数。
     *
     * @param url    请求的链接
     * @param method POST，GET 等
     * @param data   请求的参数
     * @return
     */
    public static String sendRequest(String url, String method, Map<String, String> data) {
        return sendRequest(url, method, data, null);
    }
    
    /**
     * 请求URL，需要修改Header的值，含有参数。
     *
     * @param url    请求的链接
     * @param method POST，GET 等
     * @param data   请求的参数
     * @param header 请求的 header 参数
     * @return
     */
    public static String sendRequest(String url, String method, Map<String, String> data,
            Map<String, String> header) {
        if ("post".equalsIgnoreCase(method)) {
            return sendPost(url, data, header);
        } else {
            return sendGet(url, data, header);
        }
    }
    
    /**
     * POST 请求URL，参数是 Form 提交，不需要修改Header的值。
     *
     * @param url  请求的链接
     * @param data 请求的参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> data) {
        return sendPost(url, data, null);
    }
    
    /**
     * POST 请求URL，参数是 Form 提交，需要修改Header的值。
     *
     * @param url    请求的链接
     * @param data   请求的参数
     * @param header 请求的 header 参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> data,
            Map<String, String> header) {
        FormBody.Builder builder = new FormBody.Builder();
        if (null != data && !data.isEmpty()) {
            for (String key : data.keySet()) {
                String val = data.get(key);
                if (null != val && val.length() > 0) {
                    builder.add(key, val);
                }
            }
        }
        
        Headers.Builder headersBuilder = new Headers.Builder();
        if (null != header && !header.isEmpty()) {
            for (String key : header.keySet()) {
                String val = header.get(key);
                if (null != val && val.length() > 0) {
                    headersBuilder.add(key, header.get(key));
                }
            }
        }
        
        Request request = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .post(builder.build())
                .build();
        
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseHandler(response);
    }
    
    /**
     * GET 请求URL，不需要修改Header的值，含有参数。
     *
     * @param url  请求的链接
     * @param data 请求的参数
     * @return
     */
    public static String sendGet(String url, Map<String, String> data) {
        return sendGet(url, data, null);
    }
    
    /**
     * GET 请求URL，需要修改Header的值，含有参数。
     *
     * @param url    请求的链接
     * @param data   请求的参数
     * @param header 请求的 header 参数
     * @return
     */
    public static String sendGet(String url, Map<String, String> data, Map<String, String> header) {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (null != header && !header.isEmpty()) {
            for (String key : header.keySet()) {
                headersBuilder.add(key, header.get(key));
            }
        }
        
        Request request = new Request.Builder()
                .url(url + "?" + makeGetParam(data))
                .headers(headersBuilder.build())
                .build();
        
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseHandler(response);
    }
    
    /**
     * POST 请求URL，提交是POST提交，不需要修改Header的值。
     *
     * @param url  请求的链接
     * @param json 请求的参数
     * @return
     */
    public static String sendPostBody(String url, String json) {
        return sendPostBody(url, json, null);
    }
    
    /**
     * POST 请求URL，提交是POST提交，需要修改Header的值。
     *
     * @param url    请求的链接
     * @param header   请求的参数
     * @param header 请求的 header 参数
     * @return
     */
    public static String sendPostBody(String url, String json, Map<String, String> header) {
        Headers.Builder builder = new Headers.Builder();
        if (null != header && !header.isEmpty()) {
            for (String key : header.keySet()) {
                builder.add(key, header.get(key));
            }
        }
        
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(builder.build())
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseHandler(response);
    }
    
    /**
     * 转换 map 为 get 请求的的参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data 要转换的map
     * @return
     */
    public static String makeGetParam(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        if (null != data && !data.isEmpty()) {
            Iterator<String> iterator = data.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String val = data.get(key);
                sb.append(key);
                sb.append("=");
                sb.append(val);
                if (iterator.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 转换 map 为 get 请求的的参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data 要转换的map
     * @return
     */
    public static String makeGetUrl(String url, Map<String, String> data) {
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        sb.append(makeGetParam(data));
        return sb.toString();
    }
    
    private static String responseHandler(Response response) {
        if (response != null) {
            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    logger.error("请求错误：" + response);
                    e.printStackTrace();
                }
            } else {
                logger.error("请求失败：" + response);
            }
        }
        return "";
    }
}
