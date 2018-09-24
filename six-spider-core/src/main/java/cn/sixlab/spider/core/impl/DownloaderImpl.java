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

import cn.sixlab.spider.api.Downloader;
import cn.sixlab.spider.model.Page;
import cn.sixlab.spider.model.Url;
import cn.sixlab.spider.core.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloaderImpl implements Downloader {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public Page download(Url url) {
        logger.info("下载：" + url);
        String resp = HttpUtil.sendRequest(url.getLink(), url.getMethod(), url.getParam(), url.getHeader());
        return Page.newPage(url, resp);
    }
}
