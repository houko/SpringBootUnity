package info.xiaomo.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ValidationMain.class)
@AutoConfigureMockMvc
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 合法请求应当通过校验() throws Exception {
        String body = """
                {"userName":"xiaomo","email":"xiaomo@xiaomo.info","password":"secret123","age":30}
                """;

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value(200))
                .andExpect(jsonPath("$.data").value("注册成功: xiaomo"));
    }

    @Test
    void 非法请求体应当逐字段返回错误信息() throws Exception {
        // userName 太短, email 格式错误, password 太短, age 为负数
        String body = """
                {"userName":"ab","email":"not-an-email","password":"123","age":-1}
                """;

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value(400))
                .andExpect(jsonPath("$.message").value("参数校验失败"))
                .andExpect(jsonPath("$.data.userName").value("用户名长度需在 3 到 20 之间"))
                .andExpect(jsonPath("$.data.email").value("邮箱格式不正确"))
                .andExpect(jsonPath("$.data.password").value("密码长度需在 6 到 32 之间"))
                .andExpect(jsonPath("$.data.age").value("年龄不能为负数"));
    }

    @Test
    void 缺失的必填字段应当被拦截() throws Exception {
        String body = """
                {"userName":"","email":"","password":"","age":20}
                """;

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data.userName").exists())
                .andExpect(jsonPath("$.data.email").exists())
                .andExpect(jsonPath("$.data.password").exists());
    }

    @Test
    void 合法的路径参数应当通过() throws Exception {
        mockMvc.perform(get("/register/check/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("年龄合法: 20"));
    }

    @Test
    void 非法的路径参数应当返回约束提示() throws Exception {
        mockMvc.perform(get("/register/check/16"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("参数校验失败"))
                .andExpect(jsonPath("$.data").value("年龄必须大于等于 18"));
    }

}
