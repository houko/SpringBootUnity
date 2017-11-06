package info.xiaomo.core.untils

import java.io.*
import java.nio.ByteBuffer
import java.util.*

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
 * Date: 2016/11/22 14:55
 * Copyright(©) 2015 by xiaomo.
 */

class CastUtil {
    companion object {
        private val OUT = ByteArrayOutputStream()
        private var oos: ObjectOutputStream? = null

        fun toInteger(str: Any?): Int {
            return if (str == null) 0 else (str as? Number)?.toInt() ?: toInteger(str.toString())
        }

        fun toDouble(number: Any?): Double {
            return if (number == null) {
                0.0
            } else if (number is Number) {
                number.toDouble()
            } else if (number is String) {
                val str = number as String?
                if (isNumeric(str) > 0) java.lang.Double.valueOf(str!!) else 0.0
            } else {
                0.0
            }
        }

        fun toLong(number: Any?): Long {
            return if (number == null) {
                0L
            } else if (number is Number) {
                number.toLong()
            } else if (number is String) {
                val str = number as String?
                val isNumber = isNumeric(str)
                if (isNumber == 1) java.lang.Long.parseLong(str!!) else if (isNumber == 2) java.lang.Double.valueOf(str!!).toLong() else 0L
            } else {
                0L
            }
        }

        fun toInteger(str: String?): Int {
            var intStr = str
            return if (intStr == null) {
                0
            } else {
                intStr = intStr.trim { it <= ' ' }
                if (intStr.isEmpty()) {
                    0
                } else {
                    val i = isNumeric(intStr)
                    if (i == 1) Integer.parseInt(intStr) else if (i == 2) java.lang.Double.valueOf(intStr).toInt() else 0
                }
            }
        }

        fun isNumeric(str: String?): Int {
            if (str == null) {
                return 0
            } else {
                var isdouble = false
                var hasE = false
                var i = str.length

                while (true) {
                    while (true) {
                        var c: Char
                        do {
                            --i
                            if (i < 0) {
                                return if (isdouble) {
                                    2
                                } else 1

                            }

                            c = str[i]
                        } while (i == 0 && c.toInt() == 45)

                        if (c.toInt() == 46) {
                            if (isdouble) {
                                return 0
                            }

                            isdouble = true
                        } else if (c.toInt() != 69 && c.toInt() != 101) {
                            if (!Character.isDigit(str[i])) {
                                return 0
                            }
                        } else {
                            if (hasE) {
                                return 0
                            }

                            hasE = true
                        }
                    }
                }
            }
        }

        fun copyMap(map: HashMap<*, *>): HashMap<*, *> {
            val newmap = HashMap<Any, Any>(10)

            for (key in map.keys) {
                newmap.put(key, map[key]!!)
            }

            return newmap
        }

        fun destroy(map: Hashtable<*, *>) {
            val it = map.keys.iterator()
            while (it.hasNext()) {
                val key = it.next()
                val value = map[key]
                if (value is HashMap<*, *>) {
                    destroy(value)
                }
                it.remove()
            }

        }

        fun destroy(map: HashMap<*, *>) {
            val keyit = map.keys.iterator()
            while (keyit.hasNext()) {
                val key = keyit.next()
                val value = map[key]
                if (value is HashMap<*, *>) {
                    destroy(value)
                }
                keyit.remove()
            }

        }

        fun objectToString(obj: Any): String? {
            if (obj.javaClass == String::class) {
                return obj.toString()
            } else {
                val out = ByteArrayOutputStream()

                try {
                    val e = ObjectOutputStream(out)
                    e.writeObject(obj)
                    val bytes = out.toByteArray()
                    return String(bytes)
                } catch (var4: IOException) {
                    var4.printStackTrace()
                    return null
                }

            }
        }

        fun stringToObject(string: String): Any? {
            try {
                val e = string.toByteArray(charset("ISO-8859-1"))
                val `in` = ByteArrayInputStream(e)
                val ois = ObjectInputStream(`in`)
                return ois.readObject()
            } catch (var4: IOException) {
                var4.printStackTrace()
            } catch (var4: ClassNotFoundException) {
                var4.printStackTrace()
            }

            return null
        }

        fun bytesToObject(bytes: ByteArray): Any? {
            try {
                val e = ByteArrayInputStream(bytes)
                val ois = ObjectInputStream(e)
                return ois.readObject()
            } catch (var3: IOException) {
                var3.printStackTrace()
            } catch (var3: ClassNotFoundException) {
                var3.printStackTrace()
            }

            return null
        }

        @Throws(IOException::class)
        fun objectToBytes(obj: Any): ByteArray {
            OUT.reset()

            val var2: ByteArray
            try {
                if (oos == null) {
                    oos = ObjectOutputStream(OUT)
                } else {
                    oos!!.reset()
                }

                oos!!.writeObject(obj)
                var2 = OUT.toByteArray()
            } finally {
                OUT.close()
            }

            return var2
        }

        fun stringToBytes(str: String): ByteArray {
            val sb = StringBuffer(str)
            val buffer = ByteBuffer.allocate(sb.length * 2)
            var index = 0

            while (index < sb.length) {
                buffer.putChar(sb[index++])
            }

            return buffer.array()
        }

        fun bytesToString(bytes: ByteArray): String {
            val buffer = ByteBuffer.wrap(bytes)
            val sb = StringBuffer()

            while (buffer.hasRemaining()) {
                sb.append(buffer.char)
            }

            return sb.toString()
        }

        fun combineInt2Long(low: Int, high: Int): Long {
            return low.toLong() and 4294967295L or (high.toLong() shl 32 and -4294967296L)
        }

        fun getLongLowInt(`val`: Long?): Int {
            return if (`val` == null) 0 else (4294967295L and `val`).toInt()
        }

        fun getLongHighInt(`val`: Long?): Int {
            return if (`val` == null) 0 else (-4294967296L and `val` shr 32).toInt()
        }

        fun isIntInList(i: Int, list: IntArray): Boolean {
            for (aList in list) {
                if (aList == i) {
                    return true
                }
            }

            return false
        }

        fun stringToInts(str: String, regex: String): IntArray {
            val arr = str.split(regex.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val length = arr.size
            val ret = IntArray(length)

            for (i in 0 until length) {
                ret[i] = toInteger(arr[i])
            }

            return ret
        }

        fun cacheString(i: Int): String {
            return i.toString()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            println(toInteger("2.147483647E9"))
        }
    }

}
