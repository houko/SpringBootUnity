package info.xiaomo.website.controller;

import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.website.model.ChangeLogModel;
import info.xiaomo.website.service.ChangeLogService;
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

 * Date: 2016/4/1117:40
 * Description: 更新日志控制器
 * Copyright(©) 2015 by xiaomo.
 **/
@Controller
@RequestMapping("/changeLog")
public class ChangeLogController extends BaseController {

    private final ChangeLogService service;

    @Autowired
    public ChangeLogController(ChangeLogService service) {
        this.service = service;
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return model
     */
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(changeLogModel);
    }

    /**
     * findByName
     *
     * @return result
     */
    @RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
    public Result findByName(@PathVariable("name") String name) {
        ChangeLogModel model = service.findByName(name);
        if (model == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
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
     * 分页查询更新日志
     *
     * @return 分页
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result findAll() {
        List<ChangeLogModel> pages = service.findAll();
        if (pages == null || pages.size() <= 0) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(pages);
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
     * 增加更新日志
     *
     * @return result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel != null) {
            return new Result(CodeConst.REPEAT.getResultCode(), CodeConst.REPEAT.getMessage());
        }
        changeLogModel = new ChangeLogModel();
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel addModel = service.add(changeLogModel);
        return new Result<>(addModel);
    }


    /**
     * 修改更新日志
     *
     * @return result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel updateModel = service.update(changeLogModel);
        return new Result<>(updateModel);
    }


    /**
     * 删除更新日志
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Result deleteById(@PathVariable("id") Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        ChangeLogModel delModel = service.delete(id);
        return new Result<>(delModel);
    }


}
