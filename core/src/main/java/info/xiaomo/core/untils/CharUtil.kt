package info.xiaomo.core.untils

import java.io.UnsupportedEncodingException

/**
 *
 * Title:字符编码工具类
 *
 * @author : xiaomo
 * @version 1.0
 */
object CharUtil {

    /**
     * 转换编码 ISO-8859-1到GB2312
     */
    fun iso2gb(text: String): String {
        var result: String
        try {
            result = String(text.toByteArray(charset("ISO-8859-1")))
        } catch (ex: UnsupportedEncodingException) {
            result = ex.toString()
        }

        return result
    }

    /**
     * 转换编码 GB2312到ISO-8859-1
     */
    fun gb2iso(text: String): String {
        var result = ""
        try {
            result = String(text.toByteArray(charset("GB2312")))
        } catch (ex: UnsupportedEncodingException) {
            ex.printStackTrace()
        }

        return result
    }

    /**
     * Utf8URL编码
     */
    fun utf8urlencode(text: String): String {
        val result = StringBuilder()
        for (i in 0 until text.length) {

            val c = text[i]
            if (c.toInt() <= 255) {
                result.append(c)
            } else {

                var b = ByteArray(0)
                try {
                    b = Character.toString(c).toByteArray(charset("UTF-8"))
                } catch (ignored: Exception) {
                }

                for (aB in b) {
                    var k = aB.toInt()
                    if (k < 0) {
                        k += 256
                    }
                    result.append("%").append(Integer.toHexString(k).toUpperCase())
                }

            }
        }
        return result.toString()
    }

    /**
     * Utf8URL解码
     */
    fun utf8urldecode(text: String?): String {
        var text = text
        var result = ""
        var p: Int
        if (text != null && text.length > 0) {
            text = text.toLowerCase()
            p = text.indexOf("%e")
            if (p == -1) {
                return text
            }
            while (p != -1) {
                result += text!!.substring(0, p)
                text = text.substring(p, text.length)
                if (text == "" || text.length < 9) {
                    return result
                }
                result += codetoword(text.substring(0, 9))
                text = text.substring(9, text.length)
                p = text.indexOf("%e")
            }
        }
        return result + text!!
    }

    /**
     * utf8URL编码转字符
     */
    private fun codetoword(text: String): String? {
        var result: String?
        if (utf8codecheck(text)) {
            val code = ByteArray(3)
            code[0] = (Integer.parseInt(text.substring(1, 3), 16) - 256).toByte()
            code[1] = (Integer.parseInt(text.substring(4, 6), 16) - 256).toByte()
            code[2] = (Integer.parseInt(text.substring(7, 9), 16) - 256).toByte()
            try {
                result = String(code)
            } catch (ex: UnsupportedEncodingException) {
                result = null
            }

        } else {
            result = text
        }
        return result
    }

    /**
     * 编码是否有效
     */
    private fun utf8codecheck(text: String): Boolean {
        var sign = ""
        val prefix = "%e"
        if (text.startsWith(prefix)) {
            var p = 0
            while (p != -1) {
                p = text.indexOf("%", p)
                if (p != -1) {
                    p++
                }
                sign += p
            }
        }
        return "147-1" == sign
    }

    /**
     * 判断是否Utf8Url编码
     */
    fun isUtf8Url(text: String): Boolean {
        var text = text
        text = text.toLowerCase()
        val p = text.indexOf("%")
        val nine = 9
        if (p != -1 && text.length - p > nine) {
            text = text.substring(p, p + nine)
        }
        return utf8codecheck(text)
    }

    /**
     * 进行字符规格化（全角转半角，大写转小写处理）
     *
     * @return char
     */
    fun regularize(input: Char): Char {
        var input = input
        if (input.toInt() == 12288) {
            input = 32.toChar()
        } else if (input.toInt() > 65280 && input.toInt() < 65375) {
            input = (input.toInt() - 65248).toChar()
        } else {
            val a = 'A'
            val z = 'Z'
            if (input >= a && input <= z) {
                input += 32.toChar().toInt()
            }
        }
        return input
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var url = "http://www.google.com/search?hl=zh-CN&newwindow=1&q=%E4%B8%AD%E5%9B%BD%E5%A4%A7%E7%99%BE%E7%A7%91%E5%9C%A8%E7%BA%BF%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2&btnG=%E6%90%9C%E7%B4%A2&lr="
        println(utf8urlencode("小莫"))
        println(iso2gb("小莫"))
        println(gb2iso("小莫"))
        if (CharUtil.isUtf8Url(url)) {
            println(CharUtil.utf8urldecode(url))
        }
        url = "http://www.baidu.com/baidu?word=%D6%D0%B9%FA%B4%F3%B0%D9%BF%C6%D4%DA%CF%DF%C8%AB%CE%C4%BC%EC%CB%F7&tn=myie2dg"
        if (CharUtil.isUtf8Url(url)) {
            println(CharUtil.utf8urldecode(url))
        }
    }

}
