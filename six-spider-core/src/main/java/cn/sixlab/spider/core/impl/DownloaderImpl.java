/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 22:48
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.core.impl;

import cn.sixlab.spider.core.api.Downloader;
import cn.sixlab.spider.core.model.Page;
import cn.sixlab.spider.core.model.Url;
import cn.sixlab.spider.core.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloaderImpl implements Downloader {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public Page download(Url url) {
        logger.debug("下载：" + url.getUrl());
        String resp = HttpUtil.sendGet(url.getUrl(), url.getParam());
        return Page.newPage(url, resp);
    }
}
