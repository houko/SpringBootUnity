/**
 * 文   件  名：  RandomUtil.java
 * 工   程  名：  MainServer
 * 创建日期：  2015年2月5日 下午2:38:48
 * 创建作者：  杨  强 <281455776@qq.com>
 */
package info.xiaomo.core.untils

import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * 随机工具类
 *
 * @author : xiaomo
 */
object RandomUtil {
    private val LOGGER = LoggerFactory.getLogger(RandomUtil::class.java)
    private val NUM_S = "0123456789"
    private val STR_S = "abcdefghijklmnopqrstuvwxyz0123456789"


    /**
     * 生成一个10位的tonken用于http cache(纯数字)
     *
     * @return String    返回类型(纯数字)
     */
    val tonken: String
        get() = RandomStringUtils.random(10, NUM_S)

    /**
     * 随机产生min到max之间的一个整数值，包含min和max
     */
    fun random(min: Int, max: Int): Int {
        if (min > max) {
            throw IllegalArgumentException("传入的范围不合法!最小值不能大于最大值！")
        }
        return ThreadLocalRandom.current().nextInt(max - min + 1) + min
    }

    /**
     * 根据几率计算是否生成，成功几率是sucRange/maxRange
     *
     * @param maxRange 最大范围，随机范围是[1,maxRange]
     * @param sucRange 成功范围，成功范围是[1,sucRange]
     * @return 成功true失败false
     */
    fun isGenerate(maxRange: Int, sucRange: Int): Boolean {
        return maxRange == sucRange || sucRange != 0 && random(1, maxRange) <= sucRange
    }

    /**
     * 从指定的的元素集中随机一个元素
     *
     * @param collection 元素集
     */
    fun <T> randomElement(collection: Collection<T>?): T? {
        if (collection == null || collection.isEmpty()) {
            throw IllegalArgumentException("元素集不能为空！")
        }
        val index = random(0, collection.size - 1)
        val it = collection.iterator()
        var i = 0
        while (i <= index && it.hasNext()) {
            val t = it.next()
            if (i == index) {
                return t
            }
            i++
        }
        return null
    }

    /**
     * 生成随机数
     *
     * @return String    返回类型
     */
    fun randomPwd(count: Int): String {
        return RandomStringUtils.random(count, STR_S)
    }

    /**
     * 生成随机数
     *
     * @return String    返回类型
     */
    fun randomPwd(): String {
        return RandomStringUtils.random(10, STR_S)
    }

    /**
     * 从指定的元素数组中随机出一个元素
     *
     * @param array 元素数组
     */
    fun <T> randomElement(array: Array<T>?): T? {
        if (array == null || array.size == 0) {
            throw IllegalArgumentException("元素数组不能为空！")
        }
        return randomElement(Arrays.asList(*array))
    }

    /**
     * 根据每个几率返回随机的一个索引
     *
     * @return -1失败or随机的索引
     */
    fun randomIndexByProb(probs: List<Int>): Int {
        val newProbs = LinkedList<Int>()
        var lastTotalProb = 0
        for (prob in probs) {
            val cuttentTotalProb = lastTotalProb + prob
            newProbs.add(cuttentTotalProb)
            lastTotalProb = cuttentTotalProb
        }
        if (newProbs.isEmpty()) {
            return -1
        }
        val totalProb = newProbs.last
        // 总概率为0
        if (totalProb == 0) {
            return -1
        }
        val random = random(0, totalProb - 1)
        for (i in newProbs.indices) {
            val cuttentTotalProb = newProbs[i]
            if (cuttentTotalProb > random) {
                return i
            }
        }
        LOGGER.error("计算概率错误{}", probs.toString())
        return -1
    }

    /**
     * 根据每个几率返回随机的一个索引
     *
     * @return -1失败or随机的索引
     */
    fun randomIndexByProb(array: IntArray?): Int {
        if (array == null || array.size == 0) {
            throw IllegalArgumentException("元素数组不能为空！")
        }
        val list: MutableList<Int>
        list = ArrayList()
        for (i in array) {
            list.add(i)
        }
        return randomIndexByProb(list)
    }

    /**
     * 生成盐值
     * @return
     */
    fun createSalt(): String {
        return randomPwd(10)
    }


    /**
     * 生成盐值
     * @param count
     * @return
     */
    fun createSalt(count: Int): String {
        return randomPwd(count)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val salt = createSalt()
        println(salt)
        println(Md5Util.encode("xiaomo", salt))
    }
}
