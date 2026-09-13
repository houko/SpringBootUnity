package info.xiaomo.httpinterface.client;

import info.xiaomo.httpinterface.model.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

/**
 * 声明式接口: 只描述"要什么"。方法名、参数、返回值就是全部约定,
 * 底层的 HTTP 动词、序列化、连接管理都由 Spring 生成的代理负责。
 *
 * @author : xiaomo
 */
public interface GithubApi {

    @GetExchange("/users/{user}/repos")
    List<Repository> listRepositories(@PathVariable("user") String user);

}