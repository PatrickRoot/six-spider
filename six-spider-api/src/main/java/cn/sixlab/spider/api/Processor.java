package cn.sixlab.spider.api;

import cn.sixlab.spider.model.Page;

public interface Processor<T> {
    Page<T> process(Page<T> page);
}
