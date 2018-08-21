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

import cn.sixlab.spider.core.api.LinkStore;
import cn.sixlab.spider.core.model.Url;
import cn.sixlab.spider.core.util.Storage;

import java.util.List;

public class LinkStoreImpl implements LinkStore {
    @Override
    public Url pop() {
        return Storage.poll();
    }
    
    @Override
    public void push(Url url) {
        Storage.push(url);
    }
    
    @Override
    public void push(List<Url> urlList) {
        Storage.push(urlList);
    }
}
