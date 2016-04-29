package info.xiaomo.admin.test.adminTest;

import info.xiaomo.admin.base.BaseTest;
import info.xiaomo.core.model.AdminModel;
import info.xiaomo.core.service.AdminUserService;
import info.xiaomo.core.untils.MD5Util;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
public class AdminControllerTest extends BaseTest {

    @Autowired
    private AdminUserService service;

    @Test
    public void testAddAdminUser() {
        AdminModel model = new AdminModel();
        model.setUserName("test");
        model.setPassword(MD5Util.encode("test","123"));
        model.setAuthLevel(1);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        service.addAdminUser(model);
    }
}
