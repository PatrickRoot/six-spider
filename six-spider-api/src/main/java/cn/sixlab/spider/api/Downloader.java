package cn.sixlab.spider.api;

import cn.sixlab.spider.api.model.Page;
import cn.sixlab.spider.api.model.Url;

public interface Downloader<T> {
    
    Page<T> download(Url url);
}
