package info.xiaomo.redis.dao.impl

import info.xiaomo.redis.dao.CommonRedisDao
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

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
 * Date: 2016/11/14 18:00
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

@Repository
class CommonRedisDaoImpl @Autowired
constructor(private val redisTemplate: RedisTemplate<String, String>) : CommonRedisDao {
    /**
     * 日志记录
     */
    private val LOGGER = LoggerFactory.getLogger(this.javaClass)

    /**
     * 缓存value操作
     *
     * @param key    key
     * @param value    value
     * @param time time
     * @return boolean
     */
    override fun cacheValue(key: String, value: String, time: Long): Boolean {
        val keyStr = KEY_PREFIX_VALUE + key
        try {
            val valueOps = redisTemplate.opsForValue()
            valueOps.set(keyStr, value)
            if (time > 0) {
                redisTemplate.expire(keyStr, time, TimeUnit.SECONDS)
            }
            return true
        } catch (t: Throwable) {
            LOGGER.error("缓存[$keyStr]失败, value[$value]", t)
        }

        return false
    }

    /**
     * 缓存value操作
     *
     * @param key key
     * @param value value
     * @return boolean
     */
    override fun cacheValue(key: String, value: String): Boolean {
        return cacheValue(key, value, -1)
    }

    /**
     * 判断缓存是否存在
     *
     * @param key key
     * @return boolean
     */
    override fun containsValueKey(key: String): Boolean {
        return containsKey(KEY_PREFIX_VALUE + key)
    }

    /**
     * 判断缓存是否存在
     *
     * @param key key
     * @return boolean
     */
    override fun containsSetKey(key: String): Boolean {
        return containsKey(KEY_PREFIX_SET + key)
    }

    /**
     * 判断缓存是否存在
     *
     * @param key key
     * @return boolean
     */
    override fun containsListKey(key: String): Boolean {
        return containsKey(KEY_PREFIX_LIST + key)
    }

    override fun containsKey(key: String): Boolean {
        try {
            return redisTemplate.hasKey(key)!!
        } catch (t: Throwable) {
            LOGGER.error("判断缓存存在失败key[$key, Codeor[$t]")
        }

        return false
    }

    /**
     * 获取缓存
     *
     * @param key key
     * @return string
     */
    override fun getValue(key: String): String? {
        try {
            val valueOps = redisTemplate.opsForValue()
            return valueOps.get(KEY_PREFIX_VALUE + key)
        } catch (t: Throwable) {
            LOGGER.error("获取缓存失败key[$KEY_PREFIX_VALUE$key, Codeor[$t]")
        }

        return null
    }

    /**
     * 移除缓存
     *
     * @param key key
     * @return boolean
     */
    override fun removeValue(key: String): Boolean {
        return remove(KEY_PREFIX_VALUE + key)
    }

    override fun removeSet(key: String): Boolean {
        return remove(KEY_PREFIX_SET + key)
    }

    override fun removeList(key: String): Boolean {
        return remove(KEY_PREFIX_LIST + key)
    }


    /**
     * 缓存set操作
     *
     * @param key    key
     * @param value    value
     * @param time time
     * @return boolean
     */
    override fun cacheSet(key: String, value: String, time: Long): Boolean {
        val keyStr = KEY_PREFIX_SET + key
        try {
            val valueOps = redisTemplate.opsForSet()
            valueOps.add(keyStr, value)
            if (time > 0) {
                redisTemplate.expire(keyStr, time, TimeUnit.SECONDS)
            }
            return true
        } catch (t: Throwable) {
            LOGGER.error("缓存[$keyStr]失败, value[$value]", t)
        }

        return false
    }

    /**
     * 缓存set
     *
     * @param key key
     * @param value value
     * @return boolean
     */
    override fun cacheSet(key: String, value: String): Boolean {
        return cacheSet(key, value, -1)
    }

    /**
     * 缓存set
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    override fun cacheSet(k: String, v: Set<String>, time: Long): Boolean {
        val key = KEY_PREFIX_SET + k
        try {
            val setOps = redisTemplate.opsForSet()
            setOps.add(key, *v.toTypedArray())
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS)
            }
            return true
        } catch (t: Throwable) {
            LOGGER.error("缓存[$key]失败, value[$v]", t)
        }

        return false
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    override fun cacheSet(k: String, v: Set<String>): Boolean {
        return cacheSet(k, v, -1)
    }

    /**
     * 获取缓存set数据
     *
     * @param k key
     * @return set
     */
    override fun getSet(k: String): Set<String>? {
        try {
            val setOps = redisTemplate.opsForSet()
            return setOps.members(KEY_PREFIX_SET + k)
        } catch (t: Throwable) {
            LOGGER.error("获取set缓存失败key[$KEY_PREFIX_SET$k, Codeor[$t]")
        }

        return null
    }

    /**
     * list缓存
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    override fun cacheList(k: String, v: String, time: Long): Boolean {
        val key = KEY_PREFIX_LIST + k
        try {
            val listOps = redisTemplate.opsForList()
            listOps.rightPush(key, v)
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS)
            }
            return true
        } catch (t: Throwable) {
            LOGGER.error("缓存[$key]失败, value[$v]", t)
        }

        return false
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    override fun cacheList(k: String, v: String): Boolean {
        return cacheList(k, v, -1)
    }

    /**
     * 缓存list
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    override fun cacheList(k: String, v: List<String>, time: Long): Boolean {
        val key = KEY_PREFIX_LIST + k
        try {
            val listOps = redisTemplate.opsForList()
            listOps.rightPushAll(key, v)
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS)
            }
            return true
        } catch (t: Throwable) {
            LOGGER.error("缓存[$key]失败, value[$v]", t)
        }

        return false
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    override fun cacheList(k: String, v: List<String>): Boolean {
        return cacheList(k, v, -1)
    }

    /**
     * 获取list缓存
     *
     * @param k     key
     * @param start start
     * @param end   end
     * @return list
     */
    override fun getList(k: String, start: Long, end: Long): List<String>? {
        try {
            val listOps = redisTemplate.opsForList()
            return listOps.range(KEY_PREFIX_LIST + k, start, end)
        } catch (t: Throwable) {
            LOGGER.error("获取list缓存失败key[$KEY_PREFIX_LIST$k, Codeor[$t]")
        }

        return null
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param key key
     * @return long
     */
    override fun getListSize(key: String): Long {
        try {
            val listOps = redisTemplate.opsForList()
            return listOps.size(KEY_PREFIX_LIST + key)!!
        } catch (t: Throwable) {
            LOGGER.error("获取list长度失败key[$KEY_PREFIX_LIST$key], Codeor[$t]")
        }

        return 0
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps listOps
     * @param k       k
     * @return long
     */
    override fun getListSize(listOps: ListOperations<String, String>, k: String): Long {
        try {
            return listOps.size(KEY_PREFIX_LIST + k)!!
        } catch (t: Throwable) {
            LOGGER.error("获取list长度失败key[$KEY_PREFIX_LIST$k], Codeor[$t]")
        }

        return 0
    }

    /**
     * 移除list缓存
     *
     * @param k k
     * @return boolean
     */
    override fun removeOneOfList(k: String): Boolean {
        val key = KEY_PREFIX_LIST + k
        try {
            val listOps = redisTemplate.opsForList()
            listOps.rightPop(key)
            return true
        } catch (t: Throwable) {
            LOGGER.error("移除list缓存失败key[$KEY_PREFIX_LIST$k, Codeor[$t]")
        }

        return false
    }

    /**
     * 移除缓存
     *
     * @param key key
     * @return boolean
     */
    private fun remove(key: String): Boolean {
        try {
            redisTemplate.delete(key)
            return true
        } catch (t: Throwable) {
            LOGGER.error("获取缓存失败key[$key, Codeor[$t]")
        }

        return false
    }

    companion object {
        /**
         * 前缀
         */
        private val KEY_PREFIX_VALUE = "info:xiaomo:value:"
        private val KEY_PREFIX_SET = "info:xiaomo:set:"
        private val KEY_PREFIX_LIST = "info:xiaomo:list:"
    }
}
