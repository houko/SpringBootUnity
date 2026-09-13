package info.xiaomo.elasticsearch.repository;

import info.xiaomo.elasticsearch.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 方法名即查询: findByTitleContaining 会生成对 title 字段做 match 的查询。
 * 复杂的 bool/聚合查询再换成 NativeQuery 或 @Query 注解。
 *
 * @author : xiaomo
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    List<Article> findByTitleContaining(String keyword);

    List<Article> findByAuthor(String author);

}