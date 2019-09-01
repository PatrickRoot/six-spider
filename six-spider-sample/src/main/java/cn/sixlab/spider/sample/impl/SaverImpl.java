/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/9/24 16:23
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.sample.impl;

import cn.sixlab.spider.api.Saver;
import cn.sixlab.spider.model.Page;
import org.jsoup.nodes.Document;

public class SaverImpl implements Saver<Document> {
    @Override
    public void store(Page<Document> page) {
    
    }
}
