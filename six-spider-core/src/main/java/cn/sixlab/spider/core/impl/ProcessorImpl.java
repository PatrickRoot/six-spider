/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 22:52
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.core.impl;

import cn.sixlab.spider.core.api.Processor;
import cn.sixlab.spider.core.model.Page;
import cn.sixlab.spider.core.model.Url;

import java.util.Random;

public class ProcessorImpl implements Processor {
    @Override
    public Page process(Page page) {
        if(new Random().nextBoolean()){
            Url url = new Url(page.getUrl().getLink() + "#" + Thread.currentThread().getId());
            page.addNewUrl(url);
        }
        page.setData(page.getDocument().data());
        return page;
    }
}
