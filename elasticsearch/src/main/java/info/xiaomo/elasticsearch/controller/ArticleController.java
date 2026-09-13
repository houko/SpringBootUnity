package info.xiaomo.elasticsearch.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.elasticsearch.model.Article;
import info.xiaomo.elasticsearch.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : xiaomo
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping
    public Result<Article> index(@RequestBody Article article) {
        return new Result<>(service.index(article));
    }

    @GetMapping("/search")
    public Result<List<Article>> search(@RequestParam String title) {
        return new Result<>(service.searchByTitle(title));
    }

    @GetMapping("/author/{author}")
    public Result<List<Article>> byAuthor(@PathVariable String author) {
        return new Result<>(service.findByAuthor(author));
    }

}