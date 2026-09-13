package info.xiaomo.aop;

import info.xiaomo.aop.metrics.InvocationMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AopMain.class)
@AutoConfigureMockMvc
class AopAspectTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InvocationMetrics metrics;

    @BeforeEach
    void 清空计数() {
        metrics.reset();
    }

    @Test
    void 带Loggable注解的方法应当被切面拦截并计数() throws Exception {
        mockMvc.perform(get("/greeting/{name}", "xiaomo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("你好, xiaomo"));

        assertThat(metrics.totalCount()).isEqualTo(1);
        assertThat(metrics.countOf("GreetingController.greet(..)")).isEqualTo(1);
    }

    @Test
    void 每调用一次计数就增加一次() throws Exception {
        mockMvc.perform(get("/greeting/{name}", "xiaomo")).andExpect(status().isOk());
        mockMvc.perform(get("/greeting/{name}", "houko")).andExpect(status().isOk());

        assertThat(metrics.totalCount()).isEqualTo(2);
        assertThat(metrics.countOf("GreetingController.greet(..)")).isEqualTo(2);
    }

}