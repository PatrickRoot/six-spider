/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 22:54
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.core.impl;

import cn.sixlab.spider.core.api.Saver;
import cn.sixlab.spider.core.model.Page;

public class SaverImpl implements Saver {
    @Override
    public void store(Page page) {
        System.out.println(page.getUrl().getUrl());
        System.out.println(page.getData());
    }
}
