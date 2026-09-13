package info.xiaomo.flyway.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.flyway.dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : xiaomo
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/count")
    public Result<Integer> count() {
        return new Result<>(userDao.count());
    }

    @GetMapping("/{id}")
    public Result<Map<String, Object>> byId(@PathVariable long id) {
        return new Result<>(userDao.findById(id));
    }

}