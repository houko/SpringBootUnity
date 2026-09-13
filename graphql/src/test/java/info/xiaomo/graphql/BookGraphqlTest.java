package info.xiaomo.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 直接对 /graphql 端点发 POST 请求, 走真实的 GraphQL 执行链路(校验 + 解析 + 取数 + 序列化)。
 * 通过变量传参可以避免在 JSON 里转义 GraphQL 字符串中的引号。
 */
@SpringBootTest(classes = GraphqlMain.class)
@AutoConfigureMockMvc
class BookGraphqlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 按ID查询并只取需要字段() throws Exception {
        String body = """
                {"query":"query($id: ID!) { bookById(id: $id) { id title author } }","variables":{"id":"1"}}
                """;

        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.bookById.id").value("1"))
                .andExpect(jsonPath("$.data.bookById.title").value("Spring Boot 实战"))
                .andExpect(jsonPath("$.data.bookById.author").value("小莫"));
    }

    @Test
    void 查询全部书籍() throws Exception {
        String body = """
                {"query":"{ allBooks { id title } }"}
                """;

        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.allBooks.length()").value(3));
    }

    @Test
    void 查询不存在的字段应当返回errors() throws Exception {
        String body = """
                {"query":"query($id: ID!) { bookById(id: $id) { id noSuchField } }","variables":{"id":"1"}}
                """;

        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.data").doesNotExist());
    }

}