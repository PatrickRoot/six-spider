package cn.sixlab.spider.core;

import cn.sixlab.spider.SpiderEngine;
import cn.sixlab.spider.api.Processor;
import cn.sixlab.spider.api.Saver;
import cn.sixlab.spider.model.Page;
import cn.sixlab.spider.model.Seed;
import cn.sixlab.spider.model.Url;
import cn.sixlab.spider.core.impl.DownloaderImpl;
import cn.sixlab.spider.core.impl.LinkStoreImpl;
import org.testng.annotations.Test;

public class SimpleSixSpiderTest {
    
    private SpiderEngine build() {
        return SpiderEngine.builder()
                .setDownloader(new DownloaderImpl())
                .setLinkStore(new LinkStoreImpl())
                .setProcessor(new Processor<String>() {
                    
                    @Override
                    public Page<String> process(Page<String> page) {
    
                        page.setData(page.getResponse());
    
                        if (null == page.getResponse() || page.getResponse().length() == 932) {
                            page.addNewUrl(new Url("https://blog.sixlab.cn/archives/932/"));
                        }
                        
                        return page;
                    }
                })
                .setSaver(new Saver() {
                    @Override
                    public void store(Page page) {
                        System.out.println("<" + page.getUrl() + ">结果:" + page.getData());
                    }
                }).setSeed(new Seed("https://blog.sixlab.cn/")).setThread(5).build();
    }
    
    @Test
    public void testStart() {
        SpiderEngine engine = build();
        
        engine.start();
    }
}