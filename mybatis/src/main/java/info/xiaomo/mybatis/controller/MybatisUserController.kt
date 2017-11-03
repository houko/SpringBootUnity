package info.xiaomo.mybatis.controller

import info.xiaomo.core.base.Result
import info.xiaomo.mybatis.domain.User
import info.xiaomo.mybatis.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 * Date: 2016/11/16 9:41
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

@RestController
class MybatisUserController @Autowired
constructor(private val userMapper: UserMapper) {


    @RequestMapping("/")
    fun findAll(): Result<List<User>> {
        val all = userMapper.findAll()
        return Result(all)
    }

}
