package info.xiaomo.web.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.ChangeLogModel;
import info.xiaomo.core.service.ChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * @Description: 前台更新日志管理
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/web/changeLog")
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
     * @return result
     */
    @RequestMapping("findById")
    public ChangeLogModel findById(@RequestParam Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return null;
        }
        return changeLogModel;
    }

    /**
     * findByName
     *
     * @return result
     */
    @RequestMapping(value = "findByName", method = RequestMethod.GET)
    public ChangeLogModel findByName(@RequestParam String name) {
        ChangeLogModel model = service.findByName(name);
        if (model == null) {
            return null;
        }
        return model;
    }

    /**
     * 分页查询更新日志
     *
     * @param start    start
     * @param pageSize pageSize
     * @return result
     */
    @RequestMapping("findAll")
    public Page<ChangeLogModel> findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<ChangeLogModel> all = service.findAll(start, pageSize);
        return all;
    }

    /**
     * 增加更新日志
     *
     * @param name Name
     * @return result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ChangeLogModel add(
            @RequestParam String name
    ) {
        ChangeLogModel changeLogModel = service.findByName(name);
        if (changeLogModel != null) {
            return null;
        }
        changeLogModel = new ChangeLogModel();
        changeLogModel.setName(name);
        return service.add(changeLogModel);
    }


    /**
     * 修改更新日志
     *
     * @param name       content
     * @param onlineTime tagId
     * @return result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ChangeLogModel update(
            @RequestParam String name,
            @RequestParam String onlineTime
    ) {
        ChangeLogModel changeLogModel = service.findByName(name);
        if (changeLogModel == null) {
            return null;
        }
        changeLogModel.setName(name);
        changeLogModel.setOnlineTime(onlineTime);
        return service.update(changeLogModel);
    }


    /**
     * 删除更新日志
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public ChangeLogModel deleteById(@RequestParam Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return null;
        }
        return service.delete(id);
    }


}
