package cn.sixlab.spider.core.api;

import cn.sixlab.spider.core.model.Page;
import cn.sixlab.spider.core.model.Url;

public interface Downloader<T> {
    
    Page<T> download(Url url);
}
