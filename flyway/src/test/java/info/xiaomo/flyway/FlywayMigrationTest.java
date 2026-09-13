package info.xiaomo.flyway;

import info.xiaomo.flyway.FlywayMain;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 用内嵌 H2 跑一遍迁移脚本: 启动时会自动执行 V1/V2, 测试只验证结果。
 * 不需要 MySQL, 所以 CI 里 mvn clean install 也能稳定通过。
 */
@SpringBootTest(classes = FlywayMain.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:flyway;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
class FlywayMigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Flyway flyway;

    @Test
    void 迁移脚本执行后表结构和种子数据就绪() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        assertThat(count).isEqualTo(2);

        String name = jdbcTemplate.queryForObject("SELECT name FROM users WHERE id = 1", String.class);
        assertThat(name).isEqualTo("xiaomo");
    }

    @Test
    void 迁移历史记录了两次成功的版本() {
        // 直接用 Flyway 的元数据接口查询, 比手写 SQL 查 flyway_schema_history 更健壮
        // (Flyway 用自己的引号规则建表, 手写裸 SQL 在 H2 上容易因大小写不匹配)。
        assertThat(flyway.info().applied()).hasSize(2);
    }

}