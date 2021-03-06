/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 21:45
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.model;

import java.util.ArrayList;
import java.util.List;

public class Page <T>{
    private Url url;
    private String response;
    private List<Url> newUrl = new ArrayList<>();
    private T data;

    public static <T> Page<T> newPage(Url url, String resp) {
        Page page = new Page();
        page.setUrl(url);
        page.setResponse(resp);
        return page;
    }
    
    public void addNewUrl(Url url) {
        newUrl.add(url);
    }
    
    public void addNewUrl(List<Url> newUrl) {
        newUrl.addAll(newUrl);
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Url> getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(List<Url> newUrl) {
        this.newUrl = newUrl;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
