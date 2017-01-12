package info.xiaomo.api.controller;

import info.xiaomo.api.model.SystemSetModel;
import info.xiaomo.api.service.WebSetService;
import info.xiaomo.core.constant.Code;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * @Date: 2016/5/6 14:21
 * @Description: 系统设置控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/webSet")
@Api(value = "WebSetController",description = "网站设置相关api")
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
            return new Result(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        return new Result(list);

    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    /**
     * 根据id查看模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 根据名字查找模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result findByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
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
            return new Result(Code.CodeOR.getResultCode(), Code.CodeOR.getMessage());
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
        return new Result(Code.CodeOR.getResultCode(), Code.CodeOR.getMessage());
    }
}
