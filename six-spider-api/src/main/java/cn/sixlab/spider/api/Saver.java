/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/8/21 21:42
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.spider.api;

import cn.sixlab.spider.model.Page;

public interface Saver<T> {
    void store(Page<T> page);
}
