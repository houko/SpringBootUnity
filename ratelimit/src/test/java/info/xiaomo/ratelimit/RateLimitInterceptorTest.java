package info.xiaomo.ratelimit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用可拨动的时钟验证限流行为, 不需要 sleep, 也不会因为机器快慢而 flaky。
 * maxRequests 与 windowMillis 见 config/application.properties。
 */
@SpringBootTest(classes = RateLimitMain.class)
@AutoConfigureMockMvc
@Import(RateLimitInterceptorTest.TestClockConfig.class)
class RateLimitInterceptorTest {

    private static final int MAX = 5;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MutableClock mutableClock;

    @BeforeEach
    void 重置时钟() {
        mutableClock.set(Instant.parse("2026-01-01T00:00:00Z"));
    }

    @Test
    void 超出窗口上限后应当返回429() throws Exception {
        String client = "client-a";
        for (int i = 0; i < MAX; i++) {
            mockMvc.perform(get("/api/hello").header("X-Client-Id", client))
                    .andExpect(status().isOk());
        }
        mockMvc.perform(get("/api/hello").header("X-Client-Id", client))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.resultCode").value(429))
                .andExpect(jsonPath("$.message").value("请求过于频繁, 请稍后再试"));
    }

    @Test
    void 时间窗滚动后应当重新放行() throws Exception {
        String client = "client-b";
        for (int i = 0; i < MAX; i++) {
            mockMvc.perform(get("/api/hello").header("X-Client-Id", client))
                    .andExpect(status().isOk());
        }
        mockMvc.perform(get("/api/hello").header("X-Client-Id", client))
                .andExpect(status().isTooManyRequests());

        mutableClock.advance(Duration.ofSeconds(61));

        mockMvc.perform(get("/api/hello").header("X-Client-Id", client))
                .andExpect(status().isOk());
    }

    @Test
    void 不同客户端互不影响() throws Exception {
        String a = "client-c1";
        String b = "client-c2";
        for (int i = 0; i < MAX; i++) {
            mockMvc.perform(get("/api/hello").header("X-Client-Id", a)).andExpect(status().isOk());
        }
        // a 已经用尽额度
        mockMvc.perform(get("/api/hello").header("X-Client-Id", a)).andExpect(status().isTooManyRequests());
        // b 不受影响
        mockMvc.perform(get("/api/hello").header("X-Client-Id", b)).andExpect(status().isOk());
    }

    @Test
    void 请求头缺失时退化为按来源地址限流() throws Exception {
        // 不带 X-Client-Id 时, 同一来源地址累计到上限后同样被限制
        for (int i = 0; i < MAX; i++) {
            mockMvc.perform(get("/api/hello"))
                    .andExpect(status().isOk());
        }
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.resultCode").value(429));
    }

    @TestConfiguration
    static class TestClockConfig {
        @Bean
        @Primary
        MutableClock mutableClock() {
            return new MutableClock();
        }
    }

    static class MutableClock extends Clock {
        private Instant instant = Instant.parse("2026-01-01T00:00:00Z");

        void set(Instant instant) {
            this.instant = instant;
        }

        void advance(Duration duration) {
            this.instant = this.instant.plus(duration);
        }

        @Override
        public ZoneId getZone() {
            return ZoneOffset.UTC;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }

        @Override
        public Instant instant() {
            return instant;
        }
    }

}