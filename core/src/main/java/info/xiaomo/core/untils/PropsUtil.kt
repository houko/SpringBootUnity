package info.xiaomo.core.untils

import java.io.FileInputStream
import java.util.*

/**
 * @author : xiaomo
 */
object PropsUtil {

    fun getProperties(url: String): Properties? {
        var properties: Properties? = null
        try {
            val fs = FileInputStream(url)
            properties = Properties()
            properties.load(fs)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return properties
    }

}
/**
 * 构造函数
 * 找到数据源，并用这个数据源创建连接
 */
