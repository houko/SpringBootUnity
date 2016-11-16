package info.xiaomo.multipleSource.controller;

import info.xiaomo.core.controller.Result;
import info.xiaomo.multipleSource.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * @Date: 2016/11/16 10:45
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/

@RestController
public class MultipleSourceController {
    private final JdbcTemplate jdbcTemplate1;

    private final JdbcTemplate jdbcTemplate2;

    @Autowired
    public MultipleSourceController(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate1, @Qualifier("secondaryJdbcTemplate") JdbcTemplate jdbcTemplate2) {
        this.jdbcTemplate1 = jdbcTemplate1;
        this.jdbcTemplate2 = jdbcTemplate2;
        this.jdbcTemplate1.update("DELETE  FROM  user ");
        this.jdbcTemplate2.update("DELETE  FROM  user ");
    }


    @RequestMapping("/")
    public Result index() {
        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update(Sql.addUser1, "xiaomo", 20);
        jdbcTemplate2.update(Sql.addUser1, "xiaoming", 30);

        int count1 = jdbcTemplate1.queryForObject(Sql.selectUser1, Integer.class);
        int count2 = jdbcTemplate2.queryForObject(Sql.selectUser2, Integer.class);
        return new Result(new Object[]{count1, count2});
    }
}
