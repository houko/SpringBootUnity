package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.website.model.LinkModel;
import info.xiaomo.website.service.LinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @Date: 2016/4/1119:55
 * @Description: 友情连接控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/link")
@Api("友情链接相关api")
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
    @RequestMapping("findById/{id}")
    public Result findLinkById(@PathVariable("id") Long id) {
        LinkModel model = service.findById(id);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }

    /**
     * 根据名字查找
     *
     * @param name name
     * @return model
     */
    @RequestMapping("findByName/{name}")
    public Result findByName(@PathVariable("name") String name) {
        LinkModel model = service.findByName(name);
        if (model == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(model);
    }


    /**
     * 返回所有数据
     *
     * @return 所有
     */
    @RequestMapping("findAll")
    public Result findAll() {
        List<LinkModel> pages = service.findAll();
        if (pages == null || pages.size() == 0) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(pages);
    }


//    /**
//     * 返回所有 带分页
//     *
//     * @param start    start
//     * @param pageSize pageSize
//     * @return 分页数据
//     */
//    @RequestMapping("findAll")
//    public Result findAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        Page<LinkModel> pages = service.findAll(start, pageSize);
//        if (pages == null || pages.getSize() <= 0) {
//            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
//        }
//        return new Result(pages);
//    }


    /**
     * 添加链接
     *
     * @return model
     */
    @RequestMapping("add")
    public Result add(@RequestBody LinkModel model) {
        LinkModel linkModel = service.findByName(model.getName());
        if (linkModel != null) {
            return new Result(Err.REPEAT.getCode(), Err.REPEAT.getMessage());
        }
        linkModel = new LinkModel();
        linkModel.setName(model.getName());
        linkModel.setOrder(model.getOrder());
        linkModel.setUrl(model.getUrl());
        LinkModel addModel = service.add(linkModel);
        return new Result(addModel);
    }

    /**
     * 更新链接
     *
     * @return model
     */
    @RequestMapping("update")
    public Result update(@RequestBody LinkModel model) {
        LinkModel linkModel = service.findById(model.getId());
        if (linkModel == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        linkModel.setName(model.getName());
        linkModel.setUrl(model.getUrl());
        linkModel.setOrder(model.getOrder());
        LinkModel updateModel = service.update(linkModel);
        return new Result(updateModel);
    }

    /**
     * 删除链接
     *
     * @param id id
     * @return model
     */
    @RequestMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        LinkModel LinkModel = service.findById(id);
        if (LinkModel == null) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        LinkModel delModel = service.delete(id);
        return new Result(delModel);
    }
}
