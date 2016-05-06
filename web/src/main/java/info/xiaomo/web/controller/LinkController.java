package info.xiaomo.web.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.LinkModel;
import info.xiaomo.core.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * @Date: 2016/4/1119:55
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/web/link")
public class LinkController extends BaseController {

    @Autowired
    private LinkService service;

    @RequestMapping("findById")
    public HashMap<String, Object> findLinkById(@RequestParam Long id) {
        result = new HashMap<>();
        LinkModel model = service.findById(id);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(link, model);
        return result;
    }

    @RequestMapping("findByName")
    public HashMap<String, Object> findByName(@RequestParam String name) {
        result = new HashMap<>();
        LinkModel model = service.findByName(name);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(link, model);
        return result;
    }


    @RequestMapping("findAll")
    public HashMap<String, Object> findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        result = new HashMap<>();
        Page<LinkModel> models = service.findAll(start, pageSize);
        result.put(code, success);
        result.put(links, models);
        return result;
    }


    @RequestMapping("add")
    public HashMap<String, Object> add(
            @RequestParam String name,
            @RequestParam String url) {
        result = new HashMap<>();
        LinkModel linkModel = service.findByName(name);
        if (linkModel != null) {
            result.put(code, repeat);
            return result;
        }
        linkModel = new LinkModel();
        linkModel.setName(name);
        linkModel.setUrl(url);
        LinkModel add = service.add(linkModel);
        result.put(code, success);
        result.put(link, add);
        return result;
    }

    @RequestMapping("update")
    public HashMap<String, Object> update(
            @RequestParam String name,
            @RequestParam String url

    ) {
        result = new HashMap<>();
        LinkModel linkModel = service.findByName(name);
        if (linkModel == null) {
            result.put(code, notFound);
            return result;
        }
        linkModel.setName(name);
        linkModel.setUrl(url);
        LinkModel update = service.update(linkModel);
        result.put(code, success);
        result.put(link, update);
        return result;
    }

    @RequestMapping("delete")
    public HashMap<String, Object> delete(@RequestParam Long id) {
        result = new HashMap<>();
        LinkModel LinkModel = service.findById(id);
        if (LinkModel == null) {
            result.put(code, notFound);
            return result;
        }
        LinkModel delete = service.delete(id);
        result.put(code, success);
        result.put(link, delete);
        return result;
    }
}
