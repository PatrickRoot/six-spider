package cn.sixlab.spider.core;

import cn.sixlab.spider.core.api.Downloader;
import cn.sixlab.spider.core.api.LinkStore;
import cn.sixlab.spider.core.api.Processor;
import cn.sixlab.spider.core.api.Saver;
import cn.sixlab.spider.core.impl.DownloaderImpl;
import cn.sixlab.spider.core.impl.LinkStoreImpl;
import cn.sixlab.spider.core.impl.ProcessorImpl;
import cn.sixlab.spider.core.impl.SaverImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpiderBuilder {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private Downloader downloader;
    private LinkStore linkStore;
    private Processor processor;
    private Saver saver;
    private long time;
    private int thread;
    
    private SpiderBuilder() {
    }
    
    public static SpiderBuilder builder() {
        return new SpiderBuilder();
    }
    
    public SpiderEngine build() {
        SpiderEngine engine = new SpiderEngine();
        engine.setDownloader(downloader == null ? new DownloaderImpl() : downloader);
        engine.setLinkStore(linkStore == null ? new LinkStoreImpl() : linkStore);
        engine.setProcessor(processor == null ? new ProcessorImpl() : processor);
        engine.setSaver(saver == null ? new SaverImpl() : saver);
        engine.setThread();
        return engine;
    }
    
    public SpiderBuilder setDownloader(Downloader downloader) {
        this.downloader = downloader;
        return this;
    }
    
    public SpiderBuilder setLinkStore(LinkStore linkStore) {
        this.linkStore = linkStore;
        return this;
    }
    
    public SpiderBuilder setProcessor(Processor processor) {
        this.processor = processor;
        return this;
    }
    
    public SpiderBuilder setSaver(Saver saver) {
        this.saver = saver;
        return this;
    }
    
    public SpiderBuilder setTime(long time) {
        this.time = time;
        return this;
    }
    
    public SpiderBuilder setThread(int thread) {
        this.thread = thread;
        return this;
    }
}
