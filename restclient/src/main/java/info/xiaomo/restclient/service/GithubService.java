package info.xiaomo.restclient.service;

import info.xiaomo.restclient.model.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * RestClient 是 Spring 6.1 引入的同步 HTTP 客户端, 用来取代 RestTemplate。
 * 它的 API 是流式的, 并且可以针对状态码单独挂错误处理。
 *
 * @author : xiaomo
 */
@Service
public class GithubService {

    private final RestClient restClient;

    public GithubService(RestClient.Builder builder, @Value("${app.github.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    /**
     * 查询某个用户的公开仓库。404 单独转成语义明确的异常, 而不是让调用方去看状态码。
     */
    public List<Repository> listRepositories(String user) {
        return restClient.get()
                .uri("/users/{user}/repos", user)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            throw new UserNotFoundException(user);
                        })
                .body(new org.springframework.core.ParameterizedTypeReference<>() {
                });
    }

    /**
     * 用户不存在。
     */
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String user) {
            super("GitHub 用户不存在: " + user);
        }
    }

}
