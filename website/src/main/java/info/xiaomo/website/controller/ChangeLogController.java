package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.core.model.website.ChangeLogModel;
import info.xiaomo.core.service.website.ChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * @Date: 2016/4/1117:40
 * @Description: 更新日志控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
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
    @RequestMapping("findById")
    public Result findById(@RequestParam Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(changeLogModel);
    }

    /**
     * findByName
     *
     * @return result
     */
    @RequestMapping(value = "findByName", method = RequestMethod.GET)
    public Result findByName(@RequestParam String name) {
        ChangeLogModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(model);
    }

    /**
     * 分页查询更新日志
     *
     * @param start    start
     * @param pageSize pageSize
     * @return 分页
     */
    @RequestMapping("findAll")
    public Result findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<ChangeLogModel> pages = service.findAll(start, pageSize);
        if (pages == null || pages.getSize() <= 0) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(pages);
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
            return new Result(Err.REPEAT.getErrorCode(), Err.REPEAT.getErrorMsg());
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
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel updateModel = service.update(changeLogModel);
        return new Result(updateModel);
    }


    /**
     * 删除更新日志
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public Result deleteById(@RequestParam Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        ChangeLogModel delModel = service.delete(id);
        return new Result(delModel);
    }


}
