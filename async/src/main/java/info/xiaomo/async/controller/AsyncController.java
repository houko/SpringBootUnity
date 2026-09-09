package info.xiaomo.async.controller;

import info.xiaomo.async.task.AsyncTask;
import info.xiaomo.core.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 三个任务并发执行, 总耗时接近最慢的那个而不是三者之和。
 *
 * @author : xiaomo
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);

    private final AsyncTask task;

    public AsyncController(AsyncTask task) {
        this.task = task;
    }

    @GetMapping
    public Result<List<String>> runTasks() {
        long start = System.currentTimeMillis();

        List<CompletableFuture<String>> futures = List.of(
                task.doTask("任务一"),
                task.doTask("任务二"),
                task.doTask("任务三"));

        // join 会等到全部完成, 不需要自己轮询
        List<String> results = futures.stream().map(CompletableFuture::join).toList();

        long cost = System.currentTimeMillis() - start;
        LOGGER.info("任务全部完成, 总耗时 {} 毫秒", cost);
        return new Result<>(results);
    }

}
