/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/9/24 16:18
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.sample.impl;

import cn.sixlab.spider.api.Processor;
import cn.sixlab.spider.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ProcessorImpl implements Processor<Document> {
    @Override
    public Page<Document> process(Page page) {
        String url = page.getUrl().getLink();
        
        Document document = Jsoup.parse(page.getResponse(), url);
    
        page.setData(document);
        
        return page;
    }
}
