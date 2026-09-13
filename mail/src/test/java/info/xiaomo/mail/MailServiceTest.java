package info.xiaomo.mail;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import info.xiaomo.mail.service.MailService;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * GreenMail 在测试里起一个真实的 SMTP 服务器(端口 3025), 把 spring.mail 指向它即可离线验证发信逻辑。
 */
@SpringBootTest(classes = MailMain.class, properties = {
        "spring.mail.host=127.0.0.1",
        "spring.mail.port=3025",
        "spring.mail.username=",
        "spring.mail.password=",
        "spring.mail.properties.mail.smtp.auth=false",
        "spring.mail.properties.mail.smtp.starttls.enable=false"
})
class MailServiceTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);

    @Autowired
    private MailService mailService;

    @BeforeEach
    void 清空收件箱() throws Exception {
        greenMail.purgeEmailFromAllMailboxes();
    }

    @Test
    void 发送纯文本邮件() throws Exception {
        mailService.sendSimple("to@example.com", "验证码", "你的验证码是 123456");

        assertThat(greenMail.waitForIncomingEmail(5000, 1)).isTrue();
        MimeMessage received = greenMail.getReceivedMessages()[0];
        assertThat(received.getSubject()).isEqualTo("验证码");
        assertThat(received.getAllRecipients()[0].toString()).isEqualTo("to@example.com");
    }

    @Test
    void 发送HTML邮件() throws Exception {
        mailService.sendHtml("to-html@example.com", "通知", "<h1>你好</h1>");

        assertThat(greenMail.waitForIncomingEmail(5000, 1)).isTrue();
        MimeMessage received = greenMail.getReceivedMessages()[0];
        assertThat(received.getSubject()).isEqualTo("通知");
        assertThat(received.getContentType()).startsWith("text/html");
    }

}