package cn.sixlab.spider.core.api;

import cn.sixlab.spider.core.model.Page;

public interface Processor<T> {
    Page<T> process(Page<T> page);
}
