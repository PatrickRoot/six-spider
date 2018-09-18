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
package cn.sixlab.spider.api.model;

import java.util.Map;

public class Url implements Cloneable{
    private String link;
    private String method;
    private Map<String, String> param;
    private Map<String, String> header;

    public Url() {
        setMethod("get");
    }
    
    public Url(String link) {
        setLink(link);
        setMethod("get");
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public Map<String, String> getParam() {
        return param;
    }
    
    public void setParam(Map<String, String> param) {
        this.param = param;
    }
    
    public Map<String, String> getHeader() {
        return header;
    }
    
    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
    
    public Url clone() {
        Url url = null;
        try {
            url = (Url) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            return url;
        }
    }
}
