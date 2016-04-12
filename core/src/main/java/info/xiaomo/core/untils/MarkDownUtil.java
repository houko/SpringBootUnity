package info.xiaomo.core.untils;

import org.markdownj.MarkdownProcessor;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/4/1216:17
 * @Description: markdown
 * @Copyright(©) 2015 by xiaomo.
 **/
public class MarkDownUtil {

    /**
     * markdown转换
     * @param testText
     * @return
     */
    public static String markdown(String testText) {
        MarkdownProcessor markup = new MarkdownProcessor();
        return markup.markdown(testText);
    }
}
