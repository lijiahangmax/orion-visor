package com.orion.ops.framework.redis.core.utils;

import com.orion.lang.define.cache.key.model.LongCacheIdModel;
import com.orion.lang.utils.collect.Lists;
import com.orion.ops.framework.common.constant.Const;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * 缓存工具类
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023/11/15 1:22
 */
public class CacheUtils {

    protected CacheUtils() {
    }

    /**
     * 创建屏障对象 防止穿透
     *
     * @param supplier supplier
     * @param <T>      T
     * @return barrier
     */
    public static <T extends LongCacheIdModel> T createBarrier(Supplier<T> supplier) {
        T val = supplier.get();
        val.setId(Const.NONE_ID);
        return val;
    }


    /**
     * 检测是否需要 创建屏障对象 防止穿透
     *
     * @param list     list
     * @param supplier supplier
     * @param <T>      T
     */
    public static <T extends LongCacheIdModel> void checkBarrier(List<T> list, Supplier<T> supplier) {
        if (list != null && list.isEmpty()) {
            // 添加屏障对象
            list.add(createBarrier(supplier));
        }
    }

    /**
     * 移除屏障对象
     *
     * @param list list
     * @param <T>  T
     */
    public static <T extends LongCacheIdModel> void removeBarrier(Collection<T> list) {
        if (!Lists.isEmpty(list)) {
            list.removeIf(s -> Const.NONE_ID.equals(s.getId()));
        }
    }

}

