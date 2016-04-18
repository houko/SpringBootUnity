package info.xiaomo.admin.test.BlogTest;

import info.xiaomo.admin.test.base.BaseTest;
import info.xiaomo.core.model.BlogModel;
import info.xiaomo.core.service.BlogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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
public class BlogControllerTest extends BaseTest {

    @Autowired
    private BlogService service;

    @Test
    public void testFindAll() {
        BlogModel blogModel = service.findBlogById(1L);
        System.out.println(Arrays.toString(blogModel.getTagIds()));
    }

    @Test
    public void testAdd(){
       for(int i=0;i<10;i++){
           BlogModel model = new BlogModel();
           model.setTitle("idea15破解方法"+i*10);
           model.setSummary("idea15破解方法 http://idea.lanyus.com");
           model.setContent("注册时选择 License server ，\n" +
                   "\n" +
                   "填 http://idea.lanyus.com ，然后点击 OK\n" +
                   "\n" +
                   "\n" +
                   "\n" +
                   "14的话，网上可以找到一个，根据你的用户名生成激活码。\n" +
                   "\n" +
                   "\n" +
                   "\n" +
                   "特别说明下个人使用体验：\n" +
                   "\n" +
                   "升级到15的话如果电脑硬件撑不住的话，体验相当不好！用几个小时就特别卡，卡到你想哭（这时你重启idea就好了）");
           model.setStatus(0);
           model.setAuthor("xiaomo");
           model.setVote(i);
           Long [] tagIds = new Long[2];
           tagIds[0]= 1L;
           tagIds[1] = 2L;
           model.setTagIds(tagIds);
           model.setCreateTime(new Date());
           model.setUpdateTime(new Date());
           service.addBlog(model);
       }
    }

}
