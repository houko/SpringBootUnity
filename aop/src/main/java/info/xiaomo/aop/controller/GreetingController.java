package info.xiaomo.aop.controller;

import info.xiaomo.aop.annotation.Loggable;
import info.xiaomo.core.base.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务方法里看不到任何日志代码, 只通过一个注解声明关注点。
 *
 * @author : xiaomo
 */
@RestController
public class GreetingController {

    @Loggable("打招呼")
    @GetMapping("/greeting/{name}")
    public Result<String> greet(@PathVariable("name") String name) {
        return new Result<>("你好, " + name);
    }

}