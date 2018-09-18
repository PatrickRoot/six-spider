package cn.sixlab.spider;

import cn.sixlab.spider.api.Downloader;
import cn.sixlab.spider.api.LinkStore;
import cn.sixlab.spider.api.Processor;
import cn.sixlab.spider.api.Saver;
import cn.sixlab.spider.model.SpiderException;
import cn.sixlab.spider.model.Url;

import java.util.ArrayList;
import java.util.List;


public class SpiderBuilder {
    
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

        SpiderEngine engine = new SpiderEngine();
        engine.setDownloader(downloader);
        engine.setLinkStore(linkStore);
        engine.setProcessor(processor);
        engine.setSaver(saver);
        engine.setThread(thread <= 0 ? 5 : thread);
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
