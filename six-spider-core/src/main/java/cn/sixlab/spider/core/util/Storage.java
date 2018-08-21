/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 22:36
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.core.util;

import cn.sixlab.spider.core.model.Url;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Storage {
    private static Queue<Url> urlList = new LinkedList<>();
    
    public static Url poll() {
        return urlList.poll();
    }
    
    public static void push(Url url) {
        urlList.add(url);
    }
    
    public static void push(List<Url> newUrlList) {
        urlList.addAll(newUrlList);
    }
}
