package info.xiaomo.core.untils

import java.io.*

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
 * Date: 15/9/6 16:05
 * Description: 序列化通用方法
 * Copyright(©) 2015 by xiaomo.
 */
object SerializeUtil {

    /**
     * 序列化
     */
    fun serialize(`object`: Any?): String? {
        if (`object` == null) {
            return null
        }
        val oos: ObjectOutputStream
        val baos: ByteArrayOutputStream
        try {
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(`object`)
            val bytes = baos.toByteArray()
            return String(bytes)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 反序列化
     */
    @Throws(UnsupportedEncodingException::class)
    fun unserialize(s: String): Any? {
        val bytes = s.toByteArray(charset("utf-8"))
        val bais: ByteArrayInputStream
        try {
            //反序列化
            bais = ByteArrayInputStream(bytes)
            val ois = ObjectInputStream(bais)
            return ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}