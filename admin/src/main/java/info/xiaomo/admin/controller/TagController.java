package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.TagModel;
import info.xiaomo.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * @Date: 2016/4/1119:45
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/admin/tag")
public class TagController extends BaseController {

    @Autowired
    private TagService service;

    @RequestMapping("findById")
    public HashMap<String, Object> findTagById(@RequestParam Long id) {
        TagModel model = service.findById(id);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(tag, model);
        return result;
    }

    @RequestMapping("findByName")
    public HashMap<String, Object> findByName(@RequestParam String name) {
        TagModel model = service.findByName(name);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(tag, model);
        return result;
    }


    @RequestMapping("findAll")
    public HashMap<String, Object> findAll(@RequestParam(value = "start",defaultValue = "1") int start, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        Page<TagModel> models = service.findAll(new PageRequest(start - 1, pageSize));
        result.put(code, success);
        result.put(tags, models);
        return result;
    }


    @RequestMapping("add")
    public HashMap<String, Object> add(@RequestParam String name) {
        TagModel tagModel = service.findByName(name);
        if (tagModel != null) {
            result.put(code, repeat);
            return result;
        }
        tagModel = new TagModel();
        tagModel.setName(name);
        TagModel add = service.add(tagModel);
        result.put(code, success);
        result.put(tag, add);
        return result;
    }

    @RequestMapping("update")
    public HashMap<String, Object> update(@RequestParam String name) {
        TagModel tagModel = service.findByName(name);
        if (tagModel == null) {
            result.put(code, notFound);
            return result;
        }
        tagModel.setName(name);
        TagModel update = service.update(tagModel);
        result.put(code, success);
        result.put(tag, update);
        return result;
    }

    @RequestMapping("delete")
    public HashMap<String, Object> delete(@RequestParam Long id) {
        TagModel tagModel = service.findById(id);
        if (tagModel == null) {
            result.put(code, notFound);
            return result;
        }
        TagModel delete = service.delete(id);
        result.put(code, success);
        result.put(tag, delete);
        return result;
    }
}
