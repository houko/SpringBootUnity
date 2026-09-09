package info.xiaomo.restclient;

import info.xiaomo.restclient.model.Repository;
import info.xiaomo.restclient.service.GithubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withResourceNotFound;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * 用 MockRestServiceServer 拦住 RestClient 的请求, 测试不依赖网络, 也不会因为 GitHub 限流而变成 flaky。
 */
class GithubServiceTest {

    private static final String BASE_URL = "https://api.github.example";

    private MockRestServiceServer server;
    private GithubService service;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder();
        server = MockRestServiceServer.bindTo(builder).build();
        service = new GithubService(builder, BASE_URL);
    }

    @Test
    void 应当正确解析仓库列表并映射下划线字段() {
        String json = """
                [
                  {"name":"SpringBootUnity","description":"spring boot 示例集合",
                   "html_url":"https://github.com/houko/SpringBootUnity","stargazers_count":1024},
                  {"name":"another","description":null,
                   "html_url":"https://github.com/houko/another","stargazers_count":7,
                   "unknown_field":"新增字段不应导致反序列化失败"}
                ]
                """;
        server.expect(requestTo(BASE_URL + "/users/houko/repos"))
                .andExpect(method(org.springframework.http.HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<Repository> repos = service.listRepositories("houko");

        server.verify();
        assertThat(repos).hasSize(2);
        assertThat(repos.getFirst().name()).isEqualTo("SpringBootUnity");
        // html_url / stargazers_count 通过 @JsonProperty 映射到驼峰字段
        assertThat(repos.getFirst().htmlUrl()).isEqualTo("https://github.com/houko/SpringBootUnity");
        assertThat(repos.getFirst().stars()).isEqualTo(1024);
    }

    @Test
    void 用户不存在时应当抛出语义明确的异常而不是原始状态码错误() {
        server.expect(requestTo(BASE_URL + "/users/no-such-user/repos"))
                .andRespond(withResourceNotFound());

        assertThatThrownBy(() -> service.listRepositories("no-such-user"))
                .isInstanceOf(GithubService.UserNotFoundException.class)
                .hasMessageContaining("no-such-user");

        server.verify();
    }

    @Test
    void 空列表应当正常返回而不是null() {
        server.expect(requestTo(BASE_URL + "/users/empty/repos"))
                .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        assertThat(service.listRepositories("empty")).isEmpty();
        server.verify();
    }

}
