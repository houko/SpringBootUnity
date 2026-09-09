package info.xiaomo.actuator.controller;

import info.xiaomo.core.base.Result;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义业务指标。注册到 MeterRegistry 后可以在 /actuator/metrics/greeting.count 看到。
 *
 * @author : xiaomo
 */
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final Counter greetingCounter;
    private final Timer greetingTimer;

    public GreetingController(MeterRegistry registry) {
        this.greetingCounter = Counter.builder("greeting.count")
                .description("打招呼接口被调用的次数")
                .register(registry);
        this.greetingTimer = Timer.builder("greeting.latency")
                .description("打招呼接口的耗时")
                .register(registry);
    }

    @GetMapping("/{name}")
    public Result<String> greet(@PathVariable("name") String name) {
        return greetingTimer.record(() -> {
            greetingCounter.increment();
            return new Result<>("你好, " + name);
        });
    }

}
