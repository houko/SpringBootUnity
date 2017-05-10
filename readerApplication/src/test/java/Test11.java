import info.xiaomo.reader.ReaderMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

import static info.xiaomo.core.untils.ExcelUtil.getListData;

/**
 * author 小莫 (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/13 18:33
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes = ReaderMain.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class Test11 {

//    @Autowired
//    private
//    CountryDao dao;

    @Test
    public void test() throws IOException {
        List<List<String>> listData = getListData("E:\\thinkpage_cities.xls", 1);
        for (List<String> listDatum : listData) {
//            CountryModel model = new CountryModel();
//            model.setCityName(listDatum.get(0));
//            model.setName(listDatum.get(1));
//            model.setCreateTime(new Date());
//            model.setUpdateTime(new Date());
//            dao.save(model);
        }

    }
}

