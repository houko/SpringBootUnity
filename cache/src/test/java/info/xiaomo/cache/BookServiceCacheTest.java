package info.xiaomo.cache;

import info.xiaomo.cache.model.Book;
import info.xiaomo.cache.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 通过"方法体执行次数"来断言缓存行为, 而不是去断言缓存框架内部状态 ——
 * 前者才是使用方真正关心的效果。
 */
@SpringBootTest(classes = CacheMain.class)
class BookServiceCacheTest {

    @Autowired
    private BookService service;

    @BeforeEach
    void 清空缓存() {
        service.evictAll();
    }

    @Test
    void 重复查询同一个key只会真正加载一次() {
        int before = service.loadCount();

        Book first = service.findByIsbn("978-1");
        Book second = service.findByIsbn("978-1");
        Book third = service.findByIsbn("978-1");

        assertThat(first).isEqualTo(second).isEqualTo(third);
        assertThat(service.loadCount() - before).isEqualTo(1);
    }

    @Test
    void 不同的key各自加载一次() {
        int before = service.loadCount();

        service.findByIsbn("978-2");
        service.findByIsbn("978-3");

        assertThat(service.loadCount() - before).isEqualTo(2);
    }

    @Test
    void 清除缓存后应当重新加载() {
        int before = service.loadCount();

        service.findByIsbn("978-4");
        service.evict("978-4");
        service.findByIsbn("978-4");

        assertThat(service.loadCount() - before).isEqualTo(2);
    }

    @Test
    void CachePut应当刷新缓存而不再触发加载() {
        int before = service.loadCount();

        service.findByIsbn("978-5");
        service.update(new Book("978-5", "改过的书名"));
        Book afterUpdate = service.findByIsbn("978-5");

        // 只有第一次 findByIsbn 真正执行了方法体
        assertThat(service.loadCount() - before).isEqualTo(1);
        assertThat(afterUpdate.title()).isEqualTo("改过的书名");
    }

}
