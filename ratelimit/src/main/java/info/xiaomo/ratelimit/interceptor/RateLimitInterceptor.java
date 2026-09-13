package info.xiaomo.ratelimit.interceptor;

import tools.jackson.databind.ObjectMapper;
import info.xiaomo.core.base.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于固定时间窗口的极简限流: 每个客户端在每个窗口内最多允许 maxRequests 次请求。
 * 纯内存实现, 单实例够用; 多实例部署需要换成 Redis 之类的共享存储, 但"拦截器"这个位置不变。
 *
 * <p>客户端标识优先取 X-Client-Id 请求头, 没有则退化为按来源 IP 限流。
 *
 * @author : xiaomo
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();
    private final Clock clock;
    private final ObjectMapper objectMapper;
    private final int maxRequests;
    private final long windowMillis;

    public RateLimitInterceptor(Clock clock, ObjectMapper objectMapper,
            @Value("${app.rate-limit.max-requests:5}") int maxRequests,
            @Value("${app.rate-limit.window-millis:60000}") long windowMillis) {
        this.clock = clock;
        this.objectMapper = objectMapper;
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String clientId = clientIdOf(request);
        if (tryAcquire(clientId)) {
            return true;
        }
        reject(response);
        return false;
    }

    /**
     * 计算当前窗口并让计数加一, 返回是否还在额度内。窗口起点按时间向下取整到 windowMillis 的整数倍,
     * 时间一翻篇 compute 就会换一个新的窗口对象, 计数自然清零。
     */
    private boolean tryAcquire(String clientId) {
        long now = clock.millis();
        long windowStart = now / windowMillis * windowMillis;
        Window window = windows.compute(clientId, (key, existing) ->
                existing == null || existing.windowStart != windowStart ? new Window(windowStart) : existing);
        return window.count.incrementAndGet() <= maxRequests;
    }

    private String clientIdOf(HttpServletRequest request) {
        String fromHeader = request.getHeader("X-Client-Id");
        if (fromHeader != null && !fromHeader.isBlank()) {
            return fromHeader;
        }
        // 没带客户端标识时退化为按来源 IP 限流; remoteAddr 可能为空(例如某些代理/测试环境), 兜底成一个常量
        String remoteAddr = request.getRemoteAddr();
        return remoteAddr == null || remoteAddr.isBlank() ? "unknown" : remoteAddr;
    }

    private void reject(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(
                new Result<>(HttpStatus.TOO_MANY_REQUESTS.value(), "请求过于频繁, 请稍后再试", null)));
    }

    private static final class Window {
        final long windowStart;
        final AtomicInteger count = new AtomicInteger();

        Window(long windowStart) {
            this.windowStart = windowStart;
        }
    }

}