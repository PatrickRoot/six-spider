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

public class ProcessorImpl implements Processor {
    @Override
    public Page process(Page page) {
        page.addNewUrl(page.getUrl());
        page.setData(page.getDocument().data());
        return page;
    }
}
