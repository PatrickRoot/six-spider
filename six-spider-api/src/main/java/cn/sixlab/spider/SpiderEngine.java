package cn.sixlab.spider;

import cn.sixlab.spider.api.Downloader;
import cn.sixlab.spider.api.LinkStore;
import cn.sixlab.spider.api.Processor;
import cn.sixlab.spider.api.Saver;
import cn.sixlab.spider.model.Page;
import cn.sixlab.spider.model.Seed;
import cn.sixlab.spider.model.SpiderException;
import cn.sixlab.spider.model.Url;
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
    
    private SpiderEngine() {
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    private void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }
    
    private void setLinkStore(LinkStore linkStore) {
        this.linkStore = linkStore;
    }
    
    private void setProcessor(Processor processor) {
        this.processor = processor;
    }
    
    private void setSaver(Saver saver) {
        this.saver = saver;
    }
    
    private void setThread(int thread) {
        if (thread <= 0) {
            thread = 5;
        }
        pool = new ThreadPoolExecutor(thread, thread, 1500L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
    
    public void start() {
        logger.info("六只蜘蛛出发!");
        
        crawling = true;
        
        while (crawling) {
            logger.info("六只蜘蛛线程池" + "已完成:" + pool.getCompletedTaskCount()
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
            Url url = linkStore.poll();
            if (url == null && pool.getActiveCount() == 0) {
                pool.shutdown();
                try {
                    pool.awaitTermination(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    logger.error("六只蜘蛛关闭线程池失败！", e);
                }
                logger.info("六只蜘蛛已回家!");
                break;
            } else if (url == null) {
                //没有取到种子就等待!
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                logger.info("六只蜘蛛发现目标:" + url.getLink());
                pool.execute(new SpiderThread(url.clone()));
            }
        }
        
        logger.info("六只蜘蛛已回家!");
    }
    
    public void stop() {
        crawling = false;
    }
    
    class SpiderThread implements Runnable {
        private Url url;
        
        SpiderThread(Url url) {
            this.url = url;
        }
        
        @Override
        public void run() {
            Page page = downloader.download(url);
            
            if (null != page) {
                page = processor.process(page);
                
                saver.store(page);
                
                if (null != page.getNewUrl() && page.getNewUrl().size() > 0) {
                    linkStore.push(page.getNewUrl());
                }
            }
        }
    }
    
    public static class Builder {
        
        private Downloader downloader;
        private LinkStore linkStore;
        private Processor processor;
        private Saver saver;
        private Seed seed;
        private int thread;
        
        private Builder() {
        }
        
        public SpiderEngine build() {
            if (null == downloader) {
                throw new SpiderException("下载器 Downloader 未设置");
            }
            if (null == linkStore) {
                throw new SpiderException("链接存储器 LinkStore 未设置");
            }
            if (null == processor) {
                throw new SpiderException("页面处理器 Processor 未设置");
            }
            if (null == saver) {
                throw new SpiderException("内存存储器 Saver 未设置");
            }
            if (null == seed) {
                throw new SpiderException("种子 Seed 未设置");
            }
            
            linkStore.push(seed.getUrlList());
            
            SpiderEngine engine = new SpiderEngine();
            engine.setDownloader(downloader);
            engine.setLinkStore(linkStore);
            engine.setProcessor(processor);
            engine.setSaver(saver);
            engine.setThread(thread <= 0 ? 5 : thread);
            return engine;
        }
        
        public Builder setDownloader(Downloader downloader) {
            this.downloader = downloader;
            return this;
        }
        
        public Builder setLinkStore(LinkStore linkStore) {
            this.linkStore = linkStore;
            return this;
        }
        
        public Builder setProcessor(Processor processor) {
            this.processor = processor;
            return this;
        }
        
        public Builder setSaver(Saver saver) {
            this.saver = saver;
            return this;
        }
        
        public Builder setThread(int thread) {
            this.thread = thread;
            return this;
        }
    
        public Builder setSeed(String url) {
            this.seed = new Seed(url);
            return this;
        }
    
        public Builder setSeed(Seed seed) {
            this.seed = seed;
            return this;
        }
    }
}
