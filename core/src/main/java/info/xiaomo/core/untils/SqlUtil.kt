package info.xiaomo.core.untils

import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 *
 * @author : xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiamoo.info
 * QQ_NO: 83387856
 * Date: 17/5/11 17:56
 * Description:
 * Copyright(©) 2017 by xiaomo.
 */
class SqlUtil {

    private fun delNewSQlString(sql: String): String {
        return "in (" + sql.replace('|', ',') + ")"
    }

    private fun delSQlString(sql: String): String {
        var delSql = StringBuilder("in(")
        val tokenizer = StringTokenizer(sql, "|")

        // 标记本身等于分隔符的特殊情况
        delSql.append(tokenizer.nextToken())
        while (tokenizer.hasMoreTokens()) {
            delSql.append(tokenizer.nextToken()).append(",")
        }
        delSql = StringBuilder(delSql.substring(0, delSql.length - 1) + ")")
        return delSql.toString()
    }

    companion object {


        /**
         * 功能描述: 生成sql占位符 ?,?,?
         *
         * @return String    返回类型
         */
        fun sqlHolder(size: Int): String {
            val paras = arrayOfNulls<String>(size)
            Arrays.fill(paras, "?")
            return StringUtils.join(paras, ',')
        }

        /**
         * sql语句 处理
         *
         * @param sql    要进行处理的sql语句
         * @param dbtype 数据库类型
         * @return 处理后的sql语句
         */
        fun sql4DB(sql: String, dbtype: String): String {
            var sqlStr = sql
            val oracle = "oracle"
            if (!oracle.equals(dbtype, ignoreCase = true)) {
                sqlStr = StringUtil.toISO(sqlStr)
            }
            return sqlStr
        }
    }
}
