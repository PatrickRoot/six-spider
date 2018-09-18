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
package cn.sixlab.spider.api.impl;

import cn.sixlab.spider.api.Saver;
import cn.sixlab.spider.api.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaverImpl implements Saver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void store(Page page) {
        logger.debug("请求:"+page.getUrl().getLink()+">>>>>"+page.getData());
    }
}
