package info.xiaomo.restclient.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.restclient.model.Repository;
import info.xiaomo.restclient.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : xiaomo
 */
@RestController
@RequestMapping("/repos")
public class RepositoryController {

    private final GithubService github;

    public RepositoryController(GithubService github) {
        this.github = github;
    }

    @GetMapping("/{user}")
    public Result<List<Repository>> listRepositories(@PathVariable("user") String user) {
        return new Result<>(github.listRepositories(user));
    }

    @ExceptionHandler(GithubService.UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<String> handleNotFound(GithubService.UserNotFoundException e) {
        return new Result<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
    }

}
