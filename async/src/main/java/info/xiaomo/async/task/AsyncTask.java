package info.xiaomo.async.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 用 @Async 把方法丢到线程池里执行, 调用方立刻拿到一个 CompletableFuture 而不会阻塞。
 * 生效的前提是启动类上加了 @EnableAsync, 见 {@link info.xiaomo.async.AsyncMain}。
 *
 * @author : xiaomo
 */
@Component
public class AsyncTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    @Async
    public CompletableFuture<String> doTask(String name) {
        long start = System.currentTimeMillis();
        LOGGER.info("开始{}", name);
        try {
            // 用随机耗时模拟一个慢操作
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
        } catch (InterruptedException e) {
            // 恢复中断状态, 让上层能感知到取消
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(e);
        }
        long cost = System.currentTimeMillis() - start;
        LOGGER.info("完成{}, 耗时 {} 毫秒", name, cost);
        return CompletableFuture.completedFuture(name + "完成, 耗时 " + cost + " 毫秒");
    }

}
