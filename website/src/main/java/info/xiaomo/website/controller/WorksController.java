package info.xiaomo.website.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.service.website.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @Date: 2016/11/3 14:36
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/


@RequestMapping("/works")
@RestController
public class WorksController extends BaseController {


    private WorksService service;

    @Autowired
    public WorksController(WorksService service) {
        this.service = service;
    }
}
