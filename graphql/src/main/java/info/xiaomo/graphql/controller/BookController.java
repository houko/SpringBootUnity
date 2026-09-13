package info.xiaomo.graphql.controller;

import info.xiaomo.graphql.model.Book;
import info.xiaomo.graphql.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 这里的 @Controller 不是 REST 控制器, 而是 GraphQL 的"数据取值的入口"。
 * 方法名与 schema 中的查询字段同名, @QueryMapping 会把它们绑定到一起。
 *
 * @author : xiaomo
 */
@Controller
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<Book> allBooks() {
        return service.findAll();
    }

}