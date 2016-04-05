package info.xiaomo.admin.test;

import info.xiaomo.admin.test.base.BaseTest;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 16/4/5 23:41
 * @Description: admin测试
 * @Copyright(©) 2015 by xiaomo.
 */
public class UserControllerTest extends BaseTest {

    @Autowired
    private UserService service;

    @Test
    public void testFindAll() {
        UserModel userModel = service.findUserById(19L);
        System.out.println(userModel.getNickName());
    }

}
