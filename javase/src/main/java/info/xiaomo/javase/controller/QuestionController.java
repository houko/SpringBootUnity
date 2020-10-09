package info.xiaomo.javase.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.javase.model.QuestionModel;
import info.xiaomo.javase.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 *
 * @author : xiaomo
 * github  : https://github.com/houko
 * email   : xiaomo@xiaomo.info
 * QQ      : 83387856
 * Date    : 2017/11/20 19:00
 * desc    :
 * Copyright(©) 2017 by xiaomo.
 */
@RestController
@RequestMapping("/question")
@Api(value = "question", description = "question")
public class QuestionController {


    private final QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }

    /**
     * 根据id 查找用户
     *
     * @param id id
     * @return result
     */
    @ApiOperation(value = "查找问题", notes = "查找问题", httpMethod = "GET")
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    public Result findUserById(@PathVariable("id") Long id) {
        QuestionModel questionModel = service.findById(id);
        if (questionModel == null) {
            return new Result<>(CodeConst.NOT_FOUNT.getResultCode(), CodeConst.NOT_FOUNT.getMessage());
        }
        return new Result<>(questionModel);
    }

    /**
     * 根据id 查找用户
     *
     * @return result
     */
    @ApiOperation(value = "添加", notes = "添加", httpMethod = "POST")
    @RequestMapping(value = "findById/{id}", method = RequestMethod.POST)
    public Result addQuestion(@RequestBody QuestionModel questionModel) {
        boolean add = service.add(questionModel);
        return new Result<>(add);
    }

}
