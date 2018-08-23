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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DownloaderImpl implements Downloader {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public Page download(Url url) {
        logger.info("下载：" + url.getLink());
        // String resp = HttpUtil.sendGet(url.getUrl(), url.getParam());

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append(sdf.format(new Date()));
        try {
            Thread.sleep(new Random().nextInt(3000));
            // 模拟下载
            sb.append("：id="+Thread.currentThread().getId());
            sb.append("；name=" +Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return Page.newPage(url, sb.toString());
    }
}
