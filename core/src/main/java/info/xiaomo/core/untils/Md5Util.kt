package info.xiaomo.core.untils

import java.security.MessageDigest

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 *
 * Date: 16/4/3 10:03
 * Description: md5加密解密
 * Copyright(©) 2015 by xiaomo.
 */
object Md5Util {

    private val HEX_DIGITS = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    fun byteArrayToString(b: ByteArray): String {
        val resultSb = StringBuilder()
        for (aB in b) {
            //若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
            resultSb.append(byteToHexString(aB))
        }
        return resultSb.toString()
    }

    private fun byteToNumString(b: Byte): String {

        var tempB = b.toInt()
        if (tempB < 0) {
            tempB += 256
        }

        return tempB.toString()
    }

    private fun byteToHexString(b: Byte): String {
        var n = b.toInt()
        if (n < 0) {
            n = 256 + n
        }
        val d1 = n / 16
        val d2 = n % 16
        return HEX_DIGITS[d1] + HEX_DIGITS[d2]
    }

    fun encode(password: String, salt: String): String? {
        var resultString: String? = null
        try {
            resultString = password + salt
            val md = MessageDigest.getInstance("md5")
            resultString = byteArrayToString(md.digest(resultString.toByteArray()))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return resultString
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val password = "xiaomo"
        val salt = RandomUtil.createSalt()
        println("原数据：" + password)
        println("盐值：" + salt)
        println("MD5后：" + encode(password, salt))
    }

}
