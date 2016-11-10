package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.website.model.SystemSetModel;
import info.xiaomo.website.service.WebSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * @Date: 2016/5/6 14:21
 * @Description: 系统设置控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/webSet")
@Api("网站设置相关api")
public class WebSetController extends BaseController {

    private final WebSetService service;

    @Autowired
    public WebSetController(WebSetService service) {
        this.service = service;
    }

    /**
     * 查找所有
     *
     * @return list
     */
    @ApiOperation(value = "查找所有", notes = "查找所有",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result findAll() {
        List<SystemSetModel> list = service.findAll();
        if (list.isEmpty() || list.size() == 0) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(list);

    }

    /**
     * 更新
     *
     * @return SystemSetModel
     */
    @ApiOperation(value = "更新", notes = "更新",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody SystemSetModel systemSetModel) {
        List<SystemSetModel> all = service.findAll();
        if (all.size() > 1) {
            return new Result(Err.ERROR.getCode(), Err.ERROR.getMessage());
        }
        for (SystemSetModel setModel : all) {
            setModel.setSiteName(systemSetModel.getSiteName());
            setModel.setIcon(systemSetModel.getIcon());
            setModel.setFromYear(systemSetModel.getFromYear());
            setModel.setToYear(systemSetModel.getToYear());
            setModel.setBeianUrl(systemSetModel.getBeianUrl());
            setModel.setBeianNumber(systemSetModel.getBeianNumber());
            SystemSetModel add = service.update(setModel);
            return new Result(add);
        }
        return new Result(Err.ERROR.getCode(), Err.ERROR.getMessage());
    }
}
