package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
 * @Date: 2016/4/1119:45
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/admin/tag")
public class TagController extends BaseController {

    @RequestMapping("findById")
    public HashMap<String,Object> findTagById(@RequestParam Long id){

        return result;
    }
}
