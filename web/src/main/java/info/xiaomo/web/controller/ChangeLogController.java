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

import java.util.HashMap;

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

    @Autowired
    private ChangeLogService service;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     */
    @RequestMapping("findById")
    public HashMap<String, Object> findById(@RequestParam Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(changeLog, changeLogModel);
        return result;
    }

    /**
     * findByName
     *
     * @return result
     */
    @RequestMapping(value = "findByName", method = RequestMethod.GET)
    public HashMap<String, Object> findByName(@RequestParam String name) {
        ChangeLogModel model = service.findByName(name);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(changeLog, model);
        return result;
    }

    /**
     * 分页查询更新日志
     *
     * @param start
     * @param pageSize
     * @return
     */
    @RequestMapping("findAll")
    public HashMap<String, Object> findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<ChangeLogModel> all = service.findAll(start, pageSize);
        result.put(code, success);
        result.put(changeLogs, all);
        return result;
    }

    /**
     * 增加更新日志
     *
     * @param Name    Name
     * @param summary summary
     * @param content content
     * @param tagId   tagId
     * @return result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public HashMap<String, Object> add(
            @RequestParam String name
    ) {
        ChangeLogModel changeLogModel = service.findByName(name);
        if (changeLogModel != null) {
            result.put(code, repeat);
            return result;
        }
        changeLogModel = new ChangeLogModel();
        changeLogModel.setName(name);
        ChangeLogModel add = service.add(changeLogModel);
        result.put(code, success);
        result.put(changeLog, add);
        return result;
    }


    /**
     * 修改更新日志
     *
     * @param Name     Name
     * @param nickName nickName
     * @param summary  summary
     * @param content  content
     * @param tagId    tagId
     * @return result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, Object> update(
            @RequestParam String name
    ) {
        ChangeLogModel changeLogModel = service.findByName(name);
        if (changeLogModel == null) {
            result.put(code, notFound);
            return result;
        }
        changeLogModel.setName(name);
        ChangeLogModel update = service.update(changeLogModel);
        result.put(code, success);
        result.put(changeLog, update);
        return result;
    }


    /**
     * 删除更新日志
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public HashMap<String, Object> deleteById(@RequestParam Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(changeLog, changeLogModel);
        return result;
    }


}
