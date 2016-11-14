package info.xiaomo.test;

import info.xiaomo.website.dao.MongoDao;
import info.xiaomo.website.model.UserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class MongoTests {

    @Autowired
    private MongoDao UserModelRepository;

    @Before
    public void setUp() {
        UserModelRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {

        // 创建三个UserModel，并验证UserModel总数
        UserModelRepository.save(new UserModel());
        UserModelRepository.save(new UserModel());
        UserModelRepository.save(new UserModel());
        Assert.assertEquals(3, UserModelRepository.findAll().size());

        // 删除一个UserModel，再验证UserModel总数
        UserModel u = UserModelRepository.findOne(1L);
        UserModelRepository.delete(u);
        Assert.assertEquals(2, UserModelRepository.findAll().size());

        // 删除一个UserModel，再验证UserModel总数
        Assert.assertEquals(1, UserModelRepository.findAll().size());

    }

}
