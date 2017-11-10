package info.xiaomo.website.controller;

import info.xiaomo.website.util.MailUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/11/14 11:06
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 **/

@RestController
@RequestMapping("/mail")
public class MailController {

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public boolean sendMail() {
        return MailUtil.send("hupengbest@163.com", "测试邮件是否正常", "测试");
    }

}
