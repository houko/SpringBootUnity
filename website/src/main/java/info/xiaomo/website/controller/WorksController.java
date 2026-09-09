package info.xiaomo.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.website.model.WorksModel;
import info.xiaomo.website.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
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


@RequestMapping("/works")
@RestController
@Tag(name = "作品相关api")
public class WorksController {

    private final WorksService service;

    @Autowired
    public WorksController(WorksService service) {
        this.service = service;
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @Operation(summary = "根据id查找作品", description = "根据id查找作品")
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH),
    })
    public Result<WorksModel> findById(@PathVariable Long id) {
        WorksModel model = service.findById(id);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @Operation(summary = "查找所有", description = "查找所有")
    public Result<List<WorksModel>> findAll() {
        List<WorksModel> all = service.findAll();
        if (all == null || all.isEmpty()) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(all);
    }


    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @Operation(summary = "根据名字查找作品", description = "根据名字查找作品")
    @Parameters({
            @Parameter(name = "name", description = "作品名字", required = true, in = ParameterIn.PATH),
    })
    public Result<WorksModel> findByName(@PathVariable String name) {
        WorksModel model = service.findByName(name);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
    }

    @Operation(summary = "添加作品", description = "添加作品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<WorksModel> add(@RequestBody WorksModel model) {
        WorksModel addModel = service.findByName(model.getName());
        if (addModel != null) {
            return new Result<>(CodeConst.REPEAT.getResultCode(), CodeConst.REPEAT.getMessage());
        }
        addModel = service.add(model);
        return new Result<>(addModel);
    }

    @Operation(summary = "更新作品", description = "更新作品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<WorksModel> update(@RequestBody WorksModel model) {
        WorksModel worksModel = service.findById(model.getId());
        if (worksModel == null) {
            return new Result<>(CodeConst.CodeOR.getResultCode(), CodeConst.CodeOR.getMessage());
        }
        worksModel = service.update(worksModel);
        return new Result<>(worksModel);
    }


    @Operation(summary = "根据id删除作品", description = "根据id删除作品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH),
    })
    public Result<WorksModel> delete(@PathVariable Long id) {
        WorksModel model = service.findById(id);
        if (model == null) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        service.del(id);
        return new Result<>(model);
    }

}
