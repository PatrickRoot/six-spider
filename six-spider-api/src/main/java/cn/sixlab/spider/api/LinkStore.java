/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 22:09
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.api;

import cn.sixlab.spider.api.model.Url;

import java.util.List;

public interface LinkStore {
    Url pop();
    
    void push(Url url);
    
    void push(List<Url> urlList);
    
}
