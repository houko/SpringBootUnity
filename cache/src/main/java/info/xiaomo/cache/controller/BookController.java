package info.xiaomo.cache.controller;

import info.xiaomo.cache.model.Book;
import info.xiaomo.cache.service.BookService;
import info.xiaomo.core.base.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 连续请求同一个 isbn, 第一次会慢(约 200ms), 之后走缓存立即返回, loadCount 不再增长。
 *
 * @author : xiaomo
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{isbn}")
    public Result<Book> findByIsbn(@PathVariable("isbn") String isbn) {
        return new Result<>(service.findByIsbn(isbn));
    }

    @PutMapping
    public Result<Book> update(@RequestBody Book book) {
        return new Result<>(service.update(book));
    }

    @DeleteMapping("/{isbn}")
    public Result<Boolean> evict(@PathVariable("isbn") String isbn) {
        service.evict(isbn);
        return new Result<>(true);
    }

    /**
     * 方法体被真正执行过的次数, 用来观察缓存命中情况。
     */
    @GetMapping("/load-count")
    public Result<Integer> loadCount() {
        return new Result<>(service.loadCount());
    }

}
