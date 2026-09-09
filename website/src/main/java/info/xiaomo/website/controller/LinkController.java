package info.xiaomo.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.website.model.LinkModel;
import info.xiaomo.website.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1119:55
 * Description: 友情连接控制器
 * Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/link")
@Tag(name = "友情链接相关api", description = "友情链接相关api")
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
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @Operation(summary = "通过id查找", description = "通过id查找")
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH)
    })
    public Result findLinkById(@PathVariable("id") Long id) {
        LinkModel model = service.findById(id);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
    }

    /**
     * 根据名字查找
     *
     * @param name name
     * @return model
     */
    @Override
    @RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
    @Operation(summary = "根据名字查找", description = "根据名字查找")
    @Parameters({
            @Parameter(name = "name", description = "友情链接名字", required = true, in = ParameterIn.PATH)
    })
    public Result findByName(@PathVariable("name") String name) {
        LinkModel model = service.findByName(name);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
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
     * 返回所有数据
     *
     * @return 所有
     */
    @Override
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @Operation(summary = "返回所有数据", description = "返回所有数据")
    public Result findAll() {
        List<LinkModel> pages = service.findAll();
        if (pages == null || pages.size() == 0) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
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
     * 根据id查看模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加链接
     *
     * @return model
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @Operation(summary = "添加链接", description = "添加链接")
    public Result add(@RequestBody LinkModel model) {
        LinkModel linkModel = service.findByName(model.getName());
        if (linkModel != null) {
            return new Result<>(CodeConst.REPEAT.getResultCode(), CodeConst.REPEAT.getMessage());
        }
        linkModel = new LinkModel();
        linkModel.setName(model.getName());
        linkModel.setUrl(model.getUrl());
        LinkModel addModel = service.add(linkModel);
        return new Result<>(addModel);
    }

    /**
     * 更新链接
     *
     * @return model
     */
    @Operation(summary = "更新链接", description = "更新链接")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody LinkModel model) {
        LinkModel linkModel = service.findById(model.getId());
        if (linkModel == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        linkModel.setName(model.getName());
        linkModel.setUrl(model.getUrl());
        LinkModel updateModel = service.update(linkModel);
        return new Result<>(updateModel);
    }

    /**
     * 删除链接
     *
     * @param id id
     * @return model
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @Operation(summary = "删除链接", description = "删除链接")
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH)
    })
    public Result delete(@PathVariable("id") Long id) {
        LinkModel linkmodel = service.findById(id);
        if (linkmodel == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        LinkModel delModel = service.delete(id);
        return new Result<>(delModel);
    }
}
