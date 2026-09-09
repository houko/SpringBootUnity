package info.xiaomo.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ActuatorMain.class)
@AutoConfigureMockMvc
class ActuatorEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 健康检查应当返回UP并包含自定义的检查项() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.components.diskSpaceRatio").exists())
                .andExpect(jsonPath("$.components.diskSpaceRatio.details.freeRatio").exists());
    }

    @Test
    void info端点应当返回配置的应用信息() throws Exception {
        mockMvc.perform(get("/actuator/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app.name").value("SpringBootUnity actuator 示例"));
    }

    @Test
    void 未在白名单中的端点不应当被暴露() throws Exception {
        // beans 没有列在 management.endpoints.web.exposure.include 里
        mockMvc.perform(get("/actuator/beans"))
                .andExpect(status().isNotFound());
    }

    @Test
    void 调用业务接口后自定义指标应当可见且计数递增() throws Exception {
        mockMvc.perform(get("/greeting/{name}", "xiaomo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("你好, xiaomo"));

        mockMvc.perform(get("/greeting/{name}", "houko"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/actuator/metrics/greeting.count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("greeting.count"))
                .andExpect(jsonPath("$.measurements[0].value").value(2.0));
    }

}
