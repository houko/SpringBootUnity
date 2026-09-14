package info.xiaomo.core.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 覆盖本次 bug 修复涉及的几个工具方法, 防止改回去。
 */
class UtilsBugFixTest {

    @Test
    void isImage应当识别常见图片扩展名而不误判() {
        assertThat(FileUtil.isImage("avatar.jpg")).isTrue();
        assertThat(FileUtil.isImage("avatar.JPEG")).isTrue();
        assertThat(FileUtil.isImage("avatar.Png")).isTrue();
        assertThat(FileUtil.isImage("avatar.gif")).isTrue();
        assertThat(FileUtil.isImage("avatar.bmp")).isTrue();

        assertThat(FileUtil.isImage("readme.txt")).isFalse();
        assertThat(FileUtil.isImage("video.mp4")).isFalse();
    }

    @Test
    void 剩余天数小时应按既定格式输出() {
        // 1天 + 20小时 + 5分 + 0秒
        assertThat(TimeUtil.getLeftTimeString(1L * 86400000 + 20 * 3600000 + 5 * 60000))
                .isEqualTo("1天20小时5分0秒");
        // 20小时 + 0分 + 0秒
        assertThat(TimeUtil.getLeftTimeString(20L * 3600000)).isEqualTo("20小时0分0秒");
        assertThat(TimeUtil.getLeftTimeString(1000)).isEqualTo("1秒");
    }

    @Test
    void 剩余时间为零或负数时应当返回零秒() {
        assertThat(TimeUtil.getLeftTimeString(0)).isEqualTo("0秒");
        assertThat(TimeUtil.getLeftTimeString(-1)).isEqualTo("0秒");
    }

    @Test
    void 序列化应当能无损往返() {
        String pole = SerializeUtil.serialize("中文abc123!@#");
        Object back = SerializeUtil.unserialize(pole);

        assertThat(back).isEqualTo("中文abc123!@#");
    }

    @Test
    void 数值判断应当区分非数字整数与小数() {
        assertThat(CastUtil.isNumeric("123")).isEqualTo(1);
        // 负整数仍应视为整数
        assertThat(CastUtil.isNumeric("-5")).isEqualTo(1);
        assertThat(CastUtil.isNumeric("3.14")).isEqualTo(2);
        assertThat(CastUtil.isNumeric("1e3")).isEqualTo(2);
        assertThat(CastUtil.isNumeric("2.147483647E9")).isEqualTo(2);
        // 这些以前会抛 NumberFormatException/越界
        assertThat(CastUtil.isNumeric("1.2.3")).isEqualTo(0);
        assertThat(CastUtil.isNumeric("abc")).isEqualTo(0);
        assertThat(CastUtil.isNumeric(null)).isEqualTo(0);
        assertThat(CastUtil.isNumeric("")).isEqualTo(0);
    }

    @Test
    void 正则全量匹配无可匹配时应当返回空数组() {
        assertThat(RegExUtil.regMatchAll2Array("\\d+", "abc")).isEmpty();
        assertThat(RegExUtil.splitTags("\\[#\\w+#\\]", "没有标签")).isEmpty();
    }

    @Test
    void 正则全量匹配应当返回所有匹配项() {
        assertThat(RegExUtil.regMatchAll2Array("\\d+", "a1b22c"))
                .containsExactly("1", "22");
        assertThat(RegExUtil.splitTags("\\[#\\w+#\\]", "[#tag1#] [#tag2#]"))
                .containsExactly("tag1", "tag2");
    }

    @Test
    void 取IP应当优先取转发头第一段并跳过unknown() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("X-Forwarded-For", "203.0.113.7, 10.0.0.1");
        assertThat(StringUtil.getIP(request)).isEqualTo("203.0.113.7");

        MockHttpServletRequest request2 = new MockHttpServletRequest();
        request2.addHeader("X-Forwarded-For", "unknown, 203.0.113.9");
        request2.setRemoteAddr("192.168.1.5");
        assertThat(StringUtil.getIP(request2)).isEqualTo("203.0.113.9");
    }

    @Test
    void 取IP无转发头时应当回退到远端地址() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.5");
        assertThat(StringUtil.getIP(request)).isEqualTo("192.168.1.5");
    }

    @Test
    void 解码尾部残缺百分号时不应越界() {
        // 以前会 charAt(i+1) 越界抛异常
        assertThat(StringUtil.decode("abc%")).isEqualTo("abc%");
        assertThat(StringUtil.decode("a%20b")).isEqualTo("a b");
        // 残缺的 %u 转义(不足 4 位十六进制)原样保留, 不越界
        assertThat(StringUtil.decode("abc%u12A")).isEqualTo("abc%u12A");
    }

    @Test
    void md5编码应当使用UTF8且稳定() {
        // md5("") 的经典结果, 顺带锁定输出为 32 位小写 hex
        assertThat(Md5Util.encode("", "")).isEqualTo("d41d8cd98f00b204e9800998ecf8427e");
        assertThat(Md5Util.encode("abc", "123")).hasSize(32).matches("[0-9a-f]{32}");
    }
}