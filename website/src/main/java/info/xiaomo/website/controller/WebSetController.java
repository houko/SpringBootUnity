package info.xiaomo.website.controller;

import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.website.model.SystemSetModel;
import info.xiaomo.website.service.WebSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 2016/5/6 14:21
 * Description: 系统设置控制器
 * Copyright(©) 2015 by xiaomo.
 **/
@Controller
@RequestMapping("/webSet")
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
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result findAll() {
        List<SystemSetModel> list = service.findAll();
        if (list.isEmpty() || list.size() == 0) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(list);

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
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody SystemSetModel systemSetModel) {
        List<SystemSetModel> all = service.findAll();
        if (all.size() > 1) {
            return new Result(CodeConst.CodeOR.getResultCode(), CodeConst.CodeOR.getMessage());
        }
        for (SystemSetModel setModel : all) {
            setModel.setSiteName(systemSetModel.getSiteName());
            setModel.setIcon(systemSetModel.getIcon());
            setModel.setFromYear(systemSetModel.getFromYear());
            setModel.setToYear(systemSetModel.getToYear());
            setModel.setBeianUrl(systemSetModel.getBeianUrl());
            setModel.setBeianNumber(systemSetModel.getBeianNumber());
            SystemSetModel add = service.update(setModel);
            return new Result<>(add);
        }
        return new Result(CodeConst.CodeOR.getResultCode(), CodeConst.CodeOR.getMessage());
    }
}
