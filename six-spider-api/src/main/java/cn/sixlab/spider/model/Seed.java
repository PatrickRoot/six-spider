/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/9/23 07:28
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.model;

import java.util.ArrayList;
import java.util.List;

public class Seed {
    private List<Url> urlList = new ArrayList<>();
    
    public Seed() {
    
    }
    
    public Seed(String link) {
        urlList.add(new Url(link));
    }
    
    public List<Url> getUrlList() {
        return urlList;
    }
    
    public void setUrlList(List<Url> urlList) {
        this.urlList = urlList;
    }
}
