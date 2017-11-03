package info.xiaomo.core.untils

import org.slf4j.LoggerFactory


/**
 * @author : xiaomo
 */
object IDUtil {
    private val LOGGER = LoggerFactory.getLogger(IDUtil::class.java)
    /**
     * 锁
     */
    private val ID_LOCK = Any()
    /**
     * 当前秒数
     */
    private var CURRENT_SECOND = System.currentTimeMillis() / 1000L
    private var ID = 0

    /**
     * 获取唯一一个id
     *
     * @return long
     */
    val id: Long
        get() {
            var tempId = 0
            var tempCurSec = System.currentTimeMillis() / 1000L
            synchronized(ID_LOCK) {
                ID += 1
                tempId = ID
                val i = 65000
                if (ID > i) {
                    ID = 0
                    CURRENT_SECOND += 1L
                }
                if (tempCurSec > CURRENT_SECOND) {
                    CURRENT_SECOND = tempCurSec
                } else {
                    tempCurSec = CURRENT_SECOND
                }
            }
            return tempCurSec shl 16 or ((tempId and 0xFFFF).toLong())
        }

    @JvmStatic
    fun main(args: Array<String>) {
        LOGGER.info((Integer.MAX_VALUE / (365 * 24 * 60 * 60)).toString())
        LOGGER.info(Integer.toBinaryString((System.currentTimeMillis() / 1000).toInt()))
    }

}
