package info.xiaomo.graphql.service;

import info.xiaomo.graphql.model.Book;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 内存数据源, 只用来演示 GraphQL 层怎么取值, 实际项目里换成 JPA / MyBatis 即可。
 *
 * @author : xiaomo
 */
@Service
public class BookService {

    private final Map<String, Book> books = new LinkedHashMap<>();

    public BookService() {
        books.put("1", new Book("1", "Spring Boot 实战", "小莫"));
        books.put("2", new Book("2", "深入理解 Java 虚拟机", "周志明"));
        books.put("3", new Book("3", "Clean Code", "Robert C. Martin"));
    }

    public Book findById(String id) {
        return books.get(id);
    }

    public List<Book> findAll() {
        return List.copyOf(books.values());
    }

}