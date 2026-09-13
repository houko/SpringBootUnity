package info.xiaomo.ratelimit.controller;

import info.xiaomo.core.base.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xiaomo
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Result<String> hello() {
        return new Result<>("你好, 这是一个被限流的接口");
    }

}