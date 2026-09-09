package info.xiaomo.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.website.model.TechnologyModel;
import info.xiaomo.website.service.TechnologyService;
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
 * Date: 2016/11/3 14:36
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 **/


@RestController
@RequestMapping("/technology")
@Tag(name = "技术中心相关api", description = "技术中心相关api")
public class TechnologyController extends BaseController {
    private final TechnologyService service;

    @Autowired
    public TechnologyController(TechnologyService service) {
        this.service = service;
    }


    @Override
    @Operation(summary = "根据id查找技术", description = "根据id查找技术")
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @Parameters({
            @Parameter(name = "id", description = "唯一Id", required = true, in = ParameterIn.PATH)
    })
    public Result<TechnologyModel> findById(@PathVariable Long id) {
        TechnologyModel model = service.findById(id);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
    }

    @Override
    @Operation(summary = "根据名字查找技术", description = "根据名字查找技术")
    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @Parameters({
            @Parameter(name = "name", description = "技术名字", required = true, in = ParameterIn.PATH)
    })
    public Result findByName(@PathVariable String name) {
        TechnologyModel model = service.findByName(name);
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

    @Override
    @Operation(summary = "查找所有", description = "查找所有")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        List<TechnologyModel> all = service.findAll();
        if (all == null || all.isEmpty()) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(all);
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


    @Operation(summary = "添加链接", description = "添加链接")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody TechnologyModel model) {
        TechnologyModel addModel = service.findByName(model.getName());
        if (addModel != null) {
            return new Result<>(CodeConst.REPEAT.getResultCode(), CodeConst.REPEAT.getMessage());
        }
        addModel = service.add(model);
        return new Result<>(addModel);
    }

    @Operation(summary = "更新链接", description = "更新链接")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody TechnologyModel model) {
        TechnologyModel update = service.findById(model.getId());
        if (update == null) {
            return new Result<>(CodeConst.CodeOR.getResultCode(), CodeConst.CodeOR.getMessage());
        }
        update = service.update(model);
        return new Result<>(update);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Operation(summary = "删除链接", description = "删除链接")
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH)
    })
    public Result delete(@PathVariable Long id) {
        TechnologyModel model = service.findById(id);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        service.del(id);
        return new Result<>(model);
    }

}
