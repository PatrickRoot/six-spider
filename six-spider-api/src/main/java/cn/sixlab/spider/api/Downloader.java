package cn.sixlab.spider.api;

import cn.sixlab.spider.model.Page;
import cn.sixlab.spider.model.Url;

public interface Downloader<T> {
    
    Page<T> download(Url url);

}
