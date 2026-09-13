package info.xiaomo.flyway.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 用 JdbcTemplate 读取被 Flyway 迁移后的表, 纯粹演示"迁移脚本 + 数据访问"的配合。
 *
 * @author : xiaomo
 */
@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        return count == null ? 0 : count;
    }

    public Map<String, Object> findById(long id) {
        return jdbcTemplate.queryForMap("SELECT id, name, email FROM users WHERE id = ?", id);
    }

}