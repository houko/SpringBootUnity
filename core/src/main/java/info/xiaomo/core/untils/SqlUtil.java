package info.xiaomo.core.untils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 * <p>
 *
 * @author : xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiamoo.info
 * QQ_NO: 83387856
 * Date: 17/5/11 17:56
 * Description:
 * Copyright(©) 2017 by xiaomo.
 */
public class SqlUtil {


    /**
     * 功能描述: 生成sql占位符 ?,?,?
     *
     * @return String    返回类型
     */
    public static String sqlHolder(int size) {
        String[] paras = new String[size];
        Arrays.fill(paras, "?");
        return StringUtils.join(paras, ',');
    }

    /**
     * sql语句 处理
     *
     * @param sql    要进行处理的sql语句
     * @param dbtype 数据库类型
     * @return 处理后的sql语句
     */
    public static String sql4DB(String sql, String dbtype) {
        String oracle = "oracle";
        if (!oracle.equalsIgnoreCase(dbtype)) {
            sql = StringUtil.toISO(sql);
        }
        return sql;
    }

    private String delNewSQlString(String sql) {
        return "in (" + sql.replace('|', ',') + ")";
    }

    private String delSQlString(String sql) {
        StringBuilder delSql = new StringBuilder("in(");
        StringTokenizer tokenizer = new StringTokenizer(sql, "|");

        // 标记本身等于分隔符的特殊情况
        delSql.append(tokenizer.nextToken());
        while (tokenizer.hasMoreTokens()) {
            delSql.append(tokenizer.nextToken()).append(",");
        }
        delSql = new StringBuilder(delSql.substring(0, delSql.length() - 1) + ")");
        return delSql.toString();
    }
}
