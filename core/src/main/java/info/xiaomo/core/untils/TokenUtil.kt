package info.xiaomo.core.untils

import com.alibaba.fastjson.JSONObject
import info.xiaomo.core.constant.SymbolConst
import java.util.regex.Pattern

/**
 * Token 帮助类
 * @author : xiaomo
 */
object TokenUtil {

    private val STR_S = "abcdefghijklmnopqrstuvwxyz0123456789"

    /**
     * 参考自 qq sdk
     *
     * @param string string
     * @return String    返回类型
     */
    fun getAccessToken(string: String): String {
        var accessToken = ""
        try {
            val json = JSONObject.parseObject(string)
            if (null != json) {
                accessToken = json.getString("access_token")
            }
        } catch (e: Exception) {
            val regex = "^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$"
            val m = Pattern.compile(regex).matcher(string)
            if (m.find()) {
                accessToken = m.group(1)
            } else {
                val regex1 = "^access_token=(\\w+)&expires_in=(\\w+)$"
                val m2 = Pattern.compile(regex1).matcher(string)
                if (m2.find()) {
                    accessToken = m2.group(1)
                } else {
                    val temp = string.split(SymbolConst.DENGHAO.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                    accessToken = temp.split(SymbolConst.AND.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                }
            }
        }

        return accessToken
    }

    /**
     * 匹配openid
     *
     * @return String    返回类型
     */
    fun getOpenId(string: String): String? {
        var openid: String? = null
        val regex = "\"openid\"\\s*:\\s*\"(\\w+)\""
        val m = Pattern.compile(regex).matcher(string)
        if (m.find()) {
            openid = m.group(1)
        }
        return openid
    }

    /**
     * sina uid于qq分离
     *
     * @return String    返回类型
     */
    fun getUid(string: String): String {
        val json = JSONObject.parseObject(string)
        return json.getString("uid")
    }
}
