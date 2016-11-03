package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.website.model.WorksModel;
import info.xiaomo.website.service.WorksService;
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


@RequestMapping("/works")
@RestController
public class WorksController extends BaseController {

    private final WorksService service;

    @Autowired
    public WorksController(WorksService service) {
        this.service = service;
    }


    @RequestMapping("/findById/{id}")
    public Result findById(@PathVariable Long id) {
        WorksModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }


    @RequestMapping("/findById/{name}")
    public Result findByName(@PathVariable String name) {
        WorksModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody WorksModel model) {
        WorksModel addModel = service.findByName(model.getName());
        if (addModel != null) {
            return new Result(Err.REPEAT.getCode(), Err.REPEAT.getMessage());
        }
        addModel = service.add(model);
        return new Result(addModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody WorksModel model) {
        WorksModel worksModel = service.findById(model.getId());
        if (worksModel == null) {
            return new Result(Err.ERROR.getCode(), Err.ERROR.getMessage());
        }
        worksModel = service.update(worksModel);
        return new Result(worksModel);
    }


    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        WorksModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        service.del(id);
        return new Result(model);
    }

}
