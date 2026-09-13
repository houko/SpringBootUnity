package info.xiaomo.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @Document(indexName = "articles") 把这个类映射成 ES 里的一个索引。
 * 中文分词这类定制需要给索引配置相应 analyzer, 这里用默认标准分析器保持零依赖可跑。
 *
 * @author : xiaomo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "articles")
public class Article {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Keyword)
    private List<String> tags;

}