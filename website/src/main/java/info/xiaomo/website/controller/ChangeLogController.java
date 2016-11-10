package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.website.model.ChangeLogModel;
import info.xiaomo.website.service.ChangeLogService;
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
 * @Date: 2016/4/1117:40
 * @Description: 更新日志控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/changeLog")
@Api("更新日志相关api")
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
    @ApiOperation(value = "通过id查找", notes = "通过id查找",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findById/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(changeLogModel);
    }

    /**
     * findByName
     *
     * @return result
     */
    @ApiOperation(value = "通过名字查找", notes = "通过名字查找",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
    public Result findByName( @PathVariable("name") String name) {
        ChangeLogModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }

    /**
     * 分页查询更新日志
     *
     * @return 分页
     */
    @ApiOperation(value = "分页查询更新日志", notes = "分页查询更新日志",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result findAll() {
        List<ChangeLogModel> pages = service.findAll();
        if (pages == null || pages.size() <= 0) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(pages);
    }

    /**
     * 增加更新日志
     *
     * @return result
     */
    @ApiOperation(value = "增加更新日志", notes = "增加更新日志",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel != null) {
            return new Result(Err.REPEAT.getCode(), Err.REPEAT.getMessage());
        }
        changeLogModel = new ChangeLogModel();
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel addModel = service.add(changeLogModel);
        return new Result(addModel);
    }


    /**
     * 修改更新日志
     *
     * @return result
     */
    @ApiOperation(value = "修改更新日志", notes = "修改更新日志",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel updateModel = service.update(changeLogModel);
        return new Result(updateModel);
    }


    /**
     * 删除更新日志
     */
    @ApiOperation(value = "删除更新日志", notes = "删除更新日志",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Result deleteById(@PathVariable("id") Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        ChangeLogModel delModel = service.delete(id);
        return new Result(delModel);
    }


}
