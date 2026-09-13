package info.xiaomo.aop.aspect;

import info.xiaomo.aop.annotation.Loggable;
import info.xiaomo.aop.metrics.InvocationMetrics;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 拦截所有带 {@link Loggable} 注解的方法, 打印耗时并累计调用次数。
 * {@code @Around} 是能力最强的通知类型, 可以决定是否继续执行、替换返回值、记录耗时。
 *
 * @author : xiaomo
 */
@Aspect
@Component
public class GreetingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingAspect.class);

    private final InvocationMetrics metrics;

    public GreetingAspect(InvocationMetrics metrics) {
        this.metrics = metrics;
    }

    @Around("@annotation(loggable)")
    public Object logAndMeasure(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        String signature = joinPoint.getSignature().toShortString();
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long costMs = (System.nanoTime() - start) / 1_000_000;
            metrics.record(signature);
            String label = loggable.value().isBlank() ? signature : loggable.value();
            LOGGER.info("[{}] 执行完成, 耗时 {} 毫秒", label, costMs);
        }
    }

}