package info.xiaomo.async;

import info.xiaomo.async.task.AsyncTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Async 生效时三个任务并发执行, 总耗时应当明显小于串行执行之和。
 * 单个任务耗时 1000~3000ms, 串行最少 3000ms, 并发则不超过最慢的那个。
 */
@SpringBootTest(classes = AsyncMain.class)
class AsyncTaskTest {

    @Autowired
    private AsyncTask task;

    @Test
    void 三个任务应当并发执行而不是串行() {
        long start = System.currentTimeMillis();

        List<CompletableFuture<String>> futures = List.of(
                task.doTask("任务一"),
                task.doTask("任务二"),
                task.doTask("任务三"));
        List<String> results = futures.stream().map(CompletableFuture::join).toList();

        long cost = System.currentTimeMillis() - start;

        assertThat(results).hasSize(3);
        assertThat(results).allSatisfy(r -> assertThat(r).contains("完成"));
        // 串行至少需要 3 * 1000ms, 留出足够余量后仍应远低于该值
        assertThat(cost).isLessThan(3000);
    }

    @Test
    void 被代理的方法应当立即返回而不阻塞调用方() {
        long start = System.currentTimeMillis();
        CompletableFuture<String> future = task.doTask("任务一");
        long returnCost = System.currentTimeMillis() - start;

        // 方法体最少睡 1000ms, 调用方却能立刻拿到 future
        assertThat(returnCost).isLessThan(500);
        assertThat(future.join()).contains("任务一");
    }

}
