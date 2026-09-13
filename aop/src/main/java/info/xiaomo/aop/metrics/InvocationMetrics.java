package info.xiaomo.aop.metrics;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 记录每个方法被真正执行过的次数。与 cache 示例里的计数器作用相同: 用一个可观测的状态
 * 来断言切面确实拦截到了方法, 而不是去断言 AOP 框架的内部机制。
 *
 * @author : xiaomo
 */
@Component
public class InvocationMetrics {

    private final ConcurrentHashMap<String, AtomicInteger> counts = new ConcurrentHashMap<>();

    public void record(String method) {
        counts.computeIfAbsent(method, key -> new AtomicInteger()).incrementAndGet();
    }

    public int countOf(String method) {
        return counts.getOrDefault(method, new AtomicInteger()).get();
    }

    public int totalCount() {
        return counts.values().stream().mapToInt(AtomicInteger::get).sum();
    }

    public void reset() {
        counts.clear();
    }

}