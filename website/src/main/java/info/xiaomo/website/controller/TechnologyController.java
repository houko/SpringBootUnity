package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.website.model.TechnologyModel;
import info.xiaomo.website.service.TechnologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


@RestController
@RequestMapping("/technology")
@Api(value = "TechnologyController",description = "技术中心相关api")
public class TechnologyController extends BaseController {
    private final TechnologyService service;

    @Autowired
    public TechnologyController(TechnologyService service) {
        this.service = service;
    }


    @ApiOperation(value = "根据id查找技术", notes = "根据id查找技术",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable Long id) {
        TechnologyModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }

    @ApiOperation(value = "根据名字查找技术", notes = "根据名字查找技术",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/findByName/{name}",method = RequestMethod.GET)
    public Result findByName(@PathVariable String name) {
        TechnologyModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }

    @ApiOperation(value = "查找所有", notes = "查找所有",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll() {
        List<TechnologyModel> all = service.findAll();
        if (all == null || all.isEmpty()) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(all);
    }


    @ApiOperation(value = "添加链接", notes = "添加链接",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody TechnologyModel model) {
        TechnologyModel addModel = service.findByName(model.getName());
        if (addModel != null) {
            return new Result(Err.REPEAT.getCode(), Err.REPEAT.getMessage());
        }
        addModel = service.add(model);
        return new Result(addModel);
    }

    @ApiOperation(value = "更新链接", notes = "更新链接",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody TechnologyModel model) {
        TechnologyModel update = service.findById(model.getId());
        if (update == null) {
            return new Result(Err.ERROR.getCode(), Err.ERROR.getMessage());
        }
        update = service.update(model);
        return new Result(update);
    }


    @ApiOperation(value = "删除链接", notes = "删除链接",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public Result delete(@PathVariable Long id) {
        TechnologyModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        service.del(id);
        return new Result(model);
    }

}
