package info.xiaomo.anysc.task

import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.Future

/**
 * @author : xiaomo
 */
@Component
open class AsyncTask {

    @Async
    @Throws(Exception::class)
    open fun doTaskOne(): Future<String> {
        println("开始做任务一")
        val start = System.currentTimeMillis()
        Thread.sleep(random.nextInt(10000).toLong())
        val end = System.currentTimeMillis()
        println("完成任务一，耗时：" + (end - start) + "毫秒")
        return AsyncResult("任务一完成")
    }

    @Async
    @Throws(Exception::class)
    open fun doTaskTwo(): Future<String> {
        println("开始做任务二")
        val start = System.currentTimeMillis()
        Thread.sleep(random.nextInt(10000).toLong())
        val end = System.currentTimeMillis()
        println("完成任务二，耗时：" + (end - start) + "毫秒")
        return AsyncResult("任务二完成")
    }

    @Async
    @Throws(Exception::class)
    open fun doTaskThree(): Future<String> {
        println("开始做任务三")
        val start = System.currentTimeMillis()
        Thread.sleep(random.nextInt(10000).toLong())
        val end = System.currentTimeMillis()
        println("完成任务三，耗时：" + (end - start) + "毫秒")
        return AsyncResult("任务三完成")
    }

    companion object {

        private val random = Random()
    }

}
