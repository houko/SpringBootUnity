package info.xiaomo.graphql.model;

/**
 * 与 schema 里的 Book 类型一一对应。GraphQL 的 ID 标量在 Java 侧默认映射成 String。
 *
 * @author : xiaomo
 */
public record Book(String id, String title, String author) {
}