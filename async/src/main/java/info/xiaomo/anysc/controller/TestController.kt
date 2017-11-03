package info.xiaomo.anysc.controller

import info.xiaomo.anysc.task.AsyncTask
import info.xiaomo.core.base.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 *
 * Date: 2016/11/15 15:12
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

@RestController
@RequestMapping("/")
class TestController @Autowired
constructor(private val task: AsyncTask) {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    @Throws(Exception::class)
    fun task(): Result<*> {
        val start = System.currentTimeMillis()

        val task1 = task.doTaskOne()
        val task2 = task.doTaskTwo()
        val task3 = task.doTaskThree()

        while (true) {
            if (task1.isDone && task2.isDone && task3.isDone) {
                // 三个任务都调用完成，退出循环等待
                break
            }
            Thread.sleep(1000)
        }

        val end = System.currentTimeMillis()

        println("任务全部完成，总耗时：" + (end - start) + "毫秒")
        return Result(end - start)
    }

}
