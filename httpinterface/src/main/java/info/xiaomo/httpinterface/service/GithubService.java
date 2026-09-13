package info.xiaomo.httpinterface.service;

import info.xiaomo.httpinterface.client.GithubApi;
import info.xiaomo.httpinterface.model.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

/**
 * 用 {@link HttpServiceProxyFactory} 把声明式接口变成可调用的代理。
 * 这里的底座是 {@link RestClient}, 换成 WebClient 只需替换 adapter, 接口本身不用动。
 *
 * @author : xiaomo
 */
@Service
public class GithubService {

    private final GithubApi api;

    public GithubService(RestClient.Builder builder, @Value("${app.github.base-url}") String baseUrl) {
        RestClient restClient = builder.baseUrl(baseUrl).build();
        this.api = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(GithubApi.class);
    }

    /**
     * 查询某个用户的公开仓库。404 单独转成语义明确的异常, 而不是让调用方去看状态码。
     */
    public List<Repository> listRepositories(String user) {
        try {
            return api.listRepositories(user);
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(user);
        }
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