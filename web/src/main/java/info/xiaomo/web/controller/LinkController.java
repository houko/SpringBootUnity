package info.xiaomo.web.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.website.LinkModel;
import info.xiaomo.core.service.website.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * @Date: 2016/4/1119:55
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/web/link")
public class LinkController extends BaseController {

    private final LinkService service;

    @Autowired
    public LinkController(LinkService service) {
        this.service = service;
    }

    @RequestMapping("findById")
    public LinkModel findLinkById(@RequestParam Long id) {
        LinkModel model = service.findById(id);
        if (model == null) {
            return null;
        }
        return model;
    }

    @RequestMapping("findByName")
    public LinkModel findByName(@RequestParam String name) {
        LinkModel model = service.findByName(name);
        if (model == null) {
            return null;
        }
        return model;
    }


    @RequestMapping("findAll")
    public Page<LinkModel> findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return service.findAll(start, pageSize);
    }

    @RequestMapping("add")
    public LinkModel add(
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam int level) {
        LinkModel linkModel = service.findByName(name);
        if (linkModel != null) {
            return null;
        }
        linkModel = new LinkModel();
        linkModel.setName(name);
        linkModel.setLevel(level);
        linkModel.setUrl(url);
        return service.add(linkModel);
    }

    @RequestMapping("update")
    public LinkModel update(
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam int level

    ) {
        LinkModel linkModel = service.findByName(name);
        if (linkModel == null) {
            return null;
        }
        linkModel.setName(name);
        linkModel.setUrl(url);
        linkModel.setLevel(level);
        return service.update(linkModel);
    }

    @RequestMapping("delete")
    public LinkModel delete(@RequestParam Long id) {
        LinkModel LinkModel = service.findById(id);
        if (LinkModel == null) {
            return null;
        }
        return service.delete(id);
    }
}
