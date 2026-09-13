package info.xiaomo.elasticsearch.service;

import info.xiaomo.elasticsearch.model.Article;
import info.xiaomo.elasticsearch.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author : xiaomo
 */
@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article index(Article article) {
        if (article.getId() == null || article.getId().isBlank()) {
            article.setId(UUID.randomUUID().toString());
        }
        return repository.save(article);
    }

    public List<Article> searchByTitle(String keyword) {
        return repository.findByTitleContaining(keyword);
    }

    public List<Article> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

}