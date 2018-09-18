/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 22:50
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.api.impl;

import cn.sixlab.spider.api.LinkStore;
import cn.sixlab.spider.api.model.Url;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkStoreImpl implements LinkStore {

    private static Queue<Url> urlList = new LinkedList<>();

    static {
        for (int i = 0; i < 10; i++) {
            Url url = new Url("http://www.example.com/"+i);
            urlList.add(url);
        }
    }

    @Override
    public Url pop() {
        return urlList.poll();
    }
    
    @Override
    public void push(Url url) {
        urlList.add(url);
    }
    
    @Override
    public void push(List<Url> newList) {
        urlList.addAll(newList);
    }
}
