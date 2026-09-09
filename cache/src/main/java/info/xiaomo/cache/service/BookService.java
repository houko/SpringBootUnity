package info.xiaomo.cache.service;

import info.xiaomo.cache.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用一个计数器暴露"方法体到底执行了几次", 以便直观看到缓存是否命中。
 *
 * @author : xiaomo
 */
@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final AtomicInteger loadCount = new AtomicInteger();

    /**
     * 命中缓存时方法体根本不会执行, 因此 loadCount 不会增加。
     */
    @Cacheable(cacheNames = "books", key = "#isbn")
    public Book findByIsbn(String isbn) {
        loadCount.incrementAndGet();
        LOGGER.info("缓存未命中, 从慢速数据源加载: {}", isbn);
        slowLoad();
        return new Book(isbn, "书名-" + isbn);
    }

    /**
     * 与 @Cacheable 不同, @CachePut 一定会执行方法体, 并用返回值刷新缓存。
     */
    @CachePut(cacheNames = "books", key = "#book.isbn")
    public Book update(Book book) {
        LOGGER.info("更新并回写缓存: {}", book.isbn());
        return book;
    }

    @CacheEvict(cacheNames = "books", key = "#isbn")
    public void evict(String isbn) {
        LOGGER.info("清除缓存: {}", isbn);
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    public void evictAll() {
        LOGGER.info("清空全部缓存");
    }

    public int loadCount() {
        return loadCount.get();
    }

    private void slowLoad() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
