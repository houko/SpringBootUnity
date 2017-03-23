package info.xiaomo.website.controller;

import info.xiaomo.website.model.SystemSetModel;
import info.xiaomo.website.service.WebSetService;
import info.xiaomo.website.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * author: xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiaomo.info

 * Date: 2016/11/21 10:53
 * Copyright(©) 2015 by xiaomo.
 **/

@Controller
public class IndexController {

    private final WebSetService setService;

    @Autowired
    public IndexController(WebSetService setService) {
        this.setService = setService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session) {
        List<SystemSetModel> all = setService.findAll();
        if (all.size() > 0) {
            SystemSetModel model = all.get(0);
            session.setAttribute("webSet", model);
        }
        return UserView.INDEX.getName();
    }
}
