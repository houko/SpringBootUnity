package info.xiaomo.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = I18nMain.class)
@AutoConfigureMockMvc
class I18nTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 中文环境返回中文文案() throws Exception {
        mockMvc.perform(get("/greeting/{name}", "xiaomo").header(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("你好, xiaomo"));
    }

    @Test
    void 英文环境返回英文文案() throws Exception {
        mockMvc.perform(get("/greeting/{name}", "xiaomo").header(HttpHeaders.ACCEPT_LANGUAGE, "en-US"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Hello, xiaomo"));
    }

    @Test
    void 未指定语言时回退到默认的中文() throws Exception {
        mockMvc.perform(get("/greeting/{name}", "xiaomo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("你好, xiaomo"));
    }

}