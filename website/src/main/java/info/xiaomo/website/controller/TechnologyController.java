package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.core.model.website.TechnologyModel;
import info.xiaomo.core.service.website.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


@RequestMapping("/technology")
@RestController
public class TechnologyController extends BaseController {

    private TechnologyService service;

    @Autowired
    public TechnologyController(TechnologyService service) {
        this.service = service;
    }


    @RequestMapping(name = "/findById/{id}")
    public Result findById(@PathVariable Long id) {
        TechnologyModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }

    @RequestMapping(name = "/findByName/{name}")
    public Result findByName(@PathVariable String name) {
        TechnologyModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }


    @RequestMapping(name = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody TechnologyModel model) {
        TechnologyModel addModel = service.findByName(model.getName());
        if (addModel != null) {
            return new Result(Err.REPEAT.getCode(), Err.REPEAT.getMessage());
        }
        addModel = service.add(model);
        return new Result(addModel);
    }

    @RequestMapping(name = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody TechnologyModel model) {
        TechnologyModel update = service.findById(model.getId());
        if (update == null) {
            return new Result(Err.ERROR.getCode(), Err.ERROR.getMessage());
        }
        update = service.update(model);
        return new Result(update);
    }


    @RequestMapping(name = "/delete/{id}")
    public Result delete(@PathVariable Long id) {
        TechnologyModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        service.del(id);
        return new Result(model);
    }

}
