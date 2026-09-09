package info.xiaomo.restclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 只声明用得上的字段。@JsonIgnoreProperties(ignoreUnknown = true) 让外部接口新增字段时不会把反序列化打挂。
 *
 * @author : xiaomo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Repository(
        String name,
        String description,
        @JsonProperty("html_url") String htmlUrl,
        @JsonProperty("stargazers_count") int stars) {
}
