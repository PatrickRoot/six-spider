/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/9/24 16:17
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.sample;

import cn.sixlab.spider.SpiderEngine;

public class Sample {
    public static void main(String[] args) {
        SpiderEngine engine = SpiderEngine.builder().build();

        engine.start();
    }
}
