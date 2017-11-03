package info.xiaomo.core.base

import org.springframework.web.bind.annotation.PathVariable
import java.util.*

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/13 9:51
 */
interface AuthOperate {


    /**
     * 登录
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 结果
     */
    fun login(@PathVariable name: String, @PathVariable password: String): Result<Boolean>


    /**
     * 修改密码
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 结果
     */
    fun changePassword(@PathVariable name: String, @PathVariable password: String): Result<Boolean>

    /**
     * 注册
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 是否己发送验证码
     */
    fun register(@PathVariable name: String, @PathVariable password: String): Result<Boolean>

    /**
     * 验证
     *
     * @param validCode 验证码
     * @param time      发送时间
     * @return 是否验证通过
     */
    fun validate(@PathVariable validCode: Int, @PathVariable time: Date): Result<Boolean>

}
