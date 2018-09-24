package cn.sixlab.spider;

import cn.sixlab.spider.api.Downloader;
import cn.sixlab.spider.api.LinkStore;
import cn.sixlab.spider.api.Processor;
import cn.sixlab.spider.api.Saver;
import cn.sixlab.spider.model.Page;
import cn.sixlab.spider.model.Seed;
import cn.sixlab.spider.model.Url;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SpiderEngineTest {
    
    private SpiderEngine build() {
        return SpiderEngine.builder().setDownloader(new Downloader() {
            @Override
            public Page download(Url url) {
                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                Page page = new Page();
                page.setResponse("result@" + url.getLink());
                page.setUrl(url);
                return page;
            }
        }).setLinkStore(new LinkStore() {
            private ConcurrentLinkedQueue<Url> urlList = new ConcurrentLinkedQueue<>();
            
            @Override
            public Url poll() {
                return urlList.poll();
            }
            
            @Override
            public void push(Url url) {
                urlList.add(url);
            }
            
            @Override
            public void push(List<Url> newList) {
                urlList.addAll(newList);
            }
        }).setProcessor(new Processor<String>() {
            private Integer index = 0;
            
            @Override
            public Page<String> process(Page<String> page) {
                synchronized (index){
                    index++;
                    page.setData(page.getResponse() + "@" + index);
    
                    if (index < 20) {
                        page.addNewUrl(new Url("https://blog.sixlab.cn/" + index));
                    }
                }
                
                return page;
            }
        }).setSaver(new Saver() {
            @Override
            public void store(Page page) {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("保存<" + page.getUrl() + ">结果:" + page.getData());
            }
        }).setSeed(new Seed("https://blog.sixlab.cn/")).setThread(5).build();
    }
    
    @Test
    public void testBuilder() {
        SpiderEngine engine = build();
        Assert.assertNotNull(engine);
    }
    
    @Test
    public void testStart() {
        SpiderEngine engine = build();
        
        engine.start();
    }
    
    @Test
    public void testStop() {
        SpiderEngine engine = build();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                engine.stop();
            }
        }).start();
    
        engine.start();
    }
}