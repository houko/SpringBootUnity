package info.xiaomo.validation.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.validation.request.RegisterRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Valid 校验请求体, @Validated + 约束注解校验路径参数。
 * 两者校验失败抛出的异常类型不同, 统一由 {@link info.xiaomo.validation.handler.GlobalExceptionHandler} 处理。
 *
 * @author : xiaomo
 */
@RestController
@RequestMapping("/register")
@Validated
public class RegisterController {

    /**
     * 校验请求体。失败时抛 MethodArgumentNotValidException。
     */
    @PostMapping
    public Result<String> register(@Valid @RequestBody RegisterRequest request) {
        return new Result<>("注册成功: " + request.userName());
    }

    /**
     * 校验路径参数。失败时抛 ConstraintViolationException。
     */
    @GetMapping("/check/{age}")
    public Result<String> checkAge(@PathVariable @Min(value = 18, message = "年龄必须大于等于 18") int age) {
        return new Result<>("年龄合法: " + age);
    }

}
