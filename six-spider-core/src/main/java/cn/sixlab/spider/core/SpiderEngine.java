package cn.sixlab.spider.core;

import cn.sixlab.spider.core.api.Downloader;
import cn.sixlab.spider.core.api.LinkStore;
import cn.sixlab.spider.core.api.Processor;
import cn.sixlab.spider.core.api.Saver;
import cn.sixlab.spider.core.model.Page;
import cn.sixlab.spider.core.model.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SpiderEngine {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private Downloader downloader;
    private LinkStore linkStore;
    private Processor processor;
    private Saver saver;
    private boolean crawling = false;
    private ThreadPoolExecutor pool;
    
    protected SpiderEngine() {
    }
    
    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }
    
    public void setLinkStore(LinkStore linkStore) {
        this.linkStore = linkStore;
    }
    
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
    
    public void setSaver(Saver saver) {
        this.saver = saver;
    }
    
    public void setThread(int thread){
        if (thread <= 0) {
            thread = 5;
        }
        pool = new ThreadPoolExecutor(thread, thread, 1500L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
    
    public void start() {
        crawling = true;
        
        while (crawling) {
            logger.info("当前线程池" + "已完成:" + pool.getCompletedTaskCount()
                    + "    运行中：" + pool.getActiveCount()
                    + "    最大运行:" + pool.getPoolSize()
                    + "    等待队列:" + pool.getQueue().size());
            
            if (pool.getQueue().size() > pool.getCorePoolSize()) {
                //如果等待队列大于了100.就暂停接收新的url。不然会影响优先级队列的使用。
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            Url url = linkStore.pop();
            if (url == null && pool.getActiveCount() == 0) {
                pool.shutdown();
                try {
                    pool.awaitTermination(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    logger.error("关闭线程池失败！", e);
                }
                logger.info("爬虫结束！");
                break;
            } else if (url == null) {
                //没有取到种子就等待!
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                logger.info("正在处理:" + url.getUrl());
                pool.execute(new SpiderThread(url.clone()));
            }
        }
    }
    
    public void stop() {
        crawling = false;
    }
    
    private void loop(){
        Url url = linkStore.pop();
    }
    
    class SpiderThread implements Runnable{
        private Url url;
    
        SpiderThread(Url url) {
            this.url = url;
        }
    
        @Override
        public void run() {
            Page page = downloader.download(url);
            page = processor.process(page);
            saver.store(page);
            linkStore.push(page.getNewUrl());
        }
    }
}
