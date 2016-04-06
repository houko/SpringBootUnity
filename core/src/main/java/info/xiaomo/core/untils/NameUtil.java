package info.xiaomo.core.untils;

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
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 15/9/7 13:04
 * @Description: 名字
 * @Copyright(©) 2015 by xiaomo.
 **/
public class NameUtil {
    static public String buildName(Class clazz, String suffix) {
        String className = clazz.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(className.substring(0, 1).toLowerCase())
                .append(className.substring(1));
        if (suffix != null)
            sb.append(suffix);
        return sb.toString();
    }
}
