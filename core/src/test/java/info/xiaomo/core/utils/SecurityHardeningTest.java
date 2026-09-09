package info.xiaomo.core.utils;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 覆盖 CodeQL 报出的几处安全问题, 防止改回去。
 */
class SecurityHardeningTest {

    @Test
    void 文件名中的路径穿越片段应当被过滤掉() {
        // 扩展名取的是最后一个点之后的内容, 不过滤的话整段路径都会被带进文件名
        String name = FileUtil.getNewFileName("a.b/../../evil", "someone@xiaomo.info");

        assertThat(name).doesNotContain("..").doesNotContain("/").doesNotContain("\\");
    }

    @Test
    void 邮箱前缀中的路径字符应当被过滤掉() {
        String name = FileUtil.getNewFileName("photo.png", "../../etc/passwd@xiaomo.info");

        assertThat(name).doesNotContain("..").doesNotContain("/");
        assertThat(name).endsWith(".png");
    }

    @Test
    void 正常的文件名与邮箱应当保持可读() {
        String name = FileUtil.getNewFileName("photo.PNG", "xiao-mo_1@xiaomo.info");

        assertThat(name).contains("xiao-mo_1").endsWith(".png");
    }

    @Test
    void cookie应当同时带上secure与httpOnly() {
        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpUtil.setCookie(response, "uid", "42", 3600);

        Cookie cookie = response.getCookie("uid");
        assertThat(cookie).isNotNull();
        assertThat(cookie.getSecure()).as("secure 标志").isTrue();
        assertThat(cookie.isHttpOnly()).as("httpOnly 标志").isTrue();
    }

    @Test
    void 盐值应当有足够长度且不重复() {
        Set<String> salts = new HashSet<>();
        for (int i = 0; i < 500; i++) {
            String salt = RandomUtil.createSalt();
            assertThat(salt).hasSize(10);
            salts.add(salt);
        }
        // 500 次取值不应出现碰撞, 否则说明随机源的取值空间或质量有问题
        assertThat(salts).hasSize(500);
    }

    @Test
    void token应当是纯数字且长度固定() {
        for (int i = 0; i < 50; i++) {
            assertThat(RandomUtil.getTonken()).hasSize(10).containsOnlyDigits();
        }
    }

}
