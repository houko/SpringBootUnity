package info.xiaomo.core.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/3/2 10:18
 * @Description: runner
 * @Copyright(©) 2015 by xiaomo.
 **/
public class StartupRunner implements CommandLineRunner {
    protected final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("hello，this is startupRunner");
    }
}
