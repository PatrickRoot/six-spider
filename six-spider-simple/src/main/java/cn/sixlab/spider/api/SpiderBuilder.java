package cn.sixlab.spider.api;

import cn.sixlab.spider.api.impl.DownloaderImpl;
import cn.sixlab.spider.api.impl.LinkStoreImpl;
import cn.sixlab.spider.api.impl.ProcessorImpl;
import cn.sixlab.spider.api.impl.SaverImpl;
import cn.sixlab.spider.api.model.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SpiderBuilder {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private Downloader downloader;
    private LinkStore linkStore;
    private Processor processor;
    private Saver saver;
    private List<Url> urlList;
    private int thread;
    
    private SpiderBuilder() {
        urlList = new ArrayList<>();
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
        engine.setThread(thread<0?5:thread);
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
    
    public SpiderBuilder setThread(int thread) {
        this.thread = thread;
        return this;
    }

    public SpiderBuilder addLink(String link) {
        Url url = new Url();
        url.setLink(link);
        urlList.add(url);
        return this;
    }

    public SpiderBuilder addLink(List<String> linkList) {
        for (String link : linkList) {
            Url url = new Url();
            url.setLink(link);
            urlList.add(url);
        }
        return this;
    }
}
