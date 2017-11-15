package info.xiaomo.redis.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.redis.dao.CommonRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/11/14 17:25
 * Copyright(©) 2015 by xiaomo.
 **/

@RestController
@RequestMapping("/redis")
public class TestController {

    private final CommonRedisDao dao;

    @Autowired
    public TestController(CommonRedisDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "get/{key}", method = RequestMethod.GET)
    public Result<String> find(@PathVariable("key") String key) {
        String value = dao.getValue(key);
        return new Result<>(value);
    }

    @RequestMapping(value = "add/{key}/{value}", method = RequestMethod.GET)
    public Result<Boolean> add(@PathVariable("value") String value, @PathVariable("key") String key) {
        return new Result<>(dao.cacheValue(key, value));
    }

    @RequestMapping(value = "del/{key}", method = RequestMethod.GET)
    public Result<Boolean> del(@PathVariable("key") String key) {
        return new Result<>(dao.removeValue(key));
    }

    @RequestMapping(value = "count/{key}", method = RequestMethod.GET)
    public Result<Long> count(@PathVariable("key") String key) {
        return new Result<>(dao.getListSize(key));
    }


}
