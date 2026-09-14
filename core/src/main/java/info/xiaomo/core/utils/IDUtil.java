package info.xiaomo.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author : xiaomo
 */
public class IDUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(IDUtil.class);
    /**
     * 锁
     */
    private static final Object ID_LOCK = new Object();
    /**
     * 当前秒数
     */
    private static long CURRENT_SECOND = System.currentTimeMillis() / 1000L;
    private static int ID = 0;

    public static void main(String[] args) {
        LOGGER.info(String.valueOf(Integer.MAX_VALUE / (365 * 24 * 60 * 60)));
        LOGGER.info(Integer.toBinaryString((int) (System.currentTimeMillis() / 1000)));
    }

    /**
     * 获取唯一一个id。
     *
     * <p>注意: 该实现只在单个 JVM 进程内(秒内 65000 个)保证单调不回退, 并非跨进程、
     * 跨重启全局唯一的 snowflake 方案, 不适用于分布式主键场景。
     *
     * @return long
     */
    public static long getId() {
        int tempId;
        long tempCurSec = System.currentTimeMillis() / 1000L;
        synchronized (ID_LOCK) {
            ID += 1;
            tempId = ID;
            int i = 65000;
            if (ID > i) {
                ID = 0;
                CURRENT_SECOND += 1L;
            }
            if (tempCurSec > CURRENT_SECOND) {
                CURRENT_SECOND = tempCurSec;
            } else {
                tempCurSec = CURRENT_SECOND;
            }
        }
        return ((tempCurSec) << 16 | tempId & 0xFFFF);
    }

}
