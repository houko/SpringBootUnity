package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
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
 * @Description: 友情连接控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/link")
public class LinkController extends BaseController {

    private final LinkService service;

    @Autowired
    public LinkController(LinkService service) {
        this.service = service;
    }

    /**
     * 根据id查找
     *
     * @param id id
     * @return model
     */
    @RequestMapping("findById")
    public Result findLinkById(@RequestParam Long id) {
        LinkModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(model);
    }

    /**
     * 根据名字查找
     *
     * @param name name
     * @return model
     */
    @RequestMapping("findByName")
    public Result findByName(@RequestParam String name) {
        LinkModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(model);
    }


    /**
     * 返回所有 带分页
     *
     * @param start    start
     * @param pageSize pageSize
     * @return 分页数据
     */
    @RequestMapping("findAll")
    public Result findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<LinkModel> pages = service.findAll(start, pageSize);
        if (pages == null || pages.getSize() <= 0) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(pages);
    }


    /**
     * 添加链接
     *
     * @param name  name
     * @param url   url
     * @param order order
     * @return model
     */
    @RequestMapping("add")
    public LinkModel add(
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam int order) {
        LinkModel linkModel = service.findByName(name);
        if (linkModel != null) {
            return null;
        }
        linkModel = new LinkModel();
        linkModel.setName(name);
        linkModel.setOrder(order);
        linkModel.setUrl(url);
        return service.add(linkModel);
    }

    /**
     * 更新链接
     *
     * @param name  name
     * @param url   url
     * @param order order
     * @return model
     */
    @RequestMapping("update")
    public LinkModel update(
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam int order

    ) {
        LinkModel linkModel = service.findByName(name);
        if (linkModel == null) {
            return null;
        }
        linkModel.setName(name);
        linkModel.setUrl(url);
        linkModel.setOrder(order);
        return service.update(linkModel);
    }

    /**
     * 删除链接
     *
     * @param id id
     * @return model
     */
    @RequestMapping("delete")
    public Result delete(@RequestParam Long id) {
        LinkModel LinkModel = service.findById(id);
        if (LinkModel == null) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        LinkModel delModel = service.delete(id);
        return new Result(delModel);
    }
}
