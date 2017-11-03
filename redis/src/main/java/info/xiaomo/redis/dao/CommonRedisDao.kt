package info.xiaomo.redis.dao

import org.springframework.data.redis.core.ListOperations

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 *
 * Date: 2016/11/14 17:59
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

interface CommonRedisDao {

    /**
     * 添加
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    fun cacheValue(key: String, value: String, time: Long): Boolean

    /**
     * 添加
     *
     * @param key
     * @param value
     * @return
     */
    fun cacheValue(key: String, value: String): Boolean

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    fun containsValueKey(key: String): Boolean

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    fun containsSetKey(key: String): Boolean

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    fun containsListKey(key: String): Boolean

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    fun containsKey(key: String): Boolean

    /**
     * 获取缓存
     * @param key
     * @return
     */
    fun getValue(key: String): String

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    fun removeValue(key: String): Boolean

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    fun removeSet(key: String): Boolean

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    fun removeList(key: String): Boolean

    /**
     * 缓存Set
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    fun cacheSet(key: String, value: String, time: Long): Boolean

    /**
     * 缓存Set
     *
     * @param key
     * @param value
     * @return
     */
    fun cacheSet(key: String, value: String): Boolean

    /**
     * 缓存Set
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    fun cacheSet(k: String, v: Set<String>, time: Long): Boolean

    /**
     * 缓存Set
     *
     * @param k
     * @param v
     * @return
     */
    fun cacheSet(k: String, v: Set<String>): Boolean

    /**
     * 获取Set
     *
     * @param k
     * @return
     */
    fun getSet(k: String): Set<String>

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    fun cacheList(k: String, v: String, time: Long): Boolean

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @return
     */
    fun cacheList(k: String, v: String): Boolean

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    fun cacheList(k: String, v: List<String>, time: Long): Boolean

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @return
     */
    fun cacheList(k: String, v: List<String>): Boolean

    /**
     * 获取List
     *
     * @param k
     * @param start
     * @param end
     * @return
     */
    fun getList(k: String, start: Long, end: Long): List<String>

    /**
     * 获取页码
     *
     * @param key
     * @return
     */
    fun getListSize(key: String): Long

    /**
     * 获取页码
     *
     * @param listOps
     * @param k
     * @return
     */
    fun getListSize(listOps: ListOperations<String, String>, k: String): Long

    /**
     * 移除list缓存
     *
     * @param k
     * @return
     */
    fun removeOneOfList(k: String): Boolean
}
