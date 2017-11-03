package info.xiaomo.core.untils

import javax.servlet.http.HttpSession

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * @email: xiaomo@xiaomo.info
 *
 * Date: 2016/11/31 9:50
 * Copyright(©) 2015 by xiaomo.
 */

object AuthUtil {
    fun isAuthorized(session: HttpSession): Boolean {
        val currentUser = session.getAttribute("currentUser") as String
        return !(currentUser.isEmpty())
    }

}
