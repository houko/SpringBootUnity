package info.xiaomo.validation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 注册请求。约束直接声明在字段上, 由 jakarta.validation 在进入控制器之前完成校验。
 *
 * @author : xiaomo
 */
public record RegisterRequest(

        @NotBlank(message = "用户名不能为空")
        @Size(min = 3, max = 20, message = "用户名长度需在 3 到 20 之间")
        String userName,

        @NotBlank(message = "邮箱不能为空")
        @Email(message = "邮箱格式不正确")
        String email,

        @NotBlank(message = "密码不能为空")
        @Pattern(regexp = "^.{6,32}$", message = "密码长度需在 6 到 32 之间")
        String password,

        @Min(value = 0, message = "年龄不能为负数")
        @Max(value = 150, message = "年龄不能大于 150")
        int age) {
}
