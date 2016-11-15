package info.xiaomo.anysc.controller;

import info.xiaomo.anysc.task.AsyncTask;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

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
 * @Date: 2016/11/15 15:12
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/

@RestController
@RequestMapping("/async")
public class TestController extends BaseController{

    private final AsyncTask task;

    @Autowired
    public TestController(AsyncTask task) {
        this.task = task;
    }

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public Result task() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return new Result(end - start);
    }


}
