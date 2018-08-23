package cn.sixlab.spider.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SixSpider {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public static void main(String[] args) {
        // 1. SpiderBuilder 构造器，传入各个处理器类，构造一个爬虫引擎，有默认实现。
        // 2. SpiderEngine 启动后，开始进行爬虫。
        // 3. *从 队列类A 取出一个链接 Url 对象*
        // 4. *使用 下载类B 下载页面接受 Url 对象，下载页面得到 Page 对象*
        // 5. *使用 处理器类C 处理 Page 对象，得到 新链接 和 数据对象*
        // 6. *使用 存储器类D 处理新数据*
        // 7. *将新链接放入 队列类A*
        
        SpiderEngine engine = SpiderBuilder.builder()
                .setDownloader(null)
                .setLinkStore(null)
                .setProcessor(null)
                .setSaver(null)
                .addLink("https://www.sixlab.cn/")
                .build();

        engine.start();
    }

    public SixSpider run() {
        logger.info("六只蜘蛛出发!");
        return this;
    }

    public SixSpider stop() {
        logger.info("六只蜘蛛回家!");
        return this;
    }

    public SixSpider pause() {
        logger.info("六只蜘蛛休息!");
        return this;
    }
}
