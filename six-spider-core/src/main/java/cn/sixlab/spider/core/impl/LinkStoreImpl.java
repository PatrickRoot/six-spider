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
package cn.sixlab.spider.core.impl;

import cn.sixlab.spider.api.LinkStore;
import cn.sixlab.spider.model.Url;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LinkStoreImpl implements LinkStore {
    
    private ConcurrentLinkedQueue<Url> urlList = new ConcurrentLinkedQueue<>();
    
    @Override
    public Url poll() {
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
