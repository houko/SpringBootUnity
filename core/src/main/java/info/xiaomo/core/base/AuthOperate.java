package info.xiaomo.core.base;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/13 9:51
 */
public interface AuthOperate {


    /**
     * 登录
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 结果
     */
    Result<Boolean> login(@PathVariable String name, @PathVariable String password);


    /**
     * 修改密码
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 结果
     */
    Result<Boolean> changePassword(@PathVariable String name, @PathVariable String password);

    /**
     * 注册
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 是否己发送验证码
     */
    Result<Boolean> register(@PathVariable String name, @PathVariable String password);

    /**
     * 验证
     *
     * @param validCode 验证码
     * @param time      发送时间
     * @return 是否验证通过
     */
    Result<Boolean> validate(@PathVariable int validCode, @PathVariable Date time);

}
